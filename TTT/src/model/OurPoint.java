/**
 * A simple Point class to store two integers that
 * represent the row and column of a Tic Tac Toe board
 * 
 * @author Rick Mercer
 */
package model;

public class OurPoint {
  
  public int row, col;
  
  public OurPoint(int row, int col) {
    this.row = row;
    this.col = col;
  }

// It is easier to have public instance variables than these two getters
//
//  /**
//   * @return The row
//   */
//  public int getRow() {
//    return row;
//  }
//  
//  /**
//   * @return The column
//   */
//  public int getCol() {
//    return col;
//  }
}
