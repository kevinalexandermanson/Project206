package Tatai.view.welcome;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewUserController implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Label lblWelcome;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private Label lblNewUser;

    @FXML
    private JFXTextField txtCreateUser;

    @FXML
    private JFXButton btnBack;
    
    

    @FXML
    void btnBackHandler(ActionEvent event) throws IOException {
		Parent parentLogin = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		Scene sceneLogin = new Scene(parentLogin);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(sceneLogin);
    }

    @FXML
    void btnCreateHandler(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		Parent parentLogin = loader.load();
	
		LoginController controller = loader.getController();
		
		ObservableList<String> userNames = controller.getUsers();
		
		
		
		String user = txtCreateUser.getText();
		
		if (user.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Creation");
			alert.setHeaderText("Invalid Name");
			alert.setContentText("Username cannot be blank!");

			alert.showAndWait(); 
		} else if (userNames.contains(user)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Creation");
			alert.setHeaderText("User Exists");
			alert.setContentText("Please enter a different name.");

			alert.showAndWait(); 
		} else if (user.contains("]") || user.contains("[") || user.contains(":") || user.contains(";") || user.contains("|") || user.contains("=") || user.contains(",") || user.contains("*") || user.contains(".") || user.contains("\"") || user.contains("/") || user.contains("\\") || user.contains("<") || user.contains(">")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Creation");
			alert.setHeaderText("Invalid characters.");
			alert.setContentText("Please enter a different name.");

			alert.showAndWait(); 
		} else if (user.length() > 12) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Creation");
			alert.setHeaderText("Name too long");
			alert.setContentText("Please enter a name that is 12 characters or less.");
			
			alert.showAndWait(); 
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Creation");
			alert.setHeaderText("Creation Success");
			alert.setContentText(user + " has been created.");

			alert.showAndWait(); 
			LoginController.createPlayerXML(user);
			controller.addUser(user);
			
			Scene sceneLogin = new Scene(parentLogin);
			
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(sceneLogin);
			
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(cardPane,  4);
		JFXDepthManager.setDepth(topPane,  5);
		
	}

}
