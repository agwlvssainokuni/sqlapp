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
<h1 class="app-subject">
	<s:message code="secure/exec/select/index.message.0" />
</h1>
<div class="app-portion">
	<h1 class="app-subject">
		<s:message code="secure/exec/select/index.message.1" />
	</h1>
	<div class="app-portion">
		<s:hasBindErrors name="execSelectForm">
			<div class="ui-state-error">
				<f:errors path="execSelectForm" element="div" />
				<f:errors path="execSelectForm.select" element="div" />
				<f:errors path="execSelectForm.from" element="div" />
				<f:errors path="execSelectForm.where" element="div" />
				<f:errors path="execSelectForm.groupBy" element="div" />
				<f:errors path="execSelectForm.having" element="div" />
				<f:errors path="execSelectForm.orderBy" element="div" />
				<f:errors path="execSelectForm.limit" element="div" />
				<f:errors path="execSelectForm.offset" element="div" />
			</div>
		</s:hasBindErrors>
		<f:form servletRelativeAction="/secure/exec/select/req" method="POST"
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
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
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
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="having"><s:message
									code="execSelectForm.having" /></label></th>
						<td><f:textarea path="having"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="orderBy"><s:message
									code="execSelectForm.orderBy" /></label></th>
						<td><f:textarea path="orderBy"
								cssClass="app-width50 app-height5"
								cssErrorClass="app-width50 app-height5 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="limit"><s:message
									code="execSelectForm.limit" /></label></th>
						<td><f:input path="limit" cssClass="app-width10"
								cssErrorClass="app-width10 ui-state-error" /></td>
					</tr>
					<tr>
						<th><label for="offset"><s:message
									code="execSelectForm.offset" /></label></th>
						<td><f:input path="offset" cssClass="app-width10"
								cssErrorClass="app-width10 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td>
							<button type="submit" name="submit" class="app-button">
								<s:message code="secure/exec/select/index.execButton" />
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<h1 class="app-subject">
		<s:message code="secure/exec/select/index.message.2" />
	</h1>
	<div class="app-portion"></div>
</div>
