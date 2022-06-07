package simulation;

import model.Player;
import util.CardDeck;
import model.GhostFigure;
import model.PlayableFigure;
import exceptions.*;
import gui.MainFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Simulation {
	public final static int MIN_NUM_OF_PLAYERS = 2;
	public final static int MAX_NUM_OF_PLAYERS = 4;
	public final static int MIN_MAP_DIM = 7;
	public final static int MAX_MAP_DIM = 10;
	public final static int NUMBER_OF_HOLES_CREATED = 5;
	public static int numOfPlayers;
	public static int mapDimension;
	public static Object[][] MAP;
	public static Player[] PLAYERS;
	public static ArrayList<Integer> PATH = new ArrayList<>();
	public static boolean gamePaused = false;
	public static CardDeck DECK = new CardDeck();
	public static long timeReference;
	public static GhostFigure ghostFigure;
	public static Thread mainThread;
	
	public static void main(String args[]) {
		// validacija unosa argumenata komandne linije
		mainThread = Thread.currentThread();
		try {
			timeReference = new Date().getTime();
			
			validation(args);
			numOfPlayers = Integer.parseInt(args[0]);
			mapDimension = Integer.parseInt(args[1]);
			MAP = new Object[mapDimension][mapDimension];
			createMatrix();
			createPath();
			createPlayers();
			
			
			
			
			
			
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
			frame.initializeStaticLabels();
			
			Thread matrixThread = new Thread(frame.getMatrixPanel());
			matrixThread.setDaemon(true);
			matrixThread.start();
			
			Thread cardThread = new Thread(frame.getCurrentCardLabel());
			cardThread.setDaemon(true);
			cardThread.start();
			
			Thread gameDurationThread = new Thread(frame.getGameDurationLabel());
			gameDurationThread.setDaemon(true);
			gameDurationThread.start();
			
			ghostFigure = new GhostFigure();
			Thread ghostFigureThread = new Thread(ghostFigure);
			ghostFigureThread.setDaemon(true);
			ghostFigureThread.start();
			
			while(isGameActive()) {
				for(Player player : PLAYERS)
					player.playAMove();
			}
			System.out.println("KRAJ IGREEEEE");
			
			for(Player p : PLAYERS) {
				for(PlayableFigure f : p.getFigures()) {
					System.out.println(f.getTimeSpentMoving() + " s");
				}
			}
			
			
			
//			Scanner scan = new Scanner(System.in);
//	        String option = "";
//	        while (!"END".equals(option)) {
//	            option = scan.nextLine();
//	            if ("PAUZA".equals(option)) {
//	                ghostFigure.paused = true;
//	            }
//	            if ("NASTAVAK".equals(option)) {
//	                ghostFigure.paused = false;
//	                try {
//	                    synchronized (ghostFigure) {
//	                        ghostFigure.notify();
//	                    }
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//	        scan.close();
		}
		catch(InvalidArgumentsException | InvalidNumberOfPlayersException | InvalidDimensionException e) {
			System.out.println("Simulacija ne moze biti pokrenuta! Opis greske:");
			System.out.println(e.getMessage());
		}	
	}
	public static void game() {
		boolean gameEnded = false;
		while (!gameEnded) {
			gameEnded = checkIfGameEnded(gameEnded);
			for(int i = 0; i < numOfPlayers; i++) {
				if(PLAYERS[i].isInGame()) {
					PLAYERS[i].playAMove();
				}
				while(gamePaused) {
					
				}
			}
		}
	}
	private static boolean checkIfGameEnded(boolean flag) {
		flag = false;
		for(Player p : PLAYERS) {
			if(p.isInGame())
				flag = true;
		}
		return flag;
	}
	// ispis mape
	public static void printMap() {
		for(int i = 0; i < mapDimension; i++) {
			for(int j = 0; j < mapDimension; j++) {
				System.out.printf("%4s", MAP[i][j]);
			}
			System.out.println();
		}
	}
	// smjestanje polja koja su na putanji u array-listu
	// popraviti da se ne duplira kod
	public static void createPath() {
		if(mapDimension % 2 == 1) {
			for(int t = 0; t < (mapDimension + 1) / 2; t++) {
	            int i = t;
	            int j = (mapDimension - 1) / 2;
	            while(i <= (mapDimension - 1) / 2) {
	                PATH.add(Integer.parseInt((String)MAP[i++][j++]));
	            }
	            j--; j--;
	            while(i > (mapDimension - 1) / 2 && i < mapDimension - t) {
	                PATH.add(Integer.parseInt((String)MAP[i++][j--]));
	            }
	            i--;
	            i--;
	            while(i >= (mapDimension - 1) / 2) {
	                PATH.add(Integer.parseInt((String)MAP[i--][j--]));
	            }
	            j++;
	            j++;
	            while(i >= 1 + t) {
	                PATH.add(Integer.parseInt((String)MAP[i--][j++]));
	            }
	        }
		}
		else {
			for(int t = 0; t < (mapDimension + 1) / 2; t++) {
	            int i = t;
	            int j = (mapDimension - 1) / 2;
	            while(i <= mapDimension / 2) {
	            	PATH.add(Integer.parseInt((String)MAP[i++][j++]));
	            }
	            j -= 2;
	            while(i < mapDimension - t) {
	                PATH.add(Integer.parseInt((String)MAP[i++][j--]));
	            }
	            i -= 2;
	            while(i >= mapDimension / 2 - 1) {
	            	if(!PATH.contains(Integer.parseInt((String)MAP[i][j])))
	                	PATH.add(Integer.parseInt((String)MAP[i--][j--]));
	            	else
	            		i = mapDimension / 2 - 2;
	            }
	            j += 2;
	            while(i >= 1 + t) {
	                PATH.add(Integer.parseInt((String)MAP[i--][j++]));
	            }
	        }
		}
		
	}
	// validacija argumenata komandne linije
	private static void validation(String args[]) throws InvalidArgumentsException, InvalidDimensionException, InvalidNumberOfPlayersException {
		if(args.length != 2) {
			throw new InvalidArgumentsException();
		}
		if(Integer.parseInt(args[0]) < MIN_NUM_OF_PLAYERS || Integer.parseInt(args[0]) > MAX_NUM_OF_PLAYERS) { // OBRISATI -1
			throw new InvalidNumberOfPlayersException();
		}
		if(Integer.parseInt(args[1]) < MIN_MAP_DIM || Integer.parseInt(args[1]) > MAX_MAP_DIM) {
			throw new InvalidDimensionException();
		}
	}
	// kreiranje izmedju 2 i 4 igraca
	private static void createPlayers() {
		PLAYERS = new Player[numOfPlayers];
		for(int i = 0; i < numOfPlayers; i++) {
			PLAYERS[i] = new Player();
		}
		// shuffle
		List<Player> playerList = Arrays.asList(PLAYERS);
		Collections.shuffle(playerList);
		playerList.toArray(PLAYERS);
	}
	
	// provjeravanje da li je igra i dalje traje
	public static boolean isGameActive() {
		for(Player player : PLAYERS) {
			if(player.getFiguresRemaining() != 0)
				return true;
		}
		return false;
	}
	// kreiranje matrice (popunjavanje matrice string reprezentacijama brojeva)
	public static void createMatrix() {
		for(int i = 0; i < mapDimension; i++) {
			for(int j = 0; j < mapDimension; j++) {
				MAP[i][j] = "" + (i * mapDimension + j + 1);
			}
		}
	}
	// metoda koja vraca polozaj u matrici od broja koji je proslijedjen kao argument
	public static String coordinates(Integer number) {
		int first = (number - 1) / mapDimension;
		int second = (number - 1) - first * mapDimension;
		return first + "," + second;
	}
}
