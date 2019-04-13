<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="expenseForm" action="/save_expense">
	<div class="scrollit">
		<table border="1" id="filler">
			<tr>
				<th>Description</th>
				<th>Amount</th>
				<th>Username</th>
				<th>Date</th>
				<th>Currency</th>
				<th>Description</th>
				<th>Payment Mode</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="entry" items="${expenses}">
				<tr>
					<td>${entry.cat.name}</td>
					<td>${entry.amount}</td>
					<td>${entry.user.name}</td>
					<td>${entry.getFormattedDate()}</td>
					<td>${entry.currency.description}</td>
					<td>${entry.description}</td>
					<td>${entry.expenseType.name}</td>
					<td><a href="<c:url value="/edit_expense?id=${entry.id}"/>">edit</a></td>
					<td><a href="<c:url value="/delete_expense?id=${entry.id}"/>">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</form:form>