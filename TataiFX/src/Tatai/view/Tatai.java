package Tatai.view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import Tatai.view.stats.PersonalStats;
import Tatai.view.stats.PersonalStats.gameMode;
import Tatai.view.stats.PersonalStats.statType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tatai extends Application {
	
	public static PersonalStats CurrentPlayer;
	public static final String GUEST = "guest";
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		
		Font.loadFont(getClass().getResourceAsStream("/Tatai/resources/BebasNeue.otf"), 12);
		
		Scene scene = new Scene(root);
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Tatai/resources/ic_add_box_black_24dp.png")));
		
		stage.setScene(scene);
		stage.setResizable(false);;
		stage.show();
		
		//Initialise a guest player. Saves .xml in current directory.
		createPlayerXML(GUEST);
		loadDataXML(new File("./" + GUEST + ".xml"));

	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	
	public static void loadDataXML(File file){
		try{
			FileInputStream fis = new FileInputStream(file);
			XMLDecoder decoder = new XMLDecoder(fis);
			
			CurrentPlayer = (PersonalStats)decoder.readObject();
			decoder.close();
			fis.close();
			System.out.println(CurrentPlayer.getPlayerName() + " currently loaded.");
			//System.out.println(CurrentPlayer.getPlayerName() +" scored " +CurrentPlayer.getELast() +" points on " + gameMode.EASY.toString()+".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void changeStatXML(PersonalStats player, gameMode mode, statType stat, int Score){
		try{
			player.setStats(mode, stat, Score);
			
			FileOutputStream fos = new FileOutputStream(new File("./" + player.getPlayerName() + ".xml"));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(player);
			encoder.close();
			fos.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createPlayerXML(String name){
		try{
			PersonalStats p1 = new PersonalStats(name);
			
			FileOutputStream fos = new FileOutputStream(new File("./" + p1.getPlayerName() + ".xml"));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(p1);
			encoder.close();
			fos.close();
			System.out.println("New player: "+ p1.getPlayerName() + " successfully created.");
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PersonalStats getCurrentPlayer(){
		return CurrentPlayer;
	}

}
