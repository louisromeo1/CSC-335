/**
 * This interface must be implemented by any observer that wants
 * to be notified of any change to the state of the model.
 * 
 * @author Rick Mercer, Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
package model;

public interface OurObserver {
   void update(Object theObserved);
}
