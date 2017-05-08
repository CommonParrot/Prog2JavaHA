package de.tuberlin.snet.prog2.ue03.monitoring;

import java.io.File;

public class Monitor {

	public static void main(String[] args) {
		File monitorFile = new File("src/de/tuberlin/snet/prog2/ue03/monitoring/Monitored.txt");
		File monitorFile2 = new File("src/de/tuberlin/snet/prog2/ue03/monitoring/Monitored2.txt");
		BeobachterThread beobachter1 = new BeobachterThread(monitorFile);
		BeobachterRunnable beobachter2 = new BeobachterRunnable(monitorFile2);
		beobachter1.start();
		Thread runnable = new Thread(beobachter2);
		runnable.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		beobachter1.interrupt();
		runnable.interrupt();
	}

}
