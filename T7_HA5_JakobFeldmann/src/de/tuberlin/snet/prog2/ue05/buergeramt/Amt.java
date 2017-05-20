package de.tuberlin.snet.prog2.ue05.buergeramt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Represents an Office with a limited number of workplaces for handling a {@link Kunde} 
 * @author prog2-team
 *
 */
public class Amt {

	/**
	 * Represents the number of workplaces for handling a {@link Kunde} 
	 */
	static private int arbeitsplaetze = 5;
	
	/**
	 * Possible concerns that can be handled by the {@link Amt}
	 * @author prog2-team
	 *
	 */
	enum Anliegen {
		Ummeldung, Fuehrerschein, Personalausweis
	}

	/**
	 * Creates new workplaces for handling a {@link Amt} with some workplaces and starts the work. 
	 */
	public static void main(String[] args) throws InterruptedException{
		
		ExecutorService execution = Executors.newFixedThreadPool(arbeitsplaetze);
		
		for(int i = 0; i < 40; i++){
			execution.submit(new Kunde(i, Anliegen.values()[(int) (Math.random() * Anliegen.values().length)]));
		}
		
		Thread.sleep(8000);
		execution.shutdown();
		
	}
}
