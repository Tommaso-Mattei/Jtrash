package model;

import java.util.*;

import model.GenericPlayer.ImpossibleDiscardException;
/**
 * The game class which handles everything related to the Trash game
 */
public class Game {
	private ArrayList<GenericPlayer> players;
	private Giocatore utente;
	private Mazzo mazzo;
	private int Nplayers;
	private int turno;
	private Boolean trash;
	private ArrayList<GenericPlayer> trashPlayers;
	private int firstTrashPlayer;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * The game constructor creates the class Giocatore which is used by the human player; then it's assigned to it the User which is the same between different games.
	 * In the method players are created, same goes for the deck and then the hands of the players are populated.
	 * The first card is discarded by the deck and then useful variables are set such as the turn of the game, the trash state variable, the list of people who got a trash and the turn number of the first person getting trash.
	 * @param Nplayers , the number of players in the game
	 * @param trueUtente , the user of the application
	 * @throws TooManyPlayersException , exception if too many users are passed as input
	 */
	public Game(int Nplayers, Utente trueUtente) throws TooManyPlayersException{
		utente = new Giocatore(trueUtente.getNickname());
		utente.setGiocatore(trueUtente);
		this.Nplayers = Nplayers;
		createPlayers();
		createDeck(Nplayers);
		populateHands(); 
		mazzo.firstDiscard();
		turno = 0;
		trash = false;
		firstTrashPlayer = 6; //un numero a caso che non darà mai vero nei controlli finché il primo giocatore che fa trash lo riscriverà.
		trashPlayers = new ArrayList<GenericPlayer>();
	}
	
	/**
	 * method that registers observer as per the Observer pattern
	 */
	public void registraObserver(Observer o) {
		observers.add(o);
	}
	/**
	 * method that removes observers as per the Observer pattern
	 * @param o, the Observer object
	 */
	public void rimuoviObserver(Observer o) {
		observers.remove(o);
	}
	/**
	 * method that updates the observers as per the Observer pattern
	 * @param i, the input used to differentiate between different calls in the update method inside the observer
	 */
	public void notificaObservers(int i) {
		for (Observer o: observers) o.update(i);
	}
	/**
	 * 
	 * @return the player class inside the game
	 */
	public Giocatore getGiocatore() {
		return utente;
	}
	/**
	 * method that creates the playing card deck
	 * @param Nplayers , number of players
	 * @throws TooManyPlayersException , exception if too many players are passed
	 */
	public void createDeck(int Nplayers) throws TooManyPlayersException{
		if (Nplayers == 2) {
			this.mazzo = new Mazzo(1);
			mazzo.mescola();
		}
		else if (Nplayers == 3 || Nplayers == 4 ) {
			this.mazzo = new Mazzo(2);
			mazzo.mescola();
		}
		else {  
			throw new TooManyPlayersException(Nplayers + " è un numero incorretto di possibili avversari",Nplayers);
		}
	}
	/**
	 * method that adds to the user losses or wins after the game
	 * @param player, the player class inside the game
	 */
	public void winnerResult(GenericPlayer player) {
		if (player instanceof Giocatore){
			((Giocatore)player).getUtente().addW();
			((Giocatore)player).getUtente().addLevel(Nplayers);
			notificaObservers(3);
		}
		else {
			getGiocatore().getUtente().addL();
			notificaObservers(4);
		}
	}
	
