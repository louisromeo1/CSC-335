/**
 * This strategy selects the first available move at random.  
 * It is easy to beat this strategy because it is totally random.
 * 
 * @throws 
 *    IGotNowhereToGoException whenever asked for a 
 *    move that is impossible to deliver.
 * 
 * @author Rick Mercer
 * 
 */
package model;
import java.util.Random;

public class RandomAI implements TicTacToeStrategy {

  private static Random generator;

  public RandomAI() {
    generator = new Random();
  }

  // Find an open spot while ignoring possible wins and stops (block a guaranteed win)
  @Override
  public OurPoint desiredMove(TicTacToeGame theGame) {
    boolean set = false;
    while (!set) {
      if (theGame.maxMovesRemaining() == 0)
        throw new IGotNowhereToGoException(" -- Hey there programmer, the board is filled");

      // Otherwise, try to randomly find an open spot 
      int row = generator.nextInt(3);
      int col = generator.nextInt(3);
      if (theGame.available(row, col)) {
        set = true;
        return new OurPoint(row, col);
      }
    }
    return null; // Avoid a compile-time error
  }
}