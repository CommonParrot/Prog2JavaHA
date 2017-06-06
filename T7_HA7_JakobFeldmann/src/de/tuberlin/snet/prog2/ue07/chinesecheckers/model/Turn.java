package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.ArrayList;
import java.util.List;

public class Turn {
	
	//Player
	ArtificialPlayer pone;
	//Figur
	Piece figur;
	//Zielposition
	Position goal;
	//Liste der überspringbaren Figuren
	List<Piece> jumplist = new ArrayList<>();
	//Scor für einen Move
	float score;
	
	/**
	 * Gibt Zielposition in Abhängigkeit von jumplist und dem Inhalt von goal aus
	 * @return Zielposition
	 */
	public Position getGoal() {

		if (jumplist.isEmpty()){

			if (goal == null) {
				return figur.position;
			}

			else {
				return goal;
			}
				
		}

		else {
			return GameState.getLastPosition(getPiece(), getJumplist());
		}

	}

	public void setGoal(Position goal) {
		this.goal = goal;
	}

	public ArtificialPlayer getPone() {
		return pone;
	}

	public Piece getPiece() {
		return figur;
	}

	public List<Piece> getJumplist() {
		return jumplist;
	}

	public float getScore() {
		return pone.getTurnBenefit(figur.position, getGoal());
	}

	Turn(ArtificialPlayer player, Piece moving) {
		this.pone = player;
		this.figur = moving;
	}

	Turn(ArtificialPlayer player, Piece moving, Position desti) {
		this.pone = player;
		this.figur = moving;
		this.goal = desti;
	}

	@Override
	public Turn clone() {
		Turn flatcopy = new Turn(this.pone, this.figur, this.goal);
		for (Piece a : this.jumplist) {
			flatcopy.jumplist.add(a);
		}
		return flatcopy;
	}
	
	/**
	 * Fügt eine Figur in die Liste ein
	 * @param jumpable
	 */
	public void addJumpPiece(Piece jumpable) {
		jumplist.add(jumpable);
	}

}
