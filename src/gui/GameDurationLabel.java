package gui;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import simulation.Simulation;

public class GameDurationLabel extends JLabel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean paused = false;
	
	public GameDurationLabel() {
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public void run() {
		long gameDuration = 0;
		while(Simulation.isGameActive()) {
			if(paused) {
				synchronized (Simulation.gameDurationLabel) {
					try {
						wait();
					}
					catch (InterruptedException e) {
						Logger.getLogger(Simulation.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
					}
				}
			}
			
			this.setText("Vrijeme trajanja igre: " + gameDuration++ + " s");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Logger.getLogger(Simulation.class.getName()).log(Level.INFO, e.fillInStackTrace().toString());
			}
		}
	}

	public String getGameDuration() {
		return this.getText().split(" ")[3];
	}
}
