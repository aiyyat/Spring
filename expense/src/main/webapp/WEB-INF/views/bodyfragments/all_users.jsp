<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<form:form>
	<table border="1" id="filler">
		<tr>
			<th>User:</th>
			<th>Email:</th>
			<th>Role:</th>
			<th>Edit:</th>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<th>Delete:</th>
			</sec:authorize>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.email}" /></td>
				<td><c:out value="${user.role}" /></td>
				<td><a href="edit_user?id=${user.id}">edit</a></td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td><a href="delete_user?id=${user.id}">delete</a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
		</tr>
	</table>
</form:form>