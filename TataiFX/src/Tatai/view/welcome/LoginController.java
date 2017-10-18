package Tatai.view.welcome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.stats.PersonalStats;
import Tatai.view.stats.PersonalStats.gameMode;
import Tatai.view.stats.PersonalStats.statType;

public class LoginController implements Initializable{

	 @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblWelcome;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private JFXComboBox<String> cmbbxSelectUser;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnNewUser;

    @FXML
    private JFXButton btnDeleteUser;

    @FXML
    private Label lblSelectUser;

	public static PersonalStats CurrentPlayer;
	private ObservableList<String> userNames;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);

		userNames = FXCollections.observableArrayList();
		File[] files = new File("./").listFiles();
		for(int i = 0; i < files.length; i++){
			String fileName = files[i].getName();
			if(fileName.endsWith(".xml")||fileName.endsWith(".XML")) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				userNames.add(fileName);
			}
		}
		cmbbxSelectUser.setItems(userNames);
	}

	/* Login Handler */
	@FXML
	private void btnLoginHandler(ActionEvent event) throws IOException {

		if (cmbbxSelectUser.getValue() != null) {
			loadDataXML(cmbbxSelectUser.getValue());
			Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
			Scene sceneLevelSelect = new Scene(parentLevelSelect);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(sceneLevelSelect);
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login");
			alert.setHeaderText("Please select a user.");
			alert.setContentText("No user selected.");

			alert.showAndWait(); 
		}


	}

	/* New User Handler */
	@FXML
	private void btnNewUserHandler(ActionEvent event) throws IOException {
		Parent parentLogin = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/NewUser.fxml"));
		Scene sceneLogin = new Scene(parentLogin);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLogin);
	}
	
	@FXML
	private void btnDeleteUserHandler() {

		if (cmbbxSelectUser.getValue() != null) {
			String user = (String) cmbbxSelectUser.getValue();
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + user + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    userNames.remove(user);
			    File userFile = new File("./" + user + ".xml");
			    if (userFile.delete()) {
					Alert deleteSuccess = new Alert(AlertType.INFORMATION);
					deleteSuccess.setTitle("Delete User");
					deleteSuccess.setHeaderText("Delete Success");
					deleteSuccess.setContentText(user + " has been deleted.");

					deleteSuccess.showAndWait(); 
			    } else {
					Alert deleteFail = new Alert(AlertType.INFORMATION);
					deleteFail.setTitle("Delete User");
					deleteFail.setHeaderText("Delete Fail");
					deleteFail.setContentText("Error deleting " + user);

					deleteFail.showAndWait(); 
			    }
			    
			} else {
				Alert deleteCancel = new Alert(AlertType.INFORMATION);
				deleteCancel.setTitle("Delete User");
				deleteCancel.setHeaderText("");
				deleteCancel.setContentText(user + " will not be deleted.");

				deleteCancel.showAndWait(); 
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete User");
			alert.setHeaderText("Please select a user.");
			alert.setContentText("No user selected.");

			alert.showAndWait(); 
		}

	}
	
	/* Returns list of users */
	protected ObservableList<String> getUsers() {
		return this.userNames;
	}
	
	/* Adds a user */
	protected void addUser(String user) {
		userNames.add(user);
	}




	/**
	 * Loads an persons profile stored in an xml file.
	 * @param file
	 */
	public static void loadDataXML(String fileName){
		try{
			File file = new File("./" + fileName + ".xml");

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

	/**
	 * Whenever we wish to change a statistic for a player, this method is
	 * called. The player object is modified, then the .xml file is updated.
	 * 
	 * @param player
	 * @param mode
	 * @param stat
	 * @param Score
	 */
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

	/**
	 * Create a new player, and save it to an .xml file. Note that this does not
	 * load the newly created player. loadDataXML should be called separately.
	 * 
	 * @param name
	 */
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


}
