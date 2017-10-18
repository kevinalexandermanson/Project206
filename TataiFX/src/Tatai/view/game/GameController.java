package Tatai.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.Levels.Addition;
import Tatai.Levels.LevelInterface;
import Tatai.Levels.Division;
import Tatai.Levels.Levels;
import Tatai.Levels.Multiplication;
import Tatai.Levels.PractiseEasy;
import Tatai.Levels.PractiseHard;
import Tatai.Levels.RandomEasy;
import Tatai.Levels.RandomHard;
import Tatai.Levels.Subtraction;
import Tatai.model.AudioFeedBack;
import Tatai.model.Recording;
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
	
	@FXML
	private AnchorPane buttonPane;
	
	@FXML
	private JFXButton btnPronunciation;
	

	private String level = "";
	private boolean secondAttempt = false;
	private String currentQuestionNumber;
	private Map<String, LevelInterface> map;
	private int currentNum;

	private static final int NUMOFQUESTIONS = 10;
	private static final String NUMOFQUESTIONSSTRING = String.valueOf(NUMOFQUESTIONS);
	private static final String INTRO = "Press Record, and say your answer in Te\nReo clearly into the microphone. You have\n3 seconds.";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(scorePane, 4);
		JFXDepthManager.setDepth(questionPane, 4);
		JFXDepthManager.setDepth(topPane, 5);
		JFXDepthManager.setDepth(buttonPane,  4);

		//Initializes the buttons
		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnNextLevel.setVisible(false);
		btnPronunciation.setVisible(false);
		
		// Creates map of all possible levels
		map = new HashMap<String, LevelInterface>();
		map.put(Levels.Addition.getLevel(), new Addition());
		map.put(Levels.Subtraction.getLevel(), new Subtraction());
		map.put(Levels.Multiplication.getLevel(), new Multiplication());
		map.put(Levels.Division.getLevel(), new Division());
		map.put(Levels.Random.getLevel(), new RandomEasy());
		map.put(Levels.PractiseEasy.getLevel(), new PractiseEasy());
		map.put(Levels.PractiseHard.getLevel(), new PractiseHard());
		map.put(Levels.RandomHard.getLevel(), new RandomHard());

	}

	/*---------- Event Handlers ------------------------------------------------------------------------------*/

	/**
	 * Handles the quit button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void btnQuitHandler(ActionEvent event) throws IOException {
		
		// Loads the level select screen
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);


	}

	/**
	 * Handles the recording button
	 * @param event
	 */
	@FXML
	private void btnRecordHandler(ActionEvent event) {
		
		btnRecord.setVisible(false);
		lblRecording.setText("Recording in progress...");
		currentQuestionNumber = lblCurrentGameNumber.getText();

		// Opens a new recording thread and starts the recording as a background task
		RecordingTask task = new RecordingTask();
		Thread thread = new Thread(task);
		thread.start();



	}

	/**
	 * Handles the next question button
	 * @param event
	 */
	@FXML
	private void btnNextQuestionHandler(ActionEvent event) {

		//set color back to grey
		root.getStyleClass().removeAll("rootCorrect");
		root.getStyleClass().removeAll("rootWrong");


		secondAttempt = false;
		int num = currentQuestion();

		// Checks to see if the the numebr of questions is 10
		if (num == NUMOFQUESTIONS) {
			lblRecording.setText("");
			lblCurrentGameNumber.setText("Game Over");

			// If on practise mode, offer chance to go to next level
			if ((level.equals(Levels.PractiseEasy.getLevel()) && (currentScore() >= 8))) {
				btnNextLevel.setVisible(true);
			}
			
			// Shows game over screen
			lblScoreNumber.setText("0/" + NUMOFQUESTIONSSTRING);
			lblQuestionNumber.setText("1");
			btnPlayAgain.setVisible(true);
			btnReturnToMenu.setVisible(true);

		} 
		else {
			// Calculates next question
			map.get(level).calculate();
			lblCurrentGameNumber.setText(map.get(level).getEquation());
			lblNowPlaying.setText(map.get(level).getLabel());

			// Changes the question number
			questionNumChange();

			btnRecord.setVisible(true);
			lblRecording.setText("");

		}

		btnTryAgain.setVisible(false);
		btnNextQuestion.setVisible(false);
		btnPlayRecording.setVisible(false);
		btnPronunciation.setVisible(false);
	}

	/**
	 * Handles playing the recording
	 */
	@FXML
	private void btnPlayRecordingHandler() {
		btnPlayRecording.setDisable(true);
		
		// Sets up a new recording thread to play the recording
		PlayingThread task = new PlayingThread();
		Thread thread = new Thread(task);
		thread.start();
	}
	
	/**
	 * Handles the try again button
	 */
	@FXML
	private void btnTryAgainHandler() {

		// Checks if it is their first attempt if so, let them try again
		if (secondAttempt == false) {

			lblRecording.setText("");
			
			lblCurrentGameNumber.setText(currentQuestionNumber);

			btnRecord.setVisible(true);
			btnTryAgain.setVisible(false);
			btnNextQuestion.setVisible(false);
			btnPlayRecording.setVisible(false);

			secondAttempt = true;
		}

	}

	/**
	 * Handles play again button
	 */
	@FXML
	private void btnPlayAgainHandler() {

		// Sets up the game screen again
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnNextLevel.setVisible(false);
		btnRecord.setVisible(true);

		// Calculates a new equation depending on the level
		map.get(level).calculate();
		lblCurrentGameNumber.setText(map.get(level).getEquation());
		
		lblRecording.setText(INTRO);

	}

	/**
	 * Handles next level button
	 */
	@FXML
	private void btnNextLevelHandler() {

		// Moves the player to the next level
		level = Levels.PractiseHard.getLevel();

		btnNextLevel.setVisible(false);
		btnPlayAgain.setVisible(false);
		btnReturnToMenu.setVisible(false);
		btnRecord.setVisible(true);

		map.get(level).calculate();
		lblCurrentGameNumber.setText(map.get(level).getEquation());
		lblNowPlaying.setText(map.get(level).getLabel());
		
		lblRecording.setText(INTRO);

	}
	/**
	 * Handles pronunciation button
	 */
	@FXML
	private void btnPronunciationHanlder() {
		
		btnPronunciation.setDisable(true);
		//Sets up a new pronunciation thread task
		PronunciationThread task = new PronunciationThread();
		Thread thread = new Thread(task);
		thread.start();
		
	}
	
	/*---------- Other Methods ------------------------------------------------------------------------*/

	/**
	 * Sets the level of the game
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;

		// Calculates the apporpriate question depending on the level
		map.get(level).calculate();
		lblCurrentGameNumber.setText(map.get(level).getEquation());
		lblNowPlaying.setText(map.get(level).getLabel());
	}

	/*---------- Threads ------------------------------------------------------------------------*/

	/**
	 * Handles the recording thread
	 *
	 */
	private class RecordingTask extends Task<Recording> {


		@Override
		protected Recording call() throws Exception {
			Recording recording = new Recording();
			// Uncomment when testing on linex
			recording.record();
			return recording;
		}

		@Override 
		protected void succeeded() {
			super.succeeded();

			// Calculates the equation
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			Object result = null;
			try {
				result = engine.eval(lblCurrentGameNumber.getText());
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			int number = (int) result;
			currentNum = number;

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
				//answer = true;
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
					btnPronunciation.setVisible(true);
				}

				if (!(secondAttempt) && (!(answer))) {
					lblRecording.setText("Try again to see the correct answer");
					btnTryAgain.setVisible(true);
				}
				btnNextQuestion.setVisible(true);
				btnPlayRecording.setVisible(true);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
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
	private class PlayingThread extends Task<Void>  {

		@Override
		protected Void call() throws Exception {
			Recording recording = new Recording();
			recording.playRecording();
			return null;
		}

		@Override 
		protected void succeeded() {
			btnPlayRecording.setDisable(false);
			super.succeeded();
			
		}
	}
	
	/**
	 * Handles prounciation in a different thread
	 *
	 */
	private class PronunciationThread extends Task<Void>  {

		@Override
		protected Void call() throws Exception {
			AudioFeedBack.playFeedBackAudio(currentNum);
			return null;
		}

		@Override 
		protected void succeeded() {
			btnPronunciation.setDisable(false);
			super.succeeded();
			
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

	/**
	 * Updates question number
	 */
	private void questionNumChange() {
		int num = currentQuestion();
		num++;

		String returnString = Integer.toString(num);

		lblQuestionNumber.setText(returnString);
	}

	/**
	 * Updates score number
	 */
	private void scoreNumChange() {
		int num = currentScore();
		num++;

		String returnString = Integer.toString(num) + "/" + NUMOFQUESTIONSSTRING;

		lblScoreNumber.setText(returnString);
	}

}

