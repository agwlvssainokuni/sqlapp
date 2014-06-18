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
<s:url var="baseUri" value="/sqltool/clause/{id}">
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
	<s:message code="sqltool/clause/indexId.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="sqltool/clause/indexId.message.1" />
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
								<s:message code="sqltool/clause/indexId.updateButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<h1>
		<s:message code="sqltool/clause/indexId.message.2" />
	</h1>
	<div>
		<s:hasBindErrors name="sqltoolClauseForm">
			<div class="ui-state-error">
				<f:errors path="sqltoolClauseForm" element="div" />
				<f:errors path="sqltoolClauseForm.databaseName" element="div" />
				<f:errors path="sqltoolClauseForm.select" element="div" />
				<f:errors path="sqltoolClauseForm.from" element="div" />
				<f:errors path="sqltoolClauseForm.where" element="div" />
				<f:errors path="sqltoolClauseForm.groupBy" element="div" />
				<f:errors path="sqltoolClauseForm.having" element="div" />
				<f:errors path="sqltoolClauseForm.orderBy" element="div" />
				<f:errors path="sqltoolClauseForm.paramMap" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="${baseUri}/req" method="POST"
			modelAttribute="sqltoolClauseForm">
			<input type="hidden" id="sz" name="sz"
				value="<c:out value="${param.sz}"/>">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="databaseName"><s:message
									code="sqltoolClauseForm.databaseName" /></label></th>
						<td><f:select path="databaseName"
								cssClass="app-width50 ui-widget"
								cssErrorClass="app-width50 ui-widget ui-state-error">
								<f:options items="${dataSourceDef.names}" />
							</f:select></td>
					</tr>
					<tr>
						<th><label for="select"><s:message
									code="sqltoolClauseForm.select" /></label></th>
						<td><f:textarea path="select"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="from"><s:message
									code="sqltoolClauseForm.from" /></label></th>
						<td><f:textarea path="from"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="where"><s:message
									code="sqltoolClauseForm.where" /></label></th>
						<td><f:textarea path="where"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="groupBy"><s:message
									code="sqltoolClauseForm.groupBy" /></label></th>
						<td><f:textarea path="groupBy"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="having"><s:message
									code="sqltoolClauseForm.having" /></label></th>
						<td><f:textarea path="having"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="orderBy"><s:message
									code="sqltoolClauseForm.orderBy" /></label></th>
						<td><f:textarea path="orderBy"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="paramMap"><s:message
									code="sqltoolClauseForm.paramMap" /></label></th>
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
								<s:message code="sqltool/clause/indexId.execButton" />
							</button>
							<button type="submit" name="proc" value="update"
								class="app-button">
								<s:message code="sqltool/clause/indexId.updateButton" />
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
			<s:message code="sqltool/clause/indexId.message.3" />
		</h1>
		<div class="app-portion">
			<f:form servletRelativeAction="${baseUri}/req" method="POST"
				modelAttribute="sqltoolClauseForm" id="sqltoolClauseWithPage"
				class="app-pager-form">
				<input type="hidden" id="no2" name="no">
				<input type="hidden" id="sz2" name="sz"
					value="<c:out value="${param.sz}" />">
				<f:hidden id="databaseName2" path="databaseName" />
				<f:hidden id="select2" path="select" />
				<f:hidden id="from2" path="from" />
				<f:hidden id="where2" path="where" />
				<f:hidden id="groupBy2" path="groupBy" />
				<f:hidden id="having2" path="having" />
				<f:hidden id="orderBy2" path="orderBy" />
				<f:hidden id="paramMap2" path="paramMap" />
			</f:form>
			<div class="app-pager">
				<div class="app-pager-desc">
					<s:message code="common/pager.message.0"
						arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
				</div>
				<app:pagerLink pageSet="${pageSet}" />
			</div>
			<app:resultSet id="resultSetList" resultSet="${resultSet}"
				pageSet="${pageSet}" />
			<div class="app-pager">
				<app:pagerLink pageSet="${pageSet}" />
			</div>
		</div>
	</div>
</c:if>
