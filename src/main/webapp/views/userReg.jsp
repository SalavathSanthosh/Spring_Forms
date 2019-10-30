<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<style>
.error {
	color: #FF0000
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "1960:" + new Date()
		});
	});
	$(function() {
		$('form[id="userRegForm"]').validate({
			rules : {
				username : 'required',
				password : 'required',
				email : {
					required : true,
					email : true
				},
				phoneno : {
					required : true,
					minlength : 10,
					maxlength : 10
				}
			},
			messages : {
				username : 'Please enter username.',
				password : 'please enter password.',
				email : 'Please enter valid email.',

				phoneno : {
					required : 'please enter phone number.',
					minlength : 'please enter valid Phone No.',
					maxlength : 'please enter valid Phone No.'
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
<title>User Registration Form</title>
</head>
<body bgcolor="cyan">

	<font style="font-size: 50" color="green">${successMsg}</font>

	<h1 style="color: red;">User Registration Form</h1>
	<form:form action="registerUser" method="Post" modelAttribute="user"
		id="userRegForm">
		<table>
			<tr>
				<td>Username :</td>
				<td><form:input path="username" /></td>
			</tr>

			<tr>
				<td>Password :</td>
				<td><form:password path="password" /></td>
			</tr>

			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td>DOB :</td>
				<td><form:input path="dob" id="datepicker" /></td>
			</tr>

			<tr>
				<td>Phone Number :</td>
				<td><form:input path="phoneno" /></td>
			</tr>
			<tr>
				<td>Country :</td>
				<td><form:select path="country" items="${countries}" /></td>
			</tr>

			<tr>
				<td><input type="reset" value="Reset"></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td><a href="displayUsers?pn=1">View Users</a></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>