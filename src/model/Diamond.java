package model;

import simulation.Simulation;

public class Diamond {
	public static int MIN_NUM_OF_DIAMONDS = 2;
	public static int MAX_NUM_OF_DIAMONDS = Simulation.mapDimension;
	
	@Override
	public String toString() {
		return "<<>>";
	}
}
