/**
 * Louis Romeo
 * CSC 335 Assignment 4
 * Purpose: This class is an implementation of 
 * 			an intermediate TicTacToe game AI.
 */

package model;

import java.util.Random;

public class IntermediateAI  implements TicTacToeStrategy {
	private int[] coords = {0, 0};

  @Override
  public OurPoint desiredMove(TicTacToeGame theGame) {
	  char first, second;
	  // if the AI goes first it should be the X char
	  if (theGame.maxMovesRemaining() % 2 == 0) {
		  first = 'O';
		  second = 'X';
	  } else {
		  first = 'X';
		  second = 'O';
	  }
	  /* if the AI's char can win the game return the spot
	   * but if not check if the AI can block the player from winning
	   * If this is not possible choose a random spot that is available 
	   */
	  
	  if (!canWin(theGame, first)) {
		  if (!canWin(theGame, second)) {
			  Random r = new Random();
			  
			  boolean spot = false;
			  int row, col;
			  
			  while (!spot) {
				  row = r.nextInt(3);
				  col = r.nextInt(3);
				  
				  if (theGame.available(row, col)) {
					  coords[0] = row;
					  coords[1] = col;
					  spot = true;
				  }
			  }
		  }
	  }
	  OurPoint bestSpot = new OurPoint(coords[0], coords[1]);
    
	  return bestSpot;
  }
  
  private boolean canWin(TicTacToeGame theGame, char player) {
	  char[][] board = theGame.getTicTacToeBoard();
	  
	  for (int i = 0; i < 3; i++) {
		  for (int j = 0; j < 3; j++) {
			  if (theGame.available(i, j)) {
				  // set the spot to the char and see if that wins the game
				  board[i][j] = player;
				  if (theGame.didWin(player)) {
					  // the coordinates that will be returned from desiredMove
					  coords[0] = i;
					  coords[1] = j;
					  // undo the choice so the game is unchanged upon return
					  board[i][j] = '_';
					  
					  return true;
				  }
				  // undo the choice
				  board[i][j] = '_';
			  }
		  }
	  }
	  return false;
  }
 
}