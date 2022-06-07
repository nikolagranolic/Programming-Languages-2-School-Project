package gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import simulation.Simulation;
import util.BasicCard;

public class MoveDescriptionLabel extends JLabel implements Runnable {

	public MoveDescriptionLabel() {
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		this.setHorizontalAlignment(SwingConstants.CENTER);

	}
	@Override
	public void run() {
		while(true) {
			synchronized(Simulation.lock) {
				if(Simulation.activeCard instanceof BasicCard)
					this.setText("Na potezu je " + Simulation.activePlayerName + ", figura " + Simulation.activeFigure.getFigureId() + ", prelazi " + Simulation.fieldsToMove + 
							 " polja, pomjera se sa pozicije " + Simulation.PATH.get(Simulation.activeFigureStartingPosition) + " na poziciju " + 
							 Simulation.PATH.get(Simulation.activeFigureEndingPosition) + ".");
				else {
					this.setText("Na potezu je " + Simulation.activePlayerName + ", kreira rupe.");
				}
			}
			if(Simulation.isGameActive() == false) {
				this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
				this.setText("KRAJ IGRE");
				break;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
