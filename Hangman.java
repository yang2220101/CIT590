package hangman;

import java.util.*;
/**
 * This is the abstract hangman class for hangman game.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public abstract class Hangman {

	/**
	 * The original word for traditional hangman.
	 */
	protected String word;

	/**
	 * The word family the computer picks for evil hangman.
	 */
	protected ArrayList<String> wordFamily = new ArrayList<String>();

	/**
	 * Representing the current status of user's guesses.
	 */
	protected char[] userGuess;

	/**
	 * The number of user's guesses.
	 */
	protected int guessCount;

	/**
	 * The number of user's mistaken guesses.
	 */
	protected int mistakeCount;

	/**
	 * Constructor for Hangman.
	 * @param len
	 */
	public Hangman(int len) {
		//to avoid the situation that underscores together look like a single line
		this.userGuess = new char[2 * len];
		for(int i = 0; i < len; i++) {
			this.userGuess[2 * i] = '_';
			this.userGuess[2 * i + 1] = ' ';
		}
	}

	/**
	 * Tell if user's guess is repeated.
	 * @param ch : user's guess
	 * @return true if guess is correct but repeated.
	 */
	protected boolean repeatGuess(char ch) {	
		for(int i = 0; i < this.userGuess.length; i++) {
			if(userGuess[i] == ch) {
				return true;
			}					
		}
		return false;
	}

	/**
	 * Process the guess the user makes.
	 * @param ch : user's guess
	 * @return true if the guess is correct
	 */
	public abstract boolean makeGuess(char ch);

	/**
	 * Display current status
	 * @return userGuess.
	 */
	public char[] getStatus() {
		return this.userGuess;
	}

	/** 
	 * Get number of user guesses.
	 * @return guessCount.
	 */
	public int getGuessCount() {
		return this.guessCount;
	}

	/** 
	 * Get number of user mistakes.
	 * @return mistakeCount.
	 */
	public int getMistakeCount() {
		return this.mistakeCount;
	}


	/**
	 * Tell if game is done
	 * @return true if the word has been revealed.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < this.userGuess.length; i++) {
			if(this.userGuess[i]=='_') {
				return false;
			}
		}
		return true;
	}
}
