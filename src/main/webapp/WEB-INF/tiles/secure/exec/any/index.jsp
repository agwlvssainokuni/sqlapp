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
	value="${execResult != null && pageSet != null}" />
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
	<s:message code="secure/exec/any/index.message.0" />
</h1>
<div class="app-portion accordion">
	<h1>
		<s:message code="secure/exec/any/index.message.1" />
	</h1>
	<div>
		<s:hasBindErrors name="execAnyForm">
			<div class="ui-state-error">
				<f:errors path="execAnyForm" element="div" />
				<f:errors path="execAnyForm.sql" element="div" />
				<f:errors path="execAnyForm.paramMap" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="/secure/exec/any/req" method="POST"
			modelAttribute="execAnyForm">
			<input type="hidden" id="sz" name="sz"
				value="<c:out value="${param.sz}" />">
			<table class="app-collabel">
				<tbody>
					<tr>
						<th><label for="sql"><s:message
									code="execAnyForm.sql" /></label></th>
						<td><f:textarea path="sql" cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="paramMap"><s:message
									code="execAnyForm.paramMap" /></label></th>
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
								<s:message code="secure/exec/any/index.execButton" />
							</button>
							<button type="submit" name="proc" value="create"
								class="app-button">
								<s:message code="secure/exec/any/index.createButton" />
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
			<s:message code="secure/exec/any/index.message.2" />
		</h1>
		<div class="app-portion">
			<f:form servletRelativeAction="/secure/exec/any/req" method="POST"
				modelAttribute="execAnyForm" id="execAnyWithPage"
				class="app-pager-form">
				<input type="hidden" id="no2" name="no">
				<input type="hidden" id="sz2" name="sz"
					value="<c:out value="${param.sz}" />">
				<f:hidden id="sql2" path="sql" />
				<f:hidden id="paramMap2" path="paramMap" />
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
