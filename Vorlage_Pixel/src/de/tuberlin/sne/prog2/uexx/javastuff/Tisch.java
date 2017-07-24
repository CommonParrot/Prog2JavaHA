package de.tuberlin.sne.prog2.uexx.javastuff;
import java.util.concurrent.Semaphore;

public class Tisch {
	
	Semaphore alda = new Semaphore(1);
	
	public void use(){
		while(alda.tryAcquire() == false){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			alda.acquire();
			System.out.println("Ich werde benutzt");
			Thread.sleep(2000);
			alda.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
