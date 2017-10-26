package Tatai.view.NumberPractice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.model.AudioFeedBack;
import Tatai.model.Recording;
import Tatai.model.threads.PlayingThread;
import Tatai.model.threads.PronunciationThread;
import Tatai.model.threads.RecordingThread;
import Tatai.model.threads.GUIUpdate;
import Tatai.view.welcome.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

/** Controller for Number Practice Mode Screen **/
public class NumberPracticeController implements Initializable, GUIUpdate {

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane topPane;

	@FXML
	private Label lblNowPlaying;

	@FXML
	private AnchorPane userPane;

	@FXML
	private Label lblUser;

	@FXML
	private AnchorPane cardPane;

	@FXML
	private Label lblCurrentGameNumber;

	@FXML
	private JFXButton btnRecord;

	@FXML
	private Label lblRecording;

	@FXML
	private JFXButton btnPlayRecording;

	@FXML
	private JFXButton btnPronunciation;

	@FXML
	private JFXProgressBar progressBar;

	@FXML
	private JFXButton btnTryAgain;

	@FXML
	private AnchorPane enterPane;

	@FXML
	private JFXTextField txtField;

	@FXML
	private JFXButton btnEnter;

	@FXML
	private AnchorPane buttonPane;

	@FXML
	private JFXButton btnQuit;

	private int currentNum = 1;

	/**
	 * Initializes the buttons
	 */
	 @Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
		 //Get the player name
		 lblUser.setText(LoginController.getCurrentPlayer());

		 JFXDepthManager.setDepth(topPane, 5);
		 JFXDepthManager.setDepth(cardPane,  4);
		 JFXDepthManager.setDepth(userPane,  4);
		 JFXDepthManager.setDepth(enterPane,  4);
		 JFXDepthManager.setDepth(buttonPane,  4);

		 progressBar.setVisible(false);
		 btnTryAgain.setVisible(false);
		 btnPlayRecording.setDisable(true);
		 btnPronunciation.setDisable(true);
	 }


	 /**
	  * Handles try again button
	  */
	 @FXML
	 private void btnTryAgainHandler(){
		 btnTryAgain.setVisible(false);
		 btnRecord.setVisible(true);
		 lblCurrentGameNumber.setText(Integer.toString(currentNum));
		 btnEnter.setDisable(false);
		 lblRecording.setText("");
	 }

	 /**
	  * Handles play recording button
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
	  * Handles pronunciation button
	  */
	 @FXML
	 private void btnPronunciationHandler() {
		 btnPronunciation.setDisable(true);
		 //Sets up a new pronunciation thread task
		 PronunciationThread task = new PronunciationThread(btnPronunciation, currentNum);
		 Thread thread = new Thread(task);
		 thread.start();
	 }

	 /**
	  * Handles Menu button
	  */
	 @FXML
	 private void btnQuitHandler(ActionEvent event) throws IOException {
		 Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		 Scene sceneLevelSelect = new Scene(parentLevelSelect);

		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 stage.setScene(sceneLevelSelect);
	 }

	 /**
	  * Handles recording button
	  */
	 @FXML
	 private void btnRecordHandler() {

		 btnEnter.setDisable(true);
		 progressBar.setVisible(true);
		 btnRecord.setVisible(false);
		 lblRecording.setText("Recording in progress...");

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
	  * Handles Enter button
	  */
	 @FXML
	 private void btnEnterHandler() {
		 String number = txtField.getText();
		 if (isInteger(number)) {
			 int num = Integer.parseInt(number);
			 if (num > 0 && num < 100) {
				 lblCurrentGameNumber.setText(number);
				 currentNum = num;
			 }
		 }

	 }

	 /**
	  * Determinds if the string inputed is an integer
	  * @param s
	  * @return
	  */
	 private boolean isInteger(String s) {
		 try { 
			 Integer.parseInt(s); 
		 } catch(NumberFormatException e) { 
			 return false; 
		 } catch(NullPointerException e) {
			 return false;
		 }
		 return true;
	 }

	 /**
	  * Updates the GUI when the recording is finished
	  */
	 public void updateGUI(Recording recording) {

		 progressBar.setVisible(false);
		 btnTryAgain.setVisible(true);

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

		 lblRecording.setText("The correct answer was: " + correctNumber + "\n You said: " + recordedNumber);



		 btnPlayRecording.setDisable(false);
		 btnPronunciation.setDisable(false);

	 }


}
