package model;
/**
 * the suit of the card
 */
public enum SemeCarta {
	CUORI("Cuori"),
	QUADRI("Quadri"),
	FIORI("Fiori"),
	PICCHE("Picche");


	private final String nome;
	/**
	 * setter
	 * @param nome , the suit of the card
	 */
	private SemeCarta(String nome) {
		this.nome = nome;
	}
	/**
	 * getter
	 * @return the suit of the card
	 */
	public String getSeme() {
		return nome;
	}
}