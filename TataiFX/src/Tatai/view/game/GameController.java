package Tatai.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.model.Levels;
import Tatai.model.Recording;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController implements Initializable {
	
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblNowPlaying;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private Label lblCurrentGameNumber;

    @FXML
    private JFXButton btnRecord;

    @FXML
    private Label lblRecording;

    @FXML
    private JFXButton btnTryAgain;

    @FXML
    private JFXButton btnNextQuestion;

    @FXML
    private JFXButton btnPlayRecording;

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
    
	private String level = "";
	
	private static final int NUMOFQUESTIONS = 10;
	private static final String NUMOFQUESTIONSSTRING = String.valueOf(NUMOFQUESTIONS);
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		
		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
		
		lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
	}
	
	/*---------- Event Handlers ----------*/
	
	@FXML
	private void btnQuitHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);

		
	}
	
	@FXML
	private void btnRecordHandler(ActionEvent event) {
		btnRecord.setVisible(false);
		lblRecording.setText("Recording in progress...");
		
		RecordingTask task = new RecordingTask();
		
		Thread thread = new Thread(task);
		
		thread.start();
		
	

	}
	
	@FXML
	private void btnNextQuestionHandler(ActionEvent event) {
		btnRecord.setVisible(true);
		
		questionNumChange();
		
		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
		
		
	}
	
	
	
	/*---------- Other Methods ----------*/
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	private int randomNumber() {

		int randomNumber = 0;
		Random rand = new Random();
		if (level.equals(Levels.PractiseEasy.getLevel())) {
			randomNumber = rand.nextInt(9) + 1; //Range is now 1-9, as specified, rather than 0-9
		}
		else if (level.equals(Levels.PractiseHard.getLevel())) {
			randomNumber = rand.nextInt(99) + 1; 
		}
		
		return randomNumber;
	}

	private class RecordingTask extends Task<Void> {

		
		@Override
		protected Void call() throws Exception {
			Recording recording = new Recording();
			recording.record();
			return null;
		}
		
	    @Override protected void succeeded() {
	        super.succeeded();
			lblRecording.setText("Recording complete.");
			
			btnTryAgain.setVisible(true);
			btnNextQuestion.setVisible(true);
			btnPlayRecording.setVisible(true);
	    }

	    @Override protected void cancelled() {
	        super.cancelled();
	        updateMessage("Cancelled!");
	    }

	    @Override protected void failed() {
	    super.failed();
	    updateMessage("Failed!");
	    }
	};
		
	
	
	/**
	 * Gets Integer value of current score displayed.
	 */
	private Integer currentScore() {
		String scoreText = lblScoreNumber.getText();
		String firstNumber = scoreText.replaceAll("^\\D*(\\d+).*", "$1");
		Integer score = Integer.parseInt(firstNumber);
		return score;
	}
	
	/**
	 * Gets Integer value of current question displayed.
	 */
	private Integer currentQuestion() {
		String questionNum = lblQuestionNumber.getText();
		Integer num = Integer.parseInt(questionNum);
		return num;
	}
	
	/*
	 * Updates question number
	 */
	private void questionNumChange() {
		int num = currentQuestion();
		num++;

		String returnString = Integer.toString(num);

		lblQuestionNumber.setText(returnString);
	}

	/*
	 * Updates score number
	 */
	private void scoreNumChange() {
		int num = currentScore();
		num++;

		String returnString = Integer.toString(num) + "/" + NUMOFQUESTIONSSTRING;

		lblScoreNumber.setText(returnString);
	}

}

