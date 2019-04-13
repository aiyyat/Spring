<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="categoryForm" action="save_category">
	<table border="1" id="filler">
		<tr>
			<th>Parent Category:</th>
			<td><form:select path="parent" items="${parents}" itemValue="id"
					itemLabel="name" />
		</tr>
		<tr>
			<th>Name:</th>
			<td><form:input path="name" /> <form:hidden path="id" />
		</tr>
		<tr>
			<th>expenseType:</th>
			<td>
				<table>
					<tr>
						<form:select multiple="true" path="expenseTypes"
							items="${expenseTypes}" itemLabel="name" itemValue="id">
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