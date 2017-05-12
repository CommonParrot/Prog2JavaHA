package de.tuberlin.snet.prog2.ue04.philosophers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents a philosopher. He is doing the following (in this
 * order):
 * <ul>
 * <li>thinking for 0-1 seconds</li>
 * <li>picks up his left chopstick (or waits until it is free)</li>
 * <li>picks up his right chopstick (or waits until it is free)</li>
 * <li>eats for 0-1 seconds</li>
 * <li>puts down his right chopstick</li>
 * <li>puts down his left chopstick</li>
 * </ul>
 * and repeats this.
 * 
 * @author prog2-team
 */
public class Philosopher implements Runnable {

	Chopstick leftChopstick;
	Chopstick rightChopstick;
	String name;
	Random random = new Random();
	private static AtomicInteger gotleftstick = new AtomicInteger();

	public Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.name = name;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}

	@Override
	public void run() {
		try {
			while (true) {

				leftChopstick.pickUp();
				gotleftstick.incrementAndGet();
				System.out.println(this + " picks up his left chopstick");

				if (gotleftstick.get() == 5) {
					
					System.out.println("\n" + this + " - \"Alter das ist mein Stäbchen!\" " + "\n");
					if (gotleftstick.get() < 5) {
						pickEatThink();
					} else {
						wohlüberlegtesWeglegenDesStäbchens();
					}
					
				}
				else {
					pickEatThink();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void think() throws InterruptedException {
		System.out.println(this + " denkt \"Momentchen mal...\" ");
		Thread.sleep(random.nextInt(100));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " *schmatzt*");
		Thread.sleep(random.nextInt(100));
	}

	private void wohlüberlegtesWeglegenDesStäbchens() {
		
		this.leftChopstick.putDown();
		gotleftstick.decrementAndGet();
		System.out.println(this + " puts down his left chopstick");
		
		try {
			think();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void pickEatThink() {
		try {
			rightChopstick.pickUp();
			System.out.println(this + " picks up his right chopstick");
			eat();
			rightChopstick.putDown();
			System.out.println(this + " puts down his right chopstick");
			leftChopstick.putDown();
			gotleftstick.decrementAndGet();
			System.out.println(this + " puts down his left chopstick");
			think();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public String toString() {
		return name;
	}

}
