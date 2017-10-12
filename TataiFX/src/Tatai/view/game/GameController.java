package Tatai.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.model.EquationModel;
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
	
	@FXML
	private JFXButton btnPlayAgain;
	
	@FXML
	private JFXButton btnReturnToMenu;
	
	@FXML
	private JFXButton btnNextLevel;

	private String level = "";
	private boolean secondAttempt = false;
	private String currentQuestionNumber;
	private EquationModel model;

	private static final int NUMOFQUESTIONS = 10;
	private static final String NUMOFQUESTIONSSTRING = String.valueOf(NUMOFQUESTIONS);
	private static final String INTRO = "Press Record, and say your answer in Te\nReo clearly into the microphone. You have\n3 seconds.";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(scorePane, 4);
		JFXDepthManager.setDepth(questionPane, 4);
		JFXDepthManager.setDepth(topPane, 5);


		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnNextLevel.setVisible(false);

		model = new EquationModel();
	}

	/*---------- Event Handlers ------------------------------------------------------------------------------*/

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
		currentQuestionNumber = lblCurrentGameNumber.getText();

		RecordingTask task = new RecordingTask();

		Thread thread = new Thread(task);

		thread.start();



	}

	@FXML
	private void btnNextQuestionHandler(ActionEvent event) {

		//set color back to grey
		root.getStyleClass().removeAll("rootCorrect");
		root.getStyleClass().removeAll("rootWrong");

		
		secondAttempt = false;
		int num = currentQuestion();

		if (num == NUMOFQUESTIONS) {
			lblRecording.setText("");
			lblCurrentGameNumber.setText("Game Over");

			
			
			if ((level.equals(Levels.PractiseEasy.getLevel()) && (currentScore() >= 8))) {
				btnNextLevel.setVisible(true);
			}
			
			lblScoreNumber.setText("0/" + NUMOFQUESTIONSSTRING);
			lblQuestionNumber.setText("1");
			btnPlayAgain.setVisible(true);
			btnReturnToMenu.setVisible(true);

		} 
		else {
			if (level.equals(Levels.Addition.getLevel())) {
				model.newEquation(Levels.Addition.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Subtraction.getLevel())) {
				model.newEquation(Levels.Subtraction.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Multiplication.getLevel())) {
				model.newEquation(Levels.Multiplication.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Division.getLevel())) {
				model.newEquation(Levels.Division.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Random.getLevel())) {
				model.newEquation(Levels.Random.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else {
				lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
			}
		
			questionNumChange();

			btnRecord.setVisible(true);
			lblRecording.setText("");

		}

		

		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
	
	}

	@FXML
	public void btnPlayRecordingHandler() {
		btnPlayRecording.setVisible(false);
		PlayingThread task = new PlayingThread();

		Thread thread = new Thread(task);

		thread.start();
	}
	
	@FXML
	public void btnTryAgainHandler() {
		
		if (secondAttempt == false) {
			
			lblCurrentGameNumber.setText(currentQuestionNumber);
			
			btnRecord.setVisible(true);
			btnTryAgain.setVisible(false);
			btnNextQuestion.setVisible(false);
			btnPlayRecording.setVisible(false);
			
			secondAttempt = true;
		}
	}

	@FXML
	public void btnPlayAgainHandler() {
		
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnNextLevel.setVisible(false);
		btnRecord.setVisible(true);
		
		lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
		lblRecording.setText(INTRO);
		
		
	}

	@FXML
	public void btnNextLevelHandler() {
		
		level = Levels.PractiseHard.getLevel();
		lblNowPlaying.setText("Now Playing - [Practise Mode - Hard]");
		
		btnNextLevel.setVisible(false);
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnRecord.setVisible(true);
		
		lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
		lblRecording.setText(INTRO);

	}
	/*---------- Other Methods ------------------------------------------------------------------------*/

	public void setLevel(String level) {
		this.level = level;
		
		if (level.equals(Levels.PractiseEasy.getLevel())) {
			lblNowPlaying.setText("Now Playing - [Practise Mode - Easy]");
			lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
		}
		else if (level.equals(Levels.PractiseHard.getLevel())) {
			lblNowPlaying.setText("Now Playing - [Practise Mode - Hard]");
			lblCurrentGameNumber.setText(Integer.toString(randomNumber()));
		}
		else  {
		
			if (level.equals(Levels.Addition.getLevel())) {
				lblNowPlaying.setText("Now Playing - [Maths Mode - Addition]");
				model.newEquation(Levels.Addition.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Subtraction.getLevel())) {
				lblNowPlaying.setText("Now Playing - [Maths Mode - Subtraction]");
				model.newEquation(Levels.Subtraction.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Multiplication.getLevel())) {
				lblNowPlaying.setText("Now Playing - [Maths Mode - Multiplication]");
				model.newEquation(Levels.Multiplication.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Division.getLevel())) {
				lblNowPlaying.setText("Now Playing - [Maths Mode - Division]");
				model.newEquation(Levels.Division.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
			else if (level.equals(Levels.Random.getLevel())) {
				lblNowPlaying.setText("Now Playing - [Maths Mode - Random]");
				model.newEquation(Levels.Random.getLevel());
				lblCurrentGameNumber.setText(model.getEquation());
			}
		}
		
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


	/*---------- Threads ------------------------------------------------------------------------*/

	private class RecordingTask extends Task<Recording> {


		@Override
		protected Recording call() throws Exception {
			Recording recording = new Recording();
			// Uncomment when testing on linex
			//recording.record();
			return recording;
		}

		@Override 
		protected void succeeded() {
			super.succeeded();
			
			ScriptEngineManager manager = new ScriptEngineManager();
	        ScriptEngine engine = manager.getEngineByName("js");
	        Object result = null;
	        try {
	        	result = engine.eval(lblCurrentGameNumber.getText());
			} catch (ScriptException e) {
				e.printStackTrace();
			}
	        
			int number = (int) result;

			//gets the recording object
			Recording recording;
			try {
				recording = get();

				// Uncomment when testing on linux
				
				//get correct number and recorded number
				String correctNumber = recording.getCorrectNumber(number);
				String recordedNumber = recording.getRecordedNumber();

				//check if numbers are equivalent
				boolean answer = (recording.getCorrectNumber(number).equals(recording.getRecordedNumber()));
				 
				if (answer == true) {
					lblCurrentGameNumber.setText("Correct");
					root.getStyleClass().removeAll("rootWrong");
					root.getStyleClass().add("rootCorrect");
					scoreNumChange();
				} else {
					lblCurrentGameNumber.setText("Wrong");
					root.getStyleClass().removeAll("rootCorrect");
					root.getStyleClass().add("rootWrong");
				}

				//prepare for output
				correctNumber = correctNumber.replace("whaa", "wha");
				correctNumber = correctNumber.replace("maa", "ma");
				recordedNumber = recordedNumber.replace("whaa", "wha");
				recordedNumber = recordedNumber.replace("maa", "ma");
				
				lblRecording.setText("");
				
				if (secondAttempt && !(answer)) {
					lblRecording.setText("The correct answer was: " + correctNumber + "\n You said: " + recordedNumber);
				}

				if (!(secondAttempt) && (!(answer))) {
					lblRecording.setText("Try again to see the correct answer");
					btnTryAgain.setVisible(true);
				}
				btnNextQuestion.setVisible(true);
				btnPlayRecording.setVisible(true);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		@Override protected void cancelled() {
			super.cancelled();
			updateMessage("Cancelled!");
		}

		@Override protected void failed() {
			super.failed();
			updateMessage("Failed!");
		}
	}

	/*
	 * Handles playback in separate thread
	 */
	private class PlayingThread extends Task<Recording>  {

		@Override
		protected Recording call() throws Exception {
			Recording recording = new Recording();
			// Uncomment when testing on linex
			//recording.playRecording();
			return recording;
		}

		@Override 
		protected void succeeded() {
			super.succeeded();
			btnPlayRecording.setVisible(true);
		}

	}

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

