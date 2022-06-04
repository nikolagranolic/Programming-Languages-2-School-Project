package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import enums.Color;
import simulation.Simulation;
import util.BasicCard;
import util.Card;
import util.SpecialCard;

public class Player {
	private static int PLAYER_ID = 1;
	private static Random random = new Random();
	private String ime;
	private Color figuresColor;
	private int figuresRemaining;
	private boolean inGame;
	private int activeFigureIndex;
	private ArrayList<PlayableFigure> figures = new ArrayList<>();
	private static ArrayList<Color> availableColors = new ArrayList<Color>(Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW));
	
	public Player() {
		ime = "player" + PLAYER_ID;
		PLAYER_ID++;
		inGame = true;
		figuresRemaining = 4;
		int x = random.nextInt(availableColors.size());
		figuresColor = availableColors.remove(x);
		for(int i = 0; i < 4; i++) {
			x = random.nextInt(3);
			if(x == 0) {
				figures.add(new BasicFigure(figuresColor));
			}
			else if(x == 1) {
				figures.add(new FlyingFigure(figuresColor));
			}
			else if(x == 2) {
				figures.add(new SuperFastFigure(figuresColor));
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
	
	public ArrayList<PlayableFigure> getFigures() {
		return figures;
	}
	
	public void playAMove() {
		Card drawnCard = Simulation.DECK.drawCard();
		if(drawnCard instanceof BasicCard) {
			figures.get(activeFigureIndex).move(((BasicCard)drawnCard).getNumber());
		}
		else {
			((SpecialCard)drawnCard).createHoles();
		}
		
	}
}
