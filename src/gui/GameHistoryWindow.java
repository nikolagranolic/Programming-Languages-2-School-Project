package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simulation.Simulation;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Cursor;

public class GameHistoryWindow extends JDialog {
	private JTable table;
	public GameHistoryWindow() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameHistoryWindow.class.getResource("/Images/history.png")));
		getContentPane().setBackground(Color.PINK);
		getContentPane().setLayout(new BorderLayout(0, 0));
		String[] colNames = new String[] {"Lista fajlova sa rezultatima"};
		String[][] rowData = new String[Simulation.listOfFiles.size() / colNames.length][colNames.length];
		
		for(int i = 0; i < rowData.length; i++) {
			for(int j = 0; j < rowData[i].length; j++) {
				rowData[i][j] = Simulation.listOfFiles.get(i * colNames.length + j).getName();
			}
		}
		
		table = new JTable(rowData, colNames);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(new JScrollPane(table));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				JTable target = (JTable)me.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				try {
					JOptionPane.showMessageDialog(null, Files.readString(Path.of("./istorijaOdigranihIgara/" + table.getValueAt(row, column))));
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
