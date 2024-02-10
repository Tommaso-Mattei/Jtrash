package model;
/**
 * enumeration that represents the value of a card
 */
public enum ValoreCarta {
	ASSO(1,"Asso"),
	DUE(2,"Due"),
	TRE(3,"Tre"),
	QUATTRO(4,"Quattro"),
	CINQUE(5,"Cinque"),
	SEI(6,"Sei"),
	SETTE(7,"Sette"),
	OTTO(8,"Otto"),
	NOVE(9,"Nove"),
	DIECI(10,"Dieci"),
	JACK(11,"Jack"),
	QUEEN(12,"Regina"),
	KING(13,"Re");
	
	private final int val;
	private final String valStringa;
	/**
	 * method to set both a string and a number to the enum
	 * @param val , the value in numbers
	 * @param valStringa , the value in words
	 */
	private ValoreCarta(int val,String valStringa) {
		this.val = val;
		this.valStringa = valStringa;
	}
	/**
	 * getter
	 * @return the value 
	 */
	public int getVal() {
		return val;
	} 
	/**
	 * getter
	 * @return the value as string
	 */
	public String getValStringa() {
		return valStringa;
	}
}
