package de.tuberlin.snet.prog2.ue04.lock;

import java.util.Random;

/**
 * Represents a NewsTeam gathering sports related news.
 *
 */
public class SportsTeam extends NewsTeam {
	/**
	 * Constructs a new SportsTeam that will post to a given Ticker
	 * 
	 * @param t
	 *            the ticker to post to
	 */
	public SportsTeam(NewsTicker t) {
		super(t);
	}

	String[] teams = { "DuckTown Duckies", "Smurphies", "Telepathic Teletubbies", "Crazy Cranberrys",
			"Alltime Animals" };

	@Override
	protected String getLatestNews() {
		Random r = new Random();
		String team1 = teams[r.nextInt(teams.length)];
		String team2 = teams[r.nextInt(teams.length)];
		int score1 = r.nextInt(5);
		int score2 = r.nextInt(5);

		String message = "Latest results: " + team1 + " vs. " + team2 + " (" + score1 + ":" + score2 + ")";

		return message;
	}

}
