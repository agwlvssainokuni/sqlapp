<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="id" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@ attribute name="resultSet" required="true" rtexprvalue="true"
	type="cherry.sqlapp.service.sqltool.exec.ResultSet"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true"
	type="cherry.spring.common.lib.paginate.PageSet"%>
<table id="${id}" class="table table-striped">
	<thead>
		<tr>
			<th>#</th>
			<c:forEach var="col" items="${resultSet.header}">
				<th><c:out value="${col.label}" /></th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="count" begin="1" end="${resultSet.recordSet.size()}">
			<tr>
				<td class="text-right"><c:out
						value="${pageSet.current.from + count}" /></td>
				<c:forEach var="fnum" begin="1"
					end="${fn:length(resultSet.recordSet.get(count-1))}">
					<s:bind path="resultSet.recordSet[${count - 1}][${fnum - 1}]">
						<td>${status.value}</td>
					</s:bind>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
