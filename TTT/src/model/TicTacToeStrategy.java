package model;

/** 
 * Use Ricks's Point class to store two ints: row and col that
 * is the return type of the single method in this interface
 * indicating the choice of the ComputerPlayer.
 * 
 * @author Rick Mercer
*/

public interface TicTacToeStrategy {

  /**
   *  This one method returns the move chosen by an implementing strategy.
   *  
   *  @argument TheGame must be passed to this method so implementing 
   *  strategies know all about the current state of the game. 
   *  For example, the implementing strategy has to know if a 
   *  move can win the game, or if it would be wise to block.
   *  
   *  @return A java Point object where .x is the row and .y is the column. 
   */
  public OurPoint desiredMove(TicTacToeGame theGame);
}