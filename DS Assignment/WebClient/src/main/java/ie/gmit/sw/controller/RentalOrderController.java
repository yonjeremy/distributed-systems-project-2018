package ie.gmit.sw.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.model.Customer;
import ie.gmit.sw.model.RentalOrder;
import ie.gmit.sw.model.Vehicle;
import ie.gmit.sw.services.RentalOrderService;

@Controller
public class RentalOrderController {

	@Autowired
	private RentalOrderService rentalOrderService;

	@RequestMapping(value = "/getOrders", method=RequestMethod.GET)
	public String getRentalOrders(Model m){

		List<RentalOrder> rentalOrder = rentalOrderService.getRentalOrders();
		m.addAttribute("rentalOrder", rentalOrder);

		return "displayRentalOrders";
	}


	// this is the get request which directs to the add booking page
	@RequestMapping(value = "/addOrders", method = RequestMethod.GET)
	public String getAddBooking(@ModelAttribute("RentalOrder") RentalOrder booking,Model m, HttpServletRequest h) {
		List<RentalOrder> rentalOrder = rentalOrderService.getRentalOrders();
		
		Map<String,String> custList = new HashMap<String,String>();
		Map<String,String> carList = new HashMap<String,String>();
		
		
		for (RentalOrder r : rentalOrder) {
			
			custList.put( r.getCustomerSID().getCustomerSID(), "Customer Name: " + " " +  r.getCustomerSID().getFirstName() +r.getCustomerSID().getLastName() );
			carList.put( r.getVehicleSID().getVehicleSID(), "Vehicle Model: " + " " +  r.getVehicleSID().getModel());

		}

		

		m.addAttribute("custList", custList);
		m.addAttribute("carList", carList);
		
		return "addOrders";
	}
	
	@RequestMapping(value = "/addOrders", method = RequestMethod.POST)
	public String postBooking(@Valid @ModelAttribute("RentalOrder") RentalOrder rentalOrder, BindingResult result) {	
		rentalOrderService.createRentalOrder(rentalOrder);
		
		return "redirect:getOrders";
	}
	
	// this is the get request which directs to the add booking page
	@RequestMapping(value = "/deleteOrders", method = RequestMethod.GET)
	public String getDeleteBooking(@ModelAttribute("RentalOrder") RentalOrder booking,Model m, HttpServletRequest h) {
		List<RentalOrder> rentalOrder = rentalOrderService.getRentalOrders();
		
		Map<String,String> rentalList = new HashMap<String,String>();
		
		
		for (RentalOrder r : rentalOrder) {
			
			rentalList.put( r.getRentalSID(), "Order Name: " + " " +  r.getRentalSID());

		}

		

		m.addAttribute("rentalList", rentalList);
	
		
		return "deleteOrders";
	}

	
	@RequestMapping(value = "/deleteOrders", method = RequestMethod.POST)
	public String postDelete(@Valid @ModelAttribute("RentalOrder") RentalOrder rentalOrder, BindingResult result) {	
		
		rentalOrderService.deleteRentalOrder(rentalOrder.getRentalSID());
		
		
		
		
		
		return "redirect:getOrders";
	}
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.GET)
	public String updateBooking(@ModelAttribute("RentalOrder") RentalOrder booking, Model m, HttpServletRequest h) {

		List<RentalOrder> rentalOrder = rentalOrderService.getRentalOrders();
		Map<String,String> custList = new HashMap<String,String>();
		Map<String,String> carList = new HashMap<String,String>();
		Map<String,String> bookingsList = new HashMap<String,String>();

		for (RentalOrder r : rentalOrder) {
			bookingsList.put(r.getRentalSID(),"Booking ID: " + " " +r.getRentalSID());

			custList.put(r.getCustomerSID().getCustomerSID(),
					"Customer: " + " " + r.getCustomerSID().getFirstName() + r.getCustomerSID().getLastName());
			carList.put(r.getVehicleSID().getVehicleSID(), "Vehicle: " + " " + r.getVehicleSID().getModel());

		}
		m.addAttribute("custList", custList);
		m.addAttribute("carList", carList);
		m.addAttribute("bookingsList", bookingsList);
		
		return "updateBooking";
	}

	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public String putBooking( @ModelAttribute("RentalOrder") RentalOrder booking, BindingResult result) {

		rentalOrderService.updateBooking(booking);
		return "redirect:getOrders";
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public String handleError() {

		return "ErrorPageForOrder";
	}
	
}