package hangman;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * We launch the game from this class.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class HangmanGame {
	/**
	 * This method decides the mode of the game(evil or traditional).
	 * @param gameReader, isEvil
	 * @return evil or traditional
	 */
	public static Hangman gameType(WordReader gameReader, Boolean isTra) throws FileNotFoundException{

		//clean the word list
		ArrayList <String> lines = gameReader.readAndClean();

		if(!isTra) {
			//evil mode
			ArrayList<String> wordList = new ArrayList<String>();
			wordList = gameReader.pickWords(lines);
			HangmanEvil evil = new HangmanEvil(wordList);				
			return evil;
		}

		//traditional mode
		String word = gameReader.pickWord(lines);
		HangmanTraditional triditional = new HangmanTraditional(word);
		return triditional;	

	}


	/**
	 * This method controls the process of the game.
	 * @param gameReader, sc
	 * @throws FileNotFoundException
	 */
	public static void playGame(WordReader gameReader, Scanner sc) throws FileNotFoundException {

		//randomly decide evil or traditional
		Random random = new Random();
		Boolean isTra = random.nextBoolean();
		Hangman type = gameType(gameReader, isTra);			
		System.out.println("Guess one char at a time and try to find the word!");

		//store wrong guess
		ArrayList<String> wrong = new ArrayList<String>();	

		while(!type.isGameOver()) {

			System.out.println("");
			System.out.println("Guess a letter: ");
			System.out.println(type.getStatus());
			//parse user guess into a char:
			String guess = sc.next();
			char guessChar = guess.charAt(0);

			//if repeated guess happens
			if(type.repeatGuess(guessChar)) {
				System.out.println("Guessed already! Try another one.");
			}			

			//if wrong guess happens
			else if(!type.makeGuess(guessChar)) {
				System.out.println("Wrong guess!");
				wrong.add(guess);
			}
			//correct guess
			else {
				System.out.println("Correct guess!");	
			}

			System.out.print("Wrong Guesses:" + wrong);
			System.out.println();
		}

		//print the result
		System.out.println();
		System.out.println("You won!");
		System.out.print("The word is ");	
		System.out.println(type.getStatus());
		System.out.println("You made " + type.getGuessCount() + " guesses");		
		System.out.println("Your made " + type.getMistakeCount() + " mistakes");

		//tell user the version of the game
		if(isTra) {
			System.out.println("You were playing traditional mode!");
		}else {
			System.out.println("You were playing evil mode!");
		}		
	}		


	public static void main(String[] args) {

		//read the text file
		WordReader myGame = new WordReader("D:/words.txt");

		System.out.println("Welcome to Hangman!");
		boolean playing = true;
		Scanner sc = new Scanner(System.in);

		while(playing){
			try {				
				playGame(myGame, sc);
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
				e.printStackTrace();
			}

			//ask the player if he/she wants to play again
			System.out.println();
			System.out.println("Another round?");
			System.out.println("Press y to continue, press other key to exit.");
			String newGame = sc.next();
			if(!("y".equals(newGame))) {
				// quit game
				playing = false;
				System.out.println("Game Over!");				
			}
		}
		sc.close();

	}

}
