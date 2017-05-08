package de.tuberlin.snet.prog2.ue03.checksum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class checkChecksum {

	/**
	 * calculates the checksum of given file and returns it as String
	 * 
	 * @param digest
	 *            MesageDigest, which algorythm to use
	 * @param file
	 *            File which is to be checked
	 * @return Checksum as String
	 * @throws IOException
	 *             Standart Exception beim Lesen aus Stream/File
	 */
	static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);

		byte[] filebytes = new byte[1024];
		int bytesCount = 0;

		while ((bytesCount = fis.read(filebytes)) != -1) {
			digest.update(filebytes, 0, bytesCount);
		}
		;
		fis.close();

		byte[] digestbytes = digest.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digestbytes.length; i++) {
			sb.append(Integer.toString((digestbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/**
	 * Generates testfile and SHA-1 Digest, then gets the checksum of the
	 * testfile and runs the checkchecksum Thread and Runnable
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MessageDigest digest = null;
		String initialchecksum = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File Testfile = new File("Testfile");
		try {
			Testfile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			initialchecksum = checkChecksum.getFileChecksum(digest, Testfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread CheckChecksumThread = new Thread(new CheckChecksumThread(initialchecksum, digest, Testfile));
		Thread CheckChecksumRunnable = new Thread(new CheckChecksumRunnable(initialchecksum, digest, Testfile));
		CheckChecksumThread.start();
		CheckChecksumRunnable.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CheckChecksumRunnable.interrupt();
		CheckChecksumThread.interrupt();
	}
}
