package de.tuberlin.snet.prog2.ue03.watchtxt;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Thread that checks if a file was changed
 * @author Jakob Feldmann
 *
 */
public class ReadTextThread extends Thread{
	
	//text file
	File text;
	//time stamp
	long modtime;
	
	
	ReadTextThread(File file){
	
		text = file;
		modtime = text.lastModified();
		
	}
	
	
	
	public void setModtime(long modtime) {
		this.modtime = modtime;
	}
	
	/**
	 * takes current epoch modtime and formats it to a normal time stamp
	 * @return time stamp as string
	 */
	public String formatModtime(){
		Date date = new Date(this.modtime);
		DateFormat datum = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String datumsString = datum.format(date);
		return datumsString;
	}

	/**
	 * checks if file was modified
	 */
	@Override
	public void run(){
		while(!this.isInterrupted()){
			
			if(text.lastModified() != this.modtime){
				setModtime(text.lastModified());
				System.out.println("Dieser Text wurde um: " + formatModtime() + " zuletzt verändert");
			}
			
			try {
				ReadTextThread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread unterbrochen");
			}
			
			
		}
		
	}

}
