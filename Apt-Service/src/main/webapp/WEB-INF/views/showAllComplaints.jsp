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
		.radio-group {
		  display: flex;
		  flex-direction: row;
		  justify-content: space-between;
		  align-items: center;
		}
		
		.radio-group label {
		  margin-right: 20px;
		}
		
		.radio-group input[type="radio"] + label:before {
		  content: "";
		  display: inline-block;
		  width: 20px;
		  height: 20px;
		  border-radius: 50%;
		  border: 2px solid #ccc;
		  margin-right: 5px;
		  vertical-align: middle;
		}
		
		.radio-group input[type="radio"]:checked + label:before {
		  background-color: #007bff;
		  border-color: #007bff;
		}
		.search_form{ margin: 0 50px; float: right } .table-view{padding: 15px}
</style>
</head>
<body>
	<div class="search_form">
		<form>
			<div class="radio-group">
			  <h3>Filter data based on request status: </h3>
			  <label>
    			<input type="radio" id="service_type_pending" name="service_type" value="pending" checked>
    				Pending
			  </label>
				<label>
				    <input type="radio" id="service_type_completed" name="service_type" value="completed">
				    Completed
				</label>
			  <input type="submit" value="Submit">
			</div>
		</form>
	</div>
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
    		<c:forEach items="${all_complaints}" var="column">
        		<tr>
        			<td>${column.service_name}</td>
        			<td>${column.service_type}</td>
        			<td>${column.service_status}</td>
	        		<td>${column.service_description}</td>
	        		<td>${column.apartment.apt_number}</td>
        		</tr>
      		</c:forEach>
		</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	
	$(document).ready(function() {
	  $("form").submit(function(event) {
	    event.preventDefault(); // Prevent the form from submitting via its default action
	    var serviceType = $('input[name=service_type]:checked').val();
	    // Get the form data
	    var formData = {
	      service_status: serviceType
   		};
	    
	    window.location.href = "http://localhost:8080/detail/complaints/search/"+serviceType;
	   
	  });
	});
	
	</script>
</body>
</html>