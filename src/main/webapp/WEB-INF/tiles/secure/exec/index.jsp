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
	value="${sqlMetadataList != null && !sqlMetadataList.isEmpty()}" />
<script type="text/javascript">
	$(function() {
		$(".accordion").accordion({
			collapsible : true,
			animate : false,
			heightStyle : "content",
			active : ${hasResultList ? 'false' : 0}
		}).removeClass("ui-widget");
	});
</script>
<h1 class="app-subject">
	<s:message code="secure/exec/index.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="secure/exec/index.message.1" />
	</h1>
	<div>
		<s:hasBindErrors name="execSearchForm">
			<div class="ui-state-error">
				<f:errors path="execSearchForm" element="div" />
				<f:errors path="execSearchForm.name" element="div" />
				<f:errors path="execSearchForm.registeredFrom" element="div" />
				<f:errors path="execSearchForm.registeredTo" element="div" />
			</div>
		</s:hasBindErrors>
		<c:if test="${sqlMetadataList != null && sqlMetadataList.isEmpty()}">
			<div class="ui-state-error">
				<s:message code="secure/exec/index.message.3" />
			</div>
		</c:if>
		<f:form servletRelativeAction="/secure/exec/req" method="POST"
			modelAttribute="execSearchForm">
			<input type="hidden" id="sz" name="sz"
				value="<c:out value="${param.sz}" />">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="name"><s:message
									code="execSearchForm.name" /></label></th>
						<td><f:input path="name" cssClass="app-width20"
								cssErrorClass="app-width20 ui-state-error" /></td>
					</tr>
					<tr>
						<th><s:message code="secure/exec/index.message.10" /></th>
						<td>
							<ul class="app-flat">
								<li><f:checkbox path="select" /><label for="select1"><s:message
											code="secure/exec/index.message.11" /></label></li>
								<li><f:checkbox path="any" /><label for="any1"><s:message
											code="secure/exec/index.message.12" /></label></li>
								<li><f:checkbox path="csv" /><label for="csv1"><s:message
											code="secure/exec/index.message.13" /></label></li>
							</ul>
						</td>
					</tr>
					<tr>
						<th><s:message code="secure/exec/index.message.20" /></th>
						<td>
							<ul class="app-flat">
								<li><f:checkbox path="publish" /><label for="publish1"><s:message
											code="secure/exec/index.message.21" /></label></li>
								<li><f:checkbox path="notPublish" /><label
									for="notPublish1"><s:message
											code="secure/exec/index.message.22" /></label></li>
							</ul>
						</td>
					</tr>
					<tr>
						<th><label for="registeredFrom"><s:message
									code="execSearchForm.registeredFrom" /></label></th>
						<td><f:input path="registeredFrom" cssClass="app-width20"
								cssErrorClass="app-width20 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="registeredTo"><s:message
									code="execSearchForm.registeredTo" /></label></th>
						<td><f:input path="registeredTo" cssClass="app-width20"
								cssErrorClass="app-width20 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" class="app-button">
								<s:message code="secure/exec/index.searchButton" />
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
			<s:message code="secure/exec/index.message.2" />
		</h1>
		<div class="app-portion">
			<f:form servletRelativeAction="/secure/exec/req" method="POST"
				modelAttribute="execSearchForm" id="execSearchWithPage"
				class="app-pager-form">
				<input type="hidden" id="no2" name="no">
				<input type="hidden" id="sz2" name="sz"
					value="<c:out value="${param.sz}" />">
				<f:hidden id="name2" path="name" />
				<f:hidden id="select2" path="select" />
				<f:hidden id="any2" path="any" />
				<f:hidden id="csv2" path="csv" />
				<f:hidden id="publish2" path="publish" />
				<f:hidden id="notPublish2" path="notPublish" />
				<f:hidden id="registeredFrom2" path="registeredFrom" />
				<f:hidden id="registeredTo2" path="registeredTo" />
			</f:form>
			<div class="app-pager">
				<div class="app-pager-desc">
					<s:message code="common/pager.message.0"
						arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
				</div>
				<app:pagerLink pageSet="${pageSet}" />
			</div>
			<app:execResult id="execResultList" execResult="${execResult}"
				pageSet="${pageSet}" />
			<div class="app-pager">
				<app:pagerLink pageSet="${pageSet}" />
			</div>
		</div>
	</div>
</c:if>
