<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.Billcal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="views/Form.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Components/bill.js"></script>

</head>
<body>

<div class="container">
<div class="panel panel-default">
  <div class="panel-heading"> 
  	<h1>Insert Bill Information Form</h1>
  	<br>
  <div class="panel-body">

			<form class="form-container" id="formBill" name="formBill" method="post" action="Bill.jsp">
			<div class="form-group">
				Account Number: <input id="accNumber" name="accNumber" type="text"
				class="form-control form-control-sm"> <br> 
			</div>		
		Name: <input
			id="name" name="name" type="text"
			class="form-control form-control-sm"> <br> 
			
		Units: <input
			id="units" name="units" type="text" 
			class="form-control form-control-sm"> <br> 
			
		Date: <input 
			id="date" name="date" type="text"
			class="form-control form-control-sm"> <br> 
			
			<input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> 
			
			<input type="hidden"
			id="hidItemIDSave" name="hidItemIDSave" value="">
			
			<input id="btnreset" name="reset" type="reset" value="Reset"
			class="btn btn-danger">
			
	</form>
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	
	</div>
	</div>
	</div>
	
	
	<br>

<%
 Billcal billcalObj = new Billcal(); 
 out.print(billcalObj.readBillInfo()); 
%>

</div>
</body>
</html>