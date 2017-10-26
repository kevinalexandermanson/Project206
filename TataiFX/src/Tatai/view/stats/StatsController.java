package Tatai.view.stats;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Controller for the stats screen **/
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
	    
	    @FXML
	    private JFXButton btnRandomHard;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL url, ResourceBundle rb){
		
		lblStatistics.setText("Statistics For " + LoginController.getCurrentPlayer());
		JFXDepthManager.setDepth(topPane,  5);
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(statisticsPane, 2);
		
		IntegerBinding sizeProperty = Bindings.size(chrtStatistics.getData());
		
		//Set Practice Easy as default pressed when entering
		PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();
		
		chrtStatistics.setTitle("Practise (Easy)");
		XYChart.Series set1 = new XYChart.Series();
		int[] results = p1.getLast10PracE();
		for(int i=0; i<p1.getGamesPlayedPracE(); i++){
			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
		}
		
		//Used for one line display
		removeButtonStyles();
		btnPractise.getStyleClass().add("pressed");
		chrtStatistics.getData().clear();
		chrtStatistics.getData().add(set1);
		
		//Changes the labels for each score.
		lblBest.setText("Best Score: " + String.valueOf(p1.getBestPracE()) + " / 10");
		lblPrevious.setText("Previous Score: " + p1.getLastPracE() + " / 10");
		lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanPracE()) + " / 10");
		lblGames.setText("Games Played: " + p1.getGamesPlayedPracE() + "");
	}
	
	
	/* Handles back button */
    @FXML
    private void btnBackHandler(ActionEvent event) throws IOException {
    	Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
    }
    
    /**
     * Handles reset button
     * @throws IOException
     */
    @FXML
    private void btnResetHandler(ActionEvent event) throws IOException {
    	
    	//Sends a confirmation screen to check they wish to reset their progress.
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to reset the statistics for " + LoginController.getCurrentPlayer() + "?\n This cannot be reversed.", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			//Delete their data
			PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();
			p1.resetScores();
			Tatai.view.welcome.LoginController.saveCurrentPlayerXML();
			
			Alert deleteData = new Alert(AlertType.INFORMATION);
			deleteData.setTitle("Reset data");
			deleteData.setHeaderText("");
			deleteData.setContentText("Data deleted");
			deleteData.showAndWait();
			
			//reset stage
			Parent parentStats = FXMLLoader.load(getClass().getResource("/Tatai/view/stats/Stats.fxml"));
			Scene sceneStats = new Scene(parentStats);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(sceneStats);

		} else {
			Alert deleteCancel = new Alert(AlertType.INFORMATION);
			deleteCancel.setTitle("Delete User");
			deleteCancel.setHeaderText("");
			deleteCancel.setContentText("Cancelled");
			deleteCancel.showAndWait();
		}
	}
    
    /* Handles which game mode to display stats for */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML 
    private void btnModeHandler(ActionEvent event) {

		PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();
		
    	if (event.getSource().equals(btnPractise)) {
    		
    		chrtStatistics.setTitle("Practise (Easy)");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10PracE();
    		for(int i=0; i<p1.getGamesPlayedPracE(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnPractise.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
   
    		//Changes the labels for each score.
    		lblBest.setText("Best Score: " + String.valueOf(p1.getBestPracE()) + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastPracE() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanPracE()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedPracE() + "");
			
    	} else if (event.getSource().equals(btnPractiseHard)) {
    		
    		chrtStatistics.setTitle("Practise (Hard)");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10PracH();
    		for(int i=0; i<p1.getGamesPlayedPracH(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnPractiseHard.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    		lblBest.setText("Best Score: " + String.valueOf(p1.getBestPracH()) + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastPracH() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanPracH()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedPracH() + "");
							
    	} else if (event.getSource().equals(btnAddition)) {		//End of comment
    		
    		//Set up the chart
    		chrtStatistics.setTitle("Addition");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10Add();
    		for(int i=0; i<p1.getGamesPlayedAdd(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnAddition.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    		//Changes the labels for each score.
			lblBest.setText("Best Score: " + String.valueOf(p1.getBestAdd()) + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastAdd() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanAdd()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedAdd() + "");
			
    	} else if (event.getSource().equals(btnSubtraction)) {
    		
    		chrtStatistics.setTitle("Subtraction");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10Sub();
    		for(int i=0; i<p1.getGamesPlayedSub(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnSubtraction.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    		//Changes the labels for each score.
			lblBest.setText("Best Score: " + p1.getBestSub() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastSub() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanSub()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedSub() + "");
			
    	} else if (event.getSource().equals(btnMultiplication)) {
    		
    		chrtStatistics.setTitle("Multiplication");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10Mul();
    		for(int i=0; i<p1.getGamesPlayedMul(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnMultiplication.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    		//Changes the labels for each score.
			lblBest.setText("Best Score: " + p1.getBestMul() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastMul() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanMul()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedMul() + "");

    	} else if (event.getSource().equals(btnDivision)) {
    		
    		chrtStatistics.setTitle("Division");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10Div();
    		for(int i=0; i<p1.getGamesPlayedDiv(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used for one line display
    		removeButtonStyles();
    		btnDivision.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
    		
    		//Changes the labels for each score.
			lblBest.setText("Best Score: " + p1.getBestDiv() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastDiv() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanDiv()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedDiv() + "");
			
    	} else if (event.getSource().equals(btnRandom)) {
    		
    		chrtStatistics.setTitle("Random (Easy)");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10RandE();	
    		for(int i=0; i<p1.getGamesPlayedRandE(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used if only displaying one line at a time.
    		removeButtonStyles();
    		btnRandom.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
       		
    		//Changes the labels for each score.
       		lblBest.setText("Best Score: " + p1.getBestRandE() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastRandE() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanRandE()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedRandE() + "");
    	} else if (event.getSource().equals(btnRandomHard)) {
    																
    		chrtStatistics.setTitle("Random (Hard)");
    		XYChart.Series set1 = new XYChart.Series();
    		int[] results = p1.getLast10RandH();
    		for(int i=0; i<p1.getGamesPlayedRandH(); i++){
    			set1.getData().add(new XYChart.Data(""+ (i+1),results[i]));
    		}
    		
    		//Used if only displaying one line at a time.
    		removeButtonStyles();
    		btnRandomHard.getStyleClass().add("pressed");
    		chrtStatistics.getData().clear();
    		chrtStatistics.getData().add(set1);
       		
    		//Changes the labels for each score.
       		lblBest.setText("Best Score: " + p1.getBestRandH() + " / 10");
			lblPrevious.setText("Previous Score: " + p1.getLastRandH() + " / 10");
			lblAverage.setText("Average Score: " + String.format("%.2f", p1.getMeanRandH()) + " / 10");
			lblGames.setText("Games Played: " + p1.getGamesPlayedRandH() + "");
    	}
    }

    /**
     * Sets the appearance of all buttons to unpressed.
     */
    private void removeButtonStyles() {
    	btnPractise.getStyleClass().removeAll("pressed");
    	btnPractiseHard.getStyleClass().removeAll("pressed");
    	btnAddition.getStyleClass().removeAll("pressed");
    	btnSubtraction.getStyleClass().removeAll("pressed");
    	btnMultiplication.getStyleClass().removeAll("pressed");
    	btnDivision.getStyleClass().removeAll("pressed");
    	btnRandom.getStyleClass().removeAll("pressed");
    	btnRandomHard.getStyleClass().removeAll("pressed");
    	
    }
    

}
