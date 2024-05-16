
	/*

	 * This interface defines a simple set class that can store elements of many types.

	 *

	 * @author Rick Mercer

	 *

	 * Precondition: T must have an equals and compareTo method like Integer or String

	 */

	public interface GenericSet<T> {

	 

	  // Return true if zero elements have been added to this set or false otherwise

	  public boolean isEmpty();

	 

	  // Return the number of elements that have been successfully added to this set.

	  public int size();

	 

	  // Add element if element is not in this set and also return true.

	  // If this set already contains element, do not change the set and return false

	  public boolean add(T element);

	 

	  // Return true if element is in this set. If element is not in this set, return false.

	  public boolean contains(T element);

	 

	  // If element is successfully removed return true, otherwise return false.

	  public boolean remove(T element);

	}
