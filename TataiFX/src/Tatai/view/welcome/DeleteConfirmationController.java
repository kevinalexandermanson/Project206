package Tatai.view.welcome;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteConfirmationController {

	@FXML
	private JFXButton yes;

	@FXML
	private JFXButton no;

	@FXML
	private Label label;

	private String userName;
	private JFXButton btnNewUser;
	private Label lblSelectUser;
	private JFXButton btnLogin;
	private JFXButton btnDeleteUser;
	private JFXButton deleteUserEnterButton;
	private ObservableList<String> userNames;

	@FXML
	private void yesHandler() {
		File file = new File(userName + ".xml");
		file.delete();
		userNames.remove(userName);

		btnDeleteUser.setVisible(true);
		btnNewUser.setVisible(true);
		btnLogin.setVisible(true);
		lblSelectUser.setText("Select User");
		deleteUserEnterButton.setVisible(false);

		Stage stage = (Stage) yes.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void noHandler() {
		Stage stage = (Stage) yes.getScene().getWindow();
		stage.close();
	}

	public void setController(String userName, JFXButton btnNewUser, Label lblSelectUser, JFXButton btnLogin, JFXButton btnDeleteUser, JFXButton deleteUserEnterButton, ObservableList<String> userNames) {
		this.userName = userName;
		label.setText("Are you sure you want to delete " + userName);
		this.btnNewUser = btnNewUser;
		this.lblSelectUser = lblSelectUser;
		this.btnLogin = btnLogin;
		this.btnDeleteUser = btnDeleteUser;
		this.deleteUserEnterButton = deleteUserEnterButton;
		this.userNames = userNames;
	}

}
