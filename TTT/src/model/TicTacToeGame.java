/**
 * This type represents a Tic Toe Game model where one human user 
 * can play against a Computer player with swappable strategies.  
 * 
 * This system was designed to allow CSC 335 students to employ 
 * three design patterns under study. Another purpose for this
 * project is to let students experience modifying an existing 
 * code base and adding features to an existing system.
 * 
 * @author Rick Mercer
 */
package model;

public class TicTacToeGame extends OurObservable {
  private char[][] board;
  private int size;
  private ComputerPlayer computerPlayer;

  public TicTacToeGame() {
    size = 3;
    initializeBoard();
    computerPlayer = new ComputerPlayer();
    computerPlayer.setStrategy(new RandomAI());

    notifyObservers(this);
  }

  /**
   * Called from constructor and strartNewGame(); This method had to be added
   * because a new game could start from any of the views because of the Observer
   * design pattern.
   */
  private void initializeBoard() {
    board = new char[size][size];
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        board[r][c] = '_';
      }
    }
  }

  /**
   * Start a new game and tell all observers to draw an new game with the string
   * message startNewGame(). The code is required because java.util.Observer has
   * it.
   */
  public void startNewGame() {
    initializeBoard();
    // The state of this model just changed so tell any observer to
    // update themselves
    notifyObservers(this);
  }

  /**
   * Swap strategies for the ComputerPlayer
   * 
   * @param AI The algorithm use by the ComputerPlayer to choose the next move
   */
  public void setComputerPlayerStrategy(TicTacToeStrategy AI) {
    this.computerPlayer.setStrategy(AI);
  }

  /**
   * Get a reference to the one Computer Player and the Strategy
   * 
   * @return
   */
  public ComputerPlayer getComputerPlayer() {
    return computerPlayer;
  }

  /**
   * Select the given move and toggle between X and O player. Precondition row and
   * col are both are 0, 1 or 2
   * 
   * @param row     The row the player moves to
   * @param col     The column the player moves to column
   * @param testing Passed the value of true during testing
   * 
   */
  public void humanMove(int row, int col, boolean testing) {
    if (board[row][col] != '_')
      return;
    else {
      board[row][col] = 'X';
      if (!testing && this.stillRunning()) {
        OurPoint move = computerPlayer.desiredMove(this);
        computerMove(move.row, (int) move.col);
      }
    }
    notifyObservers(this);
  }

  /**
   * This is used for testing and is called from humanMove when not
   */
  public void computerMove(int row, int col) {
    if (board[row][col] != '_')
      return;
    board[row][col] = 'O';
    // setChanged(); // Java needs this or the next message does not happen
    notifyObservers(this); // Send update messages to all Observers
  }

  /**
   * Provide a textual version of this tic tac toe board.
   * 
   * @return The current state of this tic tac board showin X O or _
   */
  @Override
  public String toString() {
    String result = "";
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        result += " " + board[r][c] + " ";
      }
      if (r == 0 || r == 1)
        result += "\n";
    }
    return result;
  }

  /**
   * Provide access to the selections '_' or 'O' or 'X' in a 2D array
   * 
   * @return The state of this TTT board so strategies can check where everyone is
   */
  public char[][] getTicTacToeBoard() {
    return board;
  }

  /**
   * Let users know if a game ended in a tie
   * 
   * @return True if the current state of this gaem is tied
   */
  public boolean tied() {
    return maxMovesRemaining() == 0 && !didWin('X') && !didWin('O');
  }

  /**
   * Let a strategy know how many moves can still be. I found I needed this when
   * implementing a strategy and for testing. It's not really needed to build the
   * game
   * 
   * @return How many locations have not been taken.
   */
  public int maxMovesRemaining() {
    int result = 0;
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        if (board[r][c] == '_')
          result++;
      }
    }
    return result;
  }

  /**
   * Let the game know is a selection would be valid This is needed to prevent
   * users from selected a square that is not already selected
   * 
   * @return True if this space has not be selected
   */
  public boolean available(int r, int c) {
    return board[r][c] == '_';
  }

  public boolean stillRunning() {
    return !tied() && !didWin('X') && !didWin('O');
  }

  /**
   * Decide if either of the two players have won
   * 
   * @param playerChar X or O so the user can find out if either one has won
   * 
   * @return True if X or O wins
   */
  public boolean didWin(char playerChar) {
    return wonByRow(playerChar) || wonByCol(playerChar) || wonByDiagonal(playerChar);
  }

  private boolean wonByRow(char playerChar) {
    for (int r = 0; r < size; r++) {
      int rowSum = 0;
      for (int c = 0; c < size; c++)
        if (board[r][c] == playerChar)
          rowSum++;
      if (rowSum == size)
        return true;
    }
    return false;
  }

  private boolean wonByCol(char playerChar) {
    for (int c = 0; c < size; c++) {
      int colSum = 0;
      for (int r = 0; r < size; r++)
        if (board[r][c] == playerChar)
          colSum++;
      if (colSum == size)
        return true;
    }
    return false;
  }

  private boolean wonByDiagonal(char playerChar) {
    // Check Diagonal from upper left to lower right
    int sum = 0;
    for (int r = 0; r < size; r++)
      if (board[r][r] == playerChar)
        sum++;
    if (sum == size)
      return true;

    // Check Diagonal from upper right to lower left
    sum = 0;
    for (int r = size - 1; r >= 0; r--)
      if (board[size - r - 1][r] == playerChar)
        sum++;
    if (sum == size)
      return true;

    // No win on either diagonal
    return false;
  }
}