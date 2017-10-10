package Tatai.view.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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
	private JFXComboBox cmbbxSelectUser;
	@FXML
	private Label lblSelectUser;
	@FXML
	private JFXButton btnLogin;
	@FXML
	private JFXButton btnNewUser;
	
	@FXML
	private JFXButton btnDeleteUser;
	
	@FXML
	private Label lblNewUserName;
	
	@FXML
	private JFXTextField txtUsernameField;
	
	@FXML
	private JFXButton btnEnter;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);
		
		lblNewUserName.setVisible(false);
		txtUsernameField.setVisible(false);
		btnEnter.setVisible(false);
	}
	
	@FXML
	private void btnLoginHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
		
		
	/*	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		alert.setHeaderText("Welcome");
		alert.setContentText("You pressed login!");
		
		alert.showAndWait(); */
	}
	
	@FXML
	private void btnNewUserHandler() {
		
		lblSelectUser.setVisible(false);
		btnLogin.setVisible(false);
		btnNewUser.setVisible(false);
		btnDeleteUser.setVisible(false);
		cmbbxSelectUser.setVisible(false);
		
		btnEnter.setVisible(true);
		lblNewUserName.setVisible(true);
		txtUsernameField.setVisible(true);
		
	}
	
	@FXML
	private void btnEnterHandler() {
		
		String userName = txtUsernameField.getText();
		if ((userName != null) && (!(userName.equals("")))) {
			
		}
		
	}
		

}
