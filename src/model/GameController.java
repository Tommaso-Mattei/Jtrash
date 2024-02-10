package model;

import java.util.ArrayList;
//import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Game.ImpossibleDrawException;
import model.Game.TooManyPlayersException;
import model.GenericPlayer.ImpossibleDiscardException;
import model.Giocatore.ImpossibleSetException;
import model.Giocatore.WrongPositionException;
/**
 * Controller which handles the game class and view, as per the MVC pattern
 */
public class GameController implements Observer{
	
	private GameView view;
	private Game model;
	/**
	 * constructor of controller, registers as an observer and sets the right images for the view.
	 * Besides images it also handles some information about the user
	 * @param view, the game view
	 * @param model, the game class
	 */
	GameController(GameView view,Game model){
		this.view = view;
		this.model = model;
		
		model.registraObserver(this);
		
		setHandImages();
		
		cpuRoutine();
		
		setProfilePicture();
		
		setNomeGiocatore();
		
		setScartiImage();
		
	}
	/**
	 * a routine that handles what is shown on the view according to the number of players
	 */
	public void cpuRoutine() {
		if (model.getNplayers()== 2) {
			setHandImagesCPU1();
			visibilityOffCPU2();
			visibilityOffCPU3();
		}
		else if (model.getNplayers()== 3) {
			setHandImagesCPU1();
			setHandImagesCPU2();
			visibilityOffCPU3();	
		}
		else if (model.getNplayers()== 4) {
			setHandImagesCPU1();
			setHandImagesCPU2();
			setHandImagesCPU3();
		}
	}
	/**
	 * method that turns off the visiblity for CPU3 
	 */
	public void visibilityOffCPU3() {
		for (int i=0;i<view.getManoCPU3().size();i++) {
			view.getManoCPU3().get(i).setVisible(false);
		}
		view.getCPU3().setVisible(false);
	}
	/**
	 * method that turns off the visiblity for CPU2 
	 */
	public void visibilityOffCPU2() {
		for (int i=0;i<view.getManoCPU2().size();i++) {
			view.getManoCPU2().get(i).setVisible(false);
		}
		view.getCPU2().setVisible(false);
	}
	
