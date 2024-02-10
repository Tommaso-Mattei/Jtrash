package model;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
/**
 * controllr of the profile, connects model and view as per MVC
 */
public class ProfileController {
	
	private ProfileView view;
	private Utente model;
	/**
	 * constructor
	 * @param view , view of the profile
	 * @param model , the profile
	 */
	public ProfileController(ProfileView view,Utente model) {
		this.view = view;
		this.model = model;
	}
	/**
	 * method that sets the actionListeners for the buttons of the view
	 */
	public void controllerBehaviour() {
		view.getMainMenuView().getBottone().addActionListener(e -> setRoutine());
		view.getNickView().getBottone().addActionListener(e -> saveNickname());
		view.getBottone().addActionListener(e -> saveAvatar());
	}
	/**
	 * method that retrieves information from the model, setting it to the view
	 */
	public void setRoutine() {
		int losses = model.getL();
		int wins = model.getW();
		int livello = model.getLV();
		String profilePath = model.getAvatar();
		String nickname = model.getNickname();
		ImageIcon profilePic = new ImageIcon(profilePath);
		view.getProfile().setIcon(profilePic);
		view.getProfile().setText("<html>Nickname:"+nickname+"<br>Livello:"+Integer.toString(livello));
		view.getStatistiche().setText("<html>Statistiche<br><br>Partite totali:"+ Integer.toString(losses+wins)+"<br>Vittorie:"+Integer.toString(wins)+"<br>Sconfitte:"+Integer.toString(losses));
	}
	/**
	 * method that saves in the model the nickname passed
	 */
	public void saveNickname() {
		String nickname = view.getNickView().getTextField().getText();
		model.setNickname(nickname);
		setRoutine();
	}
	/**
	 * method that saves in the model the path of the avatar chosen
	 */
	public void saveAvatar() {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(view);
		File avatar = fileChooser.getSelectedFile();
		String avatarPath = avatar.getAbsolutePath();
		model.setAvatar(avatarPath);
		setRoutine();
	}
}
