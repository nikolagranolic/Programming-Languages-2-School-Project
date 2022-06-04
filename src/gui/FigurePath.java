package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import simulation.Simulation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;

public class FigurePath extends JDialog {

	/**
	 * Launch the application.
	 */
	public static ArrayList<JLabel> matrix = new ArrayList<>();
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FigurePath dialog = new FigurePath("Test", null);
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the dialog.
	 */
	public FigurePath(String figureName, ArrayList<Integer> path) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FigurePath.class.getResource("/Images/pieceicon.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new GridLayout(Simulation.mapDimension, Simulation.mapDimension, 0, 0));
		for(int i = 0; i < Simulation.mapDimension * Simulation.mapDimension; i++) { // dodavanje
			JLabel temp = new JLabel("" + (i + 1));
			temp.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setForeground(Color.LIGHT_GRAY);
			getContentPane().add(temp);
			matrix.add(temp);
		}
		this.setTitle(figureName);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		for(int i = 0; i < matrix.size(); i++) {
			if(path.contains(i)) {
				matrix.get(i).setText("O");
				matrix.get(i).setForeground(Color.BLACK);
			}
		}
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
	}

}
