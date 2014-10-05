<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><s:message code="base/common.title" /> / <s:message
		code="error/403.title" /></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/style/sqltool.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/sqltool.js" />"></script>
</head>
<body>
<body role="document">
	<div class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="nav navbar-header">
				<div class="navbar-brand">
					<s:message code="base/common.title" />
				</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<h3 class="text-danger">
			<s:message code="error/403.message.0" />
		</h3>
	</div>
	<div class="footer">
		<div class="container">
			<div class="text-center">
				<s:message code="base/common.copyright" />
			</div>
		</div>
	</div>
</body>
</html>
