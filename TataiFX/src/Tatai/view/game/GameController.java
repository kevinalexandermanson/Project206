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
import com.jfoenix.controls.JFXProgressBar;
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
import Tatai.model.threads.EquationThread;
import Tatai.model.threads.PlayingThread;
import Tatai.model.threads.PronunciationThread;
import Tatai.model.threads.RecordingThread;
import Tatai.model.threads.GUIUpdate;
import Tatai.view.stats.PersonalStats;
import Tatai.view.welcome.LoginController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

public class GameController implements Initializable, GUIUpdate {

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

	@FXML
	private Label lblUser;

	@FXML
	private AnchorPane userPane;

	@FXML
	private JFXProgressBar progressBar;


	private String level = "";
	private Levels levelEnum;
	private boolean secondAttempt = false;
	private String currentQuestionNumber;
	private Map<String, LevelInterface> map;
	private int currentNum;

	private static final int NUMOFQUESTIONS = 10;
	private static final String NUMOFQUESTIONSSTRING = String.valueOf(NUMOFQUESTIONS);
	private static final String INTRO = "Press Record, and say your answer in Te\nReo clearly into the microphone. You have\n3 seconds.";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Get the player name
		lblUser.setText(LoginController.getCurrentPlayer());

		JFXDepthManager.setDepth(userPane,  4);
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
		progressBar.setVisible(false);

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
		//If the last question has been played, then record the stats first, before exiting.
		if(event.getSource().equals(btnReturnToMenu)){

			PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();
			p1.recordLastGame(level, currentScore());
			Tatai.view.welcome.LoginController.saveCurrentPlayerXML();

			// Loads the level select screen
			Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
			Scene sceneLevelSelect = new Scene(parentLevelSelect);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(sceneLevelSelect);

		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to quit?\nYour current progress will not be saved.", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				// Loads the level select screen
				Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
				Scene sceneLevelSelect = new Scene(parentLevelSelect);

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(sceneLevelSelect);

			} else if (alert.getResult() == ButtonType.NO || alert.getResult() == ButtonType.CANCEL) {
				//Do nothing
			}
		}

	}

	/**
	 * Handles the recording button
	 * @param event
	 */
	@FXML
	private void btnRecordHandler(ActionEvent event) {

		progressBar.setVisible(true);
		btnRecord.setVisible(false);
		lblRecording.setText("Recording in progress...");
		currentQuestionNumber = lblCurrentGameNumber.getText();



		// Opens a new recording thread and starts the recording as a background task
		RecordingThread task = new RecordingThread(this);
		Thread thread = new Thread(task);
		thread.start();

		// New Timeline to handle process bar timing
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(3.5), e-> {
					if (thread.isAlive() != true) {
						progressBar.setVisible(false);
					}
				}, new KeyValue(progressBar.progressProperty(), 1))    
				);    
		timeline.play();


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

		// Checks to see if the the number of questions is 10
		if (num == NUMOFQUESTIONS) {
			lblCurrentGameNumber.setText("Game Over");

			//Save the score
			PersonalStats p1 = Tatai.view.welcome.LoginController.getCurrentPlayerStats();
			p1.recordLastGame(level, currentScore());
			Tatai.view.welcome.LoginController.saveCurrentPlayerXML();

			//Show results
			String bestScore = "";
			String recentScore = "";

			if ((level.equals(Levels.PractiseEasy.getLevel()))) {
				bestScore = p1.getBestPracE() + "";
				recentScore = p1.getLastPracE() + "";
			} else if ((level.equals(Levels.PractiseHard.getLevel()))) {
				bestScore = p1.getBestPracH() + "";
				recentScore = p1.getLastPracH() + "";		
			} else if ((level.equals(Levels.Addition.getLevel()))) {
				bestScore = p1.getBestAdd() + "";
				recentScore = p1.getLastAdd() + "";		
			} else if ((level.equals(Levels.Subtraction.getLevel()))) {
				bestScore = p1.getBestSub() + "";
				recentScore = p1.getLastSub() + "";	
			} else if ((level.equals(Levels.Multiplication.getLevel()))) {
				bestScore = p1.getBestMul() + "";
				recentScore = p1.getLastMul() + "";	
			} else if ((level.equals(Levels.Division.getLevel()))) {
				bestScore = p1.getBestDiv() + "";
				recentScore = p1.getLastDiv() + "";	
			} else if ((level.equals(Levels.Random.getLevel()))) {
				bestScore = p1.getBestRandE() + "";
				recentScore = p1.getLastRandE() + "";	
			} else if ((level.equals(Levels.RandomHard.getLevel()))) {
				bestScore = p1.getBestRandH() + "";
				recentScore = p1.getLastRandH() + "";	
			} 

			lblRecording.setText("Your previous best score was: " + bestScore + "\nYour score this time was: " + recentScore + "\nSee statistics in the main menu for more details. " );

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
			EquationThread task = new EquationThread(map, this.level, lblCurrentGameNumber, lblNowPlaying);
			Thread thread = new Thread(task);
			thread.start();

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
		PlayingThread task = new PlayingThread(btnPlayRecording);
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
		EquationThread task = new EquationThread(map, this.level, lblCurrentGameNumber, lblNowPlaying);
		Thread thread = new Thread(task);
		thread.start();

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
		PronunciationThread task = new PronunciationThread(btnPronunciation, currentNum);
		Thread thread = new Thread(task);
		thread.start();

	}

	/**
	 * Implemented from GUIUpdate interface to handle updating the GUI when recording is finished
	 */
	public void updateGUI(Recording recording) {

		progressBar.setVisible(false);
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
			btnPronunciation.setVisible(true);
		}

		if (!(secondAttempt) && (!(answer))) {
			lblRecording.setText("Try again to see the correct answer");
			btnTryAgain.setVisible(true);
		}
		btnNextQuestion.setVisible(true);
		btnPlayRecording.setVisible(true);

	}

	/*---------- Other Methods ------------------------------------------------------------------------*/

	/**
	 * Sets the level of the game
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;

		EquationThread task = new EquationThread(map, this.level, lblCurrentGameNumber, lblNowPlaying);
		Thread thread = new Thread(task);
		thread.start();
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

