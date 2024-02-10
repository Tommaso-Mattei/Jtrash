package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

/**
 * the final frame shown after winning or losing the trash game.
 * It implements an action listener for the button and uses the animation panel to show a little final animation and the result of the game.
 * It's possible to go back to the main menu through the menu.
 */
public class FinalFrame extends JFrame implements ActionListener{
	
	MainMenuView main;
	ImageIcon exitIcon;
	ImageIcon homeIcon;
	ImageIcon profileIcon;
	JMenuItem exitItem;
	JMenuItem homeItem;
	JButton bottone90;
	JButton bottone91;
	GameView gameView;
	AudioManager am;
	AnimationPanel ap;
	/**
	 * The top menu is used as usual and a single label shows the message; the rest is up to the animationPanel.
	 * @param main, the main menu
	 * @param gameView, the view of the trash game
	 * @param message , the message to show on it
	 */
	FinalFrame(MainMenuView main,GameView gameView, String message){
		ap = new AnimationPanel();
		getContentPane().add(ap);
		am = AudioManager.getInstance();
		this.main = main;
		this.gameView = gameView;
		this.setTitle("Jtrash - End");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.lightGray);
		this.setLocationRelativeTo(null);
		
		JLabel label = new JLabel(message);
		label.setFont(new Font("Arial", Font.PLAIN, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(21, 35, 309, 48);
		ap.add(label);
		
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
		this.setVisible(true);
}
	/**
	 * the action performed method, using the audio manager and redirecting where it needs to.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== homeItem) {
			am.play("src\\model\\audioAssets\\cuckoo.wav");
			main.setVisible(true);
			gameView.dispose();
			this.dispose();
		}
		if (e.getSource()== exitItem) {
			am.play("src\\model\\audioAssets\\cuckoo.wav");
			System.exit(0);
		}
	}
}
