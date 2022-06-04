package model;

import simulation.Simulation;
import java.util.Random;

public class GhostFigure extends Figure implements Runnable {
	public boolean paused = false;
	@Override
	public void run() {
		while(true) {
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
//				Simulation.printMap();
//				System.out.println();
				Thread.sleep(5000); // da li ovako mogu uspavati thread
			} catch (InterruptedException e) { // treba logovati izuzetak nesto
				e.printStackTrace();
			}
		}
	}
	
	private void addDiamonds() { // sta ako postavim dijamant iznad rupe?
		int total = 0;
		Random random = new Random();
		int n = random.nextInt(6) + 2;
		while(total < n && Diamond.count < Diamond.MAX_NUM_OF_DIAMONDS) {
			int x = random.nextInt(Simulation.PATH.size());
			String[] coords = Simulation.coordinates(Simulation.PATH.get(x)).split(",");
			int first = Integer.parseInt(coords[0]);
			int second = Integer.parseInt(coords[1]);
			synchronized(Simulation.MAP[first][second]) {
				if(!(Simulation.MAP[first][second] instanceof Diamond)) {
					Simulation.MAP[first][second] = new Diamond();
					total++;
				}
			}
		}
	}
}