	/**
	 * the method that creates the CPU players and adds the player to the list
	 */
	public void createPlayers() {
		players = new ArrayList<GenericPlayer>();
		players.add(utente);
		for (int i = 1;i < Nplayers;i++) {
			players.add(new CPU("Cpu "+String.valueOf(i)));
		}
	}
	/**
	 * the method that populates the hands of the players
	 */
	public void populateHands() {
		for (int i = 0;i < Nplayers;i++) {
			mazzo.startingHand(players.get(i));
		}
	}
	/**
	 * the method that is used by the user to draw a card from the deck
	 * @param inputDF, the input that is equals to either the discard pile or the deck
	 * @param turno, the current turn of the player
	 * @throws ImpossibleDrawException, the exception that handles various possibilities such as if the hand is already full
	 */
	public void drawPhase(int inputDF, int turno) throws ImpossibleDrawException{ //serve la posizione poiché nella fase trash non uso più turno.
		if (!Integer.valueOf(turno).equals(0)) {
			throw new ImpossibleDrawException("Puoi pescare solo durante il tuo turno!");
		}
		if (!(players.get(turno).getAppoggio()== null)) {
			throw new ImpossibleDrawException("Puoi pescare solo se non hai nessuna carta in mano.");
		}
		if (Integer.valueOf(inputDF).equals(14)) { //pesca dal mazzo
			mazzo.pesca(players.get(turno));
		}
		else if (Integer.valueOf(inputDF).equals(15)){//pesca dagli scarti
			if (mazzo.getScarti().isEmpty()){
				throw new ImpossibleDrawException("Non è possibile pescare dagli scarti poiché non è presente nessuna carta");
			}
			players.get(turno).setAppoggio(mazzo.getScarti().pop());
		}
	}
	/**
	 * method that does the turn of every single CPU involved with the game.
	 * It's also responsible for declaring if it's the end of the manche after someone does trash, as well as checking if the CPU has done trash.
	 * @throws TooManyPlayersException, exception related with having too many players
	 */
	public void turnCPU()throws TooManyPlayersException{
		Boolean giro = true;
		while (giro) {
			Boolean turn = true;
			if (!(Integer.valueOf(turno).equals(firstTrashPlayer))) {
				chooseDrawCPU((CPU)players.get(turno));//pesca dal mazzo o dagli scarti in base alla carta a terra.
				while (turn) {
					turn = ((CPU)players.get(turno)).setCard(mazzo);
					notificaObservers(0);
					trashRoutine();
				}
				try {
					players.get(turno).discard(mazzo,turno,this);
				} catch (ImpossibleDiscardException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (players.get(turno) instanceof Giocatore) {
					giro = false;
					if (Integer.valueOf(firstTrashPlayer).equals(0)) {
						fineManche();
					}
				}
			}
			else {
				giro = false;
				fineManche();
			}
		}
	}
	
	/**
	 * method that ends the manche, making the hands of the winners smaller and recreating the initial game state while communicating with the controller class.
	 * @throws TooManyPlayersException, as usual, too many players
	 */
	public void fineManche() throws TooManyPlayersException{
		for (int j = 0; j < players.size();j++) {
			for (int i = 0; i < players.get(j).getMano().size(); i++) {
				players.get(j).getMano().set(i,null);
			}
		}
		for (int i = 0;i < trashPlayers.size();i++) {
			trashPlayers.get(i).reduceMano();
		}
		createDeck(Nplayers);
		populateHands(); 
		mazzo.firstDiscard(); 
		notificaObservers(1);
		trashPlayers.clear();
		trash = false;
		turno = 0;
		firstTrashPlayer = 6; 
	}
	/**
	 * the method that chooses for the cpu from which place to draw (deck or discard pile).
	 * @param cpu, the cpu involved with the act of drawing
	 */
	public void chooseDrawCPU(CPU cpu) {
		if (!(mazzo.getScarti().isEmpty())) {
			Carta carta = mazzo.getScarti().peek();
			int valore = carta.getValore().getVal();
			if (Integer.valueOf(valore).equals(13)) {
				players.get(turno).setAppoggio(mazzo.getScarti().pop());
			}
			else if (Integer.valueOf(valore).equals(11) || Integer.valueOf(valore).equals(12) || valore > players.get(turno).getMano().size()){
				mazzo.pesca(players.get(turno));
			}
			else {  
				int valore1 = valore-1;
				Carta cartaMano = players.get(turno).getMano().get(valore1);
				Boolean scoperto = cartaMano.getVerso();
				if (scoperto.equals(true)) { 
					mazzo.pesca(players.get(turno));
				}
				else {
					players.get(turno).setAppoggio(mazzo.getScarti().pop());
				}
			}
		}
		else {
			mazzo.pesca(players.get(turno));
		}
	}
	/**
	 * the routine that checks if a player has done a trash, setting useful variables for the new game state
	 * Most importantly the method is also responsible for checking if the game has ended and if one of the players has won thanks to winnerResult()
	 */
	public void trashRoutine() {
		if (!trash) {
			Boolean trashCheck = players.get(turno).checkTrash();
			if (trashCheck) {
				if (!(Integer.valueOf(players.get(turno).getMano().size()).equals(1))) {
					trash = true;
					firstTrashPlayer = turno;
					trashPlayers.add(players.get(turno));
					notificaObservers(5);
					if (players.get(turno) instanceof Giocatore) {
						try {
							((Giocatore)players.get(turno)).discard(mazzo, turno, this);
						} catch (ImpossibleDiscardException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					winnerResult(players.get(turno));
				}
			}
		}
		else {
			Boolean trashCheck = players.get(turno).checkTrash();
			if (trashCheck) {
				int conto = 0;
				for (int z=0; z<trashPlayers.size();z++) {
					if (trashPlayers.get(z).equals(players.get(turno))) {
						conto++;
					}
				}
				if (Integer.valueOf(conto).equals(0)) {
					trashPlayers.add(players.get(turno));
				}
				if (players.get(turno) instanceof Giocatore) {
					try {
						((Giocatore)players.get(turno)).discard(mazzo, turno, this);
					} catch (ImpossibleDiscardException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * setter of turn
	 * @param turno, a the turn
	 */
	public void setTurno(int turno) {
		this.turno = turno;
	}
	/**
	 * getter of turn
	 * @return the current turn
	 */
	public int getTurno() {
		return turno;
	}
	/**
	 * getter of the deck
	 * @return the deck
	 */
	public Mazzo getMazzo() {
		return mazzo;
	}
	/**
	 * getter of players
	 * @return the list of players
	 */
	public ArrayList<GenericPlayer> getPlayers(){
		return players;
	}
	/**
	 * getter for number of players
	 * @return number of players
	 */
	public int getNplayers() {
		return Nplayers;
	}
	/**
	 * getter of current player
	 * @return the current player
	 */
	public GenericPlayer getCurrentPlayer() {
		return players.get(turno);
	}
	/**
	 * getter of trashPlayers
	 * @return the list of players who in the current turn have done trash
	 */
	public ArrayList<GenericPlayer> getTrashPlayers(){
		return trashPlayers;
	}
	/**
	 * draw exception
	 */
	class ImpossibleDrawException extends Exception{
		
		public ImpossibleDrawException(String messaggio) {
			super(messaggio);
		}
	}
	/**
	 * tooManyPlayers exception
	 */
	class TooManyPlayersException extends Exception{
		
		int Nplayers;
		
		public TooManyPlayersException(String messaggio, int Nplayers) {
			super(messaggio);
			this.Nplayers = Nplayers;
			
		}
	}
}
