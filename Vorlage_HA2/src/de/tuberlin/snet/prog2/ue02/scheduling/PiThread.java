package de.tuberlin.snet.prog2.ue02.scheduling;

/**
 * Can be used as a "long working thread". Calculates Pi up to the given
 * derivative.
 * 
 * Created by prog2-team
 */
class PiThread extends Thread {

	private String name;
	private double approx;

	/**
	 * Creates a thread with a given name and number of iterations for
	 * calculating Pi.
	 * 
	 * @param name
	 *            name of this thread
	 * @param approx
	 *            number of iterations for the calculation
	 */
	public PiThread(String name, double approx) {
		super();
		this.name = name;
		this.approx = approx;
	}

	@Override
	public void run() {
		double pi = calculatePi(approx);
		System.out.println(this.name + "," + "# of Iterations: " + approx + " , π=" + pi);
	}

	/**
	 * Calculates Pi with the given number of iterations.
	 * 
	 * @param approx
	 *            number of iterations
	 * @return
	 */
	private double calculatePi(double approx) {
		double pi = 1.0;
		int s = 1;
		for (double j = 3.0; j < approx; j = j + 2) {
			if (s % 2 == 0) {
				pi = pi + (1 / j);
			} else {
				pi = pi - (1 / j);
			}
			s = s + 1;
		}
		pi = 4 * pi;
		return pi;
	}

}
