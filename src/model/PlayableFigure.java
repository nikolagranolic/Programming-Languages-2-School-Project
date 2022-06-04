package model;

import enums.*;
import simulation.Simulation;
import util.Hole;

import java.util.ArrayList;
	
public abstract class PlayableFigure extends Figure {
	protected Color color;
	protected int movementQuotient;
	protected int numOfCollectedDiamonds = 0;
	protected boolean reachedFinish;
	protected int positionOnPath;
	protected ArrayList<Integer> figurePath = new ArrayList<>(); // dodati to
		
		
	public PlayableFigure(Color color) {
		this.color = color;
		this.reachedFinish = false;
		this.positionOnPath = -1;
	}
		
	public void move(int n) { // pripaziti na sinhronizaciju!!! // popraviti!
		if(positionOnPath == -1) { // ako tek izlazim iz "kucice"
			positionOnPath = 0; // sta ako je prvo polje zauzeto a drugo rupa, da li upadam u rupu?
			String[] coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(","); // dodati fug
			int first = Integer.parseInt(coords[0]), second = Integer.parseInt(coords[1]);
			while(Simulation.MAP[first][second] instanceof PlayableFigure) {
				positionOnPath++;
				coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(",");
				first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
			}
		}
		else {
			int fieldsToMove = (n + numOfCollectedDiamonds) * movementQuotient;
			String[] coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath + fieldsToMove)).split(",");
			int first = Integer.parseInt(coords[0]), second = Integer.parseInt(coords[1]);
			if(Simulation.MAP[first][second] instanceof Hole) {
				if(this instanceof FlyingFigure) {
					
				}
			}
		}
		
	}
	
	public ArrayList<Integer> getFigurePath() {
		return figurePath;
	}
	
	public Color getFigureColor() {
		return color;
	}
	private void moveToNextFreeField() { // popraviti!
		int i = 1;
		String[] coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath + i++)).split(","); 
		int first = Integer.parseInt(coords[0]), second = Integer.parseInt(coords[1]);
		while(Simulation.MAP[first][second] instanceof PlayableFigure) {
			coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath + i++)).split(","); // popraviti!!!!!
			first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
		}
	}
	
	protected void collectDiamond() {
		this.numOfCollectedDiamonds++;
		Diamond.count--;
	}
}