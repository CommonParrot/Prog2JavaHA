package de.tuberlin.snet.prog2.ue02.debugging;

@SuppressWarnings("serial")
/**
 * Exception for weak Passwords
 * 
 * @author bartosch
 *
 */
public class WeakPasswordException extends Exception {
	public WeakPasswordException() {
		super("Your Password does not match the security critera.");
	}
}
