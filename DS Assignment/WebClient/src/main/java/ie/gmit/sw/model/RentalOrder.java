//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.12.09 at 04:19:20 PM GMT 
//


package ie.gmit.sw.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RentalOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RentalOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pickUpDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dropOffDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="vehicle_SID" type="{http://sw.gmit.ie/model/}Vehicle"/&gt;
 *         &lt;element name="customer_SID" type="{http://sw.gmit.ie/model/}Customer"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="rental_SID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="orderDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RentalOrder", namespace = "http://sw.gmit.ie/model/", propOrder = {
    "pickUpDate",
    "dropOffDate",
    "vehicleSID",
    "customerSID"
})
public class RentalOrder implements Serializable{

    @XmlElement(namespace = "http://sw.gmit.ie/model/", required = true)
    @XmlSchemaType(name = "date")
    protected Date pickUpDate;
    @XmlElement(namespace = "http://sw.gmit.ie/model/", required = true)
    @XmlSchemaType(name = "date")
    protected Date dropOffDate;
    @XmlElement(name = "vehicle_SID", namespace = "http://sw.gmit.ie/model/", required = true)
    protected Vehicle vehicleSID;
    @XmlElement(name = "customer_SID", namespace = "http://sw.gmit.ie/model/", required = true)
    protected Customer customerSID;
    @XmlAttribute(name = "rental_SID", required = true)
    protected String rentalSID;
    @XmlAttribute(name = "orderDate", required = true)
    @XmlSchemaType(name = "date")
    protected Date orderDate;

    
    public RentalOrder(Date pickUpDate, Date dropOffDate, Vehicle vehicleSID,
			Customer customerSID, String rentalSID, Date orderDate) {
		super();
		this.pickUpDate = pickUpDate;
		this.dropOffDate = dropOffDate;
		this.vehicleSID = vehicleSID;
		this.customerSID = customerSID;
		this.rentalSID = rentalSID;
		this.orderDate = orderDate;
	}

	public RentalOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Gets the value of the pickUpDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getPickUpDate() {
        return pickUpDate;
    }

    /**
     * Sets the value of the pickUpDate property.
     * 
     * @param date
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickUpDate(Date date) {
        this.pickUpDate = date;
    }

    /**
     * Gets the value of the dropOffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDropOffDate() {
        return dropOffDate;
    }

    /**
     * Sets the value of the dropOffDate property.
     * 
     * @param date
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDropOffDate(Date date) {
        this.dropOffDate = date;
    }

    /**
     * Gets the value of the vehicleSID property.
     * 
     * @return
     *     possible object is
     *     {@link Vehicle }
     *     
     */
    public Vehicle getVehicleSID() {
        return vehicleSID;
    }

    /**
     * Sets the value of the vehicleSID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vehicle }
     *     
     */
    public void setVehicleSID(Vehicle value) {
        this.vehicleSID = value;
    }

    /**
     * Gets the value of the customerSID property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomerSID() {
        return customerSID;
    }

    /**
     * Sets the value of the customerSID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomerSID(Customer value) {
        this.customerSID = value;
    }

    /**
     * Gets the value of the rentalSID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalSID() {
        return rentalSID;
    }

    /**
     * Sets the value of the rentalSID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalSID(String value) {
        this.rentalSID = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param date
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderDate(Date date) {
        this.orderDate = date;
    }

}
