package ie.gmit.sw.services;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.spi.factory.ResponseImpl;

import ie.gmit.sw.model.*;

@Service
public class RentalOrderService {

	private final String REST_URI = "http://localhost:8080/dsAssignment/webapi/bookings/";
	private Client client;
	private WebResource webResource;
	
	public RentalOrderService() {
		// create the target
		System.out.println("Initialising Client..");
		
		this.client = Client.create();
		this.webResource = this.client.resource(REST_URI);
		this.webResource.type(MediaType.APPLICATION_XML);

	}
	
	// GET REQUEST/ READ OPERATION
	public List<RentalOrder> getRentalOrders() {
		List<RentalOrder> output = null;

		try {

			this.webResource = this.client.resource(REST_URI).path("/read");

			webResource.type(MediaType.APPLICATION_XML);

			output = webResource.get(new GenericType<List<RentalOrder>>() {});

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return output;
	}

	// POST / ADD OPERATION
	public String createRentalOrder(RentalOrder rentalOrder) {

		System.out.println("my object i created : " + rentalOrder.toString());
		//POST
		webResource = this.client.resource(REST_URI).path("/create");
		webResource.post(rentalOrder);

		  return "passed";
	}
	
	public String deleteRentalOrder(String rentalOrder) {
		
//		RentalOrder ro = new RentalOrder();
//		ro.setRentalSID("testernew");
//		ro.setVehicle(new Vehicle());
//		ro.getVehicle().setVehicleSID("33333");
//		ro.setCustomer(new Customer());
//		ro.getCustomer().setCustomerSID("22222");
//		java.util.Date today = new java.util.Date();
//		ro.setDropOffDate(new Date(today.getTime()));
//		
		System.out.println("my object i created : " + rentalOrder.toString());
		//POST
		webResource = this.client.resource(REST_URI).path("/delete/" + rentalOrder);
		
		webResource.delete(rentalOrder);

		  return "deleted";
	}
	public void updateBooking(@Valid RentalOrder booking) {
		webResource = this.client.resource(REST_URI).path("/update");
		webResource.put(booking);
		
	}
	
//	
//	// GET REQUEST/ READ OPERATION
//	public List<Customer> getCustomers() {
//		List<Customer> output = null;
//
//		try {
//
//			this.webResource = this.client.resource(REST_URI).path("/getCustomer");
//
//			webResource.type(MediaType.APPLICATION_XML);
//
//			output = webResource.get(new GenericType<List<Customer>>() {});
//
//			System.out.println("Output from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//		return output;
//	}
//	
//	
//	// GET REQUEST/ READ OPERATION
//	public List<Vehicle> getVehicles() {
//		List<Vehicle> output = null;
//
//		try {
//
//			this.webResource = this.client.resource(REST_URI).path("/getVehicle");
//
//			webResource.type(MediaType.APPLICATION_XML);
//
//			output = webResource.get(new GenericType<List<Vehicle>>() {});
//
//			System.out.println("Output from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//		return output;
//	}

}



//
////POST 
// System.out.println("Posting to DB..");
//   ResponseImpl response = client
//      .target(REST_URI)
//      .path("create/")
//      .request(MediaType.APPLICATION_XML)
//      .post(Entity.entity(ro, MediaType.APPLICATION_XML));
//  System.out.println( "Response : " + response);
//  return response.toString();
