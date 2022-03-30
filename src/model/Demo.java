package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import java.util.Timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Demo extends Application {
	
	public static UserBag userBag = new UserBag();
	public static File currentFile = null;
	public static Stack<String> textAreaStack = new Stack<String>();
	public static HashMap<String, Integer> dictionaryHashmap;
	public static boolean[] editOnOff = new boolean[] {false, false, false, false, false};
	public static parentMap parentMap = new parentMap();
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		userBag = IO.restoreUsers(userBag);
		dictionaryHashmap = IO.restoreDictionary(dictionaryHashmap);
		FXMLLoader loader = new FXMLLoader();
		System.out.println();
		loader.setLocation(getClass().getResource("/view/SignInPane.fxml"));
		Pane root = loader.<Pane>load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void init() throws ClassNotFoundException, IOException {
		IO.restoreUsers(userBag);
		IO.restoreDictionary(dictionaryHashmap);
	}
	public void stop() throws IOException {
		IO.backUpUsers(userBag);
	}
	
	

}
