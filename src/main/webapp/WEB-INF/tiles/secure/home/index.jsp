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
	<s:message code="secure/home/index.message.0" />
</h1>
<div class="app-portion">
	<ul>
		<li><a href="<c:url value="/secure/sqlexec" />"><s:message
					code="secure/home/index.menu.sqlexec" /></a></li>
		<li><a href="<c:url value="/secure/sqladmin" />"><s:message
					code="secure/home/index.menu.sqladmin" /></a></li>
	</ul>
</div>
