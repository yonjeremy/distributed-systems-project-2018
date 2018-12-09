package ie.gmit.sw.DAO;

import java.io.*;
import java.util.*;
import java.math.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;

import ie.gmit.sw.model.*;

public class GetRentalOrder {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "test";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed with
	 * MySQL)
	 */
	private final String dbName = "carrental";

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection(
				"jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}



	public RentalOrder get(String bookingID) throws JAXBException, IOException {
		JAXBContext jc;
		
		jc = JAXBContext.newInstance("ie.gmit.sw.model");
		
		ObjectFactory objFactory = new ObjectFactory();
		Address a1 = new Address();
		Customer c1 = new Customer();
		Vehicle v1 = new Vehicle();
		RentalOrder r1 = new RentalOrder();

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}

		try {

			GregorianCalendar c = new GregorianCalendar();
			c.setTime(new Date(System.nanoTime()));
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

			String vehicle_SID = null, customer_SID = null, address_SID = null;

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();

			String sql1 = "SELECT *  FROM rentalorder where rental_SID = '" + bookingID + "'";

			ResultSet rs = stmt.executeQuery(sql1);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				r1.setRentalSID(rs.getString("rental_SID"));
				r1.setOrderDate(date);
				r1.setPickUpDate(date);
				r1.setDropOffDate(date);

				vehicle_SID = (rs.getString("vehicle_SID"));
				customer_SID = (rs.getString("customer_SID"));

			}

			String sql2 = "SELECT *  FROM vehicle where vehicle_SID = '" + vehicle_SID + "'";

			rs = stmt.executeQuery(sql2);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				v1.setVehicleSID(rs.getString("vehicle_SID"));
				v1.setVehiclePlate(rs.getString("vehiclePlate"));
				v1.setAvailableIndicator(rs.getInt("availableIndicator"));
				v1.setModel(rs.getString("model"));

			}
			String sql3 = "SELECT *  FROM customer where customer_SID = '" + customer_SID + "'";

			rs = stmt.executeQuery(sql3);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				c1.setCustomerSID(rs.getString("customer_SID"));
				c1.setFirstName(rs.getString("firstName"));
				c1.setLastName(rs.getString("lastName"));

				address_SID = rs.getString("address_SID");
			}

			String sql4 = "SELECT *  FROM address where address_SID = '" + address_SID + "'";

			rs = stmt.executeQuery(sql4);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				a1.setAddressSID(rs.getString("address_SID"));
				a1.setStreet(rs.getString("street"));
				a1.setCity(rs.getString("city"));
				a1.setCounty(rs.getString("county"));
			}

			c1.setAddressSID(a1);
			r1.setVehicleSID(v1);
			r1.setCustomerSID(c1);

			rs.close();
		} catch (Exception e) {

		}

		// Marshal the PurchaseOrder into XML
		System.out.println("\n\n######### XML Format #########");
		Marshaller m1 = jc.createMarshaller();
		m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m1.marshal(r1, new FileWriter("order.xml"));
		m1.marshal(r1, System.out);
//
//		// Marshal the PurchaseOrder in JSON
//		System.out.println("\n\n######### JSON Format #########");
//		Marshaller m2 = jc.createMarshaller();
//		m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m2.setProperty("eclipselink.media-type", "application/json");
//		m2.setProperty("eclipselink.json.include-root", false);
//		m2.marshal(ro, new FileWriter("order.json"));
//		m2.marshal(ro, System.out);

//		// Unmarshal the XML into a PurchaseOrder Object
//		File file1 = new File("order.xml");
//		Unmarshaller um1 = jc.createUnmarshaller();
//		StreamSource source1 = new StreamSource(file1);
//		JAXBElement<RentalOrder> roElement1 = um1.unmarshal(source1, RentalOrder.class);
//		RentalOrder roFromXml = (RentalOrder) roElement1.getValue();
//		System.out.println("\n\n######### XML Unmarshalling #########\n" + roFromXml.getRentalSID());
//
//		// Unmarshal the JSON into a PurchaseOrder Object
//		File file2 = new File("order.json");
//		Unmarshaller um2 = jc.createUnmarshaller();
//		um2.setProperty("eclipselink.media-type", "application/json");
//		um2.setProperty("eclipselink.json.include-root", false);
//		StreamSource source2 = new StreamSource(file2);
//		JAXBElement<RentalOrder> poElement2 = um2.unmarshal(source2, RentalOrder.class);
//		RentalOrder poFromJson = (RentalOrder) poElement2.getValue();
//		System.out.println("\n\n######### JSON Unmarshalling #########\n" + poFromJson.getRentalSID());

		return r1;
	}






}