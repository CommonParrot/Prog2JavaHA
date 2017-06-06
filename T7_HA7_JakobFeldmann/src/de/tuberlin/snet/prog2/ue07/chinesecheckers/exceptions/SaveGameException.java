package de.tuberlin.snet.prog2.ue07.chinesecheckers.exceptions;

public class SaveGameException extends Exception {
	private static final long serialVersionUID = 3841337408309667463L;
	
	public final Exception cause;
	/**
	 * throws save game exception
	 * @param cause
	 */
	public SaveGameException(Exception cause) {
		this.cause = cause;
		//System.out.println("ParserConfigurationException: " + cause.getMessage());
	}
}
