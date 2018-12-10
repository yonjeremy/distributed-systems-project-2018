package ie.gmit.sw.services;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.MediaType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.model.RentalOrder;

@Service
public class RentalOrderService {


	//these are the models 
	private RentalOrder rentalOrder;

	//get the current date 
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date date = new Date();

	// this gets all the orders from mysql
	public List<RentalOrder> getRentalOrders(){
		
		List<RentalOrder> output = null;

		try {
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/dsAssignment/webapi/bookings/read/");
		

		
		webResource.type(MediaType.APPLICATION_XML);


		output = webResource.get(new GenericType<List<RentalOrder>>(){});



		System.out.println("Output from Server .... \n");
		System.out.println(output);

	  } catch (Exception e) {

		e.printStackTrace();

	  }
		
		return output;
	}
	
//	public OrderInfo save(OrderInfo order) {
//		//this sets the date as the current date in the database
//		order.setDate(dateFormat.format(date));	
//		//search the ship and shipping company from the ids 
//		ship =SI.findOne(order.getShip().getSid());
//		shipC = SC.findOne(order.getShippingCompany().getScid());
//		
//		//set the ship scid value to the shipc scid gotten from select  
//		ship.setShippingCompany(shipC);
//		//update the balance of the shipping company to the 
//		//new balance = old balance - cost of the ship 
//		shipC.setBalance(shipC.getBalance().subtract(ship.getCost()));
//		
//		//save the changes made 
//		return orderInfoInterface.save(order);
//	}
}