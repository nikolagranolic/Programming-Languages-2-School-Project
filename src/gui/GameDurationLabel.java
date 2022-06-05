package gui;

import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import simulation.Simulation;

public class GameDurationLabel extends JLabel implements Runnable {

	public GameDurationLabel() {
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	@Override
	public void run() {
		while(true) {
			if(Simulation.isGameActive())
				this.setText("Vrijeme trajanja igre: " + ((new Date().getTime() - Simulation.timeReference) / 1000) + " s");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
