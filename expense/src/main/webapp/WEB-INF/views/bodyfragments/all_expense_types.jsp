<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="categoryForm" action="save_category">
	<table border="1" id="filler" class="data">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Direction</th>
			<th>Edit</th>
			<th>Delete</a>
			</th>
		</tr>
		<c:forEach items="${expenseTypes}" var="expenseType">
			<tr>
				<td>${expenseType.name}</td>
				<td>${expenseType.description}</td>
				<td>${expenseType.direction}</td>
				<td><a href="edit_expense_type?id=${expenseType.id}">edit</a></td>
				<td><a href="delete_expense_type?id=${expenseType.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</form:form>