package it.polito.dp2.FDS.lab3.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polito.dp2.FDS.lab3.FDSBookingClient;
import it.polito.dp2.FDS.lab3.FDSUnbookingClient;
import it.polito.dp2.FDS.lab3.MissingDataException;
import it.polito.dp2.FDS.lab3.OperationFailException;
import it.polito.dp2.FDS.lab3.UnbookClientImpl;
import it.polito.dp2.FDS.lab3.server.FlightDate;
import it.polito.dp2.FDS.lab3.server.FlightInstance;

public class FDSTests {
	
	private FDSBookingClient clientUnderTest;
	private FDSUnbookingClient unbookingClient;
	
	private GregorianCalendar date;
	private String flightNo;
	
	private String clientUnderTestClass = "it.polito.dp2.FDS.sol3.FDSBookingClientImpl";
	
	private final int MAX_PASSENGERS = 200;
	
	private List<String> test_passengers;
	
    @BeforeClass
    public static void setUpBeforeClass() {
    	// NOP
    }
    
    @Before
    public void setUp() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = FDSTests.class.getClassLoader();
		}
		try{
			Class<?> t = null;
			t = (loader != null) ? loader.loadClass(clientUnderTestClass) : 
				 Class.forName(clientUnderTestClass);
	        clientUnderTest = (FDSBookingClient) t.newInstance();
	        unbookingClient = new UnbookClientImpl();
	        assertNotNull("The solution client cannot be instantiated", clientUnderTest);
	        assertNotNull(unbookingClient);
	        
	        FlightInstance fInstance = unbookingClient.getFlightInstances().iterator().next();
			
			date = flightDateToGregorianCalendar(fInstance.getDate());
			flightNo = fInstance.getNumber();
			
			clientUnderTest.setDepartureDate(date);
			clientUnderTest.setFlightNumber(flightNo);
			
			test_passengers = new ArrayList<String>();
	    	test_passengers.add("Christian Blanc");
	    	test_passengers.add("John Whyte");
	    	test_passengers.add("Steve Red");
			unbookingClient.unBook(flightNo, date, test_passengers);
			
			List<String> passengers_to_unbook = new ArrayList<String>();
			int i=0;
			while(i < MAX_PASSENGERS)
			{
				passengers_to_unbook.add(test_passengers.get(2)+i);
				i++;
			}
			unbookingClient.unBook(flightNo, date, passengers_to_unbook);
			
			//System.out.println("FlightNo = " + flightNo);
			//System.out.println("Date = " + date.get(GregorianCalendar.DAY_OF_MONTH)+"/"+date.get(GregorianCalendar.MONTH)+"/"+date.get(GregorianCalendar.YEAR));
		} 
		catch (ClassNotFoundException ex)
		{
			System.err.println("[ERROR] The class it.polito.dp2.FDS.sol3.FDSBookingClientImpl cannot be found!");
			System.err.println("Check your solution and the name of your class!");
			fail();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
    }

    @Test
    public final void testBooking() {
    	try
    	{
    		int startValue = clientUnderTest.getPassengers().size();
    		Set<String> passengers = new HashSet<String>();
    		passengers.add(test_passengers.get(0));
    		clientUnderTest.book(passengers, true);
    	
    		assertEquals("Booking operation failed!", startValue+1, clientUnderTest.getPassengers().size());
    		
    		passengers = new HashSet<String>();
    		passengers.add(test_passengers.get(1));
    		clientUnderTest.book(passengers, true);
    		
    		assertEquals("Second booking operation failed!", startValue+2, clientUnderTest.getPassengers().size());
    	} 
    	catch(Exception ex) 
    	{ 
    		ex.printStackTrace();
    		fail();
    	}
    }
    
    @Test
    public final void testPartialBooking() {
    	try
    	{
    		int startValue = clientUnderTest.getPassengers().size();
	    	Set<String> passengers = new HashSet<String>();
	    	int i=0;
			while(i < MAX_PASSENGERS)
			{
				passengers.add(test_passengers.get(2)+i);
				i++;
			}
			clientUnderTest.book(passengers, false);	// Partial booking disallowed -> this call should not book anything
			
			assertEquals("Partial booking operation failed!", startValue, clientUnderTest.getPassengers().size());
    	}
    	catch (OperationFailException ex)
    	{
    		System.out.println("Test partial booking OK!");
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		fail();
    	}
    }
    
    private GregorianCalendar flightDateToGregorianCalendar(FlightDate date)
    {
    	GregorianCalendar cal = new GregorianCalendar(date.getYear().intValue(), date.getMonth()-1, date.getDay());
    	return cal;
    }
	
}
