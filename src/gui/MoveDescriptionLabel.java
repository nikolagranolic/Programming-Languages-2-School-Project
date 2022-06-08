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
		while(Simulation.isGameActive()) {
			synchronized(Simulation.lock) {
				if(Simulation.activeCard instanceof BasicCard)
					this.setText("Na potezu je " + Simulation.activePlayerName + ", figura " + Simulation.activeFigure.getFigureId() + ", prelazi " + Simulation.fieldsToMove + 
							 " polja, pomjera se sa pozicije " + ((Simulation.PATH.get(Simulation.activeFigureStartingPosition) == 4) ?
							 Simulation.PATH.get(Simulation.activeFigureStartingPosition) : Simulation.PATH.get(Simulation.activeFigureStartingPosition - 1)) +
							 " na poziciju " + Simulation.PATH.get(Simulation.activeFigureEndingPosition) + ".");
				else {
					this.setText("Na potezu je " + Simulation.activePlayerName + ", kreira rupe.");
				}
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.setText("Igra je zavrsena! :D");
	}

}
