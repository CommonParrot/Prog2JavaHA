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
	 * Returns a random move turn. First element of the list is
	 * the piece that should be moved, the second element is the
	 * target position. 
	 * 
	 * @return list with piece and target position for a random move
	 */
	List<Position> getSimpleMoveTurn() {
		List<Position> myMove = new ArrayList<>();
		
		Collection<Piece> moveables = player.getPieces().stream()
				.filter(e -> (Board.getAllMoveTurns(player.state, new Position(e)).size() > 0))
				.collect(Collectors.toList());
		Position selectedPiece = new Position(ArtificialPlayer.getRandomElement(moveables));
		myMove.add(selectedPiece);
		
		Collection<Position> moves = Board.getAllMoveTurns(player.state, selectedPiece);
		Position toPos = moves.stream().skip((int) (moves.size() * Math.random())).findFirst().get();
		myMove.add(toPos);
		
		return myMove;
	}
	
	/**
	 * Returns the best move turn of all possible move turns.
	 * @return move turn with the best progress
	 */
	List<Position> getBestMoveTurn() {
		List<Position> bestMove = new ArrayList<>();
		
		Collection<List<Position>> allMoveTurns = getAllPossibleMoveTurns();
		
		bestMove = allMoveTurns.stream()
				.sorted((t2, t1) -> Float.compare(player.getTurnBenefit(t1.get(0), t1.get(1)), player.getTurnBenefit(t2.get(0), t2.get(1))))
				.findFirst()
				.get();
		
		return bestMove;
	}
	
	/**
	 * Searches and returns all possible move turns of this player.
	 * @return collection of moves consisting of positions
	 */
	private Collection<List<Position>> getAllPossibleMoveTurns() {
		Collection<Piece> moveables = player.getPieces().stream()
				.filter(e -> (Board.getAllMoveTurns(player.state, new Position(e)).size() > 0))
				.collect(Collectors.toList());
		
		Collection<List<Position>> allMoveTurns = new ArrayList<>();
		for (Piece piece : moveables) {
			for (Position movePosition : Board.getAllMoveTurns(player.state, new Position(piece))) {
				List<Position> move = new ArrayList<>();
				move.add(new Position(piece));
				move.add(movePosition);
				allMoveTurns.add(move);
			}
		}
		return allMoveTurns;
	}
}
