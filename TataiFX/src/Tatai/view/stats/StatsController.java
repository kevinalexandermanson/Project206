package Tatai.view.stats;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.game.GameController;
import Tatai.view.welcome.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatsController implements Initializable{
	
	 @FXML
	    private AnchorPane root;

	    @FXML
	    private AnchorPane topPane;

	    @FXML
	    private Label lblStatistics;

	    @FXML
	    private AnchorPane cardPane;

	    @FXML
	    private AnchorPane buttonPane;

	    @FXML
	    private JFXButton btnPractise;

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

	    @FXML
	    private LineChart<?, ?> chrtStatistics;

	    @FXML
	    private CategoryAxis chrtx;

	    @FXML
	    private NumberAxis chrty;

	    @FXML
	    private Label lblBest;

	    @FXML
	    private Label lblPrevious;

	    @FXML
	    private Label lblAverage;

	    @FXML
	    private Label lblGames;
	    
	    @FXML
	    private AnchorPane statisticsPane;

	
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		
		lblStatistics.setText("Statistics For " + LoginController.getCurrentPlayer());
		JFXDepthManager.setDepth(topPane,  5);
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(statisticsPane, 2);
		//initData();
		//System.out.println("Henlo");
		/*
		//Get an array with the game modes.
		Object[] gameModes = gameMode.values();
		String[] gameModeStrings = new String[gameMode.values().length];
		
		int i=0;
		for (gameMode g: gameMode.values()){
			gameModeStrings[i]=g.toString();
			i++;
		}
		*/
		/*
		XYChart.Series<String, Integer> set1 = new XYChart.Series<String, Integer>();
		set1.getData().add(new XYChart.Data<String,Integer>("1", 23));
		set1.getData().add(new XYChart.Data("2", 13));
		set1.getData().add(new XYChart.Data("3", 43));
		set1.getData().add(new XYChart.Data("4", 33));
		set1.getData().add(new XYChart.Data("5", 41));
		set1.getData().add(new XYChart.Data("6", 55));
		set1.getData().add(new XYChart.Data("7", 23));
		//chrtAddition.getData().addAll(set1);
		 */
	}
	
	
	/* Handles back button */
    @FXML
    private void btnBackHandler(ActionEvent event) throws IOException {
    	Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
    }
    
/*    public void setPersonData(){
    	
    }*/
    
    /* Handles which game mode to display stats for */
    @FXML 
    private void btnModeHandler(ActionEvent event) {

		PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();

		
    	if (event.getSource().equals(btnPractise)) {
    		removeButtonStyles();
    		btnPractise.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Practise");
    	} else if (event.getSource().equals(btnAddition)) {
    		removeButtonStyles();
    		btnAddition.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Addition");
    		/*
			lblBest.setText("Best Score: " + p1.getBestAdd() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastAdd() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanAdd() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedAdd() + "");
			*/
    	} else if (event.getSource().equals(btnSubtraction)) {
    		removeButtonStyles();
    		btnSubtraction.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Subtraction");
    		/*
			lblBest.setText("Best Score: " + p1.getBestSub() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastSub() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanSub() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedSub() + "");
			*/
    	} else if (event.getSource().equals(btnMultiplication)) {
    		removeButtonStyles();
    		btnMultiplication.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Multiplication");
    		/*
			lblBest.setText("Best Score: " + p1.getBestMul() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastMul() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanMul() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedMul() + "");
*/
    	} else if (event.getSource().equals(btnDivision)) {
    		removeButtonStyles();
    		btnDivision.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Division");
    		/*
			lblBest.setText("Best Score: " + p1.getBestDiv() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastDiv() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanDiv() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedDiv() + "");
			*/
    	} else if (event.getSource().equals(btnRandom)) {
    		removeButtonStyles();
    		btnRandom.getStyleClass().add("pressed");
    		
    		chrtStatistics.setTitle("Random");
    	}
    }
    
    private void removeButtonStyles() {
    	btnPractise.getStyleClass().removeAll("pressed");
    	btnAddition.getStyleClass().removeAll("pressed");
    	btnSubtraction.getStyleClass().removeAll("pressed");
    	btnMultiplication.getStyleClass().removeAll("pressed");
    	btnDivision.getStyleClass().removeAll("pressed");
    	btnRandom.getStyleClass().removeAll("pressed");
    	
    }
    
/*  //TODO: Retrieve the data from the players account info.
  	@FXML
  	private void loadData(ActionEvent event) {
  		//lblBestAdd.setText("change change change changing the score works");
  		//initData();
		if (Tatai.view.Tatai.CurrentPlayer != null) {
			PersonalStats p1 = Tatai.view.Tatai.CurrentPlayer;

			lblBestAdd.setText(p1.getBestAdd() + " / 10");
			lblLastAdd.setText(p1.getLastAdd() + " / 10");
			lblMeanAdd.setText(p1.getMeanAdd() + " / 10");
			lblGamesPlayedAdd.setText(p1.getGamesPlayedAdd() + "");

			lblBestSub.setText(p1.getBestSub() + " / 10");
			lblLastSub.setText(p1.getLastSub() + " / 10");
			lblMeanSub.setText(p1.getMeanSub() + " / 10");
			lblGamesPlayedSub.setText(p1.getGamesPlayedSub() + "");

			lblBestMul.setText(p1.getBestMul() + " / 10");
			lblLastMul.setText(p1.getLastMul() + " / 10");
			lblMeanMul.setText(p1.getMeanMul() + " / 10");
			lblGamesPlayedMul.setText(p1.getGamesPlayedMul() + "");

			lblBestDiv.setText(p1.getBestDiv() + " / 10");
			lblLastDiv.setText(p1.getLastDiv() + " / 10");
			lblMeanDiv.setText(p1.getMeanDiv() + " / 10");
			lblGamesPlayedDiv.setText(p1.getGamesPlayedDiv() + "");

			lblBestHard.setText(p1.getBestHard() + " / 10");
			lblLastHard.setText(p1.getLastHard() + " / 10");
			lblMeanHard.setText(p1.getMeanHard() + " / 10");
			lblGamesPlayedHard.setText(p1.getGamesPlayedHard() + ""A);

			System.out.println("Statistics for " + p1.getPlayerName() + " loaded.");

		}
  	}
    
  	@FXML
  	private void clearData(ActionEvent event) {
  		//lblBestAdd.setText("change change change changing the score works");
  		lblBestAdd.setText("Add / 10");
		lblLastAdd.setText("Add / 10");
		lblMeanAdd.setText("Add / 10");
		lblGamesPlayedAdd.setText("");

		lblBestSub.setText("Sub / 10");
		lblLastSub.setText("Sub / 10");
		lblMeanSub.setText("Sub / 10");
		lblGamesPlayedSub.setText("");
		
		lblBestMul.setText("Mul / 10");
		lblLastMul.setText("Mul / 10");
		lblMeanMul.setText("Mul / 10");
		lblGamesPlayedMul.setText("");
		
		lblBestDiv.setText("Div / 10");
		lblLastDiv.setText("Div / 10");
		lblMeanDiv.setText("Div / 10");
		lblGamesPlayedDiv.setText("");
		
		lblBestHard.setText("Hard / 10");
		lblLastHard.setText("Hard / 10");
		lblMeanHard.setText("Hard / 10");
		lblGamesPlayedHard.setText("");
		
		System.out.println("Statistics cleared.");
  	}*/



}
