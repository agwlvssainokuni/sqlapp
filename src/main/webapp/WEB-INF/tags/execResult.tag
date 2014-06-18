<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="id" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@ attribute name="execResult" required="true" rtexprvalue="true"
	type="cherry.sqlapp.service.sqltool.ExecResult"%>
<%@ attribute name="pageSet" required="true" rtexprvalue="true"
	type="cherry.spring.common.lib.paginate.PageSet"%>
<table id="${id}" class="app-stripe app-width-full">
	<thead>
		<tr>
			<th>#</th>
			<c:forEach var="col" items="${execResult.header}">
				<th><c:out value="${col.label}" /></th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="record" items="${execResult.recordSet}"
			varStatus="status">
			<tr>
				<td><c:out value="${pageSet.current.from + status.count}" /></td>
				<c:forEach var="field" items="${record}">
					<td><c:out value="${field}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
