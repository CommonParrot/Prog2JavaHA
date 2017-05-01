package de.tuberlin.snet.prog2.ue02.monitors;

import java.util.Random;

/**
 * 
 * Class that can treat patient threads
 * 
 * @author Jakob Feldmann
 *
 */
public class Behandlungsraum {

	// max number of patients
	int maxp;

	// number of patients that are currently being treated
	int patientz = 0;

	/**
	 * Constructor that takes the max number of patients at a time
	 * 
	 * @param anz
	 *            max number of patients
	 */
	public Behandlungsraum(int anz) {
		maxp = anz;
	}

	/**
	 * 
	 * @param patientz
	 */
	public void setPatientz(int patientz) {
		this.patientz = patientz;
	}

	/**
	 * Treatment for patient threads
	 * 
	 * @param x
	 *            the patient
	 */
	public void behandlung(Patient x) {
		synchronized (this) {
			try {

				++patientz;
				System.out.println(x + " in Behandlung");
				Patient.sleep(behandlungszeit(1000, 10000));
				System.out.println(x + " gute Besserung!");
				x.setBehandelt(true);
				--patientz;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Takes min max and selects a random integer between
	 * 
	 * @param min
	 *            minimal time in ms
	 * @param max
	 *            maximal time
	 * @return returns a random integer
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}

	/**
	 * Determines a treatment time for patients
	 * 
	 * @param anf
	 *            minimal treatment time
	 * @param end
	 *            maximal treatment time
	 * @return returns a treatment time as a integer in ms
	 */
	private int behandlungszeit(int anf, int end) {
		int rng = randInt(anf, end);
		return rng;
	}

}
