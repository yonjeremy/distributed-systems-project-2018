package ie.gmit.sw.DAO;

import java.io.*;
import java.util.*;
import java.math.*;
import java.sql.Connection;
import java.sql.Date;
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

public class GetAllRentalOrders {

	public List<RentalOrder> getAll(Connection conn) throws JAXBException, IOException {
		
		JAXBContext jc;
		
		jc = JAXBContext.newInstance("ie.gmit.sw.model");
		
		ObjectFactory objFactory = new ObjectFactory();
		Address a1 = new Address();
		Customer c1 = new Customer();
		Vehicle v1 = new Vehicle();
		
		List<RentalOrder> list = new ArrayList<RentalOrder>();



		try {
			Date date = new Date(System.nanoTime());
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();

			String sql1 = "SELECT *  FROM rentalorder natural join vehicle natural join customer natural join address;";  

			ResultSet rs = stmt.executeQuery(sql1);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				
				RentalOrder r1 = new RentalOrder();

				
				r1.setOrderDate(rs.getDate("orderDate"));
				r1.setPickUpDate(rs.getDate("pickUpDate"));
				r1.setDropOffDate(rs.getDate("dropOffDate"));
				

				
				r1.setRentalSID(rs.getString("rental_SID"));
				a1.setAddressSID(rs.getString("address_SID"));
				v1.setVehicleSID(rs.getString("vehicle_SID"));
				c1.setCustomerSID(rs.getString("customer_SID"));
				v1.setVehiclePlate(rs.getString("vehiclePlate"));
				v1.setAvailableIndicator(rs.getInt("availableIndicator"));
				v1.setModel(rs.getString("model"));
				c1.setFirstName(rs.getString("firstName"));
				c1.setLastName(rs.getString("lastName"));
				a1.setStreet(rs.getString("street"));
				a1.setCity(rs.getString("city"));
				a1.setCounty(rs.getString("county"));
				
				c1.setAddressSID(a1);
				r1.setVehicleSID(v1);
				r1.setCustomerSID(c1);

				list.add(r1);
				
				
			}



System.out.println(list.toString());

			rs.close();
		} catch (Exception e) {
			
		}

//		// Marshal the PurchaseOrder into XML
//		System.out.println("\n\n######### XML Format #########");
//		Marshaller m1 = jc.createMarshaller();
//		m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m1.marshal(r1, new FileWriter("order.xml"));
//		m1.marshal(r1, System.out);
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
System.out.println(list.size());
		return list;
	}






}