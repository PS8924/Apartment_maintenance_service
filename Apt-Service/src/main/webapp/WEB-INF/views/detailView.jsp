<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="common-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	table {
			border-collapse: collapse;
			width: 100%;
			margin-top: 20px;
		}
		th, td {
			padding: 10px;
			text-align: left;
			border-bottom: 1px solid #ddd;
		}
		th {
			background-color: #f2f2f2;
			color: #444;
		}
		td a {
			display: block;
			text-align: center;
			text-decoration: none;
			color: #444;
			font-weight: bold;
		}
		td a:hover {
			background-color: #f2f2f2;
			color: #000;
		}
</style>
</head>
<body>
	<div class="table-view">
		<table id="apartment_deatils">
		<thead>
	        <tr>
	            <c:forEach items="${allheader}" var="column">
        			<th>${column}</th>
      			</c:forEach>
	        </tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${all_apartments}" var="column">
        		<tr>
        			<td>${column.apt_number}</td>
        			<td>${column.floor}</td>
	        		<td>${column.availability}</td>
	        		<td>${column.numTenants}</td>
        		</tr>
      		</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>