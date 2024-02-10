package model;

import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
/**
 * view of the profile
 */
public class ProfileView extends JFrame implements ActionListener{
	
	String profileId;
	ImageIcon exitIcon;
	ImageIcon homeIcon;
	ImageIcon profileIcon;
	JMenuItem exitItem;
	JMenuItem homeItem;
	MainMenuView mainMenu;
	JLabel label;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JButton bottone1;
	JButton bottone2;
	ChooseNicknameView nickView;
	AudioManager audioManager;
	/**
	 * constructor of the view, creates all the buttons and the labels shown with also the information passed through the controller
	 * @param profileId , the id of the user
	 * @param mainMenu , the main menu frame
	 */
	ProfileView(String profileId, MainMenuView mainMenu){
		audioManager = AudioManager.getInstance();
		this.mainMenu = mainMenu;
		this.profileId = profileId;
		nickView = new ChooseNicknameView(profileId,mainMenu);
		nickView.setVisible(false);
		this.setTitle("Jtrash - Profilo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,768);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.lightGray);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
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
		
		label = new JLabel();
		label.setText("PROFILO UTENTE");
		label.setForeground(Color.black);
		label.setFont(new Font("Arial",Font.BOLD,50));
		label.setIconTextGap(10);
		label.setBackground(Color.lightGray);
		label.setOpaque(true);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(264,10,500,100);
		this.add(label);
		
		label2 = new JLabel();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		label2.setBounds(214,111,600,300);
		label2.setBorder(border);
		this.add(label2);
		
		
		label3 = new JLabel();
		int height = (label2.getHeight()-20)/2;
		label3.setBounds(10,10,400,height);
		label3.setBorder(border);
		label3.setFont(new Font("Arial",Font.BOLD,18));
		label3.setBackground(Color.lightGray);
		label3.setOpaque(true);
		label3.setVerticalAlignment(JLabel.TOP);
		label3.setHorizontalAlignment(JLabel.LEFT);
		label3.setHorizontalTextPosition(JLabel.RIGHT); //set text LEFT,CENTER,RIGHT of imageicon
		label3.setVerticalTextPosition(JLabel.CENTER);
		label2.add(label3);
		
		label4 = new JLabel();
		label4.setBounds(10,10+height,400,height);
		label4.setBorder(border);
		label4.setFont(new Font("Arial",Font.BOLD,18));
		label4.setBackground(Color.lightGray);
		label4.setOpaque(true);
		label4.setVerticalAlignment(JLabel.TOP);
		label4.setHorizontalAlignment(JLabel.LEFT);
		label2.add(label4);
		
		bottone1 = new JButton();
		bottone1.setBounds(628,130,180,100);
		bottone1.addActionListener(this);
		bottone1.setText("Cambia Nickname");
		bottone1.setFocusable(false);
		bottone1.setFont(new Font("Arial",Font.BOLD,16));
		
		bottone2 = new JButton();
		bottone2.setBounds(628,290,180,100);
		bottone2.addActionListener(this);
		bottone2.setText("Cambia avatar");
		bottone2.setFocusable(false);
		bottone2.setFont(new Font("Arial",Font.BOLD,16));
		
		this.add(bottone1);
		this.add(bottone2);
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.setIconImage(icona.getImage());
		this.setVisible(true);
	}
	/**
	 * getter
	 * @return button related to changing the nickname
	 */
	public JButton getBottone() {
		return bottone2;
	}
	/**
	 * getter
	 * @return label of the profilePic
	 */
	public JLabel getProfile() {
		return this.label3;
	}
	/**
	 * getter
	 * @return label of the stats
	 */
	public JLabel getStatistiche() {
		return this.label4;
	}
	
	/**
	 * getter
	 * @return the frame where you choose the new nickname
	 */
	public ChooseNicknameView getNickView() {
		return this.nickView;
	}
	/**
	 * getter
	 * @return the frame of the main menu
	 */
	public MainMenuView getMainMenuView() {
		return this.mainMenu;
	}
	/**
	 * action performed with the usual audioManager stuff
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== homeItem) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			mainMenu.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource()== exitItem) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			System.exit(0);
		}
		if (e.getSource()== bottone1) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
			nickView.setVisible(true);
		}
		if (e.getSource()== bottone2) {
			audioManager.play("src\\model\\audioAssets\\cuckoo.wav");
		}
		
		
		
	}
}
