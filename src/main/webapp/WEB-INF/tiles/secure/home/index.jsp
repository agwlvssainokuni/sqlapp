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
	<s:message code="secure/home/index.message.0" />
</h2>
<div class="list-group">
	<a class="list-group-item" href="<c:url value="/sqltool/search" />"><s:message
			code="secure/home/index.menu.search" /></a> <a class="list-group-item"
		href="<c:url value="/sqltool/clause" />"><s:message
			code="secure/home/index.menu.execClause" /></a> <a
		class="list-group-item" href="<c:url value="/sqltool/statement" />"><s:message
			code="secure/home/index.menu.execStatement" /></a> <a
		class="list-group-item" href="<c:url value="/sqltool/load" />"><s:message
			code="secure/home/index.menu.execLoad" /></a>
</div>
