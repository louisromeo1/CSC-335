/**
 * Tests the Board class.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package tests;

import model.Accounts;
import model.Board;
import model.shopItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.util.Scanner;


public class BoardTest {

	Scanner scanner = new Scanner(System.in);

	@Test
	public void testInit() {
		Board testBoard = new Board(4);
		testBoard.initBoard(null);
		assertEquals("A", testBoard.getCard(0, 0).getType2());
		assertEquals("1", testBoard.getCard(0, 0).getType1());
		assertEquals(4, testBoard.getSize());
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a newly initialized board? Y/N\n");
		String input = scanner.nextLine();
		assertEquals("Y", input);
	}
	
	@Test
	public void testMode() {
		Board testBoard = new Board(4);
		assertEquals(0, testBoard.getMode());
		testBoard.changeMode(2);
		assertEquals(2, testBoard.getMode());
	}
	
	@Test
	public void testShuffle() {
		Board testBoard = new Board(6);
		testBoard.initBoard(null);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled board? Y/N\n");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}
	
	@Test
	public void testOdd() {
		Board testBoard = new Board(5);
		testBoard.changeMode(1);
		testBoard.initBoard(null);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled odd one out board? Y/N\n");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}
	
	@Test
	public void testThree() {
		Board testBoard = new Board(3);
		testBoard.changeMode(2);
		testBoard.initBoard(null);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled 3 of a kind board? Y/N\n");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}
	
	@Test
	public void testPowers() {
		// Test even size
		Board testBoard = new Board(4);
		testBoard.changeMode(3);
		testBoard.initBoard(null);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled Powers board? Y/N\n");
		System.out.println("There should be 2 power cards");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	    // Test odd size
		Board testBoard2 = new Board(3);
		testBoard2.changeMode(3);
		testBoard2.initBoard(null);
		testBoard2.shuffle();
		System.out.println(testBoard2.toString());
		System.out.println("Does this look like a shuffled Powers board? Y/N\n");
		System.out.println("There should be 1 power card");
	    String input2 = scanner.nextLine();
	    assertEquals("Y", input2);
	}
	
	@Test
	public void testUnlockedPowers() {
		// Test even size
		Board testBoard = new Board(4);
		Accounts user = new Accounts("", "");
		user.addUnlockedItem(new shopItem("Unlock Power Card: Bomb", 0));
		user.addUnlockedItem(new shopItem("Unlock Power Card: Laser", 0));
		testBoard.changeMode(3);
		testBoard.initBoard(user);
		testBoard.shuffle();
		System.out.println(testBoard.toString());
		System.out.println("Does this look like a shuffled Powers board? Y/N\n");
		System.out.println("There should be 2 power cards, randomly chosen of the 3");
	    String input = scanner.nextLine();
	    assertEquals("Y", input);
	}

}

