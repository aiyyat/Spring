<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script>
	$(function() {
		$("#menu").menu();
	});
</script>
<div style="padding: 5px; vertical-align: top;">
	<ul id="menu">
		<sec:authorize access="! isAuthenticated()">
			<li><a href="${pageContext.request.contextPath}/welcome">Login</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li><a href="#">User</a>
				<ul>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="${pageContext.request.contextPath}/add_user">Add
								User</a></li>
					</sec:authorize>
					<li><a href="${pageContext.request.contextPath}/all_users">All
							Users</a></li>
				</ul></li>
			<li><a href="#">Expense</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/add_expense">Add
							Expense</a></li>
					<li><a href="${pageContext.request.contextPath}/view_expenses">All
							Expenses</a></li>
				</ul></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="#">Category</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/add_category">Add
								Category</a></li>
						<li><a
							href="${pageContext.request.contextPath}/all_categories">All
								Categories</a></li>
					</ul></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="#">ExpenseType</a>
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/add_expense_type">Add
								Expense Type</a></li>
						<li><a
							href="${pageContext.request.contextPath}/all_expense_types">All
								Expense Types</a></li>
					</ul></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="#">Currency</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/add_currency">Add
								Currency</a></li>
						<li><a
							href="${pageContext.request.contextPath}/all_currencies">All
								Currencies</a></li>
					</ul>
			</sec:authorize>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</sec:authorize>
		<li><a href="${pageContext.request.contextPath}/aboutus">About
				Us</a></li>
	</ul>
</div>