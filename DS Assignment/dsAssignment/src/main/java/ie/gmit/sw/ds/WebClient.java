package ie.gmit.sw.ds;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;

import ie.gmit.sw.model.RentalOrder;

public class WebClient  {

	public static void main(String[] args) {
		
		// create the target 
		Client client = ClientBuilder.newClient();

		String REST_URI  = "http://localhost:8080/Project/webapi/bookings/";
	 
	 
	    RentalOrder ro = client.target(REST_URI).path(String.valueOf(1)).request(MediaType.APPLICATION_XML).get(RentalOrder.class);
	   
	  //  String hi = client.target(REST_URI).request(MediaType.TEXT_PLAIN).get(String.class);
		   
	    System.out.println(ro);
	}

}