	/**
	 * the update method related to the Observer pattern. It has a wide array of function paired to an integer number.
	 * (0) calls the method to update the view for the cpus
	 * (1) handles the end of a round, making the hand appear smaller
	 * (3) uses the winner function to display the correct message on the final frame
	 * (4) uses the loser function to display the correct message on the final frame
	 * (5) tells the user if it's the last round of a manche (Someone has done a trash)
	 */
	public void update(int i) {
		if (Integer.valueOf(i).equals(0)) {
			cpuRoutine();
		}
		else if(Integer.valueOf(i).equals(1)){
			setScartiImage();
			setHandImages();
			cpuRoutine();
			ArrayList<GenericPlayer> trashPlayers = model.getTrashPlayers();
			for (int j=0; j < trashPlayers.size();j++) {
				if (trashPlayers.get(j) instanceof Giocatore) {
					setLastBlank();
				}
				else if (trashPlayers.get(j).getNome().contains("1")){
					setLastBlankCPU1();
				}
				else if (trashPlayers.get(j).getNome().contains("2")) {
					setLastBlankCPU2();
				}
				else if (trashPlayers.get(j).getNome().contains("3")) {
					setLastBlankCPU3();
				}
			}
			view.showMessage("Fine del round! Tutti i giocatori che hanno fatto trash diminuiscono di 1 il numero di carte");
		}
		else if (Integer.valueOf(i).equals(3)){
			winner();
		}
		else if (Integer.valueOf(i).equals(4)){
			loser();
		}
		else if (Integer.valueOf(i).equals(5)) {
			view.showMessage("TRASH! Ultimo round prima dell'inizio della prossima manche!");
		}
	}
	/**
	 * puts on the final frame a victory message
	 */
	public void winner() {
		new FinalFrame(view.getMainMenuView(),view,"Congratulazioni, hai vinto!").setVisible(true);
	}
	/**
	 * puts on the final frame a loss message
	 */
	public void loser() {
		new FinalFrame(view.getMainMenuView(),view,"Peccato, hai perso!").setVisible(true);
	}
	/**
	 * reduces the player's hand in the view to match the model
	 */
	public void setLastBlank() {
		ArrayList<Carta> mano = model.getGiocatore().getMano();
		ArrayList<JButton> manoBottoni = view.getManoPlayer();
		int size = mano.size();
		manoBottoni.get(size).setVisible(false);
		manoBottoni.get(size).setEnabled(false);
	}
	/**
	 * reduces the cpu1 hand in the view to match the model
	 */
	public void setLastBlankCPU1() {
		ArrayList<Carta> mano = model.getPlayers().get(1).getMano();
		ArrayList<JLabel> manoCPU1 = view.getManoCPU1();
		int size = mano.size();
		manoCPU1.get(size).setVisible(false);
		manoCPU1.get(size).setEnabled(false);
	}
	/**
	 * reduces the cpu2 hand in the view to match the model
	 */
	public void setLastBlankCPU2() {
		ArrayList<Carta> mano = model.getPlayers().get(2).getMano();
		ArrayList<JLabel> manoCPU2 = view.getManoCPU2();
		int size = mano.size();
		manoCPU2.get(size).setVisible(false);
		manoCPU2.get(size).setEnabled(false);
	}
	/**
	 * reduces the cpu3 hand in the view to match the model
	 */
	public void setLastBlankCPU3() {
		ArrayList<Carta> mano = model.getPlayers().get(3).getMano();
		ArrayList<JLabel> manoCPU3 = view.getManoCPU3();
		int size = mano.size();
		manoCPU3.get(size).setVisible(false);
		manoCPU3.get(size).setEnabled(false);
	}
	/**
	 * the method responsible for setting every action listener related to the view
	 */
	public void controllerBehaviour() {
		view.getBottonePescaMazzo().addActionListener(e -> pescaMazzo());
		view.getBottonePescaScarti().addActionListener(e -> pescaScarti());
		view.getBottoneScarta().addActionListener(e -> scarta());
		ArrayList<JButton> manoBottoni = view.getManoPlayer();
		manoBottoni.get(0).addActionListener(e -> setCartaMano(1));
		manoBottoni.get(1).addActionListener(e -> setCartaMano(2));
		manoBottoni.get(2).addActionListener(e -> setCartaMano(3));
		manoBottoni.get(3).addActionListener(e -> setCartaMano(4));
		manoBottoni.get(4).addActionListener(e -> setCartaMano(5));
		manoBottoni.get(5).addActionListener(e -> setCartaMano(6));
		manoBottoni.get(6).addActionListener(e -> setCartaMano(7));
		manoBottoni.get(7).addActionListener(e -> setCartaMano(8));
		manoBottoni.get(8).addActionListener(e -> setCartaMano(9));
		manoBottoni.get(9).addActionListener(e -> setCartaMano(10));
	}
	/**
	 * method that calls the model in order to set a card
	 * @param i, the position in which the card should go
	 */
	public void setCartaMano(int i) {
		AudioManager am = view.getAudioManager();
		am.play("src\\model\\audioAssets\\draw.wav");
		try {
			model.getGiocatore().setCard(model.getMazzo(),i,model);
			setManoImage();
			setHandImages();
		} catch (WrongPositionException | ImpossibleSetException | TooManyPlayersException e) {
			view.showErrorMessage("Impossibile mettere la carta nella posizione scelta, seleziona una posizione adeguata", "Sbagliato.");
		}
	}
	/**
	 * method that handles the connection with the model to draw from the deck
	 */
	public void pescaMazzo() {
		AudioManager am = view.getAudioManager();
		am.play("src\\model\\audioAssets\\draw.wav");
		try {
			model.drawPhase(14, model.getTurno());
			setManoImage();
		} catch (ImpossibleDrawException e) {
			view.showErrorMessage("Impossibile pescare quando si ha già una carta in mano","Errore.");
		}
	}
	/**
	 * method that handles the connection with the model to draw from the discard pile
	 */
	public void pescaScarti() {
		AudioManager am = view.getAudioManager();
		am.play("src\\model\\audioAssets\\draw.wav");
		try {
			model.drawPhase(15, model.getTurno());
			setScartiImage();
			setManoImage();
		} catch (ImpossibleDrawException e) {
			view.showErrorMessage("Impossibile pescare quando si ha già una carta in mano","Errore.");
		}
	}
	/**
	 * method that reproduces the sublime game music 
	 */
	public void reproduceMainGameMusic() {
		AudioManager am = view.getAudioManager();
		am.play("src\\model\\audioAssets\\JTrashGameMusic.wav");
	}
	/**
	 * method responsible for discarding a card in the model through the view
	 */
	public void scarta() {
		AudioManager am = view.getAudioManager();
		am.play("src\\model\\audioAssets\\draw.wav");
		try {
			model.getGiocatore().discard(model.getMazzo(), model.getTurno(), model);
		} catch (ImpossibleDiscardException e) {
			view.showErrorMessage("Impossibile scartare quando non si ha una carta in mano", "Nessuna carta.");
		}
		JLabel spazioMano = view.getSpazioMano();
		spazioMano.setIcon(null);
		setScartiImage();
	}
	
