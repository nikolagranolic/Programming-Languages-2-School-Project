package model;

import simulation.Simulation;

import java.util.ArrayList;
import java.util.Random;

public class GhostFigure extends Figure implements Runnable {
	public boolean paused = false;
	public static ArrayList<Integer> diamondsPositions = new ArrayList<>();
	@Override
	public void run() {
		while(Simulation.isGameActive()) {
			if(paused) {
				synchronized (this) {
					try {
						wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			addDiamonds();
			System.out.println("duh");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) { // treba logovati izuzetak nesto
				e.printStackTrace();
			}
		}
	}
	
	private void addDiamonds() {
		synchronized(Simulation.MAP) {
			String[] coords;
			int first, second;
			if(diamondsPositions.size() > 0) { // ako je prethodno bilo dijamanata na mapi uklanjaju se
				for(int i = 0; i < diamondsPositions.size(); i++) {
					coords = Simulation.coordinates(diamondsPositions.get(i)).split(",");
					first = Integer.parseInt(coords[0]); second = Integer.parseInt(coords[1]);
					if(Simulation.MAP[first][second] instanceof Diamond)
						Simulation.MAP[first][second] = "";
				}
				diamondsPositions.clear();
			}
			int total = 0;
			Random random = new Random();
			int n = random.nextInt(Simulation.mapDimension - 1) + 2;
			while(total < n) {
				int x = random.nextInt(Simulation.PATH.size());
				coords = Simulation.coordinates(Simulation.PATH.get(x)).split(",");
				first = Integer.parseInt(coords[0]);
				second = Integer.parseInt(coords[1]);
				
				if(!(Simulation.MAP[first][second] instanceof Figure) && !(Simulation.MAP[first][second] instanceof Diamond)) { // pitati moze li ovako!!!!!!!!!
					Simulation.MAP[first][second] = new Diamond();
					diamondsPositions.add(Simulation.PATH.get(x));
					total++;
				}
				
			}
		}
	}
}
