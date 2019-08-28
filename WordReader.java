package hangman;

import java.util.*;
import java.io.*;

/** 
 * This class reads and cleans the words from file:
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class WordReader {
	/**
	 * A String that stores filename.
	 */
	String filename;


	/**
	 * List of clean words.
	 */
	ArrayList<String> cleanWords = new ArrayList<>();


	/**
	 * The max length of approved words: to be used in evil hangman random pick
	 */
	private int maxLength;

	/**
	 * Constructor for WordReader Class.
	 * @param filename
	 */
	public WordReader(String filename) {
		this.filename = filename;


	}


	/**
	 * This method reads and cleans words from file.		 
	 * @return ArrayList of String that contains legal words
	 */
	public ArrayList<String> readAndClean() throws FileNotFoundException{
		ArrayList<String> cleanWords = new ArrayList<>();
		File myfile = new File(filename);
		Scanner sc = new Scanner(myfile);

		while(sc.hasNext()) {
			String line = sc.nextLine();
			// Add to cleanWords if this word is approved
			if (isApproved(line)) {
				cleanWords.add(line);
				if(line.length() > this.maxLength) {
					this.maxLength = line.length();
				}
			}
		}
		// Return lists of approved words
		sc.close();
		this.cleanWords = cleanWords;
		return cleanWords;
	}


	/**
	 * To tell if a given word is approved by computer.
	 * @param line
	 * @return true if this word is approved to use.
	 */
	public boolean isApproved(String line) {
		// Keep only approved words:
		for (int i = 0; i < line.length(); i++) {
			//check if there are Upper case letters
			if(Character.isUpperCase(line.charAt(i))) {
				return false;
			}
			//check if there are digits
			if(Character.isDigit(line.charAt(i))) {
				return false;
			}
		}	
		// check if there are compound words - words with spaces
		if(line.isEmpty()|| line.contains(" ")) {
			return false;
		}
		//check is there are Abbreviations, apostrophe or hyphen 
		if(line.contains(".")||line.contains("'")|| line.contains("-")) {
			return false;
		}	
		return true;
	}


	/**
	 * Pick a word randomly
	 * @param words : approved words list
	 * @return picked word
	 */
	public String pickWord(ArrayList<String> words) {
		// Computer randomly pick a word in approved list.
		Random random = new Random();		
		int idx = random.nextInt(words.size());
		return words.get(idx);
	}


	/** 
	 * For evil hangman: pick out all words of certain length
	 * @param words cleaned words
	 * @param length desired length
	 * @return all words of that length
	 */
	public ArrayList<String> pickWordsOfCertainLength(ArrayList<String> words, int length){
		ArrayList<String> pickedWords = new ArrayList<>();

		// Get all words of that length
		for(String word : words) {
			if(word.length()==length) {
				pickedWords.add(word);
			}
		}
		return pickedWords;
	}


	/**
	 * Added for evil hangman: get lists of words of random length.
	 * @param words all approved words
	 * @return picked words of random length
	 */
	public ArrayList<String> pickWords(ArrayList<String> words){
		// Pick a length randomly
		Random random = new Random();		
		int pickedLength = random.nextInt(this.maxLength);				

		// Get all words of that length
		ArrayList<String> picked = pickWordsOfCertainLength(words, pickedLength);

		// Picked words cannot be null
		while(picked == null || picked.size()==0) {	
			pickedLength = random.nextInt(this.maxLength);				
			picked = pickWordsOfCertainLength(words, pickedLength);
		}		
		return picked;
	}

}
