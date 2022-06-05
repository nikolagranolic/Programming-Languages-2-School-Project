package model;

import enums.*;
import simulation.Simulation;

import java.util.ArrayList;
	
public abstract class PlayableFigure extends Figure {
	protected String owner;
	protected Color color;
	protected int movementQuotient;
	protected int positionOnPath;
	protected int numOfCollectedDiamonds = 0;
	protected int timeOfMovement = 0;
	protected int pathLength = 0;
	protected boolean reachedFinish;
	protected boolean isLost;
	protected String[] lastPosition;
	protected ArrayList<Integer> figurePath = new ArrayList<>();		
		
	public PlayableFigure(String owner, Color color) {
		this.owner = owner;
		this.color = color;
		this.reachedFinish = false;
		this.positionOnPath = 0;
		this.isLost = false;
	}
		
	public boolean move(int n) { // pripaziti na sinhronizaciju!!! // popraviti!
//		if(positionOnPath == -1) { // ako tek izlazim iz "kucice"
//			positionOnPath = 0; 
//			String[] coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(","); 
//			int first = Integer.parseInt(coords[0]), second = Integer.parseInt(coords[1]);
//			while(Simulation.MAP[first][second] instanceof PlayableFigure) {
//				positionOnPath++;
//				coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(",");
//				first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
//			}
//			
//		}
		
		
		int fieldsToMove = (n + numOfCollectedDiamonds) * movementQuotient;
		String[] coords;
		int first, second;
		
		for(int i = 1; i <= fieldsToMove; i++) {
			if(positionOnPath == Simulation.PATH.size())
				break;
			coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(","); // koordinate u matrici sljedeceg polja
			first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
			
			synchronized(Simulation.MAP) {
				while(Simulation.MAP[first][second] instanceof PlayableFigure) { // preskakanje figura ostalih igraca
					pathLength++;
					positionOnPath++;
					coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath)).split(",");
					first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
					i++;
				}
			
			
				
			
			
				if(Simulation.MAP[first][second] instanceof Diamond) {
					collectDiamond();
				}
				Simulation.MAP[first][second] = this;
				if(lastPosition != null) { // da uklonim sebe sa prethodnog polja // radi svaki put osim prvi put
					first = Integer.parseInt(lastPosition[0]); second = Integer.parseInt(lastPosition[1]);
					Simulation.MAP[first][second] = "";
				}
			}
			
			
			positionOnPath++;
			pathLength++;
			
			
			
			
			lastPosition = coords;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(positionOnPath  == (Simulation.mapDimension * Simulation.mapDimension + 1) / 2)  { // ako sam na zadnjem polju za figuru je kraj
			coords = Simulation.coordinates(Simulation.PATH.get(positionOnPath - 1)).split(",");
			first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
			synchronized(Simulation.MAP) {
				Simulation.MAP[first][second] = "";
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reachedFinish = true;
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> getFigurePath() {
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i < pathLength; i++)
			temp.add(Simulation.PATH.get(i));
		return temp;
	}
	
	public Color getFigureColor() {
		return color;
	}
	
	public int getPositionOnPath() {
		return positionOnPath;
	}
	
	public int getPathLength() {
		return pathLength;
	}
	
	public java.awt.Color getColor() {
		if(this.getFigureColor() == enums.Color.BLUE)
			return java.awt.Color.BLUE;
		else if(this.getFigureColor() == enums.Color.GREEN)
			return new java.awt.Color(0, 128, 0);
		else if(this.getFigureColor() == enums.Color.YELLOW)
			return new java.awt.Color(255, 255, 0);
		else if(this.getFigureColor() == enums.Color.RED)
			return java.awt.Color.RED;
		return null;
	}

	
	protected void collectDiamond() {
		this.numOfCollectedDiamonds++;
	}
}