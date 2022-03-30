package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class parentMap {
	
	private TreeMap<String, babyMap> parentMap;
	public String createdString = "";
	
	
	
	public parentMap() {
		super();
		this.parentMap = new TreeMap<String, babyMap>();
	}

	public void learnText(String text) {
		String[] words = text.split("[^a-zA-Z]+");
		for (int i=0; i<words.length; i++) {
			if (!parentMap.containsKey(words[i])) {
				parentMap.put(words[i], new babyMap());
				if (i+1<words.length) {
					parentMap.get(words[i]).getBabyMap().add(words[i+1]);
				}
			} else if (parentMap.containsKey(words[i])) {
				if (i+1<words.length) {
					parentMap.get(words[i]).getBabyMap().add(words[i+1]);
				}
			}
		}
	}
	
	public String createText(String firstWord, int length) {
		String returnString = "";
		String temp = firstWord;
		returnString += temp + " ";
		for (int i=0; i<length; i++) {
			int rand = randomLengthInt(parentMap.get(temp).getBabyMap());
			returnString += parentMap.get(temp).getBabyMap().get(rand) + " ";
			temp = parentMap.get(temp).getBabyMap().get(rand);
		}
		return returnString;
	}
	
	private int randomLengthInt(LinkedList<String> list) {
		int rand = (int)(Math.random() * list.size());
		return rand;
	}

	public String getCreatedString() {
		return createdString;
	}

	public void setCreatedString(String createdString) {
		this.createdString = createdString;
	}
	
	

}
