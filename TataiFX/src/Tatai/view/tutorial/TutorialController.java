package Tatai.view.tutorial;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Tatai.view.welcome.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TutorialController implements Initializable {
	 @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblHowToPlay;

    @FXML
    private Label lblUser;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private JFXButton btnGameBasics;

    @FXML
    private JFXButton btnPracticeMode;

    @FXML
    private JFXButton btnMathsMode;

    @FXML
    private JFXButton btnAboutTatai;

    @FXML
    private JFXButton btnBack;

    @FXML
    private ImageView image;

    @FXML
    private Label lblImage;

    @FXML
    private JFXButton btnPrev;

    @FXML
    private JFXButton btnNext;
    
    @FXML
    private Label lblAbout;
    
    @FXML
    private AnchorPane imagePane;

    private boolean btnGameBasicsPressed;
    private boolean btnPracticeModePressed;
    private boolean btnMathsModePressed;
    private boolean btnAboutTataiPressed;
    
    /* Images for Game Basics */
	private final Image NowPlaying = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/NowPlaying.png"));
	private final Image Player = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/Player.png"));
	private final Image QuestionScore = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/QuestionScore.png"));
	private final Image EndGame = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/EndGame.png"));
	private final Image Instructions = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/Instructions.png"));
	private final Image PlayRecording = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/PlayRecording.png"));
	private final Image TryAgain = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/TryAgain.png"));
	private final Image CorrectAnswer = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/CorrectAnswer.png"));
	private final Image Pronunciation = new Image(getClass().getResourceAsStream("/Tatai/resources/GameBasics/Pronunciation.png"));

	/**
	 * Initializes the buttons on the screen
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lblUser.setText("Logged in as: " + LoginController.getCurrentPlayer());
		lblImage.setText("1/9");
		lblAbout.setVisible(false);
		
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane, 5);
		JFXDepthManager.setDepth(imagePane,  2);
		

		image.setImage(NowPlaying);
		
		btnGameBasics.getStyleClass().add("pressed");
		btnGameBasicsPressed = true;
		
	}
	
	/**
	 * Handles the back button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void btnBackHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);


	}
	
	/** Handles mode selection **/
	@FXML
	private void btnModeHandler(ActionEvent event) throws IOException {
		if (event.getSource().equals(btnGameBasics)) {
			lblAbout.setVisible(false);
			image.setVisible(true);
			btnNext.setVisible(true);
			btnPrev.setVisible(true);
			lblImage.setVisible(true);
			
			lblImage.setText("1/9");
			image.setImage(NowPlaying);
			
			removeButtonStyles();
			btnGameBasics.getStyleClass().add("pressed");
			
			btnGameBasicsPressed = true;
			
			Image selectMode = new Image(getClass().getResourceAsStream("/Tatai/resources/NowPlaying.png"));
			image.setImage(selectMode);
		} else if (event.getSource().equals(btnPracticeMode)) {
			lblAbout.setVisible(false);
			image.setVisible(true);
			btnNext.setVisible(true);
			btnPrev.setVisible(true);
			lblImage.setVisible(true);
			
			removeButtonStyles();
			btnPracticeMode.getStyleClass().add("pressed");
			
			btnPracticeModePressed = true;
		} else if (event.getSource().equals(btnMathsMode)) {
			lblAbout.setVisible(false);
			image.setVisible(true);
			btnNext.setVisible(true);
			btnPrev.setVisible(true);
			lblImage.setVisible(true);
			
			removeButtonStyles();
			btnMathsMode.getStyleClass().add("pressed");	
			
			btnMathsModePressed = true;
		} else if (event.getSource().equals(btnAboutTatai)) {
			lblAbout.setVisible(true);
			image.setVisible(false);
			btnNext.setVisible(false);
			btnPrev.setVisible(false);
			lblImage.setVisible(false);
			
			removeButtonStyles();
			btnAboutTatai.getStyleClass().add("pressed");	
			
			btnAboutTataiPressed = true;
		}
	}
	
	/** Handles the next image button **/
	@FXML
	private void btnNextHandler(ActionEvent event) throws IOException {
		//if we are currently on the Game Basics tab
		if (btnGameBasicsPressed) {
			if (image.getImage().equals(NowPlaying)) {
				image.setImage(Player);
				lblImage.setText("2/9");
			} else if (image.getImage().equals(Player)) {
				image.setImage(QuestionScore);
				lblImage.setText("3/9");
			} else if (image.getImage().equals(QuestionScore)) {
				image.setImage(EndGame);
				lblImage.setText("4/9");
			} else if (image.getImage().equals(EndGame)) {
				image.setImage(Instructions);
				lblImage.setText("5/9");
			} else if (image.getImage().equals(Instructions)) {
				image.setImage(PlayRecording);
				lblImage.setText("6/9");
			} else if (image.getImage().equals(PlayRecording)) {
				image.setImage(TryAgain);
				lblImage.setText("7/9");
			} else if (image.getImage().equals(TryAgain)) {
				image.setImage(CorrectAnswer);
				lblImage.setText("8/9");
			} else if (image.getImage().equals(CorrectAnswer)) {
				image.setImage(Pronunciation);
				lblImage.setText("9/9");
			}
		}
	}
	
	/** Handles the prev image button **/
	@FXML
	private void btnPrevHandler(ActionEvent event) throws IOException {
		//if we are currently on the Game Basics tab
		if (btnGameBasicsPressed) {
			if (image.getImage().equals(Pronunciation)) {
				image.setImage(CorrectAnswer);
				lblImage.setText("8/9");
			} else if (image.getImage().equals(CorrectAnswer)) {
				image.setImage(TryAgain);
				lblImage.setText("7/9");
			} else if (image.getImage().equals(TryAgain)) {
				image.setImage(PlayRecording);
				lblImage.setText("6/9");
			} else if (image.getImage().equals(PlayRecording)) {
				image.setImage(Instructions);
				lblImage.setText("5/9");
			} else if (image.getImage().equals(Instructions)) {
				image.setImage(EndGame);
				lblImage.setText("4/9");
			} else if (image.getImage().equals(EndGame)) {
				image.setImage(QuestionScore);
				lblImage.setText("3/9");
			} else if (image.getImage().equals(QuestionScore)) {
				image.setImage(Player);
				lblImage.setText("2/9");
			} else if (image.getImage().equals(Player)) {
				image.setImage(NowPlaying);
				lblImage.setText("1/9");
			}
		}
	}
	
	/** Removes blue style from all buttons and sets pressed to false.**/
    private void removeButtonStyles() {
    	btnGameBasicsPressed = false;
    	btnPracticeModePressed = false;
    	btnMathsModePressed = false;
    	btnAboutTataiPressed = false;
    	btnPracticeMode.getStyleClass().removeAll("pressed");
    	btnGameBasics.getStyleClass().removeAll("pressed");
    	btnMathsMode.getStyleClass().removeAll("pressed");
    	btnAboutTatai.getStyleClass().removeAll("pressed");
    	
    }
	

}

