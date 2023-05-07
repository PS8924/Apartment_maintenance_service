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
		#edit_form{ display: none; margin: 50px }
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
    		<c:forEach items="${staff_complaints}" var="column">
        		<tr>
        			<td>${column.service_name}</td>
        			<td>${column.service_status}</td>
	        		<td>${column.service_description}</td>
	        		<td>${column.apartment.apt_number}</td>
	        		<td><button name="edit" onclick="editRequest('${column.id}', '${column.apartment.id}', '${column.tenant.id}', '${column.staff.id}' ,'${ column.service_name }', '${column.service_status}')">Edit</button></td>
        		</tr>
      		</c:forEach>
		</tbody>
		</table>
		
		<div id="edit_form">
		<form>
			<label for="service_name">Service Name:</label>
			<input type="text" id="service_name" name="service_name" disabled="disabled"><br><br>
			
			<label for="service_status">Service Status:</label>
			<select id="service_status" name="service_type" required>
			  <option value="pending">Pending</option>
			  <option value="completed">Completed</option>
			</select><br><br>
			<input type="submit" value="Submit">
		</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	var serviceId;
	function editRequest(id,aptid, tenantid,staffid,name, status){
		serviceId = id;
		document.getElementById('service_name').value = name;
		document.getElementById('service_status').value = status;
		document.getElementById("edit_form").style.display = "block";
	}
	
	$(document).ready(function() {
		  $("form").submit(function(event) {
		    event.preventDefault(); // Prevent the form from submitting via its default action

		    // Get the form data
		    var formData = {
		      service_status: $("#service_status").val(),
    		};
		    // Send the AJAX request
		    $.ajax({
		      type: "PUT",
		      url: "/service/request/"+serviceId,
		      data: JSON.stringify(formData),
		      contentType: "application/json",
		      success: function(data) {
		        // Handle the response from the server on successful request
		        alert("Service Request completed successfully!");
		        window.location.reload();
		        
		      },
		      error: function(xhr, status, error) {
		        // Handle the error response from the server
		        alert("Error: " + error);
		      }
		    });
		  });
	});
	
	</script>
</body>
</html>