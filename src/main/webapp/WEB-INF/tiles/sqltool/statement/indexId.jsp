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
<s:url var="baseUri" value="/sqltool/statement/{id}">
	<s:param name="id" value="${id}" />
</s:url>
<c:set var="hasResultList"
	value="${resultSet != null && pageSet != null}" />
<script type="text/javascript">
	$(function() {
		$(".accordion").accordion({
			collapsible : true,
			animate : false,
			heightStyle : "content",
			active: ${hasResultList ? 'false' : 1}
		}).removeClass("ui-widget");
	});
</script>
<h1 class="app-subject">
	<s:message code="sqltool/statement/indexId.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="sqltool/statement/indexId.message.1" />
	</h1>
	<div>
		<s:hasBindErrors name="sqltoolMetadataForm">
			<div class="ui-state-error">
				<f:errors path="sqltoolMetadataForm" element="div" />
				<f:errors path="sqltoolMetadataForm.name" element="div" />
				<f:errors path="sqltoolMetadataForm.description" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="${baseUri}/metadata" method="POST"
			modelAttribute="sqltoolMetadataForm">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="name"><s:message
									code="sqltoolMetadataForm.name" /></label></th>
						<td><f:input path="name" cssClass="app-width50"
								cssErrorClass="app-width50 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="description"><s:message
									code="sqltoolMetadataForm.description" /></label></th>
						<td><f:textarea path="description"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="ownedBy"><s:message
									code="sqltoolMetadataForm.ownedBy" /></label></th>
						<td><f:input path="ownedBy" disabled="true" /></td>
					</tr>
					<tr>
						<th><label for="publishedFlg"><s:message
									code="sqltoolMetadataForm.publishedFlg" /></label></th>
						<td><f:checkbox path="publishedFlg" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="proc" value="update"
								class="app-button">
								<s:message code="sqltool/statement/indexId.updateButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<h1>
		<s:message code="sqltool/statement/indexId.message.2" />
	</h1>
	<div>
		<s:hasBindErrors name="sqltoolStatementForm">
			<div class="ui-state-error">
				<f:errors path="sqltoolStatementForm" element="div" />
				<f:errors path="sqltoolStatementForm.databaseName" element="div" />
				<f:errors path="sqltoolStatementForm.sql" element="div" />
				<f:errors path="sqltoolStatementForm.paramMap" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="${baseUri}/req" method="POST"
			modelAttribute="sqltoolStatementForm">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="databaseName"><s:message
									code="sqltoolStatementForm.databaseName" /></label></th>
						<td><f:select path="databaseName"
								cssClass="app-width50 ui-widget"
								cssErrorClass="app-width50 ui-widget ui-state-error">
								<f:options items="${dataSourceDef.names}" />
							</f:select></td>
					</tr>
					<tr>
						<th><label for="sql"><s:message
									code="sqltoolStatementForm.sql" /></label></th>
						<td><f:textarea path="sql" cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="paramMap"><s:message
									code="sqltoolStatementForm.paramMap" /></label></th>
						<td><f:textarea path="paramMap"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="proc" value="exec" class="app-button">
								<s:message code="sqltool/statement/indexId.execButton" />
							</button>
							<button type="submit" name="proc" value="update"
								class="app-button">
								<s:message code="sqltool/statement/indexId.updateButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
</div>
<c:if test="${hasResultList}">
	<div class="app-portion">
		<h1 class="app-subject">
			<s:message code="sqltool/statement/indexId.message.3" />
		</h1>
		<div class="app-portion">
			<app:resultSet id="resultSetList" resultSet="${resultSet}"
				pageSet="${pageSet}" />
		</div>
	</div>
</c:if>
