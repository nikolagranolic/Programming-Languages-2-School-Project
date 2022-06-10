package util;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.FlyingInterface;
import model.BasicFigure;
import model.PlayableFigure;
import model.SuperFastFigure;
import simulation.Simulation;

public class Hole {
	public static ArrayList<Integer> positionsOfHoles = new ArrayList<>();
	public static ArrayList<FlyingInterface> flyingFigures = new ArrayList<>();
	public static ArrayList<Integer> figuresIndices = new ArrayList<>();
	
	public static void createHoles() {
		if(Simulation.gamePaused) {
			synchronized(Simulation.mainThread) {
				try {
					Simulation.mainThread.wait();
				} catch (InterruptedException e) {
					Simulation.logger.info(e.fillInStackTrace().toString());				}
			}
		}
		
		Random rand = new Random();
		int x, first, second;
		String[] coords;
		
		synchronized(Simulation.MAP) {
			for(int i = 0; i < Simulation.NUMBER_OF_HOLES_CREATED;) {
				x = rand.nextInt(Simulation.PATH.size());
				coords = Simulation.coordinates(Simulation.PATH.get(x)).split(",");
				first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
				
				if(Simulation.MAP[first][second] instanceof FlyingInterface) {
					flyingFigures.add((FlyingInterface)Simulation.MAP[first][second]);
					figuresIndices.add(i);
				}
				
				if(!(Simulation.MAP[first][second] instanceof Hole)) {
					if(Simulation.MAP[first][second] instanceof BasicFigure || Simulation.MAP[first][second] instanceof SuperFastFigure) {
						((PlayableFigure)Simulation.MAP[first][second]).drop();
					}
					
					positionsOfHoles.add(Simulation.PATH.get(x));
					Simulation.MAP[first][second] = new Hole();
					i++;
				}
			}
		}
	}
	
	public static void deleteHoles() {
		String[] coords;
		int first, second;
		
		synchronized(Simulation.MAP) {
			for(int i = 0; i < positionsOfHoles.size(); i++) {
				coords = Simulation.coordinates(positionsOfHoles.get(i)).split(",");
				first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
				
				Simulation.MAP[first][second] = "";
				
				if(figuresIndices.contains(i)) {
					Simulation.MAP[first][second] = flyingFigures.remove(0);
				}
			}
			
			positionsOfHoles.clear();
			flyingFigures.clear();
			figuresIndices.clear();
		}
	}
}
