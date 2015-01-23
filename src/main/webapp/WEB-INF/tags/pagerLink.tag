<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true"
	type="cherry.goods.paginate.PageSet"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<%@ attribute name="pno" required="true" rtexprvalue="true"%>
<div class="pager-link" data-form="${form}" data-pno="${pno}"
	data-current="${pageSet.current.no+1}">
	<ul class="pagination">
		<li class="edge"><a href="#" title="${pageSet.prev.no+1}">&laquo;</a></li>
		<li class="edge"><a href="#" title="${pageSet.next.no+1}">&raquo;</a></li>
		<c:choose>
			<c:when test="${pageSet.first.no == pageSet.last.no}">
				<li><a href="#" title="${pageSet.first.no+1}">${pageSet.first.no+1}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#" title="${pageSet.first.no+1}">${pageSet.first.no+1}</a></li>
				<c:if test="${pageSet.range[0].no > pageSet.first.no+1}">
					<li class="disabled"><span>...</span></li>
				</c:if>
				<c:forEach var="page" items="${pageSet.range}">
					<li><a href="#" title="${page.no+1}">${page.no+1}</a></li>
				</c:forEach>
				<c:if
					test="${pageSet.range[fn:length(pageSet.range)-1].no <  pageSet.last.no-1}">
					<li class="disabled"><span>...</span></li>
				</c:if>
				<li><a href="#" title="${pageSet.last.no+1}">${pageSet.last.no+1}</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
