package tests;

/**
 * This unit test simulates playing many games where the human player
 * and ComputerPlayer make hard coded moves to reach a win or tie. 
 * The assertions also provide documentation of the methods available
 * in class TicTacToeGame and how they behave
 * 
 * @author Rick Mercer 
 */

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import model.TicTacToeGame;

public class TicTacToeBoardTest {
 
  @Test
  public void runToStringForCodeCoverage() {
    TicTacToeGame b = new TicTacToeGame();
    b.toString();
  }
 
  @Test
  public void runMaxMovesRemaining() {
    TicTacToeGame b = new TicTacToeGame();
    assertEquals(9, b.maxMovesRemaining());
    // When the 3rd argument is true, humanMove will NOT ask the 
    // computer player to make a move.  This is dependency injection 
    // to allow for unit testing with computerMove messages
    b.humanMove(0, 0, true);   
    assertEquals(8, b.maxMovesRemaining());
    b.humanMove(0, 2, true);   
    assertEquals(7, b.maxMovesRemaining());
 
    b.humanMove(1, 1, false); // now makes a computerMove if game not over  
    assertEquals(5, b.maxMovesRemaining());
  }
  
  @Test
  public void testStillRunningWhenXWins() {
    TicTacToeGame b = new TicTacToeGame();
    assertTrue(b.stillRunning());
    // When the 3rd argument is true, humanMove will NOT ask the computer player
    // to make a move.  This is dependency injection to allow for unit testing
    // with computerMove messages
    b.humanMove(0, 0, true);
    b.computerMove(1, 0);
    b.humanMove(0, 1, true);
    b.computerMove(1, 1);
    assertTrue(b.stillRunning());
    b.humanMove(0, 2, true);
    assertFalse(b.stillRunning());
  }

