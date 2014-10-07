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
<%@ taglib prefix="sqlapp" uri="urn:sqlapp"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<s:url var="baseUri" value="/sqltool/clause/{id}">
	<s:param name="id" value="${id}" />
</s:url>
<c:set var="hasResultList"
	value="${resultSet != null && pageSet != null}" />
<h2 class="page-header">
	<s:message code="sqltool/clause/initId.message.0" />
</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#metadataForm"><s:message
						code="sqltool/clause/initId.message.1" /></a>
			</h3>
		</div>
		<c:set var="hasError">
			<s:hasBindErrors name="sqltoolMetadataForm">in</s:hasBindErrors>
		</c:set>
		<div id="metadataForm" class="panel-collapse collapse ${hasError}">
			<div class="panel-body">
				<s:hasBindErrors name="sqltoolMetadataForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqltoolMetadataForm" element="div" />
							<f:errors path="sqltoolMetadataForm.name" element="div" />
							<f:errors path="sqltoolMetadataForm.description" element="div" />
							<f:errors path="sqltoolMetadataForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/metadata" method="POST"
					modelAttribute="sqltoolMetadataForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="name" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolMetadataForm.name" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="name" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="description" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolMetadataForm.description" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="description" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<div class="form-group">
						<f:label path="ownedBy" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolMetadataForm.ownedBy" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="ownedBy" cssClass="col-sm-2 form-control"
								disabled="true" />
						</div>
					</div>
					<div class="form-group">
						<f:label path="publishedFlg" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolMetadataForm.publishedFlg" />
						</f:label>
						<div class="col-sm-10">
							<f:checkbox path="publishedFlg" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="sqltool/clause/initId.updateButton" />
							</f:button>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#clauseForm"><s:message
						code="sqltool/clause/initId.message.2" /></a>
			</h3>
		</div>
		<div id="clauseForm"
			class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
			<div class="panel-body">
				<s:hasBindErrors name="sqltoolClauseForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqltoolClauseForm" element="div" />
							<f:errors path="sqltoolClauseForm.databaseName" element="div" />
							<f:errors path="sqltoolClauseForm.select" element="div" />
							<f:errors path="sqltoolClauseForm.from" element="div" />
							<f:errors path="sqltoolClauseForm.where" element="div" />
							<f:errors path="sqltoolClauseForm.groupBy" element="div" />
							<f:errors path="sqltoolClauseForm.having" element="div" />
							<f:errors path="sqltoolClauseForm.orderBy" element="div" />
							<f:errors path="sqltoolClauseForm.paramMap" element="div" />
							<f:errors path="sqltoolClauseForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/execute" method="POST"
					modelAttribute="sqltoolClauseForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="databaseName">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="databaseName" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.databaseName" />
						</f:label>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="col-sm-2 form-control">
								<c:set var="dataSourceDef" value="${sqlapp:dataSourceDef()}" />
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="select">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="select" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.select" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="select" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="from">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="from" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.from" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="from" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="where">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="where" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.where" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="where" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="groupBy">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="groupBy" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.groupBy" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="groupBy" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="having">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="having" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.having" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="having" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="orderBy">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="orderBy" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.orderBy" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="orderBy" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="paramMap">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="paramMap" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolClauseForm.paramMap" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="sqltool/clause/initId.execButton" />
							</f:button>
							<f:button type="submit" name="download" class="btn btn-default">
								<s:message code="sqltool/clause/initId.downloadButton" />
							</f:button>
							<f:button type="submit" name="update" class="btn btn-default">
								<s:message code="sqltool/clause/initId.updateButton" />
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
					<a data-toggle="collapse" href="#clauseResult"><s:message
							code="sqltool/clause/initId.message.3" /></a>
				</h3>
			</div>
			<div id="clauseResult" class="panel-collapse collapse in">
				<f:form servletRelativeAction="${baseUri}/execute" method="POST"
					modelAttribute="sqltoolClauseForm" id="sqltoolClause2"
					class="app-pager-form">
					<f:hidden id="pageNo2" path="pageNo" cssClass="app-page-no" />
					<f:hidden id="pageSz2" path="pageSz" cssClass="app-page-sz" />
					<f:hidden id="lockVersion2" path="lockVersion" />
					<f:hidden id="databaseName2" path="databaseName" />
					<f:hidden id="select2" path="select" />
					<f:hidden id="from2" path="from" />
					<f:hidden id="where2" path="where" />
					<f:hidden id="groupBy2" path="groupBy" />
					<f:hidden id="having2" path="having" />
					<f:hidden id="orderBy2" path="orderBy" />
					<f:hidden id="paramMap2" path="paramMap" />
				</f:form>
				<div class="panel-body">
					<div>
						<div class="app-pager-desc">
							<s:message code="common/pager.message.0"
								arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
						</div>
						<app:pagerLink pageSet="${pageSet}" />
					</div>
					<app:resultSet id="resultSetList" resultSet="${resultSet}"
						pageSet="${pageSet}" />
					<div>
						<app:pagerLink pageSet="${pageSet}" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
