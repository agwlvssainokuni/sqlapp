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
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#searchForm"><s:message
						code="sqltool/search/index.message.1" /></a>
			</h3>
		</div>
		<div id="searchForm"
			class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
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
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? 'has-error' : ''}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
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
					<c:set var="hasError">
						<s:bind path="registeredFrom">${status.isError() ? 'has-error' : ''}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="registeredFrom" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolSearchForm.registeredFrom" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="registeredFrom" cssClass="form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="registeredFrom">${status.isError() ? 'has-error' : ''}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
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
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
				</f:form>
			</div>
		</div>
	</div>
	<c:if test="${hasResultList}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<a data-toggle="collapse" href="#searchResult"><s:message
							code="sqltool/search/index.message.2" /></a>
				</h3>
			</div>
			<div id="searchResult" class="panel-collapse collapse in">
				<div class="panel-body">
					<f:form servletRelativeAction="/sqltool/search/req" method="POST"
						modelAttribute="sqltoolSearchForm" id="sqltoolSearchForm2"
						cssClass="app-pager-form">
						<f:hidden id="name2" path="name" />
						<f:hidden id="clause2" path="clause" />
						<f:hidden id="statement2" path="statement" />
						<f:hidden id="load2" path="load" />
						<f:hidden id="publish2" path="publish" />
						<f:hidden id="notPublish2" path="notPublish" />
						<f:hidden id="registeredFrom2" path="registeredFrom" />
						<f:hidden id="registeredTo2" path="registeredTo" />
						<f:hidden id="pageNo2" path="pageNo" cssClass="app-page-no" />
						<f:hidden id="pageSz2" path="pageSz" cssClass="app-page-sz" />
					</f:form>
					<div>
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
							<c:forEach var="count" begin="1"
								end="${result.metadataList.size()}">
								<s:nestedPath path="result.metadataList[${count - 1}]">
									<s:url var="uri" value="/sqltool/{type}/{id}">
										<s:param name="type">
											<s:bind path="sqlType">${status.value}</s:bind>
										</s:param>
										<s:param name="id">
											<s:bind path="id">${status.value}</s:bind>
										</s:param>
									</s:url>
									<tr>
										<td class="text-right"><c:out
												value="${result.pageSet.current.from + count}" /></td>
										<td><s:bind path="name">
												<a href="${uri}" title="${status.value}"><c:out
														value="${status.value}" /></a>
											</s:bind></td>
										<td><s:bind path="sqlType">${status.value}</s:bind></td>
										<td><s:bind path="registeredAt">${status.value}</s:bind></td>
										<td><s:bind path="publishedFlg">
												<c:choose>
													<c:when test="${!status.actualValue.isTrue()}">
														<s:message
															code="sqltool/search/index.column.publishedFlg.0" />
													</c:when>
													<c:otherwise>
														<s:message
															code="sqltool/search/index.column.publishedFlg.1" />
													</c:otherwise>
												</c:choose>
											</s:bind></td>
										<td><s:bind path="ownedBy">${status.value}</s:bind></td>
										<td><s:bind path="description">${status.value}</s:bind></td>
									</tr>
								</s:nestedPath>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<app:pagerLink pageSet="${result.pageSet}" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
