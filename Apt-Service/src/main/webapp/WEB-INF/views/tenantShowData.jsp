<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="common-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>My Page</title>
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
		#tenant_deatils{
			display: table
		}
		#complaint_deatils{display: none}
		#edit-tenant-form{display: none}
		form {
		  max-width: 300px;
		  margin: 0 auto;
		}
		
		form label {
		  display: block;
		  margin-bottom: 5px;
		}
		
		form input[type="text"], form input[type="email"], form input[type="password"], form select {
		  width: 100%;
		  height: 35px;
		  padding: 5px 10px;
		  margin-bottom: 20px;
		  border: 1px solid #ccc;
		  border-radius: 5px;
		}
		
		form select {
		  background-color: #f9f9f9;
		}
		
		form button[type="submit"] {
		  background-color: #007bff;
		  color: #fff;
		  border: none;
		  border-radius: 5px;
		  padding: 10px 20px;
		  font-size: 16px;
		  cursor: pointer;
		}
		
		form button[type="submit"]:hover {
		  background-color: #0069d9;
		}
		
	</style>
	</head>
	<body>
		<div class="content">
		<table id="complaint_deatils">
		<thead>
	        <tr>
	            <th>Complaint ID</th>
	            <th>Subject</th>
	            <th>Status</th>
	            <th>Open Date</th>
	            <th>Close Date</th>
	        </tr>
    	</thead>
    	<tbody>
	        <tr>
	            <td>001</td>
	            <td>No hot water</td>
	            <td>In Progress</td>
	            <td>04/25/2023</td>
	            <td></td>
	        </tr>
	        <tr>
	            <td>002</td>
	            <td>Broken window</td>
	            <td>Resolved</td>
	            <td>03/05/2023</td>
	            <td>03/07/2023</td>
	        </tr>
		</tbody>
		</table>
		
		<table id="tenant_deatils">
		<thead>
			<tr>
				<th>Full Name</th>
				<th>Email</th>
				<th>Username</th>
				<th>Apartment Number</th>
				<th>Lease Start Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${tenant.firstName} ${tenant.lastName}</td>
				<td>${tenant.email}</td>
				<td>${tenant.username}</td>
				<td>105</td>
				<td>01/01/2023</td>
				<td><button name="edit" onclick="openEditForm('${tenant.id}','${tenant.firstName}', '${tenant.lastName}', '${tenant.email}' , ' ${tenant.username}')">Edit</button></td>
			</tr>
			<!-- add more rows as needed -->
		</tbody>
		</table>
		
		</div>
		
		<div class="content" id="edit-tenant-form">
			<form>
			  <label for="firstName">First Name:</label>
			  <input type="text" id="firstName" name="firstName" required>
			
			  <label for="lastName">Last Name:</label>
			  <input type="text" id="lastName" name="lastName" required>
			
			  <label for="email">Email:</label>
			  <input type="email" id="email" name="email" required>
		
			  <button type="submit">Submit</button>
			  
			 </form>
		</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		var fname, lname, email, username;
		var tId;
	    function showComplaintDetails() {
	    	//alert('clicked');
	        document.getElementById("tenant_deatils").style.display = "none";
	        document.getElementById("complaint_deatils").style.display = "table";
	    }
	    
	    function showTenantDetails(){
	    	document.getElementById("tenant_deatils").style.display = "table";
	        document.getElementById("complaint_deatils").style.display = "none";
	    }
	    
	    function openEditForm(id,fname, lname, email, username){
	    	
	    	tId = id;
	    	
	    	document.getElementById("edit-tenant-form").style.display = "block";
	    	document.getElementById("firstName").value = fname;
	    	document.getElementById("lastName").value = lname;
	    	document.getElementById("email").value = email;
	    }
	    
	    $(document).ready(function() {
	    	  $("form").submit(function(event) {
	    	    event.preventDefault(); // Prevent the form from submitting via its default action

	    	    // Get the form data
	    	    var formData = {
	    	      firstName: $("#firstName").val(),
	    	      lastName: $("#lastName").val(),
	    	      email : $("#email").val(),
	    	    };
	    		console.log(formData);
	    	    // Send the AJAX request
				document.getElementById("edit-tenant-form").style.display = "none";
	    	    $.ajax({
	    	      type: "PUT",
	    	      url: "/tenant/"+tId+"/update",
	    	      data: JSON.stringify(formData),
	    	      contentType: "application/json",
	    	      success: function(data) {
	    	        // Handle the response from the server on successful request
	    	        alert("Tenant details edited successfully!");
	    	        window.location.reload();
	    	      },
	    	      error: function(xhr, status, error) {
	    	        // Handle the error response from the server
	    	        alert("updated data");
	    	        window.location.reload();
	    	      }
	    	    });
	    	  });
	    	});
	    
	</script>
</body>
</html>