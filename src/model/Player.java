package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import simulation.Simulation;
import util.BasicCard;
import util.Card;
import util.Hole;
import util.SpecialCard;

public class Player {
	private static int PLAYER_ID = 1;
	private static Random random = new Random();
	private String name;
	private enums.Color figuresColor;
	private int id;
	private int figuresRemaining;
	private boolean inGame;
	private int activeFigureIndex;
	private ArrayList<PlayableFigure> figures = new ArrayList<>();
	private static ArrayList<enums.Color> availableColors = new ArrayList<enums.Color>(Arrays.asList(enums.Color.RED, enums.Color.BLUE, enums.Color.GREEN, enums.Color.YELLOW));
	
	public Player() {
		name = "Player" + PLAYER_ID;
		id = PLAYER_ID;
		PLAYER_ID++;
		inGame = true;
		figuresRemaining = 4;
		int x = random.nextInt(availableColors.size());
		figuresColor = availableColors.remove(x);
		for(int i = 0; i < 4; i++) {
			x = random.nextInt(3);
			if(x == 0) {
				figures.add(new BasicFigure(name, figuresColor, i + 1));
			}
			else if(x == 1) {
				figures.add(new FlyingFigure(name, figuresColor, i + 1));
			}
			else if(x == 2) {
				figures.add(new SuperFastFigure(name, figuresColor, i + 1));
			}
		}
		activeFigureIndex = 0;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public int getFiguresRemaining() {
		return figuresRemaining;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getActiveFigureIndex() {
		return activeFigureIndex;
	}
	
	public ArrayList<PlayableFigure> getFigures() {
		return figures;
	}
	
	public void playAMove() {
		if(figuresRemaining > 0) {
			Card drawnCard = Simulation.DECK.peek();
			synchronized(Simulation.lock) {
				Simulation.activeFigure = figures.get(activeFigureIndex);
				Simulation.activePlayerName = this.name;
				Simulation.activeCard = Simulation.DECK.peek();
			}
			
			if(drawnCard instanceof BasicCard) {
				if(figures.get(activeFigureIndex).isLost()) {
					activeFigureIndex++;
					figuresRemaining--;
				}
				boolean reachedEnd = figures.get(activeFigureIndex).move(((BasicCard)drawnCard).getNumber());
				if(reachedEnd) {
					activeFigureIndex++;
					figuresRemaining--;
				}
			}
			else if(drawnCard instanceof SpecialCard){
				if(!Simulation.moveDescriptionThread.isAlive())
					Simulation.moveDescriptionThread.start();
				Hole.createHoles();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Hole.deleteHoles();
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
			synchronized(Simulation.DECK) {
				Simulation.DECK.drawCard();
			}
		}
	}
	
	public java.awt.Color getColor() {
		if(figuresColor == enums.Color.BLUE)
			return java.awt.Color.BLUE;
		else if(figuresColor == enums.Color.GREEN)
			return new java.awt.Color(0, 128, 0);
		else if(figuresColor == enums.Color.YELLOW)
			return new java.awt.Color(255, 255, 0);
		else if(figuresColor == enums.Color.RED)
			return java.awt.Color.RED;
		return null;
	}
}
