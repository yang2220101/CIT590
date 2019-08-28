package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordReaderTest {

	WordReader testReader;
	String filename;

	@BeforeEach
	void setUp() throws Exception {
		this.filename = "D:/words_clean.txt";
		this.testReader = new WordReader(filename);
	}

	@Test
	void testWordReader() {
		assertTrue(this.filename.equals(this.testReader.filename));
	}



	@Test
	void testReadAndClean() {
		ArrayList<String> testLines = new ArrayList<>();
		
		try {
			testLines = testReader.readAndClean();
			// tests below are based on "words_clean.txt"
			assertTrue(testLines.size()==57);
			assertEquals("coil", testLines.get(0));
			assertEquals("bite", testLines.get(5));
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test
	void testIsApproved() {
		// Approved words
		assertTrue(testReader.isApproved("applejuice"));
		// Disapproved words
		assertFalse(testReader.isApproved("Applejuice"));
		assertFalse(testReader.isApproved("apple juice"));
		assertFalse(testReader.isApproved("5applejuice"));
		assertFalse(testReader.isApproved("apple.juice"));
		assertFalse(testReader.isApproved("apple's juice"));
		assertFalse(testReader.isApproved("apple-juice"));		
	}
	
	
	@Test
	void testPickWord() {
		// This method cannot be tested, since number is randomly picked.
	}
	
	
	@Test
	void testPickWordsOfCertainLength() throws FileNotFoundException {
		ArrayList<String> lines = new ArrayList<>();
		// Test if this method can get all three words of length 10 in lines correctly.
		ArrayList<String> length8 = new ArrayList<>();
		length8.add("sideline");
		length8.add("coverage");
		length8.add("electric");
		lines = testReader.readAndClean();
		assertEquals(length8, testReader.pickWordsOfCertainLength(lines, 8));
	}

}
