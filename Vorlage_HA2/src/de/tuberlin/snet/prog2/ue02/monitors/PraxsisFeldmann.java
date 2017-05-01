package de.tuberlin.snet.prog2.ue02.monitors;

/**
 * 
 * Contains main method for the treatment process
 * 
 * @author Jakob Feldmann
 *
 */
public class PraxsisFeldmann {

	public static void main(String[] args) {

		Behandlungsraum a1 = new Behandlungsraum(1);

		Patient a = new Patient(a1);
		Patient b = new Patient(a1);
		Patient c = new Patient(a1);

		a.start();
		b.start();
		c.start();

	}

}
