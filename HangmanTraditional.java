package hangman;
/**
 * The class for HangmanTraditional
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class HangmanTraditional extends Hangman {

	/**
	 * Constructor of HangmanTraditional
	 * @param word
	 */
	public HangmanTraditional(String word){
		super(word.length());
		this.word = word;

	}

	@Override
	public boolean makeGuess(char ch) {

		this.guessCount++;
		String guess = ch + "";

		//repeated guess
		if(this.repeatGuess(ch)) {
			return false;
		}

		//wrong guess:
		if(!this.word.contains(guess)) {
			this.mistakeCount++;
			return false;
		}

		//correct guess
		for(int i = 0; i < this.word.length(); i++) {
			if(ch == word.charAt(i) && this.userGuess[2 * i] != ch) {
				this.userGuess[2 * i] = ch;
			}
		}		
		return true;
	}

}


