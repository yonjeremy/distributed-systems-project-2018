package ie.gmit.sw.ds;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.RMI.CarHireDB;
import ie.gmit.sw.model.Customer;
import ie.gmit.sw.model.RentalOrder;
import ie.gmit.sw.model.Vehicle;

/* 
   This class handles requests from clients using REST.
   Takes in requests and class corresponding operation on RMI object
   representing the DB
*/

@Singleton
@Path("/bookings")
public class CarHireWebService {
	
	// Ask the registry running on localhost and listening in port 1099 for access to instance of
    // the MessageService object that is bound to the RMI registry .
			
	/**
	 * 
	 */
	private CarHireDB  cs;
	private ArrayList<RentalOrder> orders = new ArrayList<RentalOrder>();
	
	public CarHireWebService() throws Exception {
		super();
		try {
			this.cs = (CarHireDB) Naming.lookup("rmi://localhost:1099/carHireDB");
		//	requested  = cs.getBooking(bookingID);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	// web service receiving get req --> delegate the db stuff to rmi object
	@GET
	@Produces({MediaType.APPLICATION_XML})
	/* Note how this method has been annotated to produce both XML and JSON
	 * The response format which is sent will depend on the Accept: header field in the HTTP request from the client 
	 */
	@Path("/read")
	public List<RentalOrder> getOrder() throws RemoteException {
		List<RentalOrder> requested = null;
		
		try {
			requested  = cs.getAllBookings();
			System.out.println(requested.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return requested;		
	}
	
	// web service receiving get req --> delegate the db stuff to rmi object
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	/* Note how this method has been annotated to produce both XML and JSON
	 * The response format which is sent will depend on the Accept: header field in the HTTP request from the client 
	 */
	@Path("/read")
	public List<RentalOrder> getOrderJson() throws RemoteException {
		List<RentalOrder> requested = null;
		
		try {
			requested  = cs.getAllBookings();
			System.out.println(requested.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return requested;		
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/create")
	public Response createRentalOrder(RentalOrder toCreate) throws SQLException {
		boolean success = false;
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		toCreate.setOrderDate(date);
		// try add the booking to the DB using RM
		try {
			success = cs.addBooking(toCreate);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		// return status to client
		if(success) {
			String msg = "Resource created!";
			return Response.status(201).entity(msg).build(); // return 201 for resource created

		}
		else {
			String msg = "The booking  already exists";
			return Response.status(409).entity(msg).build();
				}
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/update")
	public Response updateOrder(@PathParam("value") String value, RentalOrder updated) {
		boolean success = false;
		
		// try add the booking to the DB using RMI
		try {
			success = cs.updateBooking(updated);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(success) {
			String msg = "The order number " + value + " was updated!";
			return Response.status(200).entity(msg).build();
		}
		else {
			String msg = "The order number " + value + " does not exist";;
			return Response.status(404).entity(msg).build(); // return 404 for resource not found
		}
	}
	
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/delete/{value}")
	public Response deleteOrder(@PathParam("value") String bookingID) throws SQLException {
		boolean success = false;
//		 try add the booking to the DB using RMI
		try {
			success = cs.deleteBooking(bookingID);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		success = true;
		if(success) {
			String msg = "The order number " + bookingID + " was deleted!";
			return Response.status(200).entity(msg).build();
		}
		else {
			String msg = "The order number " + bookingID + " does not exist";;
			return Response.status(404).entity(msg).build(); // return 404 for resource not found
		}
	}
	

}
