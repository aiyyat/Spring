<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<form:form commandName="currencyForm" action="${pageContext.request.contextPath}/save_currency">
	<table border="1" id="filler">
		<tr>
			<th>Code:</th>
			<td><form:input path="code" />
		</tr>
		<tr>
			<th>Description:</th>
			<td><form:input path="description" /> <form:hidden path="id" />
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="Save Changes" /></td>
		</tr>
	</table>
</form:form>