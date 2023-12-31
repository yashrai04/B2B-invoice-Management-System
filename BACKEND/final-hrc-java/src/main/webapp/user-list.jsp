<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand"> User
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>CUSTOMER_ORDER_ID</th>
						<th>SALES_ORG</th>
						<th>DISTRIBUTION_CHANNEL</th>
						<th>CUSTOMER_NUMBER</th>
						<th>COMPANY_CODE</th>
						<th>ORDER_CURRENCY</th>
						<th>AMOUNT_IN_USD</th>
						<th>ORDER_CREATION_DATE</th>						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
						 
							<td><c:out value="${user.customerOrderID}" /></td>
							<td><c:out value="${user.salesOrg}" /></td>
							<td><c:out value="${user.distributionChannel}" /></td>
							<td><c:out value="${user.customerNumber}" /></td>
							<td><c:out value="${user.companyCode}" /></td>
							<td><c:out value="${user.orderCurrency}" /></td>
							<td><c:out value="${user.amountInUsd}" /></td>
							<td><c:out value="${user.orderCreationDate}" /></td>
							
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>