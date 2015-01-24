/**
 * 
 */
package it.polito.dp2.FDS.lab3;

/**
 *
 */
public class OperationFailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8905071896013652788L;

	/**
	 */
	public OperationFailException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 */
	public OperationFailException(String arg0) {
		super(arg0);
	}

	/**
	 */
	public OperationFailException(Throwable arg0) {
		super(arg0);		// TODO Auto-generated constructor stub
	}

	/**
	 */
	public OperationFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 */
	public OperationFailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
