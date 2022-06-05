package util;

import java.util.ArrayList;
import java.util.Random;

import simulation.Simulation;

public class Hole {
	//public static int NUMBER_OF_HOLES_CREATED = 5;
	
	
	public static void createHoles() {
		Random rand = new Random();
		ArrayList<Integer> positionsOfHoles = new ArrayList<>();
		for(int i = 0; i < Simulation.NUMBER_OF_HOLES_CREATED; i++) {
			positionsOfHoles.add(Simulation.PATH.get(rand.nextInt(Simulation.PATH.size())));
		}
		
		
	}
	
	public static void deleteHoles() {
		
	}
}
