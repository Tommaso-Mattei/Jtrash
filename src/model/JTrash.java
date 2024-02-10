package model;



import model.Game.ImpossibleDrawException;
import model.Game.TooManyPlayersException;
import model.Giocatore.ImpossibleSetException;
import model.Giocatore.WrongPositionException;

/**
 * the main class
 */
public class JTrash {
	
	/**
	 * the main of the project. It creates the window to insert the player id. Then the player id is used to access saved information or to create something from scratch. Then the main menu is shown.
	 * @param args
	 * @throws TooManyPlayersException , same as usual
	 * @throws ImpossibleDrawException , same as usual
	 * @throws WrongPositionException , same as usual
	 * @throws ImpossibleSetException , same as usual
	 */
	public static void main(String[] args) throws TooManyPlayersException, ImpossibleDrawException, WrongPositionException, ImpossibleSetException {
		new StartUpIDFrame();
	}

}
