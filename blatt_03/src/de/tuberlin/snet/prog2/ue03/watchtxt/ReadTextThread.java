package de.tuberlin.snet.prog2.ue03.watchtxt;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadTextThread extends Thread{
	
	File text;
	long modtime;
	
	ReadTextThread(File file){
	
		text = file;
		modtime = text.lastModified();
		
	}
	
	
	
	public void setModtime(long modtime) {
		this.modtime = modtime;
	}



	@Override
	public void run(){
		
		while(!this.isInterrupted()){
			
			if(text.lastModified() != this.modtime){
				
				setModtime(text.lastModified());
				
				Date date = new Date(this.modtime);
				DateFormat datum = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String datumsString = datum.format(date);
				System.out.println("Dieser Text wurde um: " + datumsString + " zuletzt verändert");
				
				
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
