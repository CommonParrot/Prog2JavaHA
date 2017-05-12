package de.tuberlin.snet.prog2.ue04.lock;

/**
 * The NewsRoom is the central component of the exercise. All initialization
 * happens here.
 *
 */
public class NewsRoom {

	/**
	 * @param args
	 *            contains the command line arguments (not used)
	 */
	public static void main(String[] args) {
		NewsTicker ticker = new NewsTicker();

		SportsTeam st = new SportsTeam(ticker);
		ZooTeam zt = new ZooTeam(ticker);
		StockExchangeTeam set = new StockExchangeTeam(ticker);

		st.start();
		zt.start();
		set.start();

		try {
			st.join();
			zt.join();
			set.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
