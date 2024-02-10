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
import javax.swing.JTextField;
/*
 * The class that shows the frame capable of getting a new nickname for the user which will then be stored and showed inside the profile
 */
public class ChooseNicknameView extends JFrame implements ActionListener{
	
	JButton button;
	JTextField textField;
	String nickname;
	String profileId;
	MainMenuView mainMenu;
	/**
	 * The title of the window is set and textfield plus the button to get the nickname are initialized
	 * as usual the icon for the window is set and the menù on the top of the frame is created
	 * @param profileId , the id of the user
	 * @param mainMenu , the main menu frame
	 */
	ChooseNicknameView(String profileId, MainMenuView mainMenu){
		this.profileId = profileId;
		this.mainMenu = mainMenu;
		this.setTitle("Jtrash - Nickname");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		button = new JButton("Invio");
		button.addActionListener(this);
		button.setFocusable(false);
		
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250,40));
		textField.setFont(new Font("Comic Sans",Font.BOLD,35));
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBackground(Color.BLACK);
		textField.setCaretColor(Color.WHITE);
		textField.setText("Nickname");
		
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.setIconImage(icona.getImage());
		this.add(button);
		this.add(textField);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	/**
	 * 
	 * @return button
	 */
	public JButton getBottone() {
		return this.button;
	}
	/**
	 * 
	 * @return nickname, the nickname string
	 */
	public String getNickname() {
		return this.nickname;
	}
	/**
	 * 
	 * @return textField, the space to write the text in
	 */
	public JTextField getTextField() {
		return this.textField;
	}
	/**
	 * the method which disposes of the window
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button) {
			this.dispose();
		}
		
	}
	
}
