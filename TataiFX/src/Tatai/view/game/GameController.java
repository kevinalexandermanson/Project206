package Tatai.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

public class GameController {
	
	private String level;
	
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblNowPlaying;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private JFXButton btnRecord;

    @FXML
    private AnchorPane scorePane;

    @FXML
    private Label lblScoreNumber;

    @FXML
    private AnchorPane questionPane;

    @FXML
    private Label lblQuestionNumber;

    @FXML
    private JFXButton btnStatistics;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private Label lblCurrentGameNumber;
    
    @FXML
    private Label lblRecording;
	
	public void initialize(String level) {
		this.level = level;
		JFXDepthManager.setDepth(cardPane,  4);
		lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
		
	}
	
	@FXML
	private void btnQuitHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);

		
	}
	
	@FXML
	public void btnRecordingHandler(ActionEvent event) {
		btnRecord.setDisable(true);
		lblRecording.setText("Recording in progress...");
		
		RecordingThread recordingThread = new RecordingThread();
		
		
		//uncomment when on linux
		//recording.record();
		
		

	}
	
	private int randomNumber() {

		int randomNumber = 0;
		Random rand = new Random();
		if (level.equals(Levels.PractiseEasy.getNumber())) {
			randomNumber = rand.nextInt(9) + 1; //Range is now 1-9, as specified, rather than 0-9
		}
		else if (level.equals(Levels.PractiseHard.getNumber())) {
			randomNumber = rand.nextInt(99) + 1; 
		}
		
		return randomNumber;
	}

	private class RecordingThread extends Task<Void> {

		
		@Override
		protected Void call() throws Exception {
			Recording recording = new Recording();
			recording.record();
			return null;
		}
	}

}

