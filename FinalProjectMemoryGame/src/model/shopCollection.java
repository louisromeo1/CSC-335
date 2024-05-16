package model;

import java.util.ArrayList;

/**
 * The shopCollection class is a wrapper for an ArrayList of shop items,
 * including other methods that allow for adding and manipulating the array of
 * shop items for the MemoryGame application.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class shopCollection implements java.io.Serializable {
	private ArrayList<shopItem> items;

	/**
	 * The constructor for shopCollection, initializes the arrayList.
	 */
	public shopCollection() {
		items = new ArrayList<>();
	}

	/**
	 * Adds an item to the shopCollection.
	 * 
	 * @param item The shopItem being added.
	 */
	public void add(shopItem item) {
		items.add(item);
	}

	/**
	 * A getter for the size of shopCollection.
	 * 
	 * @return The size of shopCollection.
	 */
	public int getSize() {
		return items.size();
	}

	/**
	 * This method is the getter for shop at index index of the shopCollection.
	 * 
	 * @param index The index of the element being obtained.
	 * @return The element at index index of the shopCollection.
	 */
	public shopItem getElement(int index) {
		return items.get(index);
	}

	/**
	 * This method returns the underlying arrayList for the shopCollection class.
	 * 
	 * @return The underlying ArrayList of shopItems.
	 */
	public ArrayList<shopItem> getArray() {
		return items;
	}

	/**
	 * This method clears the shopCollection.
	 */
	public void clear() {
		items.clear();
	}
}
