package Tatai.view.tutorial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.welcome.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TutorialController implements Initializable {
	  @FXML
	    private AnchorPane root;

	    @FXML
	    private AnchorPane topPane;

	    @FXML
	    private Label lblHowToPlay;

	    @FXML
	    private AnchorPane cardPane;

	    @FXML
	    private JFXButton btnBack;

	    @FXML
	    private Label lblUser;


	/**
	 * Initializes the buttons on the screen
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lblUser.setText("Logged in as: " + LoginController.getCurrentPlayer());
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);
		
	}
	
	/**
	 * Handles the back button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void btnBackHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);


	}
	
	

}

