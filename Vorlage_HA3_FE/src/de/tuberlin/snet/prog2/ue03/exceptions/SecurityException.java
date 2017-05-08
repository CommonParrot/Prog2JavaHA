package de.tuberlin.snet.prog2.ue03.exceptions;

/**
 * SecurityException, die bei mangelnder Sicherheit eines Passwortes geworfen werden soll
 * @author Fabian
 *
 */
public class SecurityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityException() {
		super("Security Problem detected!");
	}
	
	public SecurityException(String s){
		super(s);
	}

}
