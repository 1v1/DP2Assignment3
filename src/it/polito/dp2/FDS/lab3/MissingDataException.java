/**
 * 
 */
package it.polito.dp2.FDS.lab3;

/**
 *	An exception thrown when an operation cannot be concluded because
 *  some of the data it requires are missing or not available
 */
public class MissingDataException extends Exception {

	/**
	 */
	private static final long serialVersionUID = 6643687975678972948L;

	/**
	 */
	public MissingDataException() {
	}

	/**
	 */
	public MissingDataException(String arg0) {
		super(arg0);
	}

	/**
	 */
	public MissingDataException(Throwable arg0) {
		super(arg0);
	}

	/**
	 */
	public MissingDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 */
	public MissingDataException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
