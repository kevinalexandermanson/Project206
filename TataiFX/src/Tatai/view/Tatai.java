package Tatai.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tatai extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Tatai/view/welcome/Login.fxml"));
		
		Font.loadFont(getClass().getResourceAsStream("/Tatai/resources/BebasNeue.otf"), 12);
		
		Scene scene = new Scene(root);
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Tatai/resources/ic_add_box_black_24dp.png")));
		
		stage.setScene(scene);
		stage.setResizable(false);;
		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}

}
