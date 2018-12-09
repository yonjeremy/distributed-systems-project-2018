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

public class UpdateRentalOrder {

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



	public boolean update(String RentalOrder) throws JAXBException, IOException {
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
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date(System.nanoTime()));
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);


		
		String sql = "update orders set CustID = ?,  CarID = ?, Date = ?  where OrderID = ?";
		p = conn.prepareStatement(sql);
		p.setInt(1, custId);
		p.setInt(2, carId);
		p.setString(3, d);
		p.setInt(4, orderId);
		p.execute();
		p.close();
		conn.close();
		System.out.println("Updated");

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