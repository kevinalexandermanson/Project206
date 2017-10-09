package Tatai.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tatai extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setResizable(false);;
		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
