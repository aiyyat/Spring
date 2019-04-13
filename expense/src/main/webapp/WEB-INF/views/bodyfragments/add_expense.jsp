<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="expenseForm" action="save_expense">
	<table border="0" id="progress" hidden="true">
		<tr>
			<td><img src="static/images/progress.gif"></td>
		</tr>
	</table>
	<table border="1" id="filler">
		<tr>
			<th>amount:</th>
			<td><form:input path="amount" /></td>
		</tr>
		<tr>
			<th>date:</th>
			<td><form:input path="formattedDate" /></td>
		</tr>
		<tr>
			<th>username:</th>
			<td>${expenseForm.user.name}</td>
		</tr>
		<tr>
			<th>category:</th>
			<td><form:select path="cat"
					onchange="javascript:loadExpenseTypes()">
					<form:options items="${expenseForm.rootCat.getRootList()}"
						itemValue="id" itemLabel="name" />
				</form:select></td>
		</tr>
		<tr>
			<th>currency:</th>
			<td><form:select path="currency"
					onchange="javascript:loadExpenseTypes()">
					<form:options items="${currencies}" itemValue="id" itemLabel="code" />
				</form:select></td>
		</tr>

		<tr>
			<th>description:</th>
			<td><form:textarea path="description" /></td>
		</tr>
		<tr>
			<th>Payment Mode:</th>
			<td><form:select path="expenseType"
					onchange="javascript:loadExpenseTypes()">
					<form:options items="${modes}" itemValue="id" itemLabel="name" />
				</form:select></td>
		</tr>
		<tr>
			<th>Remaining:</th>
			<td>${remaining}</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="Save Changes" /></td>
		</tr>
	</table>
	<form:hidden path="id" value="${expenseForm.id}" />
	<script language="javascript">
		function loadExpenseTypes() {
			document.getElementById("filler").hidden = true;
			document.getElementById("progress").hidden = false;
			document.forms[0].action = "load_expense_type";
			document.forms[0].submit();
		}
	</script>
</form:form>
