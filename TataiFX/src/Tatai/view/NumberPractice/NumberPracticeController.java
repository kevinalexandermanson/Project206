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

import Tatai.model.AudioFeedBack;
import Tatai.model.Recording;
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

public class NumberPracticeController implements Initializable {
	
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
	private JFXButton btnPlayRecording;

	@FXML
	private JFXButton btnMenu;
	
	@FXML
	private JFXButton btnPronunciation;
	
	@FXML
	private JFXProgressBar progressBar;
	
	@FXML
	private JFXButton btnEnter;
	
	@FXML
	private JFXTextField txtField;
	
	@FXML
	private JFXButton btnTryAgain;
	
	private int currentNum = 1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		progressBar.setVisible(false);
		btnTryAgain.setVisible(false);
	}
	
	
	@FXML
	private void btnTryAgainHandler(){
		btnTryAgain.setVisible(false);
		btnRecord.setVisible(true);
		lblCurrentGameNumber.setText(Integer.toString(currentNum));
		btnEnter.setDisable(false);
		lblRecording.setText("");
	}
	
	@FXML
	private void btnPlayRecordingHandler() {
		btnPlayRecording.setDisable(true);

		// Sets up a new recording thread to play the recording
		PlayingThread task = new PlayingThread();
		Thread thread = new Thread(task);
		thread.start();
	}
	
	@FXML
	private void btnPronunciationHandler() {
		btnPronunciation.setDisable(true);
		//Sets up a new pronunciation thread task
		PronunciationThread task = new PronunciationThread();
		Thread thread = new Thread(task);
		thread.start();
	}
	
	@FXML
	private void btnMenuHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);
	}
	
	@FXML
	private void btnRecordHandler() {
		
		btnEnter.setDisable(true);
		progressBar.setVisible(true);
		btnRecord.setVisible(false);
		lblRecording.setText("Recording in progress...");

		// Opens a new recording thread and starts the recording as a background task
		RecordingTask task = new RecordingTask();
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

	private class PronunciationThread extends Task<Void>  {

		@Override
		protected Void call() throws Exception {
			AudioFeedBack.playFeedBackAudio(5);
			return null;
		}

		@Override 
		protected void succeeded() {
			btnPronunciation.setDisable(false);
			super.succeeded();

		}
	}
	
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
			Recording recording;
			try {
				recording = get();

				// Uncomment when testing on linux

				//get correct number and recorded number
				String correctNumber = recording.getCorrectNumber(number);
				String recordedNumber = recording.getRecordedNumber();

				//check if numbers are equivalent
				boolean answer = (recording.getCorrectNumber(number).equals(recording.getRecordedNumber()));
				answer = true;
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
}
