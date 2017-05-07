package de.tuberlin.snet.prog2.ue03.watchtxt;

import java.io.File;

/**
 * 
 * Program execution instructions
 * 
 * @author Jakob Feldmann
 *
 */
public class ReadTxt {
	public static void main(String[] args) {
		
		new File("Read.txt");
		
		ReadTextThread rtt = new ReadTextThread();
		
		rtt.start();
		
	}
}
