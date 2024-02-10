package model;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Graphics;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.SwingConstants;
/**
 * the game view class that shows everything related to the game to the user
 */
public class GameView extends JFrame implements ActionListener{
	
	MainMenuView main;
	ImageIcon exitIcon;
	ImageIcon homeIcon;
	ImageIcon profileIcon;
	JMenuItem exitItem;
	JMenuItem homeItem;
	ArrayList<JButton> bottoniPlayer;
	ArrayList <JLabel> manoCPU1;
	ArrayList <JLabel> manoCPU2;
	ArrayList <JLabel> manoCPU3;
	JLabel label39; //MAZZO
	JLabel label35; //IMMAGINE PROFILO
	JLabel label34; //MANO
	JLabel label32; //SCARTI
	JLabel label36; //NOME CPU 2
	JLabel label38; //NOME CPU 3
	JButton bottone11;
	JButton bottone12;
	JButton bottone13;
	AudioManager audioManager;
	JFrame frame;
	
	/**
	 * Constructor of the game view, which initializes many components.
	 * As usual the top menu is created, besides that many buttons for the player to interact with are created; same goes for the many labels created to show the cpu's hands, the deck of cards and the discard pile.
	 * @param main, the view of the main menu
	 */
	GameView(MainMenuView main){
		audioManager = AudioManager.getInstance();
		this.main = main;
		this.setTitle("Jtrash - Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.setIconImage(icona.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Menù");
		
		homeIcon = new ImageIcon("src\\model\\menuAssets\\Home2.png");
		homeItem = new JMenuItem("HOME");
		homeItem.addActionListener(this);
		homeItem.setIcon(homeIcon);
		fileMenu.add(homeItem);
		
		exitItem = new JMenuItem("EXIT");
		exitItem.addActionListener(this);
		exitIcon = new ImageIcon("src\\model\\menuAssets\\Exit.png");
		exitItem.setIcon(exitIcon);
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JButton bottone1 = new JButton("");
		bottone1.setBounds(373, 632, 44, 62);
		getContentPane().add(bottone1);
		
		JButton bottone2 = new JButton("");
		bottone2.setBounds(427, 632, 44, 62);
		getContentPane().add(bottone2);
		
		JButton bottone3 = new JButton("");
		bottone3.setBounds(481, 632, 44, 62);
		getContentPane().add(bottone3);
		
		JButton bottone4 = new JButton("");
		bottone4.setBounds(535, 632, 44, 62);
		getContentPane().add(bottone4);
		
		JButton bottone5 = new JButton("");
		bottone5.setBounds(589, 632, 44, 62);
		getContentPane().add(bottone5);
		
		JButton bottone6 = new JButton("");
		bottone6.setBounds(373, 563, 44, 62);
		getContentPane().add(bottone6);
		
		JButton bottone7 = new JButton("");
		bottone7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bottone7.setBounds(427, 563, 44, 62);
		getContentPane().add(bottone7);
		
		JButton bottone8 = new JButton("");
		bottone8.setBounds(481, 563, 44, 62);
		getContentPane().add(bottone8);
		
		JButton bottone9 = new JButton("");
		bottone9.setBounds(535, 563, 44, 62);
		getContentPane().add(bottone9);
		
		JButton bottone10 = new JButton("");
		bottone10.setBounds(589, 563, 44, 62);
		getContentPane().add(bottone10);
		
		bottoniPlayer = new ArrayList<JButton>();
		
		bottoniPlayer.add(bottone6);
		bottoniPlayer.add(bottone7);
		bottoniPlayer.add(bottone8);
		bottoniPlayer.add(bottone9);
		bottoniPlayer.add(bottone10);
		bottoniPlayer.add(bottone1);
		bottoniPlayer.add(bottone2);
		bottoniPlayer.add(bottone3);
		bottoniPlayer.add(bottone4);
		bottoniPlayer.add(bottone5);
		
		JLabel label1 = new JLabel("");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(796, 343, 44, 62);
		label1.setBorder(border);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(850, 343, 44, 62);
		label2.setBorder(border);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBounds(688, 343, 44, 62);
		label3.setBorder(border);
		getContentPane().add(label3);
		
		JLabel label4 = new JLabel("");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBounds(742, 343, 44, 62);
		label4.setBorder(border);
		getContentPane().add(label4);
		
		JLabel label5 = new JLabel("");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setBounds(904, 343, 44, 62);
		label5.setBorder(border);
		getContentPane().add(label5);
		
		JLabel label6 = new JLabel("");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setBounds(688, 274, 44, 62);
		label6.setBorder(border);
		getContentPane().add(label6);
		
		JLabel label7 = new JLabel("");
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		label7.setBounds(742, 274, 44, 62);
		label7.setBorder(border);
		getContentPane().add(label7);
		
		JLabel label8 = new JLabel("");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setBounds(796, 274, 44, 62);
		label8.setBorder(border);
		getContentPane().add(label8);
		
		JLabel label9 = new JLabel("");
		label9.setHorizontalAlignment(SwingConstants.CENTER);
		label9.setBounds(850, 274, 44, 62);
		label9.setBorder(border);
		getContentPane().add(label9);
		
		JLabel label10 = new JLabel("");
		label10.setHorizontalAlignment(SwingConstants.CENTER);
		label10.setBounds(904, 274, 44, 62);
		label10.setBorder(border);
		getContentPane().add(label10);
		
		manoCPU3 = new ArrayList<JLabel>();
		manoCPU3.add(label6);
		manoCPU3.add(label7);
		manoCPU3.add(label8);
		manoCPU3.add(label9);
		manoCPU3.add(label10);
		manoCPU3.add(label3);
		manoCPU3.add(label4);
		manoCPU3.add(label1);
		manoCPU3.add(label2);
		manoCPU3.add(label5);
		
		JLabel label11 = new JLabel("");
		label11.setHorizontalAlignment(SwingConstants.CENTER);
		label11.setBounds(49, 274, 44, 62);
		label11.setBorder(border);
		getContentPane().add(label11);
		
		JLabel label12 = new JLabel("");
		label12.setHorizontalAlignment(SwingConstants.CENTER);
		label12.setBounds(49, 343, 44, 62);
		label12.setBorder(border);
		getContentPane().add(label12);
		
		JLabel label13 = new JLabel("");
		label13.setHorizontalAlignment(SwingConstants.CENTER);
		label13.setBounds(103, 274, 44, 62);
		label13.setBorder(border);
		getContentPane().add(label13);
		
		JLabel label14 = new JLabel("");
		label14.setHorizontalAlignment(SwingConstants.CENTER);
		label14.setBounds(103, 343, 44, 62);
		label14.setBorder(border);
		getContentPane().add(label14);
		
		JLabel label15 = new JLabel("");
		label15.setHorizontalAlignment(SwingConstants.CENTER);
		label15.setBounds(157, 274, 44, 62);
		label15.setBorder(border);
		getContentPane().add(label15);
		
		JLabel label16 = new JLabel("");
		label16.setHorizontalAlignment(SwingConstants.CENTER);
		label16.setBounds(157, 343, 44, 62);
		label16.setBorder(border);
		getContentPane().add(label16);
		
		JLabel label17 = new JLabel("");
		label17.setHorizontalAlignment(SwingConstants.CENTER);
		label17.setBounds(211, 274, 44, 62);
		label17.setBorder(border);
		getContentPane().add(label17);
		
		JLabel label18 = new JLabel("");
		label18.setHorizontalAlignment(SwingConstants.CENTER);
		label18.setBounds(211, 343, 44, 62);
		label18.setBorder(border);
		getContentPane().add(label18);
		
		JLabel label19 = new JLabel("");
		label19.setHorizontalAlignment(SwingConstants.CENTER);
		label19.setBounds(265, 274, 44, 62);
		label19.setBorder(border);
		getContentPane().add(label19);
		
		JLabel label20 = new JLabel("");
		label20.setHorizontalAlignment(SwingConstants.CENTER);
		label20.setBounds(265, 343, 44, 62);
		label20.setBorder(border);
		getContentPane().add(label20);
		
		manoCPU2 = new ArrayList<JLabel>();
		manoCPU2.add(label11);
		manoCPU2.add(label13);
		manoCPU2.add(label15);
		manoCPU2.add(label17);
		manoCPU2.add(label19);
		manoCPU2.add(label12);
		manoCPU2.add(label14);
		manoCPU2.add(label16);
		manoCPU2.add(label18);
		manoCPU2.add(label20);
		
		JLabel label21 = new JLabel("");
		label21.setHorizontalAlignment(SwingConstants.CENTER);
		label21.setBounds(373, 11, 44, 62);
		label21.setBorder(border);
		getContentPane().add(label21);
		
		JLabel label22 = new JLabel("");
		label22.setHorizontalAlignment(SwingConstants.CENTER);
		label22.setBounds(373, 80, 44, 62);
		label22.setBorder(border);
		getContentPane().add(label22);
		
		JLabel label23 = new JLabel("");
		label23.setHorizontalAlignment(SwingConstants.CENTER);
		label23.setBounds(427, 11, 44, 62);
		label23.setBorder(border);
		getContentPane().add(label23);
		
		JLabel label24 = new JLabel("");
		label24.setHorizontalAlignment(SwingConstants.CENTER);
		label24.setBounds(427, 80, 44, 62);
		label24.setBorder(border);
		getContentPane().add(label24);
		
		JLabel label25 = new JLabel("");
		label25.setHorizontalAlignment(SwingConstants.CENTER);
		label25.setBounds(481, 11, 44, 62);
		label25.setBorder(border);
		getContentPane().add(label25);
		
		JLabel label26 = new JLabel("");
		label26.setHorizontalAlignment(SwingConstants.CENTER);
		label26.setBounds(481, 80, 44, 62);
		label26.setBorder(border);
		getContentPane().add(label26);
		
		JLabel label27 = new JLabel("");
		label27.setHorizontalAlignment(SwingConstants.CENTER);
		label27.setBounds(535, 11, 44, 62);
		label27.setBorder(border);
		getContentPane().add(label27);
		
		JLabel label28 = new JLabel("");
		label28.setHorizontalAlignment(SwingConstants.CENTER);
		label28.setBounds(535, 80, 44, 62);
		label28.setBorder(border);
		getContentPane().add(label28);
		
		JLabel label29 = new JLabel("");
		label29.setHorizontalAlignment(SwingConstants.CENTER);
		label29.setBounds(589, 11, 44, 62);
		label29.setBorder(border);
		getContentPane().add(label29);
		
		JLabel label30 = new JLabel("");
		label30.setHorizontalAlignment(SwingConstants.CENTER);
		label30.setBounds(589, 80, 44, 62);
		label30.setBorder(border);
		getContentPane().add(label30);
		
		manoCPU1 = new ArrayList<JLabel>();
		manoCPU1.add(label21);
		manoCPU1.add(label23);
		manoCPU1.add(label25);
		manoCPU1.add(label27);
		manoCPU1.add(label29);
		manoCPU1.add(label22);
		manoCPU1.add(label24);
		manoCPU1.add(label26);
		manoCPU1.add(label28);
		manoCPU1.add(label30);
		
		JLabel label31 = new JLabel("");
		label31.setHorizontalAlignment(SwingConstants.CENTER);
		label31.setBounds(515, 311, 44, 62);
		label31.setBorder(border);
		getContentPane().add(label31);
		
		label31.setIcon(new ImageIcon("src\\model\\Carte\\Retro_Carta.png"));
		
		label32 = new JLabel("");
		label32.setHorizontalAlignment(SwingConstants.CENTER);
		label32.setBounds(441, 311, 44, 62);
		label32.setBorder(border);
		getContentPane().add(label32);
		
		bottone11 = new JButton("Pesca dagli scarti");
		bottone11.setFont(new Font("Arial",Font.BOLD,12));
		bottone11.setBounds(742, 620, 148, 30);
		bottone11.setFocusable(false);
		getContentPane().add(bottone11);
		
		bottone12 = new JButton("Scarta");
		bottone12.setFont(new Font("Arial",Font.BOLD,12));
		bottone12.setBounds(742, 579, 148, 30);
		bottone12.setFocusable(false);
		getContentPane().add(bottone12);
		
		bottone13 = new JButton("Pesca dal mazzo");
		bottone13.setFont(new Font("Arial",Font.BOLD,12));
		bottone13.setBounds(742, 661, 148, 30);
		bottone13.setFocusable(false);
		getContentPane().add(bottone13);
		
		label34 = new JLabel("");
		label34.setHorizontalAlignment(SwingConstants.CENTER);
		label34.setBounds(300, 597, 44, 62);
		label34.setBorder(border);
		getContentPane().add(label34);
		
		label35 = new JLabel();
		label35.setHorizontalAlignment(SwingConstants.CENTER);
		label35.setBounds(441, 538, 120, 14);
		getContentPane().add(label35);
		
		label36 = new JLabel("CPU 2");
		label36.setBounds(163, 242, 92, 14);
		getContentPane().add(label36);
		
		JLabel label37 = new JLabel("CPU 1");
		label37.setBounds(487, 163, 92, 14);
		getContentPane().add(label37);
		
		label38 = new JLabel("CPU 3");
		label38.setBounds(796, 242, 92, 14);
		getContentPane().add(label38);
		
		label39 = new JLabel("");
		label39.setBounds(470, 463, 64, 64);
		label39.setBorder(border);
		getContentPane().add(label39);
		
	}
	/**
	 * method capable of showing a particular message to the user
	 * @param messaggio, the message
	 */
	public void showMessage(String messaggio) {
		JOptionPane.showMessageDialog(frame,messaggio);
	}
	/**
	 * method capable of showing a particular error to the user
	 * @param messaggio, the error
	 * @param nome, the name of the panel shown
	 */
	public void showErrorMessage(String messaggio,String nome) {
		JOptionPane.showMessageDialog(frame,messaggio,nome,JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * getter of the mainMenuView
	 * @return the mainMenuView
	 */
	public MainMenuView getMainMenuView() {
		return main;
	}
	/**
	 * getter of the discard pile label
	 * @return the label of the discard pile
	 */
	public JLabel getSpazioScarti() {
		return label32;
	}
	/**
	 * getter of the hand
	 * @return the label related to the single card in your temporary hand
	 */
	public JLabel getSpazioMano() {
		return label34;
	}
	/**
	 * getter of the profileSpace
	 * @return the label for the profile image
	 */
	public JLabel getSpazioProfilo() {
		return label39;
	}
	/**
	 * getter of the space for the user's nickname
	 * @return the label
	 */
	public JLabel getSpazioNomeGiocatore() {
		return label35;
	}
	/**
	 * getter of cpu2
	 * @return the label of cpu2's name
	 */
	public JLabel getCPU2() {
		return label36;
	}
	/**
	 * getter of cpu3
	 * @return the label of cpu3's name
	 */
	public JLabel getCPU3() {
		return label38;
	}
	/**
	 * getter
	 * @return the list of buttons of the player
	 */
	public ArrayList<JButton> getManoPlayer(){
		return bottoniPlayer;
	}
	/**
	 * getter
	 * @return the labels of cpu1's hand
	 */
	public ArrayList<JLabel> getManoCPU1(){
		return manoCPU1;
	}
	/**
	 * getter
	 * @return the labels of cpu2's hand
	 */
	public ArrayList<JLabel> getManoCPU2(){
		return manoCPU2;
	}
	/**
	 * getter
	 * @return the labels of cpu1's hand
	 */
	public ArrayList<JLabel> getManoCPU3(){
		return manoCPU3;
	}
	/**
	 * getter
	 * @return the audioManager
	 */
	public AudioManager getAudioManager() {
		return audioManager;
	}
	/**
	 * getter
	 * @return the button to draw from the deck
	 */
	public JButton getBottonePescaMazzo() {
		return bottone13;
	}
	/**
	 * getter
	 * @return the button to draw from the discard pile
	 */
	public JButton getBottonePescaScarti() {
		return bottone11;
	}
	/**
	 * getter
	 * @return the button to discard your temporary hand
	 */
	public JButton getBottoneScarta() {
		return bottone12;
	}
	/**
	 * the action performed method, used as usual for the top menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== homeItem) {
			main.setVisible(true);
			this.dispose();
		}
		if (e.getSource()== exitItem) {
			System.exit(0);
		}
	}
}
