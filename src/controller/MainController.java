package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import model.Demo;
import model.IO;
import model.Tools;
import model.Utilities;
import model.parentMap;

public class MainController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//borderPane.setAlignment(borderPane, Pos.CENTER);
		statusBar.setWrapText(true);
		textAreaEntry.setWrapText(true);
		if (!Demo.parentMap.getCreatedString().isEmpty()) {
			textAreaEntry.appendText(Demo.parentMap.getCreatedString());
			Demo.parentMap.setCreatedString("");
		}
	}
	
	@FXML
    private BorderPane borderPane;
	
	@FXML
    private AnchorPane anchorPane;
	
	@FXML
    private MenuBar mainMenuBar;

    @FXML
    private MenuItem closeMenuButton;

    @FXML
    private MenuItem createMenuButton;

    @FXML
    private MenuItem exitMenuButton;

    @FXML
    private MenuItem fleschScoreMenuButton;

    @FXML
    private MenuItem learnMenuButton;

    @FXML
    private MenuItem newMenuButton;

    @FXML
    private MenuItem openMenuButton;

    @FXML
    private MenuItem saveAsMenuButton;

    @FXML
    private MenuItem saveMenuButton;

    @FXML
    private MenuItem sentenceCountMenuButton;

    @FXML
    private MenuItem spellCheckMenuButton;

    @FXML
    private TextArea statusBar;

    @FXML
    private TextArea textAreaEntry;
    
    @FXML
    private MenuItem undoMenuButton;

    @FXML
    private MenuItem wordCountMenuButton;
    
    @FXML
    private MenuItem SyllableCountMenuButton;

    @FXML
    void exitMenuButtonPressed(ActionEvent event) throws IOException {
    	if (Demo.currentFile != null) {
    		String content = textAreaEntry.getText();
    		FileWriter fw = new FileWriter(Demo.currentFile, false);
        	fw.write(content);
        	fw.close();
    	}
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void accountMenuButtonPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/AccountPane.fxml"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	Pane root = loader.<Pane>load();
    	stage.getScene().setRoot(root);
    }

    @FXML
    void closeMenuButtonPressed(ActionEvent event) throws IOException {
    	if (Demo.currentFile != null) {
    		String content = textAreaEntry.getText();
    		FileWriter fw = new FileWriter(Demo.currentFile, false);
        	fw.write(content);
        	fw.close();
        	textAreaEntry.clear();
        	statusBar.clear();
    	} else {
    		String content = textAreaEntry.getText();
        	System.out.println(content);
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Select text File");
        	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
        	File selectedFile = fileChooser.showOpenDialog(stage);
        	if (selectedFile != null) {
        		Demo.currentFile = selectedFile;
            	FileWriter fw = new FileWriter(selectedFile.getPath(), false);
            	fw.write(content);
            	fw.close();
        	}
        	textAreaEntry.clear();
        	statusBar.clear();
    	}
    	Demo.currentFile = null;
    }

    @FXML
    void createMenuButtonPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/CreateSettingsPane.fxml"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	Pane root = loader.<Pane>load();
    	stage.getScene().setRoot(root);
    }


    @FXML
    void fleschScoreMenuButtonPressed(ActionEvent event) {
    	Tools.resetEditBoolean();
    	Demo.editOnOff[4] = true;
    	String entry = textAreaEntry.getText();
    	double fleschScore = Utilities.fleschScore(entry);
    	String fleschScoreRank = "";
    	if (fleschScore > 100.0) {
    		fleschScoreRank = "Under 5th Grade";
    	} else if (fleschScore >= 90.0 && fleschScore <= 100.0) {
    		fleschScoreRank = "5th Grade";
    	} else if (fleschScore >= 80.0 && fleschScore <= 90.0) {
    		fleschScoreRank = "6th Grade";
    	} else if (fleschScore >= 70.0 && fleschScore <= 80.0) {
    		fleschScoreRank = "7th Grade";
    	} else if (fleschScore >= 60.0 && fleschScore <= 70.0) {
    		fleschScoreRank = "8th & 9th Grade";
    	} else if (fleschScore >= 50.0 && fleschScore <= 60.0) {
    		fleschScoreRank = "10th to 12th Grade";
    	} else if (fleschScore >= 30.0 && fleschScore <= 50.0) {
    		fleschScoreRank = "College";
    	} else if (fleschScore >= 10.0 && fleschScore <= 30.0) {
    		fleschScoreRank = "College Graduate";
    	} else if (fleschScore > 0.0 && fleschScore <= 10.0) {
    		fleschScoreRank = "Professional";
    	} else if (fleschScore <= 0.0) {
    		fleschScoreRank = "Not Available";
    		fleschScore = 0.0;
    	}
    	statusBar.clear();
    	statusBar.setText("Flesch Score: " + fleschScore + " (" + fleschScoreRank + ")");
    }

    @FXML
    void learnMenuButtonPressed(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select text File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	String dataLearned = Tools.importFile(selectedFile.getAbsolutePath());
    	Demo.parentMap = new parentMap();
    	Demo.parentMap.learnText(dataLearned);
    }

    @FXML
    void newMenuButtonPressed(ActionEvent event) throws IOException {
    	if (Demo.currentFile != null) {
    		String content = textAreaEntry.getText();
    		FileWriter fw = new FileWriter(Demo.currentFile, false);
        	fw.write(content);
        	fw.close();
        	textAreaEntry.clear();
    	}
    	String content = textAreaEntry.getText();
    	System.out.println(content);
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select text File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	File selectedFile = fileChooser.showSaveDialog(stage);
    	Demo.currentFile = selectedFile;
    	if (selectedFile != null) {
    		FileWriter fw = new FileWriter(selectedFile.getPath(), false);
        	fw.write(content);
        	fw.close();
        	textAreaEntry.clear();
        	statusBar.clear();
        	Tools.resetEditBoolean();
    	}
    }

    @FXML
    void openMenuButtonPressed(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select text File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	if (selectedFile != null) {
    		textAreaEntry.clear();
        	IO.importTextFile2TextArea(selectedFile.getPath(), textAreaEntry);
        	Demo.currentFile = selectedFile;
        	Tools.resetEditBoolean();
        	statusBar.clear();
    	}
    }

    @FXML
    void saveAsMenuButtonPressed(ActionEvent event) throws IOException {
    	String content = textAreaEntry.getText();
    	System.out.println(content);
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select text File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	Demo.currentFile = selectedFile;
    	FileWriter fw = new FileWriter(selectedFile.getPath(), false);
    	fw.write(content);
    	fw.close();
    }

    @FXML
    void saveMenuButtonPressed(ActionEvent event) throws IOException {
    	if (Demo.currentFile != null) {
    		String content = textAreaEntry.getText();
    		FileWriter fw = new FileWriter(Demo.currentFile, false);
        	fw.write(content);
        	fw.close();
    	} else {
    		String content = textAreaEntry.getText();
        	System.out.println(content);
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Select text File");
        	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        	Stage stage = (Stage) mainMenuBar.getScene().getWindow();
        	File selectedFile = fileChooser.showSaveDialog(stage);
        	if (selectedFile != null) {
        		Demo.currentFile = selectedFile;
            	FileWriter fw = new FileWriter(selectedFile.getPath(), false);
            	fw.write(content);
            	fw.close();
        	}
    	}
    }

    @FXML
    void sentenceCountMenuButtonPressed(ActionEvent event) {
    	Tools.resetEditBoolean();
    	Demo.editOnOff[2] = true;
    	String entry = textAreaEntry.getText();
    	statusBar.clear();
    	statusBar.setText("Sentence Count: " + Utilities.sentenceCount(entry));
    }

    @FXML
    void spellCheckMenuButtonPressed(ActionEvent event) {
    	Tools.resetEditBoolean();
    	Demo.editOnOff[0] = true;
    	String entry = textAreaEntry.getText();
    	statusBar.clear();
    	statusBar.setText(Utilities.spellCheck(entry));
    }

    @FXML
    void undoMenuButtonPressed(ActionEvent event) {
    	textAreaEntry.undo();
//    	if (Demo.textAreaStack.size() > 0) {
//        	textAreaEntry.clear();
//        	textAreaEntry.setText(Demo.textAreaStack.pop());
//    	}
    }

    @FXML
    void wordCountMenuButtonPressed(ActionEvent event) {
    	Tools.resetEditBoolean();
    	Demo.editOnOff[1] = true;
    	String entry = textAreaEntry.getText();
    	Utilities.wordCount(entry);
    	statusBar.clear();
    	statusBar.setText("Word Count: " + Utilities.wordCount(entry));
    }
    
    @FXML
    void SyllableCountMenuButtonPressed(ActionEvent event) {
    	Tools.resetEditBoolean();
    	Demo.editOnOff[3] = true;
    	String entry = textAreaEntry.getText();
    	statusBar.clear();
    	statusBar.setText("Syllable Count: " + Utilities.syllableCount(entry));
    }
    
    @FXML
    void onEditTextAreaEntry(KeyEvent event) {
    	String textAreaEntryString = textAreaEntry.getText();
    	//Demo.textAreaStack.push(textAreaEntryString);
    	if (Demo.editOnOff[0]) {
        	statusBar.clear();
        	statusBar.setText(Utilities.spellCheck(textAreaEntryString));
    	} else if (Demo.editOnOff[1]) {
    		statusBar.clear();
    		statusBar.setText("Word Count: " + Utilities.wordCount(textAreaEntryString));
    	} else if (Demo.editOnOff[2]) {
    		statusBar.clear();
    		statusBar.setText("Sentence Count: " + Utilities.sentenceCount(textAreaEntryString));
    	} else if (Demo.editOnOff[3]) {
    		statusBar.clear();
    		statusBar.setText("Syllable Count: " + Utilities.syllableCount(textAreaEntryString));
    	} else if (Demo.editOnOff[4]) {
    		statusBar.clear();
    		double fleschScore = Utilities.fleschScore(textAreaEntryString);
    		String fleschScoreRank = "";
        	if (fleschScore > 100.0) {
        		fleschScoreRank = "Under 5th Grade";
        	} else if (fleschScore >= 90.0 && fleschScore <= 100.0) {
        		fleschScoreRank = "5th Grade";
        	} else if (fleschScore >= 80.0 && fleschScore <= 90.0) {
        		fleschScoreRank = "6th Grade";
        	} else if (fleschScore >= 70.0 && fleschScore <= 80.0) {
        		fleschScoreRank = "7th Grade";
        	} else if (fleschScore >= 60.0 && fleschScore <= 70.0) {
        		fleschScoreRank = "8th & 9th Grade";
        	} else if (fleschScore >= 50.0 && fleschScore <= 60.0) {
        		fleschScoreRank = "10th to 12th Grade";
        	} else if (fleschScore >= 30.0 && fleschScore <= 50.0) {
        		fleschScoreRank = "College";
        	} else if (fleschScore >= 10.0 && fleschScore <= 30.0) {
        		fleschScoreRank = "College Graduate";
        	} else if (fleschScore > 0.0 && fleschScore <= 10.0) {
        		fleschScoreRank = "Professional";
        	} else if (fleschScore <= 0.0) {
        		fleschScoreRank = "Not Available";
        		fleschScore = 0.0;
        	}
    		statusBar.setText("Flesch Score: " + fleschScore + " (" + fleschScoreRank + ")");
    	}
    	
    }
    
    @FXML
    void onEditTextAreaChanged(KeyEvent event) {
    	//Demo.textAreaStack.push(textAreaEntry.getText());
    }

    

    


}
