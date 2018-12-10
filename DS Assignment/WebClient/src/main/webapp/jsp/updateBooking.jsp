<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Booking</title>
</head>
<body>
	<h1>Make Changes to booking </h1>
	<form:form modelAttribute="RentalOrder">
		<table>
			<!-- the path has to be the same name as in the model -->
			<tr>
				<td>Select One of the Bookings to Update:</td>
				<td><form:select path="rentalSID" items="${bookingsList}" /></td>
				<td><form:errors path="rentalSID" items="${bookingsList}" /></td>
			</tr>
			
			<tr>
				<td width="129"><form:label path="pickUpDate">Rental Start Date (YYYY-MM-DD):</form:label></td>
				<td width="152"><form:input path="pickUpDate" /></td>
			</tr>
			<tr>
				<td width="129"><form:label path="dropOffDate">Rental End Date (YYYY-MM-DD):</form:label></td>
				<td width="152"><form:input path="dropOffDate" /></td>
			</tr>
			<tr>
				<td>Update Their Customer:</td>
				<td><form:select path="customerSID.customerSID" items="${custList}" /></td>
				<td><form:errors path="customerSID.customerSID" items="${custList}" /></td>
			</tr>
			<tr>
				<td>Update Their Car:</td>
				<td><form:select path="vehicleSID.vehicleSID" items="${carList}" /></td>
				<td><form:errors path="vehicleSID.vehicleSID" items="${carList}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Update" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>