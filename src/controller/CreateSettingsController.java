package controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Demo;

public class CreateSettingsController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField numberTextField;
    
    @FXML
    private Text errorTextMessage;

    @FXML
    private TextField initialWordTextField;

    @FXML
    void enterButtonPressed(ActionEvent event) throws IOException {
    	String stringEntered = numberTextField.getText();
    	int numberEntered;
    	try {
    		numberEntered = Integer.valueOf(stringEntered);
    		String learnedText = Demo.parentMap.createText(initialWordTextField.getText(), numberEntered);
    		Demo.parentMap.setCreatedString(learnedText);
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/MainPane.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        	Pane root = loader.<Pane>load();
        	stage.getScene().setRoot(root);
        	
    		} catch (NumberFormatException e) {
    			errorTextMessage.setAccessibleText("You must enter an Integer!");
    		}
    	
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/MainPane.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	Pane root = loader.<Pane>load();
    	stage.getScene().setRoot(root);
    }

}
