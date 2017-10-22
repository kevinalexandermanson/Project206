package Tatai.view.stats;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.game.GameController;
import Tatai.view.welcome.LoginController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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

	    //Keep track of if a button has been pressed already
	    private boolean addPressed=false;
	    private boolean subPressed=false;
	    private boolean mulPressed=false;
	    private boolean divPressed=false;
	    private boolean randPressed=false;
	    private boolean pracPressed=false;
	    
	    XYChart.Series blankData = new XYChart.Series();
	    XYChart.Series addData = new XYChart.Series();
	    XYChart.Series subData = new XYChart.Series();
	    XYChart.Series mulData = new XYChart.Series();
	    XYChart.Series divData = new XYChart.Series();
	    XYChart.Series randData = new XYChart.Series();
	    XYChart.Series pracData = new XYChart.Series();
	    
	    public static final int NUMOFLEVELS = 6;
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		
		lblStatistics.setText("Statistics For " + LoginController.getCurrentPlayer());
		JFXDepthManager.setDepth(topPane,  5);
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(statisticsPane, 2);
		
		/*
		for (int i = 0; i < NUMOFLEVELS; i++) {
			chrtStatistics.getData().add(i,blankData);
		}
		*/
		IntegerBinding sizeProperty = Bindings.size(chrtStatistics.getData());
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
    		
    		chrtStatistics.setTitle("Practise");
    		XYChart.Series set1 = new XYChart.Series();
			set1.getData().add(new XYChart.Data("1", 1));
    		set1.getData().add(new XYChart.Data("2", 2));
    		set1.getData().add(new XYChart.Data("3", 3));
    		set1.getData().add(new XYChart.Data("4", 4));
    		set1.getData().add(new XYChart.Data("5", 5));
    		set1.getData().add(new XYChart.Data("6", 6));
    		set1.getData().add(new XYChart.Data("7", 7));
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnPractise.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used for multi line display
			pracData.getData().add(new XYChart.Data("1", 1));
    		pracData.getData().add(new XYChart.Data("2", 2));
    		pracData.getData().add(new XYChart.Data("3", 3));
    		pracData.getData().add(new XYChart.Data("4", 4));
    		pracData.getData().add(new XYChart.Data("5", 5));
    		pracData.getData().add(new XYChart.Data("6", 6));
    		pracData.getData().add(new XYChart.Data("7", 7));
    		if (pracPressed==false) {
    			btnPractise.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(0);
    			chrtStatistics.getData().add(0, pracData);
    			pracPressed = true;
    		} else {
    			btnPractise.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(0);
    			chrtStatistics.getData().add(0, blankData);
    			pracPressed = false;
    		} 	*/
    		
    		lblBest.setText("Best Score: " + String.valueOf(p1.getBestPracE()) + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastPracE() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanPracE() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedPracE() + "");
			
    	} else if (event.getSource().equals(btnAddition)) {
    		
    		//Set up the chart
    		chrtStatistics.setTitle("Addition");
    		XYChart.Series set1 = new XYChart.Series();
    		set1.getData().add(new XYChart.Data("1", 2));
			set1.getData().add(new XYChart.Data("2", 1));
			set1.getData().add(new XYChart.Data("3", 4));
			set1.getData().add(new XYChart.Data("4", 3));
			set1.getData().add(new XYChart.Data("5", 4));
			set1.getData().add(new XYChart.Data("6", 5));
			set1.getData().add(new XYChart.Data("7", 2));
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnAddition.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used for multi line display.

			addData.getData().add(new XYChart.Data("1", 2));
			addData.getData().add(new XYChart.Data("2", 1));
			addData.getData().add(new XYChart.Data("3", 4));
			addData.getData().add(new XYChart.Data("4", 3));
			addData.getData().add(new XYChart.Data("5", 4));
			addData.getData().add(new XYChart.Data("6", 5));
			addData.getData().add(new XYChart.Data("7", 2));
			
    		if (addPressed==false) {
    			btnAddition.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(1);
    			chrtStatistics.getData().add(1, addData);
    			addPressed = true;
    		} else {
    			btnAddition.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(1);
    			chrtStatistics.getData().add(1, blankData);
    			addPressed = false;
    			
    			if(subPressed==false && mulPressed==false && divPressed==false && randPressed==false && pracPressed==false){
    				resetGraph();
    			}	
    		} */
    		//Change the labels.
			lblBest.setText("Best Score: " + String.valueOf(p1.getBestAdd()) + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastAdd() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanAdd() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedAdd() + "");
			
    	} else if (event.getSource().equals(btnSubtraction)) {
    		
    		chrtStatistics.setTitle("Subtraction");
    		XYChart.Series set1 = new XYChart.Series();
    		set1.getData().add(new XYChart.Data("1", 1));
    		set1.getData().add(new XYChart.Data("2", 7));
    		set1.getData().add(new XYChart.Data("3", 9));
    		set1.getData().add(new XYChart.Data("4", 4));
    		set1.getData().add(new XYChart.Data("5", 1));
    		set1.getData().add(new XYChart.Data("6", 5));
    		set1.getData().add(new XYChart.Data("7", 6));
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnSubtraction.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used for multi line display
    		subData.getData().add(new XYChart.Data("1", 1));
    		subData.getData().add(new XYChart.Data("2", 7));
    		subData.getData().add(new XYChart.Data("3", 9));
    		subData.getData().add(new XYChart.Data("4", 4));
    		subData.getData().add(new XYChart.Data("5", 1));
    		subData.getData().add(new XYChart.Data("6", 5));
    		subData.getData().add(new XYChart.Data("7", 6));
    		if (subPressed==false) {
    			btnSubtraction.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(2);
    			chrtStatistics.getData().add(2, subData);
    			subPressed = true;
    		} else {
    			btnSubtraction.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(2);
    			chrtStatistics.getData().add(2, blankData);
    			subPressed = false;
    		}	*/
    		
			lblBest.setText("Best Score: " + p1.getBestSub() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastSub() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanSub() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedSub() + "");
			
    	} else if (event.getSource().equals(btnMultiplication)) {
    		
    		chrtStatistics.setTitle("Multiplication");
    		XYChart.Series set1 = new XYChart.Series();
    		set1.getData().add(new XYChart.Data("1", 8));
    		set1.getData().add(new XYChart.Data("2", 3));
    		set1.getData().add(new XYChart.Data("3", 6));
    		set1.getData().add(new XYChart.Data("4", 7));
    		set1.getData().add(new XYChart.Data("5", 1));
    		set1.getData().add(new XYChart.Data("6", 3));
    		set1.getData().add(new XYChart.Data("7", 5));
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnMultiplication.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used for multi line display
    		mulData.getData().add(new XYChart.Data("1", 8));
    		mulData.getData().add(new XYChart.Data("2", 3));
    		mulData.getData().add(new XYChart.Data("3", 6));
    		mulData.getData().add(new XYChart.Data("4", 7));
    		mulData.getData().add(new XYChart.Data("5", 1));
    		mulData.getData().add(new XYChart.Data("6", 3));
    		mulData.getData().add(new XYChart.Data("7", 5));
    		if (mulPressed==false) {
    			btnMultiplication.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(3);
    			chrtStatistics.getData().add(3, mulData);
    			mulPressed = true;
    		} else {
    			btnMultiplication.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(3);
    			chrtStatistics.getData().add(3, blankData);
    			mulPressed = false;
    		}	*/
    		
			lblBest.setText("Best Score: " + p1.getBestMul() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastMul() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanMul() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedMul() + "");

    	} else if (event.getSource().equals(btnDivision)) {
    		
    		chrtStatistics.setTitle("Division");
    		XYChart.Series set1 = new XYChart.Series();
    		set1.getData().add(new XYChart.Data("1", 2));
    		set1.getData().add(new XYChart.Data("2", 6));
    		set1.getData().add(new XYChart.Data("3", 8));
    		set1.getData().add(new XYChart.Data("4", 3));
    		set1.getData().add(new XYChart.Data("5", 2));
    		set1.getData().add(new XYChart.Data("6", 4));
    		set1.getData().add(new XYChart.Data("7", 3));
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnDivision.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used for multi line display.
    		divData.getData().add(new XYChart.Data("1", 2));
    		divData.getData().add(new XYChart.Data("2", 6));
    		divData.getData().add(new XYChart.Data("3", 8));
    		divData.getData().add(new XYChart.Data("4", 3));
    		divData.getData().add(new XYChart.Data("5", 2));
    		divData.getData().add(new XYChart.Data("6", 4));
    		divData.getData().add(new XYChart.Data("7", 3));
    		if (divPressed==false) {
    			btnDivision.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(4);
    			chrtStatistics.getData().add(4, divData);
    			divPressed = true;
    		} else {
    			btnDivision.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(4);
    			chrtStatistics.getData().add(4, blankData);
    			divPressed = false;
    		}	*/
    		
			lblBest.setText("Best Score: " + p1.getBestDiv() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastDiv() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanDiv() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedDiv() + "");
			
    	} else if (event.getSource().equals(btnRandom)) {
    		
    		chrtStatistics.setTitle("Random");
    		XYChart.Series set1 = new XYChart.Series();
    		set1.getData().add(new XYChart.Data("1", 1));	//Sample data.
    		set1.getData().add(new XYChart.Data("2", 7));
    		set1.getData().add(new XYChart.Data("3", 8));
    		set1.getData().add(new XYChart.Data("4", 3));
    		set1.getData().add(new XYChart.Data("5", 2));
    		set1.getData().add(new XYChart.Data("6", 5));
    		set1.getData().add(new XYChart.Data("7", 6));
    		
    		//Used if only displaying one line at a time.
    		removeButtonStyles();
    		btnRandom.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    	/*	//Used if implementing multiple line graphs feature
    		randData.getData().add(new XYChart.Data("1", 1));	//Sample data.
    		randData.getData().add(new XYChart.Data("2", 7));
    		randData.getData().add(new XYChart.Data("3", 8));
    		randData.getData().add(new XYChart.Data("4", 3));
    		randData.getData().add(new XYChart.Data("5", 2));
    		randData.getData().add(new XYChart.Data("6", 5));
    		randData.getData().add(new XYChart.Data("7", 6));
    		if (randPressed==false) {
    			btnRandom.getStyleClass().add("pressed");
    			chrtStatistics.getData().remove(5);
    			chrtStatistics.getData().add(5, randData);
    			randPressed = true;
    		} else {
    			btnRandom.getStyleClass().removeAll("pressed");
    			chrtStatistics.getData().remove(5);
    			chrtStatistics.getData().add(5, blankData);
    			randPressed = false;
    		} */
       		
       		lblBest.setText("Best Score: " + p1.getBestRandE() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastRandE() + " / 10");
			lblAverage.setText("Average Score: " + p1.getMeanRandE() + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedRandE() + "");
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
    
    //Remove the data, add in blank data.
    private void resetGraph(){
    	chrtStatistics.getData().remove(0, 5);
    	for (int i = 0; i < NUMOFLEVELS; i++) {
			chrtStatistics.getData().add(i,blankData);
		}
    }



}
