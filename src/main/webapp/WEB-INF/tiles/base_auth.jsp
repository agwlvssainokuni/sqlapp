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
<%@ taglib prefix="common" uri="urn:springapp:common"%>
<c:set var="name">
	<tiles:getAsString name="name" />
</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><s:message code="base/common.title" /> / <s:message
		code="${name}.title" /></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/style/sqltool.css"/>" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/sqltool.js" />"></script>
</head>
<body>
	<div>
		<div>
			<form action="<c:url value="/logout" />" method="POST">
				<button type="submit">
					<s:message code="base/auth.logout" />
				</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
			</form>
		</div>
		<s:message code="base/common.title" />
	</div>
	<div>
		<ul>
			<li><s:message code="base/auth.navigation" /></li>
			<c:forEach var="node" items="${common:navigate(name)}">
				<s:url var="uri" value="${node.uri}">
					<c:if test="${node.uri.contains('{id}')}">
						<s:param name="id" value="${id}" />
					</c:if>
				</s:url>
				<li><a href="${uri}"><s:message code="${node.name}.title" /></a></li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<tiles:insertAttribute name="content" />
	</div>
	<div>
		<s:message code="base/common.copyright" />
	</div>
</body>
</html>
