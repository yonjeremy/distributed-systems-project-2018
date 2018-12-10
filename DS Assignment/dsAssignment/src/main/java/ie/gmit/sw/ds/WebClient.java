package ie.gmit.sw.ds;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import ie.gmit.sw.model.Customer;
import ie.gmit.sw.model.RentalOrder;
import ie.gmit.sw.model.Vehicle;

public class WebClient  {

	public static void main(String[] args) {
		
		// create the target 
		Client client = ClientBuilder.newClient();
		// base url
		String REST_URI  = "http://localhost:8080/dsAssignment/webapi/bookings/";
		 
		System.out.println("Initialising Client..");
		System.out.println("getting all bookings ..");
		// GET REQUEST
		List<RentalOrder> ros =  client.target(REST_URI).path("read/").request(MediaType.APPLICATION_XML).get(new GenericType<List<RentalOrder>>(){});
		for(RentalOrder f :  ros) {
			System.out.println("booking id: " + f.getRentalSID());
			
		}
		
//		// creating a test obj to post
//		RentalOrder ro = new RentalOrder();
//		ro.setRentalSID("122");
//		ro.setVehicle(new Vehicle());
//		ro.setCustomer(new Customer());
//
//		//POST 
//		 System.out.println("Posting to DB..");
//		   Response response = client
//		      .target(REST_URI)
//		      .path("create/")
//		      .request(MediaType.APPLICATION_XML)
//		      .post(Entity.entity(ro, MediaType.APPLICATION_XML));
//		  System.out.println( "Response : " + response);
//		  
//			// GET REQUEST to check post was added
//		  System.out.println("get request:");
//		  	ros =  client.target(REST_URI).path("read/").request(MediaType.APPLICATION_XML).get(new GenericType<List<RentalOrder>>(){});
//			for(RentalOrder f :  ros) {
//				System.out.println("booking id: " + f.getRentalSID());
//				
//			}
//		  
//		  
//		// DELETE
//		 System.out.println("Deleting from DB..");
//		   response = client
//		      .target(REST_URI)
//		      .path("delete/122")
//		      .request(MediaType.APPLICATION_XML)
//		      .delete();
//		  System.out.println( "Response : " + response);
//	  
//		
//			ros =  client.target(REST_URI).path("read/").request(MediaType.APPLICATION_XML).get(new GenericType<List<RentalOrder>>(){});
//		    
//			for(RentalOrder f :  ros) {
//				System.out.println(f.getRentalSID());
//				
//			}
//			
//	
			
	}

}
