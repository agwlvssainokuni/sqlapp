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
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<c:set var="hasResultList"
	value="${pagedList != null && !pagedList.list.isEmpty()}" />
<h2 class="page-header">
	<s:message code="sqltool/search/init.message.0" />
</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#searchForm"><s:message
						code="sqltool/search/init.message.1" /></a>
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
							<f:errors path="sqltoolSearchForm.registeredFromDt" element="div" />
							<f:errors path="sqltoolSearchForm.registeredFromTm" element="div" />
							<f:errors path="sqltoolSearchForm.registeredToDt" element="div" />
							<f:errors path="sqltoolSearchForm.registeredToTm" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<c:if test="${pagedList != null && pagedList.list.isEmpty()}">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<s:message code="sqltool/search/init.message.3" />
						</div>
					</div>
				</c:if>
				<f:form servletRelativeAction="/sqltool/search/execute"
					method="POST" modelAttribute="sqltoolSearchForm"
					cssClass="form-horizontal" role="form">
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? "has-error" : ""}</s:bind>
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
								code="sqltool/search/init.form.type" /></label>
						<div class="col-sm-10">
							<c:forEach var="item"
								items="${foundation:getLabeledEnumList('cherry.sqlapp.controller.sqltool.search.SqlType')}">
								<div class="checkbox-inline">
									<f:checkbox path="sqlType" value="${item.enumName}"
										label="${item.enumLabel}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><s:message
								code="sqltool/search/init.form.published" /></label>
						<div class="col-sm-10">
							<c:forEach var="item"
								items="${foundation:getLabeledEnumList('cherry.sqlapp.controller.sqltool.search.Published')}">
								<div class="checkbox-inline">
									<f:checkbox path="published" value="${item.enumName}"
										label="${item.enumLabel}" />
								</div>
							</c:forEach>
						</div>
					</div>

					<c:set var="hasErrorDt">
						<s:bind path="registeredFromDt">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<c:set var="hasErrorTm">
						<s:bind path="registeredFromTm">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group">
						<span
							class="${hasErrorDt.isEmpty() && hasErrorTm.isEmpty() ? '' : 'has-error'}"><label
							class="col-sm-2 control-label"><s:message
									code="sqltoolSearchForm.registeredFrom" /></label></span>
						<div class="col-sm-4 ${hasErrorDt}">
							<f:input path="registeredFromDt" cssClass="form-control" />
						</div>
						<div class="col-sm-4 ${hasErrorTm}">
							<f:input path="registeredFromTm" cssClass="form-control" />
						</div>
					</div>
					<c:set var="hasErrorDt">
						<s:bind path="registeredToDt">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<c:set var="hasErrorTm">
						<s:bind path="registeredToTm">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group">
						<span
							class="${hasErrorDt.isEmpty() && hasErrorTm.isEmpty() ? '' : 'has-error'}"><label
							class="col-sm-2 control-label"><s:message
									code="sqltoolSearchForm.registeredTo" /></label></span>
						<div class="col-sm-4 ${hasErrorDt}">
							<f:input path="registeredToDt" cssClass="form-control" />
						</div>
						<div class="col-sm-4 ${hasErrorTm}">
							<f:input path="registeredToTm" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary btn-block">
								<s:message code="sqltool/search/init.searchButton" />
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
					<a data-toggle="collapse" href="#searchResult"><s:message
							code="sqltool/search/init.message.2" /></a>
				</h3>
			</div>
			<div id="searchResult" class="panel-collapse collapse in">
				<div class="panel-body">
					<f:form servletRelativeAction="/sqltool/search/execute"
						method="POST" modelAttribute="sqltoolSearchForm"
						id="sqltoolSearchForm2" cssClass="app-pager-form">
						<f:hidden id="pageNo2" path="pageNo" cssClass="app-page-no" />
						<f:hidden id="pageSz2" path="pageSz" cssClass="app-page-sz" />
						<f:hidden id="name2" path="name" />
						<f:hidden id="sqlType2" path="sqlType" />
						<f:hidden id="published2" path="published" />
						<f:hidden id="registeredFromDt2" path="registeredFromDt" />
						<f:hidden id="registeredFromTm2" path="registeredFromTm" />
						<f:hidden id="registeredToDt2" path="registeredToDt" />
						<f:hidden id="registeredToTm2" path="registeredToTm" />
					</f:form>
					<div>
						<div class="app-pager-desc">
							<s:message code="common/pager.message.0"
								arguments="${pagedList.pageSet.last.to+1},${pagedList.pageSet.current.from+1},${pagedList.pageSet.current.to+1}" />
						</div>
						<app:pagerLink pageSet="${pagedList.pageSet}" />
					</div>
					<table id="searchResultList" class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th><s:message code="sqltool/search/init.column.name" /></th>
								<th><s:message code="sqltool/search/init.column.sqlType" /></th>
								<th><s:message
										code="sqltool/search/init.column.registeredAt" /></th>
								<th><s:message
										code="sqltool/search/init.column.publishedFlg" /></th>
								<th><s:message code="sqltool/search/init.column.ownedBy" /></th>
								<th><s:message
										code="sqltool/search/init.column.description" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
								<s:nestedPath path="pagedList.list[${count - 1}]">
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
												value="${pagedList.pageSet.current.from + count}" /></td>
										<td><s:bind path="name">
												<a href="${uri}" title="${status.value}"><c:out
														value="${status.value}" /></a>
											</s:bind></td>
										<td><s:bind path="sqlType">${status.value}</s:bind></td>
										<td><s:bind path="registeredAt">${status.value}</s:bind></td>
										<td><s:bind path="publishedFlg">
												<s:message
													code="sqltool/search/init.column.publishedFlg.${status.actualValue.code()}" />
											</s:bind></td>
										<td><s:bind path="ownedBy">${status.value}</s:bind></td>
										<td><s:bind path="description">${status.value}</s:bind></td>
									</tr>
								</s:nestedPath>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<app:pagerLink pageSet="${pagedList.pageSet}" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
