/**
 * 
 */
package it.polito.dp2.FDS.lab3;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * This interface gives access to the operations
 * provided by the FDSBookingService, i.e. booking
 * of flight instances.
 */
public interface FDSBookingClient {
	/**
	 * Sets the flight number to be used for booking operations.
	 * There is no default flight number.
	 * 
	 * @param number The number of the flight to be set.
	 */
	public void setFlightNumber(String number);
	
	/**
	 * Sets the departure date of the flight instance to be used for booking operations.
	 * There is no default date.
	 * 
	 * @param gdate A GregorianCalendar whose year, month and day represent the date (in the departure airport time zone); hours,minutes,seconds are meaningless.
	 */
	public void setDepartureDate(GregorianCalendar gdate);
	
	/**
	 * Sets the URL of the service to be used for the booking operations.
	 * The default URL is the one specified in the WSDL of the service.
	 * 
	 * @param url The URL of the actual service port to be used for the booking operations.
	 */
	public void setServiceURL(URL url);
	
	/**
	 * Books the flight instance previously specified through the setFlightNumber and setDepartureDate
	 * methods for the passengers whose names are specified as the first argument.
	 * This operation is completed by contacting a remote web service.
	 * 
	 * @param passengerNames a set including the names of the passengers for whom a booking is requested
	 * @param partialBookingAllowed if true, in case the flight instance cannot be booked for all the requested passengers, a partial booking is allowed.
	 * @return a set including all the passengers for whom the specified flight instance has been booked.
	 * @throws MissingDataException if the booking operation was not started because some of the required data (i.e. flight number and date, and at least one passenger) was not available.
	 * @throws OperationFailException if the booking operation was started but failed.
	 */
	public Set<String> book(Set<String> passengerNames, boolean partialBookingAllowed) throws MissingDataException, OperationFailException;
		
	/**
	 * Gets the list of passengers for the flight instance previously specified through the setFlightNumber and setDepartureDate methods.
	 * This operation is completed by contacting a remote web service.
	 * 
	 * @return a set including the names of the passengers who have booked the specified flight instance.
	 * @throws MissingDataException if the operation was not started because some of the required data (i.e. flight number and date) was not available.
	 * @throws OperationFailException if get operation was started but failed.
	 */
	public Set<String> getPassengers() throws MissingDataException, OperationFailException;
}
