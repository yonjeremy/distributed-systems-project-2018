<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete</title>
</head>
<body>
	<form:form modelAttribute="RentalOrder">
		<h1>Delete a Order</h1>
		<table>
			<!-- the path has to be the same name as in the model -->
			<tr>
				<td>Select Rental Order To Delete:</td>
				<td><form:select path="rentalSID" items="${rentalList}" /></td>
				<td><form:errors path="rentalSID" items="${rentalList}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Delete" /></td>
			</tr>
		</table>
	</form:form>
	<br>
	<a href="/index.html">Home</a>
</body>
</html>