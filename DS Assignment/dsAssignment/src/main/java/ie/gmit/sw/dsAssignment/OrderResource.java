package ie.gmit.sw.dsAssignment;

import ie.gmit.sw.ds.Address;
import ie.gmit.sw.ds.Country;
import ie.gmit.sw.ds.Items;
import ie.gmit.sw.ds.ObjectFactory;
import ie.gmit.sw.ds.PurchaseOrder;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;

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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

@Singleton
@Path("/orders")
public class OrderResource {

	/* In this example class marshalling functionality is handled by Jersey.
	 * Notice how the PurchaseOrder type may be used directly as a return type and input parameter for each RESTful method.
	 * This means that there is no need to write JAXB/Eclipselink Moxy code manually
	 */
	
	ArrayList<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	@Path("/{value}")
	/* Note how this method has been annotated to produce both XML and JSON
	 * The response format which is sent will depend on the Accept: header field in the HTTP request from the client 
	 */
	public PurchaseOrder getOrder(@PathParam("value") String value) {
		PurchaseOrder requested = null;
		for(PurchaseOrder p : orders) {
			if(p.getOrderNumber().equals(value)) {
				requested = p;
			}
		}
		
		return requested;		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response createOrder(@PathParam("value") String value, PurchaseOrder toCreate) {
		PurchaseOrder requested = null;
		for(PurchaseOrder p : orders) {
			if(p.getOrderNumber().equals(value)) {
				requested = p;
			}
		}
		
		if(requested != null) {
			String msg = "The order number " + value + " already exists";
			return Response.status(409).entity(msg).build();
		}
		else {
			orders.add(toCreate);
			String msg = "Resource created!";
			return Response.status(201).entity(msg).build(); // return 201 for resource created
		}
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response updateOrder(@PathParam("value") String value, PurchaseOrder updated) {
		PurchaseOrder requested = null;
		for(PurchaseOrder p : orders) {
			if(p.getOrderNumber().equals(value)) {
				requested = p;
			}
		}
		
		if(requested != null) {
			String msg = "The order number " + value + " was updated!";
			orders.remove(requested);
			orders.add(updated);
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
	public Response deleteOrder(@PathParam("value") String value) {
		PurchaseOrder requested = null;
		for(PurchaseOrder p : orders) {
			if(p.getOrderNumber().equals(value)) {
				requested = p;
			}
		}
		
		if(requested != null) {
			String msg = "The order number " + value + " was deleted!";
			orders.remove(requested);
			return Response.status(200).entity(msg).build();
		}
		else {
			String msg = "The order number " + value + " does not exist";;
			return Response.status(404).entity(msg).build(); // return 404 for resource not found
		}
	}
	
	public OrderResource() {		
		init();
	}
		
	private void init() {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date(System.nanoTime()));
		XMLGregorianCalendar date = null;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		ObjectFactory objFactory = new ObjectFactory();

		PurchaseOrder po = objFactory.createPurchaseOrder();
		po.setOrderNumber("55522-BABA");
		po.setOrderDate(date);

		Address shipTo = new Address();
		shipTo.setName("John Doe");
		shipTo.setStreet("123 Castle Road");
		shipTo.setCity("Oranmore");
		shipTo.setCounty("Galway");
		shipTo.setCountry(Country.IRELAND);
		po.setShipTo(shipTo);
		po.setBillTo(shipTo);

		Items items = new Items();
		po.setItems(items);
		List<Items.Item> col = items.getItem();
		Items.Item i1 = new Items.Item();
		i1.setPartNumber("123ABC");
		i1.setProductName("11ft Trout Fly Road");
		i1.setQuantity(1);
		i1.setPrice(new BigDecimal("250.00"));
		i1.setShipDate(date);
		col.add(i1);

		Items.Item i2 = new Items.Item();
		i2.setPartNumber("177AAA");
		i2.setProductName("14ft Salmon Fly Road");
		i2.setQuantity(1);
		i2.setPrice(new BigDecimal("450.00"));
		i2.setShipDate(date);
		col.add(i2);
		
		orders.add(po);
	}
}