package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTest {

	Hangman test1;
	Hangman test2;
	Hangman test3;
	WordReader testReader;
	String filename;
	ArrayList <String> lines;

	@BeforeEach
	void setUp() throws Exception {
		// traditional guess
		this.test1 = new HangmanTraditional("aabbcd");
		this.test2 = new HangmanTraditional("qbsh");

		
		// evil guess
		this.filename = "D:/words_clean.txt";
		this.testReader = new WordReader(filename);
		this.lines = testReader.pickWordsOfCertainLength(testReader.readAndClean(), 8);
		this.test3 = new HangmanEvil(lines);
	}
	
	


	@Test
	void testMakeGuess() {
		// test hangman traditional
		assertTrue(test1.makeGuess('a'));
		assertFalse(test1.makeGuess('a'));
		assertTrue(test1.makeGuess('b'));
		assertTrue(test1.makeGuess('c'));
		assertTrue(test1.makeGuess('d'));
		assertFalse(test1.makeGuess('d'));		
		
		assertTrue(test2.makeGuess('q'));
		assertTrue(test2.makeGuess('b'));
		assertTrue(test2.makeGuess('s'));
		assertTrue(test2.makeGuess('h'));
		assertFalse(test2.makeGuess('a'));
		assertFalse(test2.makeGuess('c'));
		assertFalse(test2.makeGuess('d'));
		assertFalse(test2.makeGuess('e'));	
		
		// test hangman evil 
		// None of 3 words has 'h': should be false
		assertFalse(test3.makeGuess('h'));
				
		// 2 of 3 words don't have 'v': should be false
		assertFalse(test3.makeGuess('v'));
				
		// word "sideline" now selected and cannot reverse:
		assertTrue(test3.makeGuess('e'));
		assertTrue(test3.makeGuess('i'));
		assertTrue(test3.makeGuess('s'));
		
	}
	
	
	@Test
	void testGetStatus(){
		// test status display 
		test1.makeGuess('a');
		assertEquals('a', test1.getStatus()[0]);
		assertEquals('a', test1.getStatus()[2]);
		test1.makeGuess('b');
		assertEquals('b', test1.getStatus()[4]);
		assertEquals('b', test1.getStatus()[6]);
				
		test2.makeGuess('q');
		assertEquals('q', test2.getStatus()[0]);				
		assertEquals(' ', test2.getStatus()[1]);
		assertEquals('_', test2.getStatus()[2]);
		
		test2.makeGuess('h');
		assertEquals('h', test2.getStatus()[6]);
		assertEquals(' ', test2.getStatus()[7]);
	}

	
	@Test
	void testGetGuessCount() {
		// test guess count increment 
		test1.makeGuess('a');
		assertTrue(test1.getGuessCount() == 1);
		test1.makeGuess('a');
		assertTrue(test1.getGuessCount() == 2);
		test1.makeGuess('b');
		assertTrue(test1.getGuessCount() == 3);
		test1.makeGuess('b');
		assertTrue(test1.getGuessCount() == 4);
		test1.makeGuess('c');
		assertTrue(test1.getGuessCount() == 5);
		test1.makeGuess('d');
		assertTrue(test1.getGuessCount() == 6);		
		}
	
	
	@Test
	void testGetMistakeCount() {
		// test mistakes count increment during guess progress
		test2.makeGuess('q');
		assertTrue(test2.getMistakeCount() == 0);
		
		test2.makeGuess('a');
		assertEquals(1, test2.getMistakeCount());
		
		test2.makeGuess('a');
		assertEquals(2, test2.getMistakeCount());
		
		test2.makeGuess('b');
		assertEquals(2, test2.getMistakeCount());
		
		test2.makeGuess('b');
		assertEquals(2, test2.getMistakeCount());
		
		test2.makeGuess('d');
		assertEquals(3, test2.getMistakeCount());
		
		test2.makeGuess('h');
		assertEquals(3, test2.getMistakeCount());
	}
		
	
	@Test
	void testIsGameOver() {
		assertFalse(test1.isGameOver());
		test1.makeGuess('a');
		test1.makeGuess('a');
		test1.makeGuess('c');
		test1.makeGuess('d');
		assertFalse(test1.isGameOver());
		test1.makeGuess('b');
		test1.makeGuess('b');
		assertTrue(test1.isGameOver());
		
		assertFalse(test2.isGameOver());
		test2.makeGuess('q');
		test2.makeGuess('b');
		test2.makeGuess('h');	
		assertFalse(test2.isGameOver());
		test2.makeGuess('s');
		assertTrue(test2.isGameOver());
	}

}
