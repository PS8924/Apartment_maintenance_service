<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="common-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="form">
		<div class="content">
		<form>
		  <label for="service-name">Service Name:</label>
		  <input type="text" id="service-name" name="service_name" required><br><br>
		  
		  <label for="created-date">Created Date:</label>
		  <input type="date" id="created-date" name="createdDate" required><br><br>
		  
		  <label for="service-type">Service Type:</label>
		  <select id="service-type" name="service_type" required>
		    <option value="PLUMBING">Plumbing</option>
		    <option value="ELECTRICITY">Electricity</option>
		  </select><br><br>		
  
		  <label for="service-description">Service Description:</label>
		  <textarea id="service-description" name="service_description" required></textarea><br><br>		  
		  <button type="submit">Submit Request</button>
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
    
    const allStaff = ${all_staff};
    var staffId;
    for(var i=0; i<allStaff.length; i++){
    	if($("#service-type").val() === allStaff[i].personType){
    		staffId = allStaff[i].id;
    	}
    }
    
    var formData = {
      service_name: $("#service-name").val(),
      service_status: "pending",
      service_type : $("#service-type").val(),
      service_description: $("#service-description").val(),
      createdDate: "2023-02-25",
      apartment : {
    	  apt_id : parseInt(${aptId})
      },
      tenant : {
    	  tenant_id : ${authorized.id}
      },
      staff : {
    	  staff_id: staffId
      }
    };
	console.log(formData);
	// Send the AJAX request	
	
	console.log("/service/"+${authorized.id}+"/apt/"+${aptId}+"/staff/"+staffId);
	
	$.ajax({
      type: "POST",
      url: "/service/"+${authorized.id}+"/apt/"+${aptId}+"/staff/"+staffId,
      data: JSON.stringify(formData),
      contentType: "application/json",
      success: function(data) {
        // Handle the response from the server on successful request
        alert("Request created successfully!");
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