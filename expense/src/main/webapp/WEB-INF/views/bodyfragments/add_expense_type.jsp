<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="expenseTypeForm" action="save_expense_type">
	<table border="1" id="filler">
		<tr>
		<tr>
			<th>Name:</th>
			<td><form:input path="name" /> <form:hidden path="id" />
		</tr>
		<tr>
			<th>Description:</th>
			<td><form:input path="description" />
		</tr>
		<tr>
			<th>Direction:</th>
			<td>
				<table>
					<tr>
						<form:select path="direction" items="${directions}">
							<form:option value="${direction}"></form:option>
						</form:select>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="Save Changes" /></td>
		</tr>
	</table>
</form:form>