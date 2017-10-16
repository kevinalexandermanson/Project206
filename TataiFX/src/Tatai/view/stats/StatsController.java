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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private LineChart<?, ?> chrtAddition;

    @FXML
    private CategoryAxis chrtx;

    @FXML
    private NumberAxis chrty;
	
	//TODO: Retrieve the data from the players account info.
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
		
		XYChart.Series<String, Integer> set1 = new XYChart.Series<String, Integer>();

		set1.getData().add(new XYChart.Data<String,Integer>("1", 23));
		//set1.getData().add(new XYChart.Data("2", 13));
		//set1.getData().add(new XYChart.Data("3", 43));
		//set1.getData().add(new XYChart.Data("4", 33));
		//set1.getData().add(new XYChart.Data("5", 41));
		//set1.getData().add(new XYChart.Data("6", 55));
		//set1.getData().add(new XYChart.Data("7", 23));
		
		//chrtAddition.getData().addAll(set1);
		
		
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
