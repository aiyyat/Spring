<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="categoryForm" action="save_category">
	<table border="1" id="filler">
		<c:forEach items="${parents}" var="parent">
			<tr>
				<th colspan="${parent.children.size()}">${parent.name}</th>
				<c:forEach items="${parent.children}" var="children">
					<tr>
						<td>${children.name}</td>
						<td>
							<table>
								<c:forEach items="${children.expenseTypes}" var="expenseType">
									<tr>
										<td>${expenseType.name}</td>
									</tr>
								</c:forEach>
							</table>
						<td><a href="edit_category?id=${children.id}">edit</a></td>
						<td><a href="delete_category?id=${children.id}">delete</a></td>
					</tr>
				</c:forEach>
		</c:forEach>
	</table>
</form:form>
</html>