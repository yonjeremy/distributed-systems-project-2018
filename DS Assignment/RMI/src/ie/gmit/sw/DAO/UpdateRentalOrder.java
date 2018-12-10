package ie.gmit.sw.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZonedDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.datatype.XMLGregorianCalendar;

import ie.gmit.sw.model.*;

public class UpdateRentalOrder {
	// add the booking to the database
	public boolean updateOrder(Connection conn, RentalOrder ro) throws SQLException {

		String updateBooking = "UPDATE rentalorder SET pickupDate=?,dropOffDate=?,vehicle_SID=? WHERE rental_SID =? ";

		// create a Statement from the connection
		PreparedStatement preparedStatement = conn.prepareStatement(updateBooking);

		preparedStatement.setDate(1, ro.getPickUpDate());
		preparedStatement.setDate(2, ro.getDropOffDate());
		preparedStatement.setString(3, ro.getVehicleSID().getVehicleSID());
		preparedStatement.setString(4, (ro.getCustomerSID().getCustomerSID()));
		preparedStatement.setString(5, ro.getRentalSID());

		// execute insert SQL statement
		int rowsaffected = preparedStatement.executeUpdate();

		if (rowsaffected > 0) {
			System.out.println("Record updated!");
			return true;
		} else {
			return false;
		}

	}

}
