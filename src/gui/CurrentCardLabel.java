package gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import simulation.Simulation;
import util.BasicCard;
import util.SpecialCard;

public class CurrentCardLabel extends JLabel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		while(Simulation.isGameActive()) {
			synchronized(Simulation.DECK) {
				if(Simulation.DECK.peek() instanceof SpecialCard)
					this.setIcon(new ImageIcon(MainFrame.class.getResource("/Images/specialcard.png")));
				else if(Simulation.DECK.peek() instanceof BasicCard) {
					this.setIcon(new ImageIcon(MainFrame.class.getResource("/Images/basicimage" + ((BasicCard)Simulation.DECK.peek()).getNumber() + ".png")));
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Logger.getLogger(Simulation.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
			}
		}
		this.setIcon(null);
	}
}