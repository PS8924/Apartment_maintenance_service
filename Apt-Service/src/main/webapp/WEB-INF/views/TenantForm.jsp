<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="common-header.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	form {
  max-width: 500px;
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
<body>
<div class="form">
		<div class="content">
		
		<form>
		  <label for="firstName">First Name:</label>
		  <input type="text" id="firstName" name="firstName" required>
		
		  <label for="lastName">Last Name:</label>
		  <input type="text" id="lastName" name="lastName" required>
		
		  <label for="email">Email:</label>
		  <input type="email" id="email" name="email" required>
		
		  <label for="username">Username:</label>
		  <input type="text" id="username" name="username" required>
		
		  <label for="password">Password:</label>
		  <input type="password" id="password" name="password" required>
		  
		  <label for="apt_number">Apartment No:</label>
		  <select name="apartment" id="apartment" required>
		   	<option value="">Choose apartment number</option>
		   	<c:forEach items="${all_apartments}" var="apartment">
		      <c:if test="${apartment.availability}">
		         <option value="${apartment.id}">${apartment.apt_number}</option>
		      </c:if>
		   	</c:forEach>
		  </select>
		  
		  <label for="registeredDate">Registered Date:</label>
		  <input type="date" id="registeredDate" name="registeredDate" required>
		
		  <button type="submit">Add Tenant</button>
		</form>
</div>
</div>
		<!-- Add jQuery library if not already added -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Add a script to handle form submission with AJAX -->
<script>
$(document).ready(function() {
  $("form").submit(function(event) {
    event.preventDefault(); // Prevent the form from submitting via its default action

    // Get the form data
    var formData = {
      firstName: $("#firstName").val(),
      lastName: $("#lastName").val(),
      email : $("#email").val(),
      username: $("#username").val(),
      password: $("#password").val(),
      role: "TENANT",
      apartment: {
          id: parseInt($("#apartment").val())
      },
      registeredDate: "2023-02-25"
    };
	console.log(formData);
    // Send the AJAX request
    
	// Send the AJAX request
    $.ajax({
      type: "POST",
      url: "/tenant/"+parseInt($("#apartment").val())+"/create",
      data: JSON.stringify(formData),
      contentType: "application/json",
      success: function(data) {
        // Handle the response from the server on successful request
        alert("Tenant created successfully!");
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