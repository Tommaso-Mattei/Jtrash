package model;
/**
 * 
 * The class that represents a playing card
 */
public class Carta {
	
	private SemeCarta seme;
	private ValoreCarta valore;
	private Boolean scoperta;
	/**
	 * The constructor
	 * @param seme , suit of the card
	 * @param valore , value of the card as an integer
	 */
	public Carta(SemeCarta seme, ValoreCarta valore) {
		this.seme = seme;
		this.valore = valore;
		scoperta = false;
	}
	/**
	 * 
	 * @return the suit
	 */
	public SemeCarta getSeme() {
		return this.seme;
	}
	/**
	 * 
	 * @return the value of the card
	 */
	public ValoreCarta getValore() {
		return this.valore;
	}
	/**
	 * 
	 * @return scoperta, that is the variable that tells us if a card is face up(true) or not (false)
	 */
	public Boolean getVerso() {
		return scoperta;
	}
	
	/**
	 * The method flips the card, inverting the scoperta boolean
	 */
	public void flip() {
		scoperta = !scoperta;
	}
	/**
	 * returns the card as a string with suit and value in it
	 */
	@Override
	public String toString() {
		return seme.getSeme() + "_" + valore.getValStringa();
	}
}


