package hangman;


import java.util.*;

/**
 * The class for HangmanEvil
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class HangmanEvil extends Hangman {

	/**
	 * Constructor of HangmanEvil
	 * @param words
	 */
	public HangmanEvil(ArrayList<String> words) {
		super(words.get(0).length());
		this.wordFamily = words;
	}

	@Override
	public boolean makeGuess(char ch) {

		this.guessCount++;
		String guess = ch + "";

		//split the word list, and choose the largest family:
		ArrayList<String> larFamily = new ArrayList<>();
		larFamily = updateList(ch, this.wordFamily);

		//repeated guess
		if(this.repeatGuess(ch)) {
			return false;
		}

		//wrong guess
		if(!larFamily.get(0).contains(guess)) {
			this.wordFamily = larFamily;
			this.mistakeCount++;
			return false;
		}

		//if ch exists in the word family, show the status of the family
		String firstWord = larFamily.get(0);
		for(int i = 0; i < firstWord.length(); i++) {
			if(firstWord.charAt(i) == ch) {
				this.userGuess[2 * i] = ch;
			}
		}	
		this.wordFamily = larFamily;
		return true;
	}


	/**
	 * Choose the largest word family as new word list.
	 * @param ch 
	 * @param words 
	 * @return updateList
	 */
	private ArrayList<String> updateList(char ch, ArrayList<String> words){

		String guess = ch + "";
		Map<ArrayList<Integer>, ArrayList<String>> wordMap = new HashMap<ArrayList<Integer>, ArrayList<String>>();		

		//check every word
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);

			if(word.contains(guess)) {
				//store the positions of the char the user guesses
				ArrayList<Integer> position = new ArrayList<>();				

				//get the positions
				for(int j = 0; j < word.length(); j++) {
					if(word.charAt(j) == ch) {
						position.add(j);
					}
				}

				if(!wordMap.containsKey(position)) {
					//add the new key
					ArrayList<String> member = new ArrayList<>();
					member.add(word);
					wordMap.put(position, member);
				}else {
					//update current key
					ArrayList<String> member = wordMap.get(position);
					member.add(word);
					wordMap.replace(position, member);
				}			
			}else {
				//if guess char not in word, set position as -1
				ArrayList<Integer> position = new ArrayList<>();
				position.add(-1);

				if(!wordMap.containsKey(position)) {
					//add the new key
					ArrayList<String> member = new ArrayList<>();
					member.add(word);
					wordMap.put(position, member);
				}else {
					//update current key
					ArrayList<String> member = wordMap.get(position);
					member.add(word);
					wordMap.replace(position, member);
				}						
			}		
		}
		//choose largest family
		int maxSize = 1;
		ArrayList<Integer> maxKey = new ArrayList<>();
		ArrayList<Integer> curKey = new ArrayList<>();

		for(ArrayList<Integer> k : wordMap.keySet()) {
			int curSize = wordMap.get(k).size();
			curKey = k;
			if(curSize >= maxSize) {
				maxSize = curSize;
				maxKey = curKey;
			}
		}
		return wordMap.get(maxKey);
	}


}

