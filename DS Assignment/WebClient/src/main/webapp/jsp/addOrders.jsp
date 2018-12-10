<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="/css/style.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<h1>Car Hire Booking Service</h1>

	<h2>Create New Car Rental Booking :</h2>
	<p>&nbsp;</p>
	<form:form modelAttribute="RentalOrder">

		<p>
		<table border="2" cellspacing="1" width="303" cellpadding="3">
<tr>
				<td>Select Customer:</td>
				<td><form:select path="customerSID.customerSID" items="${custList}" /></td>
				<td><form:errors path="customerSID.customerSID" items="${custList}" /></td>
			</tr>
			<tr>
				<td>Select Car:</td>
				<td><form:select path="vehicleSID.vehicleSID" items="${carList}" /></td>
				<td><form:errors path="vehicleSID.vehicleSID" items="${carList}" /></td>
			</tr>


			<tr>
				<td width="129"><form:label path="pickUpDate">Rental Start Date (YYYY-MM-DD):</form:label></td>
				<td width="152"><form:input path="pickUpDate" /></td>
			</tr>
			<tr>
				<td width="129"><form:label path="dropOffDate">Rental End Date (YYYY-MM-DD):</form:label></td>
				<td width="152"><form:input path="dropOffDate" /></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="Create Booking">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="index.html">Return To Menu</a>
		</p>
	</form:form>
	<p>&nbsp;</p>
	<p>&nbsp;</p>

</body>
</html>