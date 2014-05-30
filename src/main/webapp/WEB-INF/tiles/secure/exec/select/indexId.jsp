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
<s:url var="baseUri" value="/secure/exec/select/{id}">
	<s:param name="id" value="${id}" />
</s:url>
<c:set var="hasResultList"
	value="${execResult != null && pageSet != null}" />
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
	<s:message code="secure/exec/select/indexId.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="secure/exec/select/indexId.message.1" />
	</h1>
	<div>
		<s:hasBindErrors name="execMetadataForm">
			<div class="ui-state-error">
				<f:errors path="execMetadataForm" element="div" />
				<f:errors path="execMetadataForm.name" element="div" />
				<f:errors path="execMetadataForm.description" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="${baseUri}/metadata" method="POST"
			modelAttribute="execMetadataForm">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="name"><s:message
									code="execMetadataForm.name" /></label></th>
						<td><f:input path="name" cssClass="app-width50"
								cssErrorClass="app-width50 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="description"><s:message
									code="execMetadataForm.description" /></label></th>
						<td><f:textarea path="description"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="proc" value="update"
								class="app-button">
								<s:message code="secure/exec/select/indexId.updateButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<h1>
		<s:message code="secure/exec/select/indexId.message.2" />
	</h1>
	<div>
		<s:hasBindErrors name="execSelectForm">
			<div class="ui-state-error">
				<f:errors path="execSelectForm" element="div" />
				<f:errors path="execSelectForm.select" element="div" />
				<f:errors path="execSelectForm.from" element="div" />
				<f:errors path="execSelectForm.where" element="div" />
				<f:errors path="execSelectForm.groupBy" element="div" />
				<f:errors path="execSelectForm.having" element="div" />
				<f:errors path="execSelectForm.orderBy" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="${baseUri}/req" method="POST"
			modelAttribute="execSelectForm">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="select"><s:message
									code="execSelectForm.select" /></label></th>
						<td><f:textarea path="select"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="from"><s:message
									code="execSelectForm.from" /></label></th>
						<td><f:textarea path="from"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="where"><s:message
									code="execSelectForm.where" /></label></th>
						<td><f:textarea path="where"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="groupBy"><s:message
									code="execSelectForm.groupBy" /></label></th>
						<td><f:textarea path="groupBy"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="having"><s:message
									code="execSelectForm.having" /></label></th>
						<td><f:textarea path="having"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="orderBy"><s:message
									code="execSelectForm.orderBy" /></label></th>
						<td><f:textarea path="orderBy"
								cssClass="app-width50 app-height3"
								cssErrorClass="app-width50 app-height3 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="proc" value="exec" class="app-button">
								<s:message code="secure/exec/select/indexId.execButton" />
							</button>
							<button type="submit" name="proc" value="update"
								class="app-button">
								<s:message code="secure/exec/select/indexId.updateButton" />
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
			<s:message code="secure/exec/select/indexId.message.3" />
		</h1>
		<div class="app-portion">
			<f:form servletRelativeAction="${baseUri}/req" method="POST"
				modelAttribute="execSelectForm" id="execSelectWithPage"
				class="app-pager-form">
				<f:hidden id="select2" path="select" />
				<f:hidden id="from2" path="from" />
				<f:hidden id="where2" path="where" />
				<f:hidden id="groupBy2" path="groupBy" />
				<f:hidden id="having2" path="having" />
				<f:hidden id="groupBy2" path="orderBy" />
				<input type="hidden" id="no" name="no">
				<input type="hidden" id="sz" name="sz" value="${param.sz}">
			</f:form>
			<div class="app-pager">
				<div class="app-pager-desc">
					<s:message code="common/pager.message.0"
						arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
				</div>
				<app:pagerLink pageSet="${pageSet}" />
			</div>
			<table id="execResultList" class="app-stripe app-width-full">
				<thead>
					<tr>
						<th>#</th>
						<c:forEach var="col" items="${execResult.header}">
							<th><c:out value="${col.label}" /></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${execResult.recordSet}"
						varStatus="status">
						<tr>
							<td><c:out value="${pageSet.current.from + status.count}" /></td>
							<c:forEach var="field" items="${record}">
								<td><c:out value="${field}" /></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="app-pager">
				<app:pagerLink pageSet="${pageSet}" />
			</div>
		</div>
	</div>
</c:if>
