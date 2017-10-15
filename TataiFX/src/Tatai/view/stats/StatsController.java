package Tatai.view.stats;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Tatai.view.stats.PersonalStats.gameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StatsController {
	
	@FXML
	private JFXButton btnBack;

	@FXML
	private TabPane tbpnGameMode;
	
	@FXML
	private JFXButton btnChange;
	

	@FXML
	private Label lblBest;

	@FXML
	private Label lblLast;

	@FXML
	private Label lblMean;

	@FXML
	private Label lblGamesPlayed;
	
	@FXML
	void changeData(ActionEvent event) {
		lblBest.setText("change change change yay changing the score works");

	}
	
	@FXML
	private void initialise(){
		//Get an array with the game modes.
		Object[] gameModes = gameMode.values();
		String[] gameModeStrings = new String[gameMode.values().length];
		
		int i=0;
		for (gameMode g: gameMode.values()){
			gameModeStrings[i]=g.toString();
			i++;
		}
		
	}
	
    @FXML
    private void goBack(ActionEvent event) throws IOException {
    	Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
    }
    
    public void setPersonData(){
    	
    }
    
}
