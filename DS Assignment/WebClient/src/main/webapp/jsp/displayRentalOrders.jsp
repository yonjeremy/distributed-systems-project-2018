<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="/css/style.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rental Orders</title>
</head>
<body>
	<h1>Rental Orders</h1>
	<table>
		<tr>
			<th>Rental Order SID</th>
			<th>Order Date</th>
			<th>Pick Up Date</th>
			<th>Drop Off Date</th>
			<th>Vehicle_SID</th>
			<th>Customer_SID</th>
			
		</tr>

		<c:forEach items="${rentalOrder}" var="orders">

			<tr>
				<td>${orders.rentalSID}</td>
				<td>${orders.orderDate}</td>
				<td>${orders.pickUpDate}</td>
				<td>${orders.dropOffDate}</td>
				<td>${orders.vehicleSID.vehicleSID}</td>
				<td>${orders.customerSID.customerSID}</td>
				
			</tr>

		</c:forEach>
	</table>
	<br>
	<a href="/index.html">Home</a>
</body>
</html>