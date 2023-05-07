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
    		<c:forEach items="${all_tenants}" var="column">
        		<tr>
        			<td>${column.firstName}</td>
        			<td>${column.lastName}</td>
	        		<td>${column.username}</td>
	        		<td>${column.email}</td>
	        		<td>${column.apartment.apt_number}</td>
	        		<td><button name="delete" onclick="deleteApartment('${column.id}', '${column.apartment.id}')">Delete</button></td>
        		</tr>
      		</c:forEach>
		</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	function deleteApartment(tenant, aptId) {
		console.log(typeof tenant);
		// Send the AJAX request
	    $.ajax({
	      type: "DELETE",
	      url: "/tenant/"+parseInt(tenant)+"/"+parseInt(aptId),
	      success: function(response) {
	          console.log("Apartment deleted successfully");
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
	          console.error("Error deleting apartment:", errorThrown);
	      }
	    });
	}
	</script>
</body>
</html>