package model;

import java.util.ArrayList;
import java.util.Stack;

import model.Game.TooManyPlayersException;
/**
 * class of the player used by the user
 */
public class Giocatore extends GenericPlayer{
	
	private Utente utente;
	/**
	 * constructor
	 * @param nome , name of the player
	 */
	public Giocatore(String nome) {
		super(nome);
	}
	/**
	 * setter
	 * @param utente, the user of the application
	 */
	public void setGiocatore(Utente utente) {
		this.utente = utente;
	}
	/**
	 * getter
	 * @return the user of the application related to the player
	 */
	public Utente getUtente() {
		return utente;
	}
	/**
	 * getter that returns the name;
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * getter that makes the player's hand readable
	 */
	public String getManoR() {
		return mano.toString();
	}
	/**
	 * getter that returns the player's hand
	 */
	public ArrayList<Carta> getMano() {
		return mano;
	}
	/**
	 * getter that return the player's temporary hand
	 */
	public Carta getAppoggio() {
		return appoggio;
	}
	/**
	 * setter that sets the player's temporary hand
	 */
	public void setAppoggio(Carta carta) {
		appoggio = carta;
	}
	/**
	 * method that sets a card in the player's hand. It handles all the possible card types and calls the trashRoutine()
	 * @param mazzo, the deck used in the game
	 * @param posizione, the position for the card chosen by the user
	 * @param game , the game being played
	 * @throws WrongPositionException , exception for wrong position
	 * @throws ImpossibleSetException, exception for impossible sets
	 * @throws TooManyPlayersException, exception for too many players
	 */
	public void setCard (Mazzo mazzo, int posizione, Game game) throws WrongPositionException, ImpossibleSetException, TooManyPlayersException{
		if (appoggio == null) {
			throw new ImpossibleSetException("Impossibile mettere una carta con la mano vuota, è necessario prima pescare");
		}
		int valore = appoggio.getValore().getVal();
		int valore1 = valore-1;
		if (Integer.valueOf(valore).equals(13)) {
			Carta carta = mano.get(posizione-1);
			if (carta.getVerso() == false) {
				mano.set(--posizione, appoggio);
				appoggio = carta;
				appoggio.flip(); 
			}
			else {
				throw new ImpossibleSetException("Impossibile mettere una carta dove è presente già una carta scoperta");
			}
		}
		else if (Integer.valueOf(valore).equals(11) || Integer.valueOf(valore).equals(12)) {
			throw new WrongPositionException("Non esiste una posizione per il Jack o la regina!",posizione);
		}
		else {
			if (Integer.valueOf(valore).equals(posizione)) {
				Carta carta = mano.get(valore1);
				Boolean scoperto = carta.getVerso();
				if (scoperto.equals(true))  {
					throw new ImpossibleSetException("Impossibile mettere una carta dove è presente già una carta scoperta");
				 }
				 mano.set(valore1, appoggio);
				 appoggio = carta;
				 appoggio.flip();
			}
			else {
				throw new WrongPositionException("La posizione scelta per la carta è sbagliata!",posizione);
			}
		}
		game.trashRoutine();
	} 
	/**
	 * method that discards from the temporary hand
	 */
	@Override
	public void discard(Mazzo mazzo, int turno, Game game) throws ImpossibleDiscardException{
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
			if (Integer.valueOf(turno).equals(1)) {
				try {
					game.turnCPU();
				} catch (TooManyPlayersException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			throw new ImpossibleDiscardException();
		}
	}
	/**
	 * Exception for choosing the wrong card position
	 */
	class WrongPositionException extends Exception{
		
		int input;
		
		public WrongPositionException(String messaggio, int input) {
			super(messaggio);
			this.input = input;
		}
	} 
	/**
	 * exception for trying to set something impossible
	 */
	class ImpossibleSetException extends Exception{
		
		public ImpossibleSetException(String messaggio) {
			super(messaggio);
		}
	}
}
