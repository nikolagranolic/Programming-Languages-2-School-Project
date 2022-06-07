package model;

import enums.*;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Date;
	
public abstract class PlayableFigure extends Figure {
	protected String owner;
	protected Color color;
	protected int movementQuotient;
	protected int positionOnPath;
	protected int numOfCollectedDiamonds = 0;
	protected int timeOfMovement = 0;
	protected int pathLength = 0;
	protected int figureId;
	protected boolean reachedFinish;
	protected boolean lost;
	protected String[] lastPosition;
	protected ArrayList<Integer> figurePath = new ArrayList<>();
	protected long timeSpentMoving = 0;
		
	public PlayableFigure(String owner, Color color, int id) {
		this.owner = owner;
		this.color = color;
		this.reachedFinish = false;
		this.positionOnPath = 0;
		this.lost = false;
		this.figureId = id; 
	}
		
	public boolean move(int n) {
		long timeReference = new Date().getTime();
		
		int fieldsToMove = n * movementQuotient + numOfCollectedDiamonds;
		// dio za opis poteza
		
		synchronized(Simulation.lock) {
			Simulation.fieldsToMove = fieldsToMove;
			Simulation.activeFigureStartingPosition = positionOnPath;
			Simulation.activeFigureEndingPosition = positionOnPath + fieldsToMove - 1;
			if(Simulation.activeFigureEndingPosition > Simulation.PATH.size() - 1) {
				Simulation.activeFigureEndingPosition = Simulation.PATH.size() - 1;
				Simulation.fieldsToMove = Simulation.activeFigureEndingPosition - Simulation.activeFigureStartingPosition;
			}
				
			
			if(!Simulation.moveDescriptionThread.isAlive())
				Simulation.moveDescriptionThread.start();
		}
		
		
		
		String[] coords;
		int first, second;
		
		for(int i = 1; i <= fieldsToMove; i++) {
			if(Simulation.gamePaused) {
				synchronized(Simulation.mainThread) {
					try {
						Simulation.mainThread.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
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
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			reachedFinish = true;

			timeSpentMoving += (new Date().getTime() - timeReference) / 1000;
			return true;
		}
		timeSpentMoving += (new Date().getTime() - timeReference) / 1000;
		return false;
	}
	
	public ArrayList<Integer> getFigurePath() {
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i < pathLength; i++)
			temp.add(Simulation.PATH.get(i));
		return temp;
	}
	
	public abstract String getType();
	
	public long getTimeSpentMoving() {
		return timeSpentMoving;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public Color getFigureColor() {
		return color;
	}
	
	public int getFigureId() {
		return figureId;
	}
	
	public int getPositionOnPath() {
		return positionOnPath;
	}
	
	public boolean isReachedFinish() {
		return reachedFinish;
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
	
	public String getColorString() {
		if(this.getFigureColor() == enums.Color.BLUE)
			return "BLUE";
		else if(this.getFigureColor() == enums.Color.GREEN)
			return "GREEN";
		else if(this.getFigureColor() == enums.Color.YELLOW)
			return "YELLOW";
		else if(this.getFigureColor() == enums.Color.RED)
			return "RED";
		return "";
	}

	public boolean isLost() {
		return lost;
	}
	
	public void drop() {
		lost = true;
	}
	
	protected void collectDiamond() {
		this.numOfCollectedDiamonds++;
	}
}