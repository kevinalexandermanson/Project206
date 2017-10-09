package Tatai.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

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

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXDepthManager.setDepth(cardPane,  4);
	}
	
	@FXML
	private void btnQuitHandler(ActionEvent event) throws IOException {
		Parent parentLevelSelect = FXMLLoader.load(getClass().getResource("/Tatai/view/levelselect/LevelSelect.fxml"));
		Scene sceneLevelSelect = new Scene(parentLevelSelect);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLevelSelect);

		
	}
	

		

}

