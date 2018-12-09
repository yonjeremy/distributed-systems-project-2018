package ie.gmit.sw.ds;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import ie.gmit.sw.model.RentalOrder;

/* 
   This class handles requests from clients using REST.
   Takes in requests and class corresponding operation on RMI object
   representing the DB
*/

@Singleton
@Path("/bookings")
public class CarHireWebService  {
	
	//Ask the registry running on localhost and listening in port 1099 for the instannce of
    // the MessageService object that is bound to the RMI registry with the name howdayService.
			
	private CarHireDB  cs;

	private ArrayList<RentalOrder> orders = new ArrayList<RentalOrder>();
	
	public CarHireWebService() throws Exception {
		super();
		cs = (CarHireDB) Naming.lookup("rmi://localhost:1099/carHireDB");
	}
//	
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        return "Got it!";
//    }
	
	// web service receiving get req --> delegate the db stuff to rmi object
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	@Path("/{value}")
	/* Note how this method has been annotated to produce both XML and JSON
	 * The response format which is sent will depend on the Accept: header field in the HTTP request from the client 
	 */
	public RentalOrder getOrder(@PathParam("value") String bookingID) {
		RentalOrder requested = null;
		try {
			requested  = cs.getBooking(bookingID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return requested;		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response createRentalOrder(@PathParam("value") String value, RentalOrder toCreate) {
		boolean success = false;
		
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
			String msg = "The booking  " + value + " already exists";
			return Response.status(409).entity(msg).build();
				}
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{value}")
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
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{value}")
	public Response deleteOrder(@PathParam("value") String value, RentalOrder toDelete) {
		boolean success = false;
		// try add the booking to the DB using RMI
		try {
			success = cs.deleteBooking(toDelete);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(success) {
			String msg = "The order number " + value + " was deleted!";
			return Response.status(200).entity(msg).build();
		}
		else {
			String msg = "The order number " + value + " does not exist";;
			return Response.status(404).entity(msg).build(); // return 404 for resource not found
		}
	}
	

}
