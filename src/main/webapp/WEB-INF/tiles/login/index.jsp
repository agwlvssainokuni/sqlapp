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
<h2 class="page-header">
	<s:message code="login/index.message.0" />
</h2>
<c:if test="${loginFailed}">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="alert alert-danger" role="alert">
			<s:message code="login/index.message.1" />
		</div>
	</div>
</c:if>
<c:if test="${loggedOut}">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="alert alert-success" role="alert">
			<s:message code="login/index.message.2" />
		</div>
	</div>
</c:if>
<form id="loginForm" action="<c:url value="/login/req" />" method="POST"
	class="form-horizontal" role="form">
	<div class="form-group">
		<label for="loginId" class="col-sm-2 control-label"><s:message
				code="loginForm.loginId" /></label>
		<div class="col-sm-10">
			<input type="text" id="loginId" name="loginId" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label"><s:message
				code="loginForm.password" /></label>
		<div class="col-sm-10">
			<input type="password" id="password" name="password"
				class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary btn-block">
				<s:message code="login/index.loginButton" />
			</button>
		</div>
	</div>
</form>
