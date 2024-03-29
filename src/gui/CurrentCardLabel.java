package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import simulation.Simulation;
import util.BasicCard;
import util.SpecialCard;

public class CurrentCardLabel extends JLabel implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Simulation.isGameActive()) {
			synchronized(Simulation.DECK) {
				if(Simulation.DECK.peek() instanceof SpecialCard)
					this.setIcon(new ImageIcon(MainFrame.class.getResource("/Images/specialcard.png")));
				else if(Simulation.DECK.peek() instanceof BasicCard) {
					this.setIcon(new ImageIcon(MainFrame.class.getResource("/Images/basicimage" + ((BasicCard)Simulation.DECK.peek()).getNumber() + ".png")));
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

}
