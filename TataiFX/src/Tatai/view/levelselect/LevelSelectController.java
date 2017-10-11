package Tatai.view.levelselect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.model.Levels;
import Tatai.view.game.GameController;
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

public class LevelSelectController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblWelcome;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnPractiseEasy;
    
    @FXML
    private JFXButton btnPractiseHard;
    
    @FXML
    private JFXButton btnAddition;
    
    @FXML
    private JFXButton btnSubtraction;
    
    @FXML
    private JFXButton btnMultiplication;
    
    @FXML
    private JFXButton btnDivision;
    
    @FXML
    private JFXButton btnRandom;

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);
	}
	
	@FXML
	private void btnBackHandler(ActionEvent event) throws IOException {
		Parent parentLogin = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		Scene sceneLogin = new Scene(parentLogin);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLogin);
	}
	
	@FXML
	private void selectModeHandler(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Tatai/view/game/Game.fxml"));
		Parent parentGame = loader.load();
		
		GameController controller = loader.getController();
		if (event.getSource().equals(btnPractiseEasy)) {
			controller.setLevel(Levels.PractiseEasy.getLevel());
		}
		else if (event.getSource().equals(btnPractiseHard)) {
			controller.setLevel(Levels.PractiseHard.getLevel());
		}
		else if (event.getSource().equals(btnAddition)) {
			controller.setLevel(Levels.Addition.getLevel());
		}
		else if (event.getSource().equals(btnSubtraction)) {
			controller.setLevel(Levels.Subtraction.getLevel());
		}
		else if (event.getSource().equals(btnMultiplication)) {
			controller.setLevel(Levels.Multiplication.getLevel());
		}
		else if (event.getSource().equals(btnDivision)) {
			controller.setLevel(Levels.Division.getLevel());
		}
		else if (event.getSource().equals(btnRandom)) {
			controller.setLevel(Levels.Random.getLevel());
		}

		
		Scene sceneGame = new Scene(parentGame);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneGame);

		
	}
	

		

}

