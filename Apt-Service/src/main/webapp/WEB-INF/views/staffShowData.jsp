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
		<table id="tenant_deatils">
		<thead>
			<tr>
				<th>Full Name</th>
				<th>Email</th>
				<th>Username</th>
				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${staff.firstName} ${staff.lastName}</td>
				<td>${staff.email}</td>
				<td>${staff.username}</td>
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