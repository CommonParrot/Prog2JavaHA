package de.tuberlin.snet.prog2.ue04.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The NewsTeam is the abstract representation of one group at the news agency.
 *
 */
public abstract class NewsTeam extends Thread {
	private NewsTicker ticker;
	static ReentrantLock lock = new ReentrantLock();

	/**
	 * constructs a new NewsTeam and set the ticker attribute
	 * 
	 * @param t
	 *            is a reference to the NewsTicker object this team will post to
	 */
	public NewsTeam(NewsTicker t) {
		this.ticker = t;
	}

	public void run() {
		while (true) {
			long amount = (long) (new Random().nextDouble() * 20000);
			try {
				this.sleep(amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String message = getLatestNews();
			NewsTeam.lock.lock();
			ticker.displayMessage(message);
			NewsTeam.lock.unlock();
		}

	}

	/**
	 * The getLatestNews method has to be implemented by every NewTeam
	 * 
	 * @return a String containing the news message
	 */
	protected abstract String getLatestNews();
}
