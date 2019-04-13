<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<form:form action="save_category">
	<table border="1" id="filler">
		<tr>
			<th>Code</th>
			<th>Description</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${currencies}" var="currency">

			<tr>
				<td>${currency.code}</td>
				<td>${currency.description}</td>
				<td><a href="edit_currency/${currency.code}">edit</a></td>
				<td><a href="delete_currency/${currency.code}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</form:form>