package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Game.TooManyPlayersException;
/**
 * window that shows up to input the amount of players in a game
 */
public class IntermediateWindow extends JFrame implements ActionListener{
	
	JButton button;
	JTextField textField;
	MainMenuView main;
	String idPlayer;
	Utente utente;
	JFrame frame;
	/**
	 * constructor, sets the icon, the button to press and the textfield to input the number of players
	 * @param idPlayer, the id of the user
	 * @param main, the main menu view
	 * @param utente , the user
	 */
	IntermediateWindow(String idPlayer, MainMenuView main, Utente utente){
		this.main = main;
		this.idPlayer = idPlayer;
		this.utente = utente;
		this.setTitle("Jtrash - Players count");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		button = new JButton("Invio");
		button.addActionListener(this);
		button.setFocusable(false);
		
		
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250,40));
		textField.setFont(new Font("Comic Sans",Font.BOLD,25));
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBackground(Color.BLACK);
		textField.setCaretColor(Color.WHITE);
		textField.setText("N. giocatori totali");
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.setIconImage(icona.getImage());
		this.add(button);
		this.add(textField);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	/**
	 * the action performed method.
	 * It instantiates the game model, the game view and connects them through the game controller.
	 * It starts the routine of the controller to show something on the view and reproduces the music for the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button) {
			int NPlayers = Integer.parseInt(textField.getText());
			try {
				Game model = new Game(NPlayers,utente);
				main.setVisible(false);
				GameView view = new GameView(main);
				view.setVisible(true); 
				GameController gc = new GameController(view,model);
				gc.controllerBehaviour();
				gc.reproduceMainGameMusic();
				} catch (TooManyPlayersException e1) {
					JOptionPane.showMessageDialog(frame,"Hai inserito un numero errato di giocatori totali, riprova!","Troppi giocatori.",JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}
		
	}
}
