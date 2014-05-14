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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=5.0, user-scalable=yes" />
<title><s:message code="base/common.title" /> / <s:message
		code="${name}.title" /></title>
<link rel="stylesheet" media="screen"
	href="//code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/style/general.css"/>" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/style/custom.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/general.js" />"></script>
<script type="text/javascript" src="<c:url value="/script/custom.js" />"></script>
</head>
<body>
	<div id="PageHeader">
		<div id="Header">
			<s:message code="base/common.title" />
		</div>
	</div>
	<div id="PageContent">
		<div id="Content">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	<div id="PageFooter">
		<div id="Footer">
			<s:message code="base/common.copyright" />
		</div>
	</div>
</body>
</html>
