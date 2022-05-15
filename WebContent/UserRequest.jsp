<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<style>
table {
  border-collapse: collapse;
  width: 200%;
}
th, td {
  height: 50px;
  padding: 8px;
  width: 20%;
  text-align: left;
  border-bottom: 2px solid #ddd;
}
tr:hover {background-color: #d4cfcf;}
</style>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
<!-- 	<div class="col-sm-4 col-lg-4"> -->
<!-- 		<img img src="Images/elec.jpg" alt="Logo" -->
<!--           	class="img-fluid mx-auto d-block upload-img zoomIn animDelay02 animated pulse-hvr lazyload"/> -->
<!--                          </div> -->
                         
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1 style="color:blue;"><b>USER MANAGEMENTS<b></h1>
				<br>
				<br>
				
				<form id="userForm" name="userForm">
				
				
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Name :</label>
						    <input type="text" id="name" class="form-control" name="name" placeholder="Enter name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">NIC :</label>
						    <input type="text" id="nic" class="form-control" name="nic" placeholder="Enter nic..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Address :</label>
						    <input type="text" id="address" class="form-control" name="address" placeholder="Enter address..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">ZipCode :</label>
						    <input type="text" id="zipcode" class="form-control" name="zipcode" placeholder="Enter zip code">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Telephone Number :</label>
						    <input type="text" id="contactnumber" class="form-control" name="conatctnumber" placeholder="Enter telephone number..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Email :</label>
						    <input type="text" id="email" class="form-control" name="email" placeholder="Enter the email">						    
						</div>
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
						<input type="hidden" id="hidConnReqIdSave" name="hidConnReqIdSave" value="">
						
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divConnReqGrid">
					<%
							User userObj = new User();
					                System.out.println(userObj.readConnRequest());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>