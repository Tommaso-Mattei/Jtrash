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
/**
 * the first frame shown to the user where you input the id
 */
public class StartUpIDFrame extends JFrame implements ActionListener{
	
	JButton button;
	JTextField textField;
	/**
	 * contrsuctor of the frame where the button and label are created
	 */
	StartUpIDFrame(){
		
		this.setTitle("Jtrash");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		textField.setText("Username");
		
		ImageIcon icona = new ImageIcon("src\\model\\Images\\JtrashIcon2.png");
		this.setIconImage(icona.getImage());
		this.add(button);
		this.add(textField);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	/**
	 * action performed where the main menu is created and the id is 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button) {
			String nomePlayer = textField.getText();
			new MainMenuView(nomePlayer).setVisible(true);
			this.dispose();
			
		}
	}
}
