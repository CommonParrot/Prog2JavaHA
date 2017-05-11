package de.tuberlin.snet.prog2.ue04.treatment;

public class Behandlungsraum {
	
	static int patientenZahl = 100;
	static Behandlungsraum raum = new Behandlungsraum();

	private int counter = 0;

	public boolean behandeln(Patient patient) {
		if (counter == 0) {
			counter++;
			System.out.println("PatientNr.:" + patient.patientNr + " wird behandelt");
			try {
				Thread.sleep(1000 + (int) (Math.random() * 4000));
			} catch (InterruptedException ie) {}
			System.out.println("PatientNr.:" + patient.patientNr + " geht");
			counter--;
			return true;
		}
		else
			return false;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < patientenZahl; i++) {
			new Patient(i, raum).start();
		}
	}

}
