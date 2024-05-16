/**
 * This class allows a Tic Tac Toe (TTT) player to play games against a variety
 * of AIs. It completely relies on the TicTacToeStrategy for it's next move
 * with the desiredMove(TicTacToeGame theGame) method that can "see" the game.
 * 
 * @author Rick Mercer
 */
package model;

public class ComputerPlayer {

  private TicTacToeStrategy myStrategy;

  public ComputerPlayer() {
    // This default TicTacToeStrategy can be changed with setStrategy
    myStrategy = new RandomAI();
  }

  /**
   * Change the AI for this ComputerPlayer.
   * 
   * @param stategy  Any type that implements TicTacToeStrategy
   */
  public void setStrategy(TicTacToeStrategy strategy) {
    myStrategy = strategy;
  }
 
  /**
   * Delegate to my strategy, which can "see" the game for my next move
   * 
   * @param theGame The current state of the game when asked for a move
   * 
   * @return A point that store two ints: an row and a colim
   */
  public OurPoint desiredMove(TicTacToeGame theGame) {
    return myStrategy.desiredMove(theGame);
  }
}