  @Test
  public void testInitialValuesAre_() {
    TicTacToeGame b3 = new TicTacToeGame();
    b3.humanMove(1, 1, true); // Block just one
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (row == 1 && col == 1)
          assertFalse(b3.available(row, col));
        else
          assertTrue(b3.available(row, col));
      }
    }

    // System.out.println(b3.toString());
    // _ _ _
    // _ _ _ 
    // _ _ _
    //
  }

  @Test
  public void testChoose() {
    TicTacToeGame b = new TicTacToeGame();
    // board will refer to the changing board in the TicTacToeBoard class
    char[][] board = b.getTicTacToeBoard();

    b.humanMove(0, 0, true);
    assertEquals('X', board[0][0]);
    assertEquals('_', board[1][1]);
    assertEquals('_', board[2][2]); // could be more, but 2 will do

    b.computerMove(0, 1);
    assertEquals('O', board[0][1]);

    b.humanMove(1, 1, true);
    assertEquals('X', board[1][1]);

    b.computerMove(2, 2);
    assertEquals('O', board[2][2]);

    // Board should now look like this if toString() is used
    //  System.out.println(b.toString());
    // X O _
    // _ X _
    // _ _ O

    // Fill the board to a tie
    b.humanMove(0, 2, true);
    b.computerMove(1, 0);
    b.humanMove(1, 2, true);
    b.computerMove(2, 0);
    b.humanMove(2, 1, true);

    // System.out.println(b.toString());
    // X O X
    // O X X 
    // O X O
    char[][] move = b.getTicTacToeBoard();
    assertEquals('X', move[0][0]);
    assertEquals('O', move[0][1]);
    assertEquals('X', move[0][2]);
    assertEquals('O', move[1][0]);
    assertEquals('X', move[1][1]);
    assertEquals('X', move[1][2]);
    assertEquals('O', move[2][0]);
    assertEquals('X', move[2][1]);
    assertEquals('O', move[2][2]);

  }

  @Test
  public void testTie() {
    TicTacToeGame b = new TicTacToeGame();

    // Fill the board to a tie
    b.humanMove(0, 0, true);
    b.computerMove(0, 1);
    b.humanMove(1, 1, true);
    b.computerMove(2, 2);
    b.humanMove(0, 2, true);
    b.computerMove(1, 0);
    b.humanMove(1, 2, true);
    b.computerMove(2, 0);
    b.humanMove(2, 1, true);

    // System.out.println(b);
    // X O X
    // O X X
    // O X O
    assertFalse(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertTrue(b.tied());
  }

  @Test
  public void testEasyXWinRow0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(0, 0, true); // when true, the computer move is not made
    b.computerMove(2, 0);
    b.humanMove(0, 1, true);
    b.computerMove(2, 1);
    b.humanMove(0, 2, true);
    // System.out.println(b);
    // X X X
    // _ _ _
    // O O _
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyXWinRow1() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(1, 0, true);
    b.computerMove(2, 0);
    b.humanMove(1, 1, true);
    b.computerMove(2, 1);
    b.humanMove(1, 2, true);
    // System.out.println(b);
    // _ _ _
    // X X X
    // O O _
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyXWinRow2() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(2, 0, true);
    b.computerMove(1, 0);
    b.humanMove(2, 1, true);
    b.computerMove(1, 1);
    b.humanMove(2, 2, true);

    // System.out.println(b);
    // _ _ _
    // X X X
    // O O _
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinRow0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(1, 1, true);
    b.computerMove(0, 0);
    b.humanMove(2, 1, true);
    b.computerMove(0, 1);
    b.humanMove(1, 2, true);
    b.computerMove(0, 2);

    // System.out.println(b);
    // O O O
    // _ X X
    // _ X _
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinCol0() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(1, 1, true);
    b.computerMove(0, 0);
    b.humanMove(0, 2, true);
    b.computerMove(1, 0);
    b.humanMove(2, 2, true);
    b.computerMove(2, 0);

    // System.out.println(b);
    // O_X
    // OX_
    // O_X
    assertEquals(3, b.maxMovesRemaining());
    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinColTwo() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(2, 0, true);
    b.computerMove(0, 2);
    b.humanMove(1, 0, true);
    b.computerMove(1, 2);
    b.humanMove(1, 1, true);
    b.computerMove(2, 2);

    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testEasyOWinColOne() {
    TicTacToeGame b = new TicTacToeGame();
    // X always is first, O's next
    b.humanMove(2, 2, true);
    b.computerMove(0, 1);
    b.humanMove(1, 0, true);
    b.computerMove(1, 1);
    b.humanMove(1, 2, true);
    b.computerMove(2, 1);

    assertTrue(b.didWin('O'));
    assertFalse(b.didWin('X'));
    assertFalse(b.tied());
  }

  @Test
  public void testWinDiagonal() {
    TicTacToeGame b = new TicTacToeGame();
    b.humanMove(0, 0, true);
    b.computerMove(0, 1);
    b.humanMove(1, 1, true);
    b.computerMove(2, 0);
    b.humanMove(2, 2, true);
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    // System.out.println(b);
    // X O _
    // _ X _
    // O _ X
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testBackDiagonal() {
    TicTacToeGame b = new TicTacToeGame();
    b.humanMove(0, 2, true);
    b.computerMove(0, 1);
    b.humanMove(1, 1, true);
    b.computerMove(1, 2);
    b.humanMove(2, 0, true);
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));

    // System.out.println(b);
    // _ O X
    // _ X O
    // X _ _
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
  }

  @Test
  public void testNewGame() {
    TicTacToeGame b = new TicTacToeGame();
    // board will refer to the changing board in the TicTacToeBoard class
    char[][] board = b.getTicTacToeBoard();

    b.humanMove(0, 0, true);
    b.computerMove(1, 0);
    b.humanMove(0, 1, true);
    b.computerMove(1, 1);
    b.humanMove(0, 2, true);
    assertEquals('X', board[0][0]);
    assertEquals('X', board[0][1]);
    assertEquals('X', board[0][2]);
    assertTrue(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());

    b.startNewGame();
    board = b.getTicTacToeBoard();
    assertFalse(b.didWin('X'));
    assertFalse(b.didWin('O'));
    assertFalse(b.tied());
    assertEquals('_', board[0][0]);
    assertEquals('_', board[0][1]);
    assertEquals('_', board[0][2]);
  }

  @Test
  public void testCantPick() {
    TicTacToeGame b = new TicTacToeGame();
    b.humanMove(0, 0, true);
    b.computerMove(0, 0);
    char[][] board = b.getTicTacToeBoard();
    assertEquals('X', board[0][0]);
    assertEquals('_', board[0][1]);

    b.humanMove(1, 0, true);
    b.computerMove(1, 1);
    b.humanMove(1, 1, true);

    assertEquals('X', board[0][0]);
    assertEquals('_', board[0][1]);
  }

}