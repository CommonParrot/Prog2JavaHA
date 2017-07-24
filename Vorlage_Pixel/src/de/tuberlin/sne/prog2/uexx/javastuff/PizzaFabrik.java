package de.tuberlin.sne.prog2.uexx.javastuff;

public class PizzaFabrik {
	
	static void main(String[] args){
		
		Tisch ar = new Tisch();
		
		Thread roll = new Thread(new RollRobot(ar));
		Thread make = new Thread(new MakeRobot(ar));
		
		roll.start();
		make.start();
		
	}
	
	
}
