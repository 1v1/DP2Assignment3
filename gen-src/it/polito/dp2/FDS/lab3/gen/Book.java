
package it.polito.dp2.FDS.lab3.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flightNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flightDate" type="{http://pad.polito.it/dp2/FDSBooking}flightDateType"/>
 *         &lt;element name="partialBookingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="passenger" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "flightNumber",
    "flightDate",
    "partialBookingAllowed",
    "passenger"
})
@XmlRootElement(name = "book")
public class Book {

    @XmlElement(required = true)
    protected String flightNumber;
    @XmlElement(required = true)
    protected FlightDateType flightDate;
    protected boolean partialBookingAllowed;
    @XmlElement(required = true)
    protected List<String> passenger;

    /**
     * Gets the value of the flightNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the value of the flightNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
    }

    /**
     * Gets the value of the flightDate property.
     * 
     * @return
     *     possible object is
     *     {@link FlightDateType }
     *     
     */
    public FlightDateType getFlightDate() {
        return flightDate;
    }

    /**
     * Sets the value of the flightDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightDateType }
     *     
     */
    public void setFlightDate(FlightDateType value) {
        this.flightDate = value;
    }

    /**
     * Gets the value of the partialBookingAllowed property.
     * 
     */
    public boolean isPartialBookingAllowed() {
        return partialBookingAllowed;
    }

    /**
     * Sets the value of the partialBookingAllowed property.
     * 
     */
    public void setPartialBookingAllowed(boolean value) {
        this.partialBookingAllowed = value;
    }

    /**
     * Gets the value of the passenger property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passenger property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassenger().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPassenger() {
        if (passenger == null) {
            passenger = new ArrayList<String>();
        }
        return this.passenger;
    }

}
