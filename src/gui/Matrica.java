package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.BasicFigure;
import model.Diamond;
import model.FlyingFigure;
import model.PlayableFigure;
import model.Player;
import model.SuperFastFigure;
import simulation.Simulation;
import util.Hole;

public class Matrica extends JPanel implements Runnable {
	private ArrayList<JLabel> polja = new ArrayList<>();
	
	public Matrica() {
		this.setLayout(new GridLayout(Simulation.mapDimension, Simulation.mapDimension, 2, 2)); // !!!!!
		for(int i = 0; i < Simulation.mapDimension * Simulation.mapDimension; i++) {		
			JLabel temp = new JLabel();
			temp.setBorder(new LineBorder(Color.LIGHT_GRAY));
			temp.setBackground(Color.PINK);
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setForeground(Color.LIGHT_GRAY);
			polja.add(temp);
			this.add(temp);
		}
	}
	@Override
	public void run() {
		while(true) {
			synchronized(Simulation.MAP) {
				for(int i = 0; i < Simulation.mapDimension; i++) {
					for(int j = 0; j < Simulation.mapDimension; j++) {
						if(Simulation.MAP[i][j] instanceof Diamond) {
							polja.get(i * Simulation.mapDimension + j).setIcon(new ImageIcon(MainFrame.class.getResource("/Images/diamond.png")));
							polja.get(i * Simulation.mapDimension + j).setText("");
						}
						else if(Simulation.MAP[i][j] instanceof BasicFigure) {
							polja.get(i * Simulation.mapDimension + j).setIcon(null);
							Color color = ((BasicFigure) Simulation.MAP[i][j]).getColor();
							polja.get(i * Simulation.mapDimension + j).setText("BF");
							polja.get(i * Simulation.mapDimension + j).setForeground(color);
						}
						else if(Simulation.MAP[i][j] instanceof SuperFastFigure) {
							polja.get(i * Simulation.mapDimension + j).setIcon(null);
							Color color = ((SuperFastFigure) Simulation.MAP[i][j]).getColor();
							polja.get(i * Simulation.mapDimension + j).setText("SFF");
							polja.get(i * Simulation.mapDimension + j).setForeground(color);
						}
						else if(Simulation.MAP[i][j] instanceof FlyingFigure) {
							polja.get(i * Simulation.mapDimension + j).setIcon(null);
							Color color = ((FlyingFigure) Simulation.MAP[i][j]).getColor();
							polja.get(i * Simulation.mapDimension + j).setText("FF");
							polja.get(i * Simulation.mapDimension + j).setForeground(color);
						}
						else if(Simulation.MAP[i][j] instanceof Hole) {
							polja.get(i * Simulation.mapDimension + j).setIcon(null);
							polja.get(i * Simulation.mapDimension + j).setBackground(Color.BLACK);
							polja.get(i * Simulation.mapDimension + j).setOpaque(true);
						}
						else {
							polja.get(i * Simulation.mapDimension + j).setIcon(null);
							polja.get(i * Simulation.mapDimension + j).setText((i * Simulation.mapDimension + j + 1) + "");
							polja.get(i * Simulation.mapDimension + j).setForeground(Color.LIGHT_GRAY);
						}
					}
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
