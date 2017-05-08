package de.tuberlin.snet.prog2.ue03.monitoring;

import java.io.File;

/**
 * Thread zur Beobachtung einer Datei
 * Meldung bei Bearbeitung dieser Datei
 * @author Fabian
 *
 */
public class BeobachterRunnable implements Runnable {

	private File file;
	
	/**
	 * Konstruktor des Threads
	 * @param file Datei die überwacht werden soll
	 */
	public BeobachterRunnable(File file) {
		this.file = file;
	}

	public File getFile() {
		return this.file;
	}

	public void run() {
		long lastModified = this.file.lastModified();
		long modified;
		while (!Thread.currentThread().isInterrupted()) {
			modified = this.file.lastModified();
			if (modified != lastModified) {
				System.out.println("Die Datei " + this.file.getName() + " wurde bearbeitet!");
				lastModified = file.lastModified();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread " + Thread.currentThread().getName() + " was interrupted!");
				Thread.currentThread().interrupt();
			}
		}
	}

}
