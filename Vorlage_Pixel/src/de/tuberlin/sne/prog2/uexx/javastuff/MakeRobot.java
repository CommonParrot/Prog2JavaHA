package de.tuberlin.sne.prog2.uexx.javastuff;

public class MakeRobot implements Runnable {

	public Tisch t;

	public MakeRobot(Tisch x) {

		this.t = x;

	}

	@Override
	public void run() {

		while (true) {

			t.use();
			System.out.println("Ich habe fertig");
			Thread.yield();
		}

	}

}
