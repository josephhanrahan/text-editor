package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Demo;
import model.User;
import model.Utilities;

public class SignUpController {

    @FXML
    private Button EnterButton;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInbutton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void enterButtonPressed(ActionEvent event) {
    	String username = usernameTextField.getText();
    	String password = passwordTextField.getText();
    	usernameTextField.clear();
    	passwordTextField.clear();
    	errorLabel.setText("");
    	if (Utilities.uniqueUsername(username) == true && Utilities.passWordCheck(password) == true) {
    		User user = new User(username, password);
    		Demo.userBag.insert(user);
    	} else if (Utilities.uniqueUsername(username) == false) {
    		errorLabel.setText("Username has already been taken.");
    	} else if (Utilities.passWordCheck(password) == false) {
    		errorLabel.setText("Password must be 6 characters long and contain an upper-case letter, lower-case letter, and digit");
    	} else {
    		errorLabel.setText("You have successfuly registered!");
    	}
    }

    @FXML
    void signInButtonPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/SignInPane.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	Pane root = loader.<Pane>load();
    	stage.getScene().setRoot(root);
    }

}
