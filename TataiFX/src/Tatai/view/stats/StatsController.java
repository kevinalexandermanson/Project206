package Tatai.view.stats;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatsController {
	
	 @FXML
	    private JFXButton btnBack;

	    @FXML
	    private TabPane tbpnGameMode;

	    @FXML
	    private Tab tbAdd;

	    @FXML
	    private Label lblBestAdd;

	    @FXML
	    private Label lblLastAdd;

	    @FXML
	    private Label lblMeanAdd;

	    @FXML
	    private Label lblGamesPlayedAdd;

	    @FXML
	    private Tab tbSub;

	    @FXML
	    private Label lblBestSub;

	    @FXML
	    private Label lblLastSub;

	    @FXML
	    private Label lblMeanSub;

	    @FXML
	    private Label lblGamesPlayedSub;

	    @FXML
	    private Tab tbMul;

	    @FXML
	    private Label lblBestMul;

	    @FXML
	    private Label lblLastMul;

	    @FXML
	    private Label lblMeanMul;

	    @FXML
	    private Label lblGamesPlayedMul;

	    @FXML
	    private Tab tbDiv;

	    @FXML
	    private Label lblBestDiv;

	    @FXML
	    private Label lblLastDiv;

	    @FXML
	    private Label lblMeanDiv;

	    @FXML
	    private Label lblGamesPlayedDiv;

	    @FXML
	    private Tab tbHard;

	    @FXML
	    private Label lblBestHard;

	    @FXML
	    private Label lblLastHard;

	    @FXML
	    private Label lblMeanHard;

	    @FXML
	    private Label lblGamesPlayedHard;

	    @FXML
	    private LineChart<?, ?> chrtAddition;

	    @FXML
	    private CategoryAxis chrtx;

	    @FXML
	    private NumberAxis chrty;

	    @FXML
	    private JFXButton btnLoad;

	    @FXML
	    private JFXButton btnClear;

	public StatsController(){
		System.out.println("Henlo.");
	}
	
	
	//@Override
	//public void initialize(URL url, ResourceBundle rb){
		
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
	//}
	
	
	
    @FXML
    private void goBack(ActionEvent event) throws IOException {
    	Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
    }
    
    public void setPersonData(){
    	
    }
    
  //TODO: Retrieve the data from the players account info.
  	@FXML
  	private void loadData(ActionEvent event) {
  		//lblBestAdd.setText("change change change changing the score works");
  		//initData();
		if (Tatai.view.Tatai.CurrentPlayer != null) {
			PersonalStats p1 = Tatai.view.Tatai.CurrentPlayer;

			lblBestAdd.setText(p1.getBestAdd() + " / 10");
			lblLastAdd.setText(p1.getLastAdd() + " / 10");
			lblMeanAdd.setText(p1.getMeanAdd() + " / 10");
			lblGamesPlayedAdd.setText(p1.getGamesPlayedAdd() + " / 10");

			lblBestSub.setText(p1.getBestSub() + " / 10");
			lblLastSub.setText(p1.getLastSub() + " / 10");
			lblMeanSub.setText(p1.getMeanSub() + " / 10");
			lblGamesPlayedSub.setText(p1.getGamesPlayedSub() + " / 10");

			lblBestMul.setText(p1.getBestMul() + " / 10");
			lblLastMul.setText(p1.getLastMul() + " / 10");
			lblMeanMul.setText(p1.getMeanMul() + " / 10");
			lblGamesPlayedMul.setText(p1.getGamesPlayedMul() + " / 10");

			lblBestDiv.setText(p1.getBestDiv() + " / 10");
			lblLastDiv.setText(p1.getLastDiv() + " / 10");
			lblMeanDiv.setText(p1.getMeanDiv() + " / 10");
			lblGamesPlayedDiv.setText(p1.getGamesPlayedDiv() + " / 10");

			lblBestHard.setText(p1.getBestHard() + " / 10");
			lblLastHard.setText(p1.getLastHard() + " / 10");
			lblMeanHard.setText(p1.getMeanHard() + " / 10");
			lblGamesPlayedHard.setText(p1.getGamesPlayedHard() + " / 10");

			System.out.println("Statistics for " + p1.getPlayerName() + " loaded.");

		}
  	}
    
  	@FXML
  	private void clearData(ActionEvent event) {
  		//lblBestAdd.setText("change change change changing the score works");
  		lblBestAdd.setText("Add / 10");
		lblLastAdd.setText("Add / 10");
		lblMeanAdd.setText("Add / 10");
		lblGamesPlayedAdd.setText("Add / 10");

		lblBestSub.setText("Sub / 10");
		lblLastSub.setText("Sub / 10");
		lblMeanSub.setText("Sub / 10");
		lblGamesPlayedSub.setText("Sub / 10");
		
		lblBestMul.setText("Mul / 10");
		lblLastMul.setText("Mul / 10");
		lblMeanMul.setText("Mul / 10");
		lblGamesPlayedMul.setText("Mul / 10");
		
		lblBestDiv.setText("Div / 10");
		lblLastDiv.setText("Div / 10");
		lblMeanDiv.setText("Div / 10");
		lblGamesPlayedDiv.setText("Div / 10");
		
		lblBestHard.setText("Hard / 10");
		lblLastHard.setText("Hard / 10");
		lblMeanHard.setText("Hard / 10");
		lblGamesPlayedHard.setText("Hard / 10");
		
		System.out.println("Statistics cleared.");
  	}
}
