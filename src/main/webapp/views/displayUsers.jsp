<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script>
function confirmDelete(){
	return confirm("Are you sure want to delete this?")
}

</script>

<title>Insert title here</title>
</head>
<body bgcolor="yellow">

<a href="registerUser">+Add User</a>

<table border="1">
<tr>
<td>SR NO</td>
<td>UserName</td>
<td>Email</td>
<td>Dob</td>
<td>Phone No</td>
<td>Country</td>
<td>Action</td>
</tr>
<c:forEach items="${UserList}" var="user" varStatus="index">
<tr>
<td>${index.count}</td>
<td>${user.username}</td>
<td>${user.email}</td>
<td>${user.dob}
<td>${user.phoneno}</td>
<td>${user.country}</td>
<td><a href="editUser?uid=${user.id}">edit</a></td>
<td><a href="deleteUser?uid=${user.id}" onclick="return confirmDelete()">delete</a></td>
</tr>
</c:forEach>
</table>

<!-- for pre page -->

<c:if test="${cp>1}">
	<a href="displayUsers?pn=${cp-1}">Previous</a>
</c:if>

<c:forEach begin="1" end="${tp}" var="i">
		<c:choose>
			<c:when test="${cp==i}">
				<c:out value="${i}" />
			</c:when>
			<c:otherwise>
				<a href="displayUsers?pn=${i}"><c:out value="${i}" /></a>
			</c:otherwise>
		</c:choose>

</c:forEach>

	<c:if test="${cp < tp}">
		<a href="displayUsers?pn=${cp+1}">Next</a>
	</c:if>

</body>
</html>