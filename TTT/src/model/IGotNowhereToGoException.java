package model;

/**
 * This exception may be thrown by a TicTacToeStrategy 
 * when it is asked to return the player's move in the
 * case where there are no more move from which to select
 *  
 * @author Rick Mercer
 */
public class IGotNowhereToGoException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Allows a strategy to bail out when asked for a desired move
   * and there are 0 possible moves to make. 
   * 
   * @param message The string written to the console when an exception is thrown.
   */
  public IGotNowhereToGoException(String message) {
    super(message);
  }
}