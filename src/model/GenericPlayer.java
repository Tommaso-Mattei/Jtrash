package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * class that represents a generic player
 */
public class GenericPlayer {
	
	protected String nome;
	protected ArrayList<Carta> mano;
	protected Carta appoggio;	
	/**
	 * constructor, it initializes the hand with 10 spaces
	 * @param nome, name of the player
	 */
	public GenericPlayer(String nome) {
		this.nome = nome;
		mano = new ArrayList<Carta>(10);
		for (int i = 0; i < 10; i++) {
			mano.add(null);
		}
	} 
	/**
	 * getter
	 * @return player's name
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * getter
	 * @return player's hand as a readable string
	 */
	public String getManoR() {
		return mano.toString();
	}
	/**
	 * getter
	 * @return the hand of the player as a list of cards
	 */
	public ArrayList<Carta> getMano() {
		return mano;
	}
	/**
	 * getter
	 * @return the temporary hand of the player
	 */
	public Carta getAppoggio() {
		return appoggio;
	}
	/**
	 * setter
	 * @param carta, the card to put in the temporary hand
	 */
	public void setAppoggio(Carta carta) {
		appoggio = carta;
	}
	/**
	 * method used to discard a card from the temporary hand to the discard pile;
	 * @param mazzo, the deck used in the game
	 * @param turno, the current turn
	 * @param game, the game being played
	 * @throws ImpossibleDiscardException, exception when discarding without anything in hand
	 */
	public void discard(Mazzo mazzo, int turno, Game game)throws ImpossibleDiscardException{
		ArrayList<GenericPlayer> players = game.getPlayers();
		Stack<Carta> scarti = mazzo.getScarti();
		if (appoggio != null) {
			scarti.push(appoggio);	
			appoggio = null;
			if (turno < players.size()-1) {
				turno = turno+1;
				game.setTurno(turno);
			}
			else {
				game.setTurno(0);
			}
		}
		else{
			throw new ImpossibleDiscardException();
		}
	}
	/**
	 * method that checks if the player has done trash
	 * @return a boolean; true if trash, false if not
	 */
	public Boolean checkTrash() {
		for (int i = 0; i < mano.size();i++) {
			Carta carta = mano.get(i);
			if (carta.getVerso()) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	/**
	 * method that through a stream reduces the size of the hand of the player
	 */
	public void reduceMano() {
		mano = mano.subList(0, mano.size()-1).stream().collect(Collectors.toCollection(ArrayList::new));
	}
	/**
	 * setter that will be modified by the subclasses
	 * @return a meaningless boolean
	 */
	public Boolean setCard() {
		return false;
	}
	/**
	 * exception related to discarding when the hand is empty
	 */
	class ImpossibleDiscardException extends Exception{
		
		public ImpossibleDiscardException() {
			super();
		}
	}
}
