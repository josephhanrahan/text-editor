package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import javafx.scene.control.TextArea;

public class Tools {
	
	public static boolean oneCapitalLetter(String word) {
		for (int i=0; i<word.length(); i++) {
			char test = word.substring(i, i+1).charAt(0);
			if (Character.isUpperCase(test)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean oneLowerLetter(String word) {
		for (int i=0; i<word.length(); i++) {
			char test = word.substring(i).charAt(0);
			if (Character.isLowerCase(test)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean oneDigit(String word) {
		for (int i=0; i<word.length(); i++) {
			char test = word.substring(i, i+1).charAt(0);
			if (Character.isDigit(test)) {
				return true;
			}
		}
		return false;
	}
	
	public static String importFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String tempString;
        while ((tempString = br.readLine()) != null) {
        	sb.append(tempString);
        	sb.append(" ");
        }
        br.close();
        String text = sb.toString();
        return text;
	}
	
	public static void writeFile(String fileName, String s) {
		Path fileName1 = Path.of(fileName);
		try {
			Files.writeString(fileName1, s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String importTextFile(String fileName) throws FileNotFoundException {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contentBuilder.toString();
	}
	
	public static void resetEditBoolean() {
		for (int i=0; i<Demo.editOnOff.length; i++) {
			Demo.editOnOff[i] = false;
		}
	}

}
