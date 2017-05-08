package de.tuberlin.snet.prog2.ue03.checksum;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Checks if the checksum changed
 * 
 * @author bartosch
 *
 */
public class CheckChecksumThread extends Thread {
	MessageDigest digest;
	File file;
	String initialchecksum;
	String checksum;
	int counter;

	/**
	 * Constructor, sets the params
	 * 
	 * @param initialchecksum
	 *            the initial checksum which is to be checked for changes
	 * @param digest
	 *            the Messagedigest which was used for the initial checksum
	 * @param file
	 *            The file which is to be checked
	 */
	CheckChecksumThread(String initialchecksum, MessageDigest digest, File file) {
		this.file = file;
		this.digest = digest;
		this.initialchecksum = initialchecksum;
		this.counter = 0;
	}

	@Override
	/**
	 * Checks if the File was changed every 500 ms until the Thread gets
	 * interrupted
	 */
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				checksum = checkChecksum.getFileChecksum(this.digest, this.file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(counter + "T: Die Checksum der Datei " + this.file + " ist " + checksum);
			if (initialchecksum.equals(checksum)) {
				System.out.println("T: Die Datei wurde somit nicht geaendert");
			} else {
				System.out.println(
						"T: Die Datei wurde geaendert, da die Checksum nicht mehr " + initialchecksum + " ist.");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted while Sleeping");
				Thread.currentThread().interrupt();
			}
			counter++;
		}
	}
}
