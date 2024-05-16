/**
 * This class represents an item in the shop that users
 * can purchase to unlock more features for their account
 * 
 *  @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package model;

public class shopItem {
	private String itemName;
	private int price;

	/**
	 * The constructor for a shopItem, specifying its name and price.
	 * 
	 * @param itemName The name of the shopItem.
	 * @param price    The price of the shopItem.
	 */
	public shopItem(String itemName, int price) {
		this.itemName = itemName;
		this.price = price;
	}

	@Override
	/**
	 * This method overrides the equals method, comparing the itemName of two
	 * objects to determine equality between two shopItems.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || getClass() != obj.getClass()) {
			return false;
		} else {
			shopItem other = (shopItem) obj;
			return itemName.equals(other.getItemName());
		}
	}

	/**
	 * This method returns the name of the shopItem.
	 * 
	 * @return The name of this shopItem.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * This method returns the price of the shopItem.
	 * 
	 * @return The price of this shopItem.
	 */
	public int getPrice() {
		return price;
	}
}
