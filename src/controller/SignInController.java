package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class SignInController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInbutton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private Label errorLabel;

    @FXML
    void signInButtonPressed(ActionEvent event) throws IOException {
    	String username = usernameTextField.getText();
    	String password = passwordTextField.getText();
    	usernameTextField.clear();
    	passwordTextField.clear();
    	User user = new User(username, password);
    	User realUser = Demo.userBag.searchByUsername(user);
    	if (Utilities.doesUserExist(user) == true && realUser.getPassword().equals(password)) {
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/MainPane.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        	Pane root = loader.<Pane>load();
        	stage.getScene().setRoot(root);
    	} else {
    		errorLabel.setText("Wrong username or password!");
    	}
    }

    @FXML
    void signUpButtonPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/SignUpPane.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	Pane root = loader.<Pane>load();
    	stage.getScene().setRoot(root);
    }


}
