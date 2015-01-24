
package it.polito.dp2.FDS.lab3.gen;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.FDS.lab3.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.FDS.lab3.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidFlightInstanceFault }
     * 
     */
    public InvalidFlightInstanceFault createInvalidFlightInstanceFault() {
        return new InvalidFlightInstanceFault();
    }

    /**
     * Create an instance of {@link GetPassengerList }
     * 
     */
    public GetPassengerList createGetPassengerList() {
        return new GetPassengerList();
    }

    /**
     * Create an instance of {@link FlightDateType }
     * 
     */
    public FlightDateType createFlightDateType() {
        return new FlightDateType();
    }

    /**
     * Create an instance of {@link BookResponse }
     * 
     */
    public BookResponse createBookResponse() {
        return new BookResponse();
    }

    /**
     * Create an instance of {@link GetPassengerListResponse }
     * 
     */
    public GetPassengerListResponse createGetPassengerListResponse() {
        return new GetPassengerListResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

}
