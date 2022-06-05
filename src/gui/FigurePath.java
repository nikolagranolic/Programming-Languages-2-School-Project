package gui;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import simulation.Simulation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Toolkit;

public class FigurePath extends JDialog {

	/**
	 * Launch the application.
	 */
	public ArrayList<JLabel> matrix = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public FigurePath(String figureName, ArrayList<Integer> path) {
		getContentPane().setBackground(Color.PINK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FigurePath.class.getResource("/Images/pieceicon.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new GridLayout(Simulation.mapDimension, Simulation.mapDimension, 0, 0));
		for(int i = 0; i < Simulation.mapDimension * Simulation.mapDimension; i++) { // dodavanje
			JLabel temp = new JLabel("" + (i + 1));
			temp.setBorder(new LineBorder(Color.LIGHT_GRAY));
			temp.setBackground(Color.PINK);
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(temp);
			matrix.add(temp);
		}
		this.setTitle(figureName);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		for(int i = 0; i < matrix.size(); i++) {
			if(path.contains(i + 1)) {
				matrix.get(i).setText("O");
				matrix.get(i).setForeground(Color.BLACK);
			}
		}
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
	}

}
