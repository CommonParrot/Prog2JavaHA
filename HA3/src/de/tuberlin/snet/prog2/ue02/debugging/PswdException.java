package de.tuberlin.snet.prog2.ue02.debugging;

/**
 * New exception for checking of passwords
 * @author Jakob
 *
 */
public class PswdException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -817947056403253641L;

	PswdException(){
		
		super("Your Password does not match the security critera.");
		
	}

}
