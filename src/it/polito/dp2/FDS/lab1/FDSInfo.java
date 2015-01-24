package it.polito.dp2.FDS.lab1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import it.polito.dp2.FDS.Aircraft;
import it.polito.dp2.FDS.FlightInstanceReader;
import it.polito.dp2.FDS.FlightInstanceStatus;
import it.polito.dp2.FDS.FlightMonitor;
import it.polito.dp2.FDS.FlightMonitorException;
import it.polito.dp2.FDS.FlightMonitorFactory;
import it.polito.dp2.FDS.FlightReader;
import it.polito.dp2.FDS.MalformedArgumentException;
import it.polito.dp2.FDS.PassengerReader;

public class FDSInfo {

	private FlightMonitor monitor;
	DateFormat dateFormat;
	
	public FDSInfo() throws FlightMonitorException {
		FlightMonitorFactory factory = FlightMonitorFactory.newInstance();
		monitor = factory.newFlightMonitor();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy z");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FDSInfo f;
		
		try {
			f = new FDSInfo();
			f.printAll();
		} catch (FlightMonitorException e) {
			System.err.println("Could not instantiate flight monitor object");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void printAll() {
		printAircrafts();
		printFlights();
		printFlightInstances();
		printPassengerLists();
	}

	private void printPassengerLists() {
		System.out.println("");
		System.out.println("Passenger Lists");
		System.out.println("");
		
		List<FlightInstanceReader> l;
		String shortheader = "Name";
		String fullheader = shortheader+"\t\t\tSeat\tBoarded";
		
		try {
			l = monitor.getFlightInstances(null, null, null);
			for (FlightInstanceReader f:l) {
				GregorianCalendar date = f.getDate();
				dateFormat.setTimeZone(date.getTimeZone());
				Set<PassengerReader> set = f.getPassengerReaders(null);
				if(set.size()==0) {
					System.out.println("No passengers for flight "+f.getFlight().getNumber()+ " of " + dateFormat.format(date.getTime()));
				}
				else {
					System.out.println("");
					System.out.println("Passenger List for flight "+f.getFlight().getNumber()+ " of " + dateFormat.format(date.getTime()));
					System.out.println("");
					if (f.getStatus()== FlightInstanceStatus.BOOKING){
						printHeader(shortheader);
						for (PassengerReader p:set)
							System.out.println(p.getName());
					}					
					else {
						printHeader(fullheader);
						for (PassengerReader p:set) {
							String seat = p.getSeat();
							if (seat==null)
								System.out.println(p.getName()+"\t-"+"\t-");
							else
								if (p.boarded())
									System.out.println(p.getName()+'\t'+seat+"\tyes");
								else
									System.out.println(p.getName()+'\t'+seat+"\tno");
						}
					}
				}		
			}
			
		} catch (MalformedArgumentException e) {
			// this exception will never be thrown because getFlightInstances is called with null arguments
			System.err.println("Unexpected exception");
			e.printStackTrace();
		}
	}

	private void printFlightInstances() {
		System.out.println("");
		System.out.println("List of flight instances:");
		System.out.println("");

		/* Print the header of the table */
		String header = new String("Flight\tDate\t\tStatus\t\tDelay\tGate");
		printHeader(header);
		List<FlightInstanceReader> l;
		try {
			l = monitor.getFlightInstances(null, null, null);
			for (FlightInstanceReader f:l) {
				// get flight info in a StringBuffer
				StringBuffer b = new StringBuffer(f.getFlight().getNumber());
				
				// add date
				b.append('\t');
				GregorianCalendar date = f.getDate();
				dateFormat.setTimeZone(date.getTimeZone());
				b.append(dateFormat.format(date.getTime()));
				
				// add status
				String status = f.getStatus().toString();
				b.append('\t');
				b.append(status);

				// add delay and gate
				b.append('\t');
				b.append(f.getDelay());
				b.append('\t');
				String gate = f.getDepartureGate();
				if (gate==null)
					b.append('-');
				else
					b.append(gate);
				
				// print
				System.out.println(b);			
			}
			
		} catch (MalformedArgumentException e) {
			// this exception will never be thrown because getFlightInstances is called with null arguments
			System.err.println("Unexpected exception");
			e.printStackTrace();
		}
	}

	private void printFlights() {
		System.out.println("");
		System.out.println("List of flights:");
		System.out.println("");

		/* Print the header of the table */
		String header = new String("Flight\t From\t To\t Time");
		printHeader(header);
		
		try {
			List<FlightReader> l = monitor.getFlights(null, null, null);
			for (FlightReader f:l) {
				printFlight(f);
			}
		} catch (MalformedArgumentException e) {
			// this exception will never be thrown because getFlights is called with null arguments
			System.err.println("Unexpected exception");
			e.printStackTrace();
		}
		
	}

	private void printFlight(FlightReader f) {
		System.out.println(printFlighttoString(f));
	}
	
	private String printFlighttoString(FlightReader f) {
		StringBuffer b = new StringBuffer();
		GregorianCalendar g = new GregorianCalendar();
		g.set(GregorianCalendar.HOUR_OF_DAY, f.getDepartureTime().getHour());
		g.set(GregorianCalendar.MINUTE, f.getDepartureTime().getMinute());	
		b.append(f.getNumber()+"\t"+f.getDepartureAirport()+"\t"+f.getDestinationAirport()+"\t");
		b.append(String.format("%1$2tH", g));
		b.append(':');
		b.append(String.format("%1$2tM", g));
		return b.toString();
	}

	private void printHeader(String header) {
		StringBuffer line = new StringBuffer(75);
		
		for (int i = 0; i < 76; ++i) {
			line.append('-');
		}
		
		System.out.println(header);
		System.out.println(line);
	}

	private void printAircrafts() {
		System.out.println("");
		System.out.println("List of known aircrafts:");
		System.out.println("");

		/* Print the header of the table */
		String header = new String("Model\t Number of seats");
		printHeader(header);

		Set<Aircraft> s = monitor.getAircrafts();
		for (Aircraft aircraft : s) {
			System.out.println(aircraft.model + "\t" + aircraft.seats.size());
		}

		System.out.println();
		
		header = new String("Lists of Seats");
		System.out.println("");
		System.out.println(header);
		System.out.println("");
		
		for (Aircraft aircraft : s) {
			printHeader("Seats for aircraft "+aircraft.model);
			Iterator<String> it = aircraft.seats.iterator();
			for (int i=1; it.hasNext(); i++) {
				System.out.print(it.next()+" ");
				if (i%10==0||!it.hasNext())
					System.out.println();
			}
			System.out.println();
		}
	}

}
