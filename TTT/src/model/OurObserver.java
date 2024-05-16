/**
 * This interface must be implemented by any observer that wants
 * to be notified of any change to the state of the model, like
 * the human player or computer player make a move on the TTT board.
 * 
 * @author Rick Mercer
 */
package model;

public interface OurObserver {
   void update(Object theObserved);
}
