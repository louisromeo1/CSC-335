package model;
/**
 * Model the tray of dice in the game Boggle. A DiceTray can 
 * be constructed with a 4x4 array of characters for testing.
 * 
 * A 2nd constructor with no arguments can be added later to
 * simulate the shaking of 16 dice and selection of one side.
 * * MODIFIED
 * 
 * @author Louis Romeo
 * 
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;


public class DiceTray {

	public char[][] theBoard;
	public static final char TRIED = '@';
	public static final char PART_OF_WORD = '!';
	private String attempt;
	private int index;
	public static final int SIZE = 4;
	public static final int NUMBER_SIDES = 6;

	/**
	 * Construct a DiceTray object using a hard-coded 2D array of chars. One is
	 * provided in the given unit test. You can create others.
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	public DiceTray() {
		// TODO: This 2nd constructor in DiceTray rolls and randomly places all 16
		// Boggle dice in the tray
		this.theBoard = new char[4][4];

		char[] sides = new char[] { 
				new Die(new char[] { 'L', 'R', 'Y', 'T', 'T', 'E' }).getRandomSide(),
				new Die(new char[] { 'A', 'N', 'A', 'E', 'E', 'G' }).getRandomSide(),
				new Die(new char[] { 'A', 'F', 'P', 'K', 'F', 'S' }).getRandomSide(),
				new Die(new char[] { 'Y', 'L', 'D', 'E', 'V', 'R' }).getRandomSide(),

				new Die(new char[] { 'V', 'T', 'H', 'R', 'W', 'E' }).getRandomSide(),
				new Die(new char[] { 'I', 'D', 'S', 'Y', 'T', 'T' }).getRandomSide(),
				new Die(new char[] { 'X', 'L', 'D', 'E', 'R', 'I' }).getRandomSide(),
				new Die(new char[] { 'Z', 'N', 'R', 'N', 'H', 'L' }).getRandomSide(),

				new Die(new char[] { 'E', 'G', 'H', 'W', 'N', 'E' }).getRandomSide(),
				new Die(new char[] { 'O', 'A', 'T', 'T', 'O', 'W' }).getRandomSide(),
				new Die(new char[] { 'H', 'C', 'P', 'O', 'A', 'S' }).getRandomSide(),
				new Die(new char[] { 'N', 'M', 'I', 'H', 'U', 'Q' }).getRandomSide(),

				new Die(new char[] { 'S', 'E', 'O', 'T', 'I', 'S' }).getRandomSide(),
				new Die(new char[] { 'M', 'T', 'O', 'I', 'C', 'U' }).getRandomSide(),
				new Die(new char[] { 'E', 'N', 'S', 'I', 'E', 'U' }).getRandomSide(),
				new Die(new char[] { 'O', 'B', 'B', 'A', 'O', 'J' }).getRandomSide() };

		Random r = new Random();
		int sidesIndex = r.nextInt(16);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				while (sides[sidesIndex] == ' ') {
					sidesIndex = r.nextInt(16); // Randomly places all 16 spots
				}
				theBoard[i][j] = sides[sidesIndex];
				sides[sidesIndex] = ' ';
			}
		}
	}

	/**
	 * Return true if attempt can found on the board following the rules of Boggle
	 * like the same letter can only be used once.
	 */
	public boolean found(String attempt) {
		
		attempt = attempt.toUpperCase();
		if (attempt.length() < 3) {
			return false;
		}

		// remove the u from attempt string
		for (int x = 0; x < attempt.length(); x++) {
			if (attempt.charAt(x) == 'Q') {
				attempt = attempt.substring(0, x + 1) + attempt.substring(x + 2);
			}
		}

		// start from every position in the grid
		for (int x = 0; x < theBoard[0].length; x++) {
			for (int y = 0; y < theBoard.length; y++) {
				boolean wordFound = backtrack(attempt, x, y, 0);

				if (wordFound) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * A helper method to recurse on the given position and check all neighbors for
	 * the next letter in the word
	 */
	private boolean backtrack(String attempt, int x, int y, int index) {
		if (!isValid(x, y)) {
			return false;
		} else if (theBoard[x][y] != attempt.charAt(index)) {
			return false;
		} else if (index == attempt.length() - 1) {
			return true;
		}

		// choose
		char curLetter = theBoard[x][y];
		theBoard[x][y] = ' '; // replace the current character

		// explore
		boolean wordFound =
				// up, down, left, right
				backtrack(attempt, x, y - 1, index + 1) || backtrack(attempt, x, y + 1, index + 1)
						|| backtrack(attempt, x - 1, y, index + 1) || backtrack(attempt, x + 1, y, index + 1) ||

						// up-right, down-right, up-left, down-left
						backtrack(attempt, x + 1, y + 1, index + 1) || backtrack(attempt, x + 1, y - 1, index + 1)
						|| backtrack(attempt, x - 1, y + 1, index + 1) || backtrack(attempt, x - 1, y - 1, index + 1);

		theBoard[x][y] = curLetter;
		return wordFound;
	}

	// Private helper to determine if a given x, y coordinate is in the grid
	private boolean isValid(int x, int y) {
		return (x < theBoard[0].length && x >= 0) && (y < theBoard.length && y >= 0);
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < theBoard.length; i++) {
			s += Arrays.toString(theBoard[i]);
			s += '\n';
		}
		return s;
	}

	private class Die {
		
		// Private class added to model the die.
		private char[] sides;

		public Die(char[] sides) {
			this.sides = sides;
		}

		public char getRandomSide() {
			Random r = new Random();
			int i = r.nextInt(6);

			return sides[i];
		}

	}
}