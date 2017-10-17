package Tatai.view.welcome;

import javafx.application.Platform;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.game.GameController;
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
	protected Label lblSelectUser;
	@FXML
	protected JFXButton btnLogin;
	@FXML
	protected JFXButton btnNewUser;

	@FXML
	protected JFXButton btnDeleteUser;


	@FXML
	private JFXButton btnBack;
	@FXML 
	private AnchorPane newUserPane;
	@FXML
	private JFXButton newUserEnterButton;
	@FXML
	private JFXTextField userNameField;
	@FXML
	private JFXTextField userNameErrorField;
	@FXML
	protected JFXButton deleteUserEnterButton;
	@FXML
	private JFXButton btnDeleteBack;
	@FXML
	private JFXTextField txtNoUserSelected;
	@FXML
	private JFXTextField txtNoUserToDelete;

	public static PersonalStats CurrentPlayer;
	protected ObservableList<String> userNames;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);
		txtNoUserToDelete.setVisible(false);
		newUserPane.setVisible(false);
		userNameErrorField.setVisible(false);
		userNameErrorField.setEditable(false);
		deleteUserEnterButton.setVisible(false);
		btnDeleteBack.setVisible(false);
		txtNoUserSelected.setVisible(false);
		txtNoUserSelected.setEditable(false);

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

	/**
	 * Method to handle login button
	 * @param event
	 * @throws IOException
	 */
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
			txtNoUserSelected.setText("Please select a user");
			txtNoUserSelected.setVisible(true);
		}

		/*	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		alert.setHeaderText("Welcome");
		alert.setContentText("You pressed login!");

		alert.showAndWait(); */
	}

	@FXML
	private void btnNewUserHandler() {

		txtNoUserSelected.setVisible(false);
		newUserPane.setVisible(true);
		btnDeleteUser.setVisible(false);
		btnNewUser.setVisible(false);
		btnLogin.setVisible(false);
		lblSelectUser.setVisible(false);
		cmbbxSelectUser.setVisible(false);

	}

	@FXML
	private void btnNewUserEnterHandler() {
		String userName = userNameField.getText();

		if (userName.equals("")) {
			userNameErrorField.setVisible(true);
			userNameErrorField.setText("Please Enter a valid username");
		}
		else if (!(userNames.contains(userName))) {
			createPlayerXML(userName);
			userNames.add(userName);

			cmbbxSelectUser.setItems(userNames);

			newUserPane.setVisible(false);
			btnDeleteUser.setVisible(true);
			btnNewUser.setVisible(true);
			btnLogin.setVisible(true);
			lblSelectUser.setVisible(true);
			cmbbxSelectUser.setVisible(true);

			userNameField.setText("");
			userNameErrorField.setText("");
			userNameErrorField.setVisible(false);

			txtNoUserSelected.setText(userName + " succesfully created");
			txtNoUserSelected.setVisible(true); 

		}
		else {
			userNameErrorField.setVisible(true);
			userNameErrorField.setText("Sorry, username already exists");
		}
	}



	@FXML
	private void btnBackHandler() {

		txtNoUserToDelete.setVisible(false);
		btnDeleteBack.setVisible(false);
		newUserPane.setVisible(false);
		lblSelectUser.setVisible(true);
		cmbbxSelectUser.setVisible(true);
		btnDeleteUser.setVisible(true);
		btnNewUser.setVisible(true);
		btnLogin.setVisible(true);
		lblSelectUser.setText("Select User");
		deleteUserEnterButton.setVisible(false);

	}

	@FXML
	private void deleteUserHandler() {

		txtNoUserSelected.setVisible(false);
		btnDeleteBack.setVisible(true);
		btnDeleteUser.setVisible(false);
		btnNewUser.setVisible(false);
		btnLogin.setVisible(false);
		lblSelectUser.setText("Select user to delete");
		deleteUserEnterButton.setVisible(true);

	}

	@FXML
	private void deleteUserEnterButtonHandler() throws IOException {
		if (cmbbxSelectUser.getValue() != null) {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DeleteConfirmation.fxml"));
			Parent confirmation = loader.load();

			DeleteConfirmationController controller = loader.getController();
			controller.setController(cmbbxSelectUser.getValue(), btnNewUser, lblSelectUser, btnLogin, btnDeleteUser, deleteUserEnterButton, userNames);
			
			Scene scene = new Scene(confirmation);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			txtNoUserToDelete.setVisible(false);
		}
		else {
			txtNoUserToDelete.setText("Please select a user to delete");
			txtNoUserToDelete.setVisible(true);
		}

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
