package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MoveTurns {

	ArtificialPlayer player;

	public MoveTurns(ArtificialPlayer player) {
		this.player = player;
	}

	/**
	 * Returns a random move turn. First element of the list is the piece that
	 * should be moved, the second element is the target position.
	 * 
	 * @return list with piece and target position for a random move
	 */
	Turn getSimpleMoveTurn() {

		Collection<Piece> moveables = player.getPieces().stream()
				.filter(e -> (Board.getAllMoveTurns(player.state, new Position(e)).size() > 0))
				.collect(Collectors.toList());

		Piece selectedPiece = ArtificialPlayer.getRandomElement(moveables);

		Collection<Position> moves = Board.getAllMoveTurns(player.state, selectedPiece.position);
		Position toPos = moves.stream().skip((int) (moves.size() * Math.random())).findFirst().get();

		Turn selectedTurn = new Turn(player, selectedPiece, toPos);

		return selectedTurn;

	}

	/**
	 * Returns the best move turn of all possible move turns.
	 * 
	 * @return move turn with the best progress
	 */
	Turn getBestMoveTurn() {

		Collection<Turn> allMoveTurns = getAllPossibleMoveTurns();

		Turn bestMove = allMoveTurns.stream()
				.sorted((t2, t1) -> Float.compare(player.getTurnBenefit(new Position(t1.getPiece()), t1.getGoal()),
						player.getTurnBenefit(new Position(t2.getPiece()), t2.getGoal())))
				.findFirst().get();

		return bestMove;

	}

	/**
	 * Searches and returns all possible move turns of this player.
	 * 
	 * @return collection of turns
	 */
	private Collection<Turn> getAllPossibleMoveTurns() {

		Collection<Piece> moveables = player.getPieces().stream()
				.filter(e -> (Board.getAllMoveTurns(player.state, new Position(e)).size() > 0))
				.collect(Collectors.toList());

		Collection<Turn> allMoveTurns = new ArrayList<>();

		for (Piece piece : moveables) {

			for (Position movePosition : Board.getAllMoveTurns(player.state, new Position(piece))) {
				allMoveTurns.add(new Turn(player, piece, movePosition));
			}

		}
		return allMoveTurns;
	}
}
