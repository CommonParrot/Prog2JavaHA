package de.tuberlin.snet.prog2.ue02.debugging;

public class PswdException extends Exception{
	
	PswdException(){
		
		super("Your Password does not match the security critera.");
		
	}

}
