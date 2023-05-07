<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Equity Apartments</title>
	<style>
		body {
			margin: 0;
			padding: 0;
		}
		nav {
			background-color: #333;
			color: #fff;
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 10px;
		}
		nav ul {
			display: flex;
			list-style: none;
			margin: 0;
			padding: 0;
		}
		nav ul li {
			margin: 0 10px;
		}
		nav ul li a {
			color: #fff;
			text-decoration: none;
		}
		nav ul li a:hover {
			color: #ccc;
		}
		.right-panel{
			width: 100%;
		}
		.content{
			padding-left: 15px;
		}
		.card-layout {
		  display: inline-block;
		  width: 22%;
		  margin: 1%;
		}
		
		.card {
		  background-color: #fff;
		  border-radius: 10px;
		  box-shadow: 0px 2px 10px rgba(0,0,0,0.2);
		  padding: 5px;
		  text-align: center;
		  cursor: pointer;
		  transition: all 0.3s ease;
		}
		
		.card h2 {
		  margin-top: 0;
		}
		
		.card p {
		  margin-bottom: 0;
		}
		
	</style>
</head>
<body>
	<nav>
		<span>Equity Apartments</span>
		<ul>
			<li><a href="/apt/login/logout">Logout</a></li>
		</ul>
	</nav>
	<div style="padding: 20px;">
	
	<c:if test="${role == 'TENANT'}">
	  <div class="right-panel">
		<div class="content">
			<h1>Welcome to Equity Apartments, ${username}! </h1>
			<div class="card-layout">
			  <a href="${pageContext.request.contextPath}/tenant/show/${authorized.id}">
			    <div class="card">
			      <h3>View my details</h3>
			    </div>
			  </a>
			</div>
			
			<div class="card-layout">
			  <a href="/service">
			    <div class="card">
			      <h3>Raise a request</h3>
			    </div>
			  </a>
			</div>
		</div>
			
		</div>
	</c:if>
	
	<c:if test="${role == 'OWNER'}">
	  <div class="right-panel">
		<div class="content">
			<h1>Welcome to Equity Apartments, ${username}! </h1>
			
			<div class="card-layout">
			  <a href="/apartment/create">
			    <div class="card">
			      <h3>Create Apartment</h3>
			    </div>
			  </a>
			</div>
			
			<div class="card-layout">
			  <a href="/tenant">
			    <div class="card">
			      <h3>Create Tenant</h3>
			    </div>
			  </a>
			</div>
			
			<div class="card-layout">
			  <a href="/staff">
			    <div class="card">
			      <h3>Create Staff</h3>
			    </div>
			  </a>
			</div>
			
			<div class="card-layout">
			  <a href="/detail/apartment">
			    <div class="card">
			      <h3>Show all apartments</h3>
			    </div>
			  </a>
			</div>

			<div class="card-layout">
			  <a href="/detail/tenant">
			    <div class="card">
			      <h3>Show all tenants</h3>
			    </div>
			  </a>
			</div>
			
			<div class="card-layout">
			  <a href="/detail/complaints">
			    <div class="card">
			      <h3>Show all complaints</h3>
			    </div>
			  </a>
			</div>
		</div>
			
		</div>
	</c:if>
	
	<c:if test="${role == 'STAFF'}">
	  	<div class="right-panel">
		<div class="content">
			<h1>Welcome to Equity Apartments, ${username}! </h1>
			<div class="card-layout">
			  <a href="${pageContext.request.contextPath}/detail/complaints/${authorized.id}">
			    <div class="card">
			      <h3>Show all complaints</h3>
			    </div>
			  </a>
			</div>
		</div>
		</div>
	</c:if>
	  
	</div>
</body>
</html>