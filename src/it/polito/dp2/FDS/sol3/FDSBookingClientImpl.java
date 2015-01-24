package it.polito.dp2.FDS.sol3;

import it.polito.dp2.FDS.lab3.FDSBookingClient;
import it.polito.dp2.FDS.lab3.MissingDataException;
import it.polito.dp2.FDS.lab3.OperationFailException;
import it.polito.dp2.FDS.lab3.gen.*;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

public class FDSBookingClientImpl implements FDSBookingClient{

	private URL url;
	private FDSBooking proxy;
	private String flightNumber;
	private FlightDateType flightDate;

	public FDSBookingClientImpl()
	{
		//Default constructor
		flightDate = new FlightDateType();
		try {
			url = new URL (System.getProperty("it.polito.dp2.FDS.sol3.URL"));

			if (url == null)
				throw new MalformedURLException("The url System.property is not set");

			FDSBookingService service = new FDSBookingService(url);
			proxy = service.getFDSBookingServiceSOAPPort();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFlightNumber(String number)
	{
		flightNumber = number;
	}

	public void setDepartureDate(GregorianCalendar gdate)
	{
		flightDate.setDay(gdate.get(Calendar.DAY_OF_MONTH));
		flightDate.setMonth(gdate.get(Calendar.MONTH)+1);
		flightDate.setYear(BigInteger.valueOf(gdate.get(Calendar.YEAR)));
	}

	public void setServiceURL(URL url)
	{
		BindingProvider bindprov = (BindingProvider) this.proxy;
		bindprov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url.toString());
	}

	public Set<String> book(Set<String> passengerNames, boolean partialBookingAllowed) throws MissingDataException, OperationFailException
	{
		// Check if all the data are valid
		if ((passengerNames.isEmpty()) || (passengerNames == null))
			throw new MissingDataException("The set of passengers are not valid");

		Set<String> returnSet = new HashSet<String>();

		try{
			List<String> passengerList = new ArrayList<String>();
			passengerList.clear();
			passengerList.addAll(passengerNames);

			Holder<List<String>> passList = new Holder<List<String>>(passengerList);
			Holder<Boolean> success = new Holder<Boolean>();

			proxy.book(flightNumber, flightDate, partialBookingAllowed, passList, success);

			if ( success.value == true )
			{
				returnSet.clear();
				returnSet.addAll(passList.value);
				return returnSet;

			}else
				throw new OperationFailException();

		}catch (InvalidFlightInstanceFault_Exception e)
		{
			e.printStackTrace();
			throw new OperationFailException("Invalid Flight Instance Data (flight number and date)");
		}
	}

	public Set<String> getPassengers() throws MissingDataException, OperationFailException
	{
		// Check if flight number and flight date are set
		if ( ( flightNumber == null ) || ( flightNumber.isEmpty() ) )
			throw new MissingDataException("Flight number has not been set");
		if  ( flightDate == null )
			throw new MissingDataException("Flight date has not been set");

		Set<String> passengerSet = new HashSet<String>();
		List<String> passengerList = new ArrayList<String>();
		try
		{
			passengerSet.clear();
			passengerList.clear();

			passengerList = proxy.getPassengerList(flightNumber, flightDate);

			if (passengerList != null)
				passengerSet.addAll(passengerList);
			else
				throw new OperationFailException("The requested passenger list is null");

		}catch (InvalidFlightInstanceFault_Exception e)
		{
			throw new OperationFailException();
		}

		return passengerSet;
	}

}
