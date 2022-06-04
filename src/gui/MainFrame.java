package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import model.Player;
import simulation.Simulation;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static ArrayList<Integer> putanja = new ArrayList<>();
	public static ArrayList<JLabel> matrica = new ArrayList<>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		putanja.add(3);
		putanja.add(11);
		putanja.add(19);
		putanja.add(27);
		putanja.add(33);
		putanja.add(39);
		putanja.add(45);
		putanja.add(37);
		putanja.add(29);
		putanja.add(21);
		putanja.add(15);
		MainFrame frame = new MainFrame();
		
		frame.setVisible(true);
		
		frame.setResizable(false);
		frame.setTitle("DiamondCircle");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < matrica.size(); i++) {
			if(putanja.contains(i)) {
				matrica.get(i).setText("x");
				matrica.get(i).setForeground(Color.BLACK);
			}
//			if(i % 4 == 0) {
//				matrica.get(i).setBackground(Color.BLACK);
//				matrica.get(i).setOpaque(true);
//			}
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("DiamondCircle");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/Images/diamondicon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 728);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1009, 0};
		gridBagLayout.rowHeights = new int[]{159, 519, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(189, 183, 107));
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		pauseResumeButton = new JButton("Pokreni/Zaustavi");
		pauseResumeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		pauseResumeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pauseResumeButton.setBounds(803, 51, 143, 72);
		panel.add(pauseResumeButton);
		
		JLabel titleLabel = new JLabel("DiamondCircle");
		titleLabel.setBorder(new LineBorder(new Color(154, 205, 50), 4));
		titleLabel.setOpaque(true);
		titleLabel.setForeground(Color.RED);
		titleLabel.setBackground(Color.BLUE);
		titleLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(355, 30, 281, 100);
		panel.add(titleLabel);
		
		noOfGamesPlayedLabel = new JLabel("Trenutni broj odigranih igara: [n]");
		noOfGamesPlayedLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		noOfGamesPlayedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noOfGamesPlayedLabel.setBounds(44, 33, 244, 54);
		panel.add(noOfGamesPlayedLabel);
		
		currentGameDurationLabel = new JLabel("Vrijeme trajanja igre:");
		currentGameDurationLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		currentGameDurationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentGameDurationLabel.setBounds(54, 85, 224, 34);
		panel.add(currentGameDurationLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(189, 183, 107));
		splitPane.setLeftComponent(panel_2);
		panel_2.setLayout(new GridLayout(1, Simulation.numOfPlayers, 0, 0)); // staviti tu num of players umjesto 4
		
		player1Label = new JLabel("Player1");
		player1Label.setForeground(Color.RED);
		player1Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		player1Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(player1Label);
		
		player2Label = new JLabel("Player2");
		player2Label.setForeground(Color.BLUE);
		player2Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		player2Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(player2Label);
		
		player3Label = new JLabel("Player3");
		player3Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		player3Label.setForeground(new Color(0, 128, 0));
		player3Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(player3Label);
		
		player4Label = new JLabel("Player4");
		player4Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		player4Label.setForeground(new Color(255, 255, 0));
		player4Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(player4Label);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setDoubleBuffered(true);
		splitPane.setRightComponent(splitPane_1);
		
		figuresPanel = new JPanel();
		figuresPanel.setBackground(new Color(189, 183, 107));
		splitPane_1.setLeftComponent(figuresPanel);
		figuresPanel.setLayout(new GridLayout(16, 1, 0, 0));
		
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setRightComponent(splitPane_2);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setDividerSize(0);
		splitPane_2.setRightComponent(splitPane_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(189, 183, 107));
		splitPane_3.setLeftComponent(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		moveDescriptionLabel = new JLabel("OpisPoteza");
		moveDescriptionLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		moveDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(moveDescriptionLabel, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(189, 183, 107));
		splitPane_3.setRightComponent(panel_7);
		panel_7.setLayout(null);
		
		displayFilesButton = new JButton("Fajlovi sa rezultatima");
		displayFilesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		displayFilesButton.setBounds(43, 36, 164, 45);
		panel_7.add(displayFilesButton);
		splitPane_3.setDividerLocation(600);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setDividerSize(0);
		splitPane_2.setLeftComponent(splitPane_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(189, 183, 107));
		splitPane_4.setLeftComponent(panel_4);
		panel_4.setLayout(null);
		
		JPanel matrixPanel = new JPanel();
		matrixPanel.setBounds(119, 29, 368, 285);
		panel_4.add(matrixPanel);
		matrixPanel.setLayout(new GridLayout(7, 7, 2, 2));
		
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(189, 183, 107));
		splitPane_4.setRightComponent(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.PINK);
		panel_8.setBounds(10, 11, 233, 325);
		panel_5.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_23 = new JLabel("Trenutna karta");
		lblNewLabel_23.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_23.setBounds(52, 11, 126, 38);
		panel_8.add(lblNewLabel_23);
		

		// C:\Users\Nikola\Desktop\Fakultet\Druga godina\Programski jezici 2\Projektni zadatak
		

		currentCardLabel = new JLabel("");
		currentCardLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/Images/specialcard.png")));
		currentCardLabel.setBounds(10, 47, 213, 267);
		panel_8.add(currentCardLabel);
		splitPane_4.setDividerLocation(600);
		splitPane_2.setDividerLocation(350);
		splitPane_1.setDividerLocation(150);
		splitPane.setDividerLocation(50);
		
		
	}
	public void initializeStaticLabels() {
		for(int i = 0; i < Simulation.numOfPlayers * 4; i++) {
			int index = i - (i / 4) * 4;
			Player player = Simulation.PLAYERS[i / 4];
			JLabel temp = new JLabel("P" + ((i / 4) + 1) + ": Figure" + ((i % 4) + 1));
			// temp.setForeground(player.getFigures().get(index).getFigureColor()); // promijeniti da umjesto enum tipa bude neka boja iz std. lib.
			temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			figuresPanel.add(temp);
			figuresLabels.add(temp);
		}
		
		for(int i = 0; i < figuresLabels.size(); i++) {
			Player player;
			int index = i - (i / 4) * 4;
			int in = i;
			player = Simulation.PLAYERS[i / 4];
			figuresLabels.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					FigurePath dialog = new FigurePath(figuresLabels.get(in).getText(), player.getFigures().get(index).getFigurePath());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
		}
	}
	
	// Variables declaration
	// Figures labels
//	private JLabel player1figure1Label;
//	private JLabel player1figure2Label;
//	private JLabel player1figure3Label;
//	private JLabel player1figure4Label;
//	private JLabel player2figure1Label;
//	private JLabel player2figure2Label;
//	private JLabel player2figure3Label;
//	private JLabel player2figure4Label;
//	private JLabel player3figure1Label;
//	private JLabel player3figure2Label;
//	private JLabel player3figure3Label;
//	private JLabel player3figure4Label;
//	private JLabel player4figure1Label;
//	private JLabel player4figure2Label;
//	private JLabel player4figure3Label;
//	private JLabel player4figure4Label;
	private ArrayList<JLabel> figuresLabels = new ArrayList<>();
	// Player labels
	private JLabel player1Label;
	private JLabel player2Label;
	private JLabel player3Label;
	private JLabel player4Label;
	// Buttons
	private JButton pauseResumeButton;
	private JButton displayFilesButton;
	// Misc. labels
	private JLabel currentGameDurationLabel;
	private JLabel noOfGamesPlayedLabel;
	private JLabel currentCardLabel;
	private JLabel moveDescriptionLabel;
	// Panels
	private JPanel figuresPanel;
}
