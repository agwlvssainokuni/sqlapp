<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fwcore" uri="urn:springapp:fwcore"%>
<c:set var="request" value="<%=request%>" />
<c:set var="token" value="${fwcore:onetimetoken(request)}" />
<input type="hidden" name="${token.name}" value="${token.value}" />
