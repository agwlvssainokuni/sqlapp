<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<c:set var="hasResultList"
	value="${result != null && !result.metadataList.isEmpty()}" />
<h2 class="page-header">
	<s:message code="sqltool/search/index.message.0" />
</h2>
<div id="accordion" class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion"
					href="#searchForm"><s:message
						code="sqltool/search/index.message.1" /></a>
			</h3>
		</div>
		<div id="searchForm" class="panel-collapse collapse in">
			<div class="panel-body">
				<s:hasBindErrors name="sqltoolSearchForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqltoolSearchForm" element="div" />
							<f:errors path="sqltoolSearchForm.name" element="div" />
							<f:errors path="sqltoolSearchForm.registeredFrom" element="div" />
							<f:errors path="sqltoolSearchForm.registeredTo" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<c:if test="${result != null && result.metadataList.isEmpty()}">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<s:message code="sqltool/search/index.message.3" />
						</div>
					</div>
				</c:if>
				<f:form servletRelativeAction="/sqltool/search/req" method="POST"
					modelAttribute="sqltoolSearchForm" cssClass="form-horizontal"
					role="form">
					<input type="hidden" id="sz" name="sz"
						value="<c:out value="${param.sz}" />">
					<div class="form-group">
						<f:label path="name" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolSearchForm.name" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="name" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><s:message
								code="sqltool/search/index.form.type" /></label>
						<div class="col-sm-10">
							<div class="checkbox-inline">
								<c:set var="label">
									<s:message code="sqltool/search/index.form.type.clause" />
								</c:set>
								<f:checkbox path="clause" label="${label}" />
							</div>
							<div class="checkbox-inline">
								<c:set var="label">
									<s:message code="sqltool/search/index.form.type.statement" />
								</c:set>
								<f:checkbox path="statement" label="${label}" />
							</div>
							<div class="checkbox-inline">
								<c:set var="label">
									<s:message code="sqltool/search/index.form.type.load" />
								</c:set>
								<f:checkbox path="load" label="${label}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><s:message
								code="sqltool/search/index.form.published" /></label>
						<div class="col-sm-10">
							<div class="checkbox-inline">
								<c:set var="label">
									<s:message code="sqltool/search/index.form.published.public" />
								</c:set>
								<f:checkbox path="publish" label="${label}" />
							</div>
							<div class="checkbox-inline">
								<c:set var="label">
									<s:message code="sqltool/search/index.form.published.private" />
								</c:set>
								<f:checkbox path="notPublish" label="${label}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<f:label path="registeredFrom" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolSearchForm.registeredFrom" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="registeredFrom" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<f:label path="registeredTo" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolSearchForm.registeredTo" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="registeredTo" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary btn-block">
								<s:message code="sqltool/search/index.searchButton" />
							</f:button>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<c:if test="${hasResultList}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#searchResult"><s:message
							code="sqltool/search/index.message.2" /></a>
				</h3>
			</div>
			<div id="searchResult" class="panel-collapse collapse in">
				<div class="panel-body">
					<f:form servletRelativeAction="/sqltool/search/req" method="POST"
						modelAttribute="sqltoolSearchForm" id="sqltoolSearchFormHidden"
						cssClass="app-pager-form" role="form">
						<input type="hidden" id="no2" name="no" class="app-page-no">
						<input type="hidden" id="sz2" name="sz"
							value="<c:out value="${param.sz}" />" class="app-page-sz">
						<f:hidden id="name2" path="name" />
						<f:hidden id="clause2" path="clause" />
						<f:hidden id="statement2" path="statement" />
						<f:hidden id="load2" path="load" />
						<f:hidden id="publish2" path="publish" />
						<f:hidden id="notPublish2" path="notPublish" />
						<f:hidden id="registeredFrom2" path="registeredFrom" />
						<f:hidden id="registeredTo2" path="registeredTo" />
					</f:form>
					<div class="app-pager">
						<div class="app-pager-desc">
							<s:message code="common/pager.message.0"
								arguments="${result.pageSet.last.to+1},${result.pageSet.current.from+1},${result.pageSet.current.to+1}" />
						</div>
						<app:pagerLink pageSet="${result.pageSet}" />
					</div>
					<table id="searchResultList" class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th><s:message code="sqltool/search/index.column.name" /></th>
								<th><s:message code="sqltool/search/index.column.sqlType" /></th>
								<th><s:message
										code="sqltool/search/index.column.registeredAt" /></th>
								<th><s:message
										code="sqltool/search/index.column.publishedFlg" /></th>
								<th><s:message code="sqltool/search/index.column.ownedBy" /></th>
								<th><s:message
										code="sqltool/search/index.column.description" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${result.metadataList}"
								varStatus="status">
								<s:url var="uri" value="/sqltool/{type}/{id}">
									<s:param name="type" value="${item.sqlType}" />
									<s:param name="id" value="${item.id}" />
								</s:url>
								<tr>
									<td class="text-right"><c:out
											value="${result.pageSet.current.from + status.count}" /></td>
									<td><a href="${uri}" title="${item.name}"><c:out
												value="${item.name}" /></a></td>
									<td><c:out value="${item.sqlType}" /></td>
									<td><c:out value="${item.registeredAt}" /></td>
									<td><c:choose>
											<c:when test="${!item.publishedFlg.isTrue()}">
												<s:message code="sqltool/search/index.column.publishedFlg.0" />
											</c:when>
											<c:otherwise>
												<s:message code="sqltool/search/index.column.publishedFlg.1" />
											</c:otherwise>
										</c:choose></td>
									<td><c:out value="${item.ownedBy}" /></td>
									<td><c:out value="${item.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="app-pager">
						<app:pagerLink pageSet="${result.pageSet}" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
