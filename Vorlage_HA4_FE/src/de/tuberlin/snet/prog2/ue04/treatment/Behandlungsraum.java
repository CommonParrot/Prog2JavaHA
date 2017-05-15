package de.tuberlin.snet.prog2.ue04.treatment;

import java.util.concurrent.Semaphore;

public class Behandlungsraum {

	static int patientenZahl = 100;
	static Behandlungsraum raum = new Behandlungsraum();
	static Semaphore semaphore = new Semaphore(2);

	public void behandeln(Patient patient) {
		try {
			Behandlungsraum.semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("PatientNr.:" + patient.patientNr + " wird behandelt");
		try {
			Thread.sleep(1000 + (int) (Math.random() * 4000));
		} catch (InterruptedException ie) {
		}
		System.out.println("PatientNr.:" + patient.patientNr + " geht");
		Behandlungsraum.semaphore.release();
	}

	public static void main(String[] args) {
		for (int i = 0; i < patientenZahl; i++) {
			new Patient(i, raum).start();
		}
	}

}
