<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true"
	type="cherry.spring.common.lib.paginate.PageSet"%>
<div class="app-pager-link">
	<span class="app-page-current" title="${pageSet.current.no + 1}"></span>
	<ul class="pagination">
		<li title="${pageSet.prev.no+1}" class="edge"><a href="#">&laquo;</a></li>
		<c:choose>
			<c:when test="${pageSet.first.no == pageSet.last.no}">
				<li title="${pageSet.first.no+1}"><a href="#">${pageSet.first.no+1}</a></li>
			</c:when>
			<c:otherwise>
				<li title="${pageSet.first.no+1}"><a href="#">${pageSet.first.no+1}</a></li>
				<c:if test="${pageSet.range[0].no > pageSet.first.no+1}">
					<li class="disabled"><span>...</span></li>
				</c:if>
				<c:forEach var="page" items="${pageSet.range}">
					<li title="${page.no+1}"><a href="#">${page.no+1}</a></li>
				</c:forEach>
				<c:if
					test="${pageSet.range[fn:length(pageSet.range)-1].no <  pageSet.last.no-1}">
					<li class="disabled"><span>...</span></li>
				</c:if>
				<li title="${pageSet.last.no+1}"><a href="#">${pageSet.last.no+1}</a></li>
			</c:otherwise>
		</c:choose>
		<li title="${pageSet.next.no+1}" class="edge"><a href="#">&raquo;</a></li>
	</ul>
</div>
