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
<c:if test="${loginFailed}">
	<div class="app-portion ui-state-error">
		<s:message code="login/index.message.1" />
	</div>
</c:if>
<c:if test="${loggedOut}">
	<div class="app-portion ui-state-highlight">
		<s:message code="login/index.message.2" />
	</div>
</c:if>
<form id="loginForm" action="<c:url value="/login/req" />" method="POST">
	<table>
		<tbody class="app-transparent">
			<tr>
				<td><label for="loginId"><s:message
							code="loginForm.loginId" /></label></td>
				<td><input type="text" id="loginId" name="loginId"
					class="app-width20" /></td>
			</tr>
			<tr>
				<td><label for="password"><s:message
							code="loginForm.password" /></label></td>
				<td><input type="password" id="password" name="password"
					class="app-width20" /></td>
			</tr>
		</tbody>
		<tfoot class="app-transparent">
			<tr>
				<td></td>
				<td>
					<button type="submit" class="app-button">
						<s:message code="login/index.loginButton" />
					</button>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
