package model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
	
	public static Comparator<User> c4u = (User o1, User o2) -> {
		return o1.getUsername().compareTo(o2.getUsername());
	};
	
	public static boolean passWordCheck(String password) {
		if (Tools.oneCapitalLetter(password) == true && Tools.oneDigit(password) == true && Tools.oneLowerLetter(password) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean uniqueUsername(String username) {
		User user = new User(username, "password");
		if (Demo.userBag.searchByUsername(user) == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean doesUserExist(User user) {
		if (Demo.userBag.searchByUsername(user)==null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String spellCheck(String textAreaEntry) {
		String[] textAreaEntrySplit = textAreaEntry.split("[^A-Za-z]+");
		String wrongWords = "";
		int counter = 0;
		for (int i =0; i<textAreaEntrySplit.length; i++) {
			if (!Demo.dictionaryHashmap.containsKey(textAreaEntrySplit[i].toLowerCase()) ) {
				if (counter > 0) {
					wrongWords += ", ";
				}
				wrongWords += textAreaEntrySplit[i];
				counter++;
			}
		}
		return wrongWords;
	}
	
	public static double fleschScore(String entry) {
		double par1 = (206.835) - (1.015*((double)wordCount(entry)/(double)sentenceCount(entry)));
		double par2 = (84.6) * ((double)syllableCount(entry)/(double)wordCount(entry));
		double par3 = par1 - par2;
		return (Math.round(par3 * 10) / 10.0);
	}
	
	public static int sentenceCount(String entry) {
    	int sentences = 0;
    	Pattern tokenSplitter = Pattern.compile("[^.!?]+");
    	Matcher m = tokenSplitter.matcher(entry);
    	while (m.find()) {
    		sentences++;
    	}
    	return sentences;
	}
	
	public static int wordCount(String entry) {
    	Pattern tokenSplitter = Pattern.compile("[A-Za-z]+");
    	Matcher m = tokenSplitter.matcher(entry);
    	int words = 0;
    	while (m.find()) {
    		words++;
    	}
    	return words;
	}
	
	public static int syllableCount(String entry) {
    	Pattern tokenSplitter = Pattern.compile("[AEIOUYaeiouy]+[^$e(,.\"/:>]");
    	Matcher m = tokenSplitter.matcher(entry);
    	int syllables = 0;
    	while (m.find()) {
    		syllables++;
    	}
    	return syllables;
	}

}