	/**
	 * method capable of setting the right images for the player's hand
	 */
	public void setHandImages(){
		ArrayList<String> immagini = model.getGiocatore().getMano().stream().map(Carta::toString).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Carta> mano = model.getGiocatore().getMano();
		ArrayList<JButton> manoBottoni = view.getManoPlayer();
		for (int i = 0; i < mano.size();i++) {
			if (mano.get(i)==null) {
				manoBottoni.get(i).setIcon(null);
			}
			else if (mano.get(i).getVerso()) {
				manoBottoni.get(i).setIcon(new ImageIcon("src\\model\\Carte\\"+immagini.get(i)+".png"));
			}
			else {
				manoBottoni.get(i).setIcon(new ImageIcon("src\\model\\Carte\\Retro_Carta.png"));
			}
		}
	}
	/**
	 * method capable of setting the right images for the cpu1
	 */
	public void setHandImagesCPU1() {
		ArrayList<String> immagini = model.getPlayers().get(1).getMano().stream().map(Carta::toString).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Carta> mano = model.getPlayers().get(1).getMano();
		ArrayList<JLabel> manoCPU1 = view.getManoCPU1();
		for (int i = 0; i < mano.size();i++) {
			if (mano.get(i) == null) {
				manoCPU1.get(i).setIcon(null);
			}
			else if (mano.get(i).getVerso()) {
				manoCPU1.get(i).setIcon(new ImageIcon("src\\model\\Carte\\"+immagini.get(i)+".png"));
			}
			else {
				manoCPU1.get(i).setIcon(new ImageIcon("src\\model\\Carte\\Retro_Carta.png"));
			}
		}
	}
	/**
	 * method capable of setting the right images for the cpu2
	 */
	public void setHandImagesCPU2() {
		ArrayList<String> immagini = model.getPlayers().get(2).getMano().stream().map(Carta::toString).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Carta> mano = model.getPlayers().get(2).getMano();
		ArrayList<JLabel> manoCPU2 = view.getManoCPU2();
		for (int i = 0; i < mano.size();i++) {
			if (mano.get(i) == null) {
				manoCPU2.get(i).setIcon(null);
			}
			else if (mano.get(i).getVerso()) {
				manoCPU2.get(i).setIcon(new ImageIcon("src\\model\\Carte\\"+immagini.get(i)+".png"));
			}
			else {
				manoCPU2.get(i).setIcon(new ImageIcon("src\\model\\Carte\\Retro_Carta.png"));
			}
		}
	}
	/**
	 * method capable of setting the right images for the cpu2
	 */
	public void setHandImagesCPU3() {
		ArrayList<String> immagini = model.getPlayers().get(3).getMano().stream().map(Carta::toString).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Carta> mano = model.getPlayers().get(3).getMano();
		ArrayList<JLabel> manoCPU3 = view.getManoCPU3();
		for (int i = 0; i < mano.size();i++) {
			if (mano.get(i) == null) {
				manoCPU3.get(i).setIcon(null);
			}
			else if (mano.get(i).getVerso()) {
				manoCPU3.get(i).setIcon(new ImageIcon("src\\model\\Carte\\"+immagini.get(i)+".png"));
			}
			else {
				manoCPU3.get(i).setIcon(new ImageIcon("src\\model\\Carte\\Retro_Carta.png"));
			}
		}
	}
	/**
	 * method that sets the right image for the discard pile
	 */
	public void setScartiImage() {
		JLabel spazioScarti = view.getSpazioScarti();
		if (!(model.getMazzo().getScarti().size()== 0)) {
			String cartaScarti = model.getMazzo().getScarti().peek().toString();
			spazioScarti.setIcon(new ImageIcon("src\\model\\Carte\\"+cartaScarti+".png"));
		}
		else {
			spazioScarti.setIcon(null);
		}
	}
	/**
	 * method that sets the right image for the card in hand
	 */
	public void setManoImage() {
		JLabel spazioMano = view.getSpazioMano();
		if (model.getGiocatore().getAppoggio() == null) {
			spazioMano.setIcon(null);
		}
		else {
			String cartaMano = model.getGiocatore().getAppoggio().toString();
			spazioMano.setIcon(new ImageIcon("src\\model\\Carte\\"+cartaMano+".png"));
		}
	}
	/**
	 * method that handles the profile picture shown on the view
	 */
	public void setProfilePicture() {
		JLabel spazioProfilo = view.getSpazioProfilo();
		String profilePicturePath = model.getGiocatore().getUtente().getAvatar();
		spazioProfilo.setIcon(new ImageIcon(profilePicturePath));
	}
	/**
	 * method that shows on the view the nickname chosen by the user
	 */
	public void setNomeGiocatore() {
		JLabel spazioNomeGiocatore = view.getSpazioNomeGiocatore();
		String nickname = model.getGiocatore().getUtente().getNickname();
		spazioNomeGiocatore.setText(nickname);
	}
}
