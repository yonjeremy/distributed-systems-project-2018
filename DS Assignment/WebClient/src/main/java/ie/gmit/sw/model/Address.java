//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.12.09 at 04:19:20 PM GMT 
//


package ie.gmit.sw.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Address complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Address"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="county" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="address_SID" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", namespace = "http://sw.gmit.ie/model/", propOrder = {
    "street",
    "city",
    "county"
})
public class Address implements Serializable{

    @XmlElement(namespace = "http://sw.gmit.ie/model/", required = true)
    protected String street;
    @XmlElement(namespace = "http://sw.gmit.ie/model/", required = true)
    protected String city;
    @XmlElement(namespace = "http://sw.gmit.ie/model/", required = true)
    protected String county;
    @XmlAttribute(name = "address_SID")
    protected String addressSID;

    public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

	public Address(String street, String city, String county, String addressSID) {
		super();
		this.street = street;
		this.city = city;
		this.county = county;
		this.addressSID = addressSID;
	}



	/**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the addressSID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressSID() {
        return addressSID;
    }

    /**
     * Sets the value of the addressSID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressSID(String value) {
        this.addressSID = value;
    }

}
