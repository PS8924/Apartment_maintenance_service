<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="common-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Apartment</title>
</head>
<style>
</style>
<body>
	<div class="apartment-form">
		<div class="content">
			<h1>Create new apartment </h1>
			<form>
				<label for="floorNo">Floor Number:</label>
				<input type="text" id="floorNo" name="floor" required><br><br>
				<label for="aptNo">Apartment Number:</label>
				<input type="text" id="aptNo" name="apt_number" required><br><br>
				<input type="submit" value="Submit">
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
      floor: $("#floorNo").val(),
      apt_number: $("#aptNo").val(),
      numTenants : 0,
      availability: true
    };

    // Send the AJAX request
    $.ajax({
      type: "POST",
      url: "/apartment/create/new",
      data: formData,
      success: function(data) {
        // Handle the response from the server on successful request
        alert("Apartment created successfully!");
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