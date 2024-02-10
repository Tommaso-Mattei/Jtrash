package model;


/**
 * the CPU class which is a subclass of genericPlayer
 */
public class CPU extends GenericPlayer{
	
	public CPU(String nome) {
		super(nome);
	}
	
	/**
	 * method used by the cpu to set a single card 
	 * @param mazzo , the deck used by the game
	 * @return boolean, if setting the card was successful or not
	 */
	public Boolean setCard(Mazzo mazzo) {
		int valore = appoggio.getValore().getVal();
		int valore1 = valore-1;
		if (Integer.valueOf(valore).equals(13)) {
			for (int i = 0; i < mano.size();i++) {
				Carta carta = mano.get(i);
				if (carta.getVerso() == false) {
					mano.set(i, appoggio);
					appoggio = carta;
					appoggio.flip();    
					return true;
				}
			}
		}
		if (Integer.valueOf(valore).equals(11) || Integer.valueOf(valore).equals(12) || valore > mano.size()) {
			return false;
		}
		Carta carta = mano.get(valore1);
		Boolean scoperto = carta.getVerso();
		if (scoperto ==true) { 
			return false;
		}
		else {
			 mano.set(valore1, appoggio);
			 appoggio = carta;
			 appoggio.flip();    
			 return true;
		}
	}
}
