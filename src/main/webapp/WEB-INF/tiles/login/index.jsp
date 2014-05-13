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
	<s:message code="login/index.message.0" />
</h1>
<c:if test="${param.err == 'login'}">
	<div class="app-portion ui-state-error">
		<s:message code="login/index.message.1" />
	</div>
</c:if>
<div>
	<f:form servletRelativeAction="/login/req" method="POST"
		modelAttribute="loginForm">
		<table>
			<tbody class="app-transparent">
				<tr>
					<td><label for="loginId"><s:message
								code="loginForm.loginId" /></label></td>
					<td><f:input path="loginId" cssClass="app-width20"
							cssErrorClass="app-width20 ui-state-error" /></td>
				</tr>
				<tr>
					<td><label for="password"><s:message
								code="loginForm.password" /></label></td>
					<td><f:password path="password" cssClass="app-width20"
							cssErrorClass="app-width20 ui-state-error" /></td>
				</tr>
			</tbody>
			<tfoot class="app-transparent">
				<tr>
					<td></td>
					<td>
						<button type="submit" name="submit" class="app-button">
							<s:message code="login/index.loginButton" />
						</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</f:form>
</div>
