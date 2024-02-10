package model;

import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * class of the main menu frame
 */
public class MainMenuView extends JFrame implements ActionListener{
	
	String idPlayer;
	JMenuItem exitItem;
	ImageIcon exitIcon;
	JButton bottone1;
	JButton bottone2;
	JButton bottone3;
	JLabel label;
	JLabel label2;
	ProfileView profilo;
	Utente modelloProfilo;
	AudioManager audioManager;
	AnimationPanel animationPanel;
	/**
	 * constructor of the main menu; it creates all the labels and buttons.
	 * The model and the view of the profile are created but hidden, the controller connects them and starts a routine.
	 * @param idPlayer, the id of the user
	 */
	MainMenuView(String idPlayer){
		audioManager = AudioManager.getInstance();
		this.idPlayer = idPlayer;
		this.setTitle("Jtrash - Menù");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,768);
		this.setResizable(false);
		
		exitIcon = new ImageIcon("src\\model\\menuAssets\\Exit.png");
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Menù");
		exitItem = new JMenuItem("EXIT");
		exitItem.addActionListener(this);
		exitItem.setIcon(exitIcon);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.getContentPane().setBackground(Color.lightGray);
		this.setLocationRelativeTo(null);
		
		
		label = new JLabel();
		ImageIcon playingCards = new ImageIcon("src\\model\\menuAssets\\MenuItemCard3.png");
		label.setIcon(playingCards);
		label.setText("TRASH!");
//		label.setHorizontalTextPosition(JLabel.RIGHT); //set text LEFT,CENTER,RIGHT of imageicon
//		label.setVerticalTextPosition(JLabel.CENTER); //set text TOP, CENTER, BOTTOM of imageicon
		label.setForeground(Color.black);
		label.setFont(new Font("Arial",Font.BOLD,80));
		label.setIconTextGap(10);
		label.setBackground(Color.lightGray);
		label.setOpaque(true);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(264,10,500,100);
		
		label2 = new JLabel();
		label2.setText("Benvenuto "+ idPlayer + "...");
		label2.setFont(new Font("Arial",Font.PLAIN,22));
		label2.setVerticalAlignment(JLabel.TOP);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(364,150,300,70);
		
		bottone1 = new JButton();
		bottone1.setBounds(414,230,200,100);
		bottone1.addActionListener(this);
		bottone1.setText("Nuova partita");
		bottone1.setFocusable(false);
		bottone1.setFont(new Font("Arial",Font.BOLD,20));
		
		bottone2 = new JButton();
		bottone2.setBounds(414,380,200,100);
		bottone2.addActionListener(this);
		bottone2.setText("Profilo");
		bottone2.setFocusable(false);
		bottone2.setFont(new Font("Arial",Font.BOLD,20));
		
		bottone3 = new JButton();
		bottone3.setBounds(414,530,200,100);
		bottone3.addActionListener(this);
		bottone3.setText("Esci");
		bottone3.setFocusable(false);
		bottone3.setFont(new Font("Arial",Font.BOLD,20));
		
		getContentPane().setLayout(null);
		getContentPane().add(label);
		getContentPane().add(label2);
		getContentPane().add(bottone1);
		getContentPane().add(bottone2);
		getContentPane().add(bottone3);
		
		this.setIconImage(icona.getImage());
		this.setVisible(true);
		
		modelloProfilo = new Utente(idPlayer);
		profilo = new ProfileView(idPlayer, this);
		profilo.setVisible(false);
		ProfileController controllerProfilo = new ProfileController(profilo,modelloProfilo);
		controllerProfilo.controllerBehaviour();
	}
	/**
	 * getter
	 * @return the button for the profile
	 */
	public JButton getBottone() {
		return bottone2;
	}
	/**
	 * action performed for the buttons.
	 * botton1 goes to the window that will create the game, the other will show the profile
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== exitItem) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			System.exit(0);
		}
		if (e.getSource()== bottone3) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			System.exit(0);
			
		}
		if (e.getSource()== bottone2) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			profilo.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource()== bottone1) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			new IntermediateWindow(idPlayer,this,modelloProfilo).setVisible(true);;
		}
		
	}
}
