package de.tuberlin.snet.prog2.ue07.chinesecheckers.exceptions;

public class InvalidConfigurationException extends Exception {
	private static final long serialVersionUID = 726410391792547327L;
	
	/**
	 * Is thrown when an invalid configuration appears,
	 *  and call the super class
	 * @param message
	 */
	public InvalidConfigurationException(String message) {
		super(message);
	}
}
