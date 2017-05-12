package de.tuberlin.snet.prog2.ue04.treatment;

/**
 * 
 * Contains main method for the treatment process
 * 
 * @author Jakob Feldmann
 *
 */
public class PraxisFeldmann {

	public static void main(String[] args) {

		Behandlungsraum a1 = new Behandlungsraum(2);

		for(int i=1; i<20; ++i){
			new Patient(a1).start();
		}
	}

}
