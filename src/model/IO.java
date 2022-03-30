package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.stream.Stream;

import javafx.scene.control.TextArea;

public class IO {
	
	public static void backUpUsers(UserBag userBag) throws IOException {
		File file = new File("src/data/userBackup.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(userBag);
		oos.close();
	}
	
	public static UserBag restoreUsers(UserBag userBag) throws ClassNotFoundException, IOException {
		File file = new File("src/data/userBackup.dat");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		userBag = (UserBag) ois.readObject();
		ois.close();
		return userBag;
	}
	
	public static void importTextFile2StringArray(String[] arr, String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		int index = 0;
		while (inputFile.hasNext()) {
			arr[index++] = inputFile.nextLine();
		}
		inputFile.close();
	}
	
	public static void importTextFile2TextArea(String fileName, TextArea textArea) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String tempString;
        while ((tempString = br.readLine()) != null) {
        	sb.append(tempString);
        	sb.append(" ");
        }
        br.close();
        String text = sb.toString();
        textArea.setText(text);
	}
	
	public static void exportTextFileFromTextArea(String fileName, String textAreaEntry) throws IOException {
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file, false);
		fw.write(textAreaEntry);
		fw.close();
	}
	
	public static HashMap<String, Integer> restoreDictionary(HashMap<String, Integer> hashMap) throws IOException, ClassNotFoundException {
		File file = new File("src/data/dictionaryBackup.dat");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		hashMap = (HashMap<String, Integer>) ois.readObject();
		ois.close();
		return hashMap;
	}
	
	public static HashMap<String, Integer> importWords2HashMap() throws FileNotFoundException {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>(99171);
		File file = new File("src/data/dictionary.txt");
		Scanner inputFile = new Scanner(file);
		while (inputFile.hasNext()) {
			hashMap.put(inputFile.nextLine(), 1);
		}
		inputFile.close();
		return hashMap;
	}
	
	public static void backUpHashMap(HashMap<String, Integer> hashmap) throws IOException {
		File file = new File("src/data/dictionaryBackup.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hashmap);
		oos.close();
	}

}
