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
<s:url var="baseUri" value="/sqltool/load/{id}">
	<s:param name="id" value="${id}" />
</s:url>
<h2 class="page-header">
	<s:message code="sqltool/load/indexId.message.0" />
</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#metadataForm"><s:message
						code="sqltool/load/indexId.message.1" /></a>
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
								<s:message code="sqltool/load/indexId.updateButton" />
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
				<a data-toggle="collapse" href="#loadForm"><s:message
						code="sqltool/load/indexId.message.2" /></a>
			</h3>
		</div>
		<div id="loadForm" class="panel-collapse collapse in">
			<div class="panel-body">
				<s:hasBindErrors name="sqltoolLoadForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqltoolLoadForm" element="div" />
							<f:errors path="sqltoolLoadForm.databaseName" element="div" />
							<f:errors path="sqltoolLoadForm.sql" element="div" />
							<f:errors path="sqltoolLoadForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/req" method="POST"
					modelAttribute="sqltoolLoadForm" enctype="multipart/form-data"
					cssClass="form-horizontal" role="form">
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="databaseName">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="databaseName" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolLoadForm.databaseName" />
						</f:label>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="col-sm-2 form-control">
								<c:set var="dataSourceDef" value="${sqlapp:dataSourceDef()}" />
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="sql">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="sql" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolLoadForm.sql" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="sql" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="file">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="file" cssClass="col-sm-2 control-label">
							<s:message code="sqltoolLoadForm.file" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="file" type="file" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="sqltool/load/indexId.execButton" />
							</f:button>
							<f:button type="submit" name="update" class="btn btn-default">
								<s:message code="sqltool/load/indexId.updateButton" />
							</f:button>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</div>
