package ie.gmit.sw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.gmit.sw.model.RentalOrder;
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

//	//handles roll back exceptions 
//	@ExceptionHandler(value = RollbackException.class)
//	public String handleErr() {
//		
//		return "Error";
//		
//	}
	//handles null pointers 
	@ExceptionHandler(value = NullPointerException.class)
	public String handleError() {

		return "ErrorPageForOrder";
	}
	
}