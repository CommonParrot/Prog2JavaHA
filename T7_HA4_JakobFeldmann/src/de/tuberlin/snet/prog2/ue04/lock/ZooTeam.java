package de.tuberlin.snet.prog2.ue04.lock;

import java.util.Random;

/**
 * Represents a NewsTeam gathering zoo related news. Cute animals, you know..
 *
 */
public class ZooTeam extends NewsTeam {

	String[] animals = { "tiger", "giraffe", "polar bear", "elephant" };

	/**
	 * Constructs a new ZooTeam that will post to a given Ticker
	 * 
	 * @param t
	 *            the ticker to post to
	 */
	public ZooTeam(NewsTicker t) {
		super(t);
	}

	@Override
	protected String getLatestNews() {
		Random r = new Random();
		String animal = animals[r.nextInt(animals.length)];

		String message = "Latest zoo news: " + "super cute " + animal + " baby was born today.";

		return message;
	}

}
