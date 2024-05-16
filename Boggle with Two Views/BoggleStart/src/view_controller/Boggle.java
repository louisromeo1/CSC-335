package view_controller;
/*
 * Louis Romeo
 * CSC 335 Assignment 3
 * Purpose: Helper class which contains and utilizes
 * 			multiple methods to return necessary objects
 * 			useful for playing Boggle.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import model.DiceTray;

public class Boggle {
	/*
	 * This function takes a filename (containing all valid words for the boggle
	 * game) as a string and creates a hashtable to store them representing
	 */ 
		
	public static Hashtable<String, String> getDictionary(String s) throws FileNotFoundException {
		Hashtable<String, String> dictWords = new Hashtable<>();
		File file = new File(s);

		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			String word = scanner.next();
			dictWords.put(word, word);
		}
		scanner.close();

		return dictWords;
	}

	/**
	 * Takes all the words found by the user and a dictionary containing valid words
	 * and calculates the users score
	 */ 
	public static Hashtable<Integer, ArrayList<String>> getScore(ArrayList<String> userWords,
			Hashtable<String, String> dict, DiceTray tray) {
		ArrayList<String> foundWords = new ArrayList<>();
		ArrayList<String> wrongWords = new ArrayList<>();
		ArrayList<String> missedWords = new ArrayList<>();

		Hashtable<Integer, ArrayList<String>> retVal = new Hashtable<>();

		// finding all words in the dictionary that the tray contains
		for (String word : dict.values()) {
			if (tray.found(word)) {
				missedWords.add(word);
			}
		}

		int score = 0;
		
		/*
		 * if the user entered the word and the dict contains it add to the score,
		 * remove that word from the missed words. If the word was not in the dictionary
		 * add it to the missed words list
		 */
		for (String word : userWords) {
			if (tray.found(word) && dict.contains(word) && !foundWords.contains(word)) {
				
				foundWords.add(word);
				missedWords.remove(word);

				if (word.length() < 5) {
					score += 1;
				} else if (word.length() == 5) {
					score += 2;
				} else if (word.length() == 6) {
					score += 3;
				} else if (word.length() == 7) {
					score += 5;
				} else {
					score += 11;
				}

			} else if (!wrongWords.contains(word) && !foundWords.contains(word)) {
				wrongWords.add(word);
			}

		}

		ArrayList<String> scoreString = new ArrayList<String>();
		scoreString.add(Integer.toString(score));
		
		retVal.put(0, scoreString);
		retVal.put(1, foundWords);
		retVal.put(2, wrongWords);
		retVal.put(3, missedWords);
		return retVal;
	}

}
