package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileReader;

/**
 * user class that holds all the relevant information
 */
public class Utente {
	
	private String identificativo;
	private String nickname;
	private String avatar;
	private int partiteW;
	private int partiteL;
	private int livello;
	private String[] info;
	private int temp;
	
	/**
	 * constructor that either takes information if already present on a text file or creates information on the same file
	 * @param identificativo , id of the user
	 */
	public Utente(String identificativo) {
		temp = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src\\model\\profileList\\profileList.txt"));
			String riga = reader.readLine();
			Boolean flag = false;
			while (riga!=null) {
				if (riga.contains(identificativo)) {
					flag = true;
					info = new String[6];
					info = riga.split(",");
				}
				riga = reader.readLine();
				if (!flag) {
					temp++;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (info == null) {
			info = new String[6];
			this.identificativo = identificativo;
			nickname = identificativo;
			avatar = "src\\model\\ProfileImages\\0.png";
			partiteW = 0;
			partiteL = 0;
			livello = 1;
			info[0] = this.identificativo;
			info[1] = nickname;
			info[2] = avatar;
			info[3] = Integer.toString(partiteW);
			info[4] = Integer.toString(partiteL);
			info[5] = Integer.toString(livello);
			String rigaFinal = String.join(",",info);
			rigaFinal = rigaFinal + "\n";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("src\\model\\profileList\\profileList.txt",true));
				writer.write(rigaFinal);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else {
			this.identificativo = info[0];
			nickname = info[1];
			avatar = info[2];
			partiteW = Integer.valueOf(info[3]);
			partiteL = Integer.valueOf(info[4]);
			livello = Integer.valueOf(info[5]);
		}
	}
	/**
	 * method that substitutes a parameter inside the file if the user already has information
	 */
	public void writeSubstitute() {
		Path path = Paths.get("src\\model\\profileList\\profileList.txt");
		try {
			List<String> righe = Files.readAllLines(path);
			String rigaFinal = String.join(",",info);
			righe.set(temp, rigaFinal);
			Files.write(path,righe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * getter
	 * @return id
	 */
	public String getIdentificativo() {
		return identificativo;
	}
	/**
	 * method that adds a win
	 */
	public void addW() {
		partiteW++;
		info[3] = Integer.toString(partiteW);
		writeSubstitute();
	}
	/**
	 * method that adds a loss
	 */
	public void addL() {
		partiteL++;
		info[4] = Integer.toString(partiteL);
		writeSubstitute();
	}
	/**
	 * method that changes the level of the user
	 * @param Npersone , number of people defeated by the user
	 */
	public void addLevel(int Npersone) {
		if (Integer.valueOf(Npersone).equals(1)){
			livello++;
		}
		else if (Integer.valueOf(Npersone).equals(2)) {
			livello+=2;
		}
		else if (Integer.valueOf(Npersone).equals(3)) {
			livello+=3;
		}
		info[5] = Integer.toString(livello);
		writeSubstitute();
	}
	/**
	 * setter
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
		info[1] = nickname;
		writeSubstitute();

	}
	/**
	 * setter
	 * @param avatar , the path of the avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
		info[2] = avatar;
		writeSubstitute();
	}
	/**
	 * geytter
	 * @return the path of the avatar
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * getter
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * getter
	 * @return number of wins
	 */
	public int getW() {
		return partiteW;
	}
	/**
	 * getter
	 * @return number of losses
	 */
	public int getL() {
		return partiteL;
	}
	/**
	 * getter
	 * @return level
	 */
	public int getLV() {
		return livello;
	}
	
	
}
