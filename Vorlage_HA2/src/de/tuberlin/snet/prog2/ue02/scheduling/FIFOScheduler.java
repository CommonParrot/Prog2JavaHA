package de.tuberlin.snet.prog2.ue02.scheduling;

import java.util.LinkedList;

/**
 * Scheduler that works through threads, that are gathered in a linked list in
 * FIFO order
 * 
 * @author Jakob Feldmann
 */
class FIFOScheduler extends Thread implements Scheduler {

	// list for storing threads
	LinkedList<Thread> schedule = new LinkedList<>();

	@Override
	public void addThread(Thread thread) {
		schedule.add(thread);
	}

	/**
	 * Takes the first thread out of the list, starts it and takes the next one
	 * if the previous has finished
	 */
	@Override
	public void run() {
		while (!schedule.isEmpty()) {
			try {

				Thread d = schedule.poll();
				d.start();
				d.join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
