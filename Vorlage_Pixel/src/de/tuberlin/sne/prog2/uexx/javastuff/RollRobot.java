package de.tuberlin.sne.prog2.uexx.javastuff;

public class RollRobot implements Runnable {

	public Tisch t;

	public RollRobot(Tisch x) {

		this.t = x;

	}

	@Override
	public void run() {

		while (true) {

			t.use();
			System.out.println("Ich hab was hingelegt");
			Thread.yield();

		}

	}

}
