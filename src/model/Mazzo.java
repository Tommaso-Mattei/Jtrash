package model;
   
import java.util.*;
/**
 * deck class
 */
public class Mazzo {
	
	private Carta[] mazzo;
	private int cursore;
	private Stack<Carta> scarti;
	private int Nmazzi;
	/**
	 * constructor that creates the deck and also fills it
	 * @param Nmazzi, the number of decks that compose the deck
	 */
	public Mazzo(int Nmazzi) {
		this.Nmazzi = Nmazzi;
		mazzo = new Carta[52*Nmazzi];
		riempimento();
	}
	/**
	 * the method that fills the deck
	 */
	public void riempimento() {
		int i = 0;
		for (int j = 0; j < Nmazzi;j++) {
			for (SemeCarta seme: SemeCarta.values()) {
				for (ValoreCarta valore: ValoreCarta.values()) {
					mazzo[i] = new Carta(seme,valore);
					i++;
				}
			}
		}
	}
	/**
	 * method that shuffles the deck
	 */
	public void mescola() {
		Random r = new Random();
		for (int i = 0;i < mazzo.length;i++) {
			int t=r.nextInt(mazzo.length);
			Carta appoggio=mazzo[i];
			mazzo[i]=mazzo[t];
			mazzo[t]=appoggio;
		}
	cursore = 0;
	}
	/**
	 * method that gets the next card in the deck
	 * @return a card
	 */
	public Carta nextCard() {
		return (cursore < mazzo.length)? mazzo[cursore++]:null;
	}
	/**
	 * getter
	 * @return cursor of the deck
	 */
	public int getCursor() {
		return this.cursore;
	}
	/**
	 * getter
	 * @return the deck
	 */
	public Carta[] getMazzo() {
		return this.mazzo;
	}
	/**
	 * getter
	 * @return the discard pile
	 */
	public Stack<Carta> getScarti() {
		return scarti;
	}
	/**
	 * method that draws from the deck
	 * @param genericPlayer, the player that is drawing
	 */
	public void pesca(GenericPlayer genericPlayer) {
		genericPlayer.setAppoggio(nextCard());
		genericPlayer.getAppoggio().flip();
	}
	/**
	 * method that draws from the discard pile
	 * @param genericPlayer, the player that is drawing
	 */
	public void pescaScarti(GenericPlayer genericPlayer) {
		genericPlayer.setAppoggio(scarti.pop());
	}
	/**
	 * method that fills the hand with cards from the deck
	 * @param genericPlayer , the player whose hand is being filled
	 */
	public void startingHand(GenericPlayer genericPlayer) {
		ArrayList<Carta> mano = genericPlayer.getMano();
		for (int i = 0; i < mano.size();i++) {
			 mano.set(i,nextCard());
		}
	}  
	
	public void firstDiscard() {
		Carta fCard = nextCard();
//		System.out.println(fCard.toString());
		fCard.flip();
		scarti = new Stack<Carta>();
		scarti.push(fCard);
		
	}
	
	
}
