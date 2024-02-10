package model;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;
/**
 * the panel in which an animation is used
 */
public class AnimationPanel extends JPanel implements ActionListener{
	
	final int larghezza = 400;
	final int altezza = 240;
	Image joker;
	Timer timer;
	int velocitàX = 1;
	int velocitàY = 1;
	int x = 0;
	int y = 0;
	/**
	 * The constructor in which the panel sized is initialized and the image to animate is created.
	 * A timer is created and started which will be usefuel for animating the image;
	 */
	AnimationPanel(){
		this.setPreferredSize(new Dimension(larghezza,altezza));
		joker = new ImageIcon("src\\model\\Images\\Joker_Queen.png").getImage();
		timer = new Timer(10,this);
		timer.start();
	}
	/**
	 * The paint method paints and changes the image creating the animation. It's automatically invoked.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(joker,x,y,null);
	}
	/**
	 * The action performed by the timer, it moves the image throughout the panel, stopping if the border of the panel is reached and bouncing back by inverting the sign.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (x >= larghezza - joker.getWidth(null) || x<0) {
			velocitàX = velocitàX * -1;
		}
		x = x+velocitàX;
		
		if (y >= altezza - joker.getHeight(null) || y<0) {
			velocitàY = velocitàY * -1;
		}
		y = y+velocitàY;
		repaint();
		
	}
}
