<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<a class="navbar-brand"> User ManagementApp </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
				
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
							Edit User
						</c:if>
						<c:if test="${user == null}">
							Add New User
						</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
				
					<input type="hidden" name="Sl_no" value="<c:out value='${user.Sl_no}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>CUSTOMER ORDER ID</label> <input type="text"
						value="<c:out value='${user.CUSTOMER_ORDER_ID}' />" class="form-control"
						name="CUSTOMER_ORDER_ID" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>SALES ORG</label> <input type="text"
						value="<c:out value='${user.SALES_ORG}' />" class="form-control"
						name="SALES_ORG">
				</fieldset>

				<fieldset class="form-group">
					<label>DISTRIBUTION CHANNEL</label> <input type="text"
						value="<c:out value='${user.DISTRIBUTION_CHANNEL}' />" class="form-control"
						name="DISTRIBUTION_CHANNEL">
				</fieldset>
				
				<fieldset class="form-group">
					<label>CUSTOMER NUMBER</label> <input type="text"
						value="<c:out value='${user.CUSTOMER_NUMBER}' />" class="form-control"
						name="CUSTOMER_NUMBER">
				</fieldset>
				
				<fieldset class="form-group">
					<label>COMPANY CODE</label> <input type="text"
						value="<c:out value='${user.COMPANY_CODE}' />" class="form-control"
						name="COMPANY_CODE">
				</fieldset>
				
				<fieldset class="form-group">
					<label>ORDER CURRENCY</label> <input type="text"
						value="<c:out value='${user.ORDER_CURRENCY}' />" class="form-control"
						name="ORDER_CURRENCY">
				</fieldset>
				
				<fieldset class="form-group">
					<label>AMOUNT IN USD</label> <input type="text"
						value="<c:out value='${user.AMOUNT_IN_USD}' />" class="form-control"
						name="AMOUNT_IN_USD">
				</fieldset>
				
				<fieldset class="form-group">
					<label>ORDER CREATION DATE</label> <input type="text"
						value="<c:out value='${user.ORDER_CREATION_DATE}' />" class="form-control"
						name="ORDER_CREATION_DATE">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
