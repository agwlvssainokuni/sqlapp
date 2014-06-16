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
<c:set var="hasResult" value="${execResult != null}" />
<script type="text/javascript">
	$(function() {
		$(".accordion").accordion({
			collapsible : true,
			animate : false,
			heightStyle : "content",
			active : ${hasResult ? 'false' : 0}
		}).removeClass("ui-widget");
	});
</script>
<h1 class="app-subject">
	<s:message code="secure/exec/csv/index.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="secure/exec/csv/index.message.1" />
	</h1>
	<div>
		<s:hasBindErrors name="execCsvForm">
			<div class="ui-state-error">
				<f:errors path="execCsvForm" element="div" />
				<f:errors path="execCsvForm.databaseName" element="div" />
				<f:errors path="execCsvForm.sql" element="div" />
				<f:errors path="execCsvForm.sqlAlt1" element="div" />
				<f:errors path="execCsvForm.sqlAlt2" element="div" />
				<f:errors path="execCsvForm.paramMap" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="/secure/exec/csv/req" method="POST"
			modelAttribute="execCsvForm">
			<input type="hidden" id="sz" name="sz"
				value="<c:out value="${param.sz}" />">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="databaseName"><s:message
									code="execCsvForm.databaseName" /></label></th>
						<td><f:select path="databaseName"
								cssClass="app-width50 ui-widget"
								cssErrorClass="app-width50 ui-widget ui-state-error">
								<f:options items="${dataSourceDef.names}" />
							</f:select></td>
					</tr>
					<tr>
						<th><label for="sql"><s:message
									code="execCsvForm.sql" /></label></th>
						<td><f:textarea path="sql" cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="sqlAlt1"><s:message
									code="execCsvForm.sqlAlt1" /></label></th>
						<td><f:textarea path="sqlAlt1"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="sqlAlt2"><s:message
									code="execCsvForm.sqlAlt2" /></label></th>
						<td><f:textarea path="sqlAlt2"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="paramMap"><s:message
									code="execCsvForm.paramMap" /></label></th>
						<td><f:textarea path="paramMap"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="file"><s:message
									code="execCsvForm.file" /></label></th>
						<td><input id="file" name="file" type="file" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="proc" value="exec" class="app-button">
								<s:message code="secure/exec/csv/index.execButton" />
							</button>
							<button type="submit" name="proc" value="create"
								class="app-button">
								<s:message code="secure/exec/csv/index.createButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
</div>
<c:if test="${hasResult}">
	<div class="app-portion">
		<h1 class="app-subject">
			<s:message code="secure/exec/csv/index.message.2" />
		</h1>
		<div class="app-portion ui-state-highlight">
			<s:message code="secure/exec/csv/indexId.message.3"
				arguments="${execResult}" />
		</div>
	</div>
</c:if>
