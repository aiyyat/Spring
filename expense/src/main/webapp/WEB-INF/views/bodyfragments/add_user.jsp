<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form modelAttribute="user" action="save_user">
	<table border="1" id="filler">
		<tr>
			<form:hidden path="id" />
			<th>name:</th>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<th>password:</th>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<th>email:</th>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<th>role:</th>
			<td><form:select path="role">
					<form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>
					<form:option value="ROLE_USER">ROLE_USER</form:option>
				</form:select></td>
		</tr>
		<tr>
			<th>Status:</th>
			<td>${status}</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Save Changes" /></td>
		</tr>
	</table>
</form:form>