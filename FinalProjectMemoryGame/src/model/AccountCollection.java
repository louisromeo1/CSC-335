package model;

import java.util.ArrayList;

/**
 * The AccountCollection class is a wrapper for an ArrayList of Accounts,
 * including other methods that allow for adding and manipulating the array of
 * Accounts for the MemoryGame application.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class AccountCollection implements java.io.Serializable {
	private ArrayList<Accounts> defAccounts;

	/**
	 * The constructor for AccountCollection, initializes the arrayList.
	 */
	public AccountCollection() {
		defAccounts = new ArrayList<>();
	}

	/**
	 * Adds an account to the AccountCollection.
	 * 
	 * @param account The account being added.
	 */
	public void add(Accounts account) {
		defAccounts.add(account);
	}

	/**
	 * A getter for the size of AccountCollection.
	 * 
	 * @return The size of AccountCollection.
	 */
	public int getSize() {
		return defAccounts.size();
	}

	/**
	 * This method is the getter for account at index index of the
	 * AccountCollection.
	 * 
	 * @param index The index of the element being obtained.
	 * @return The element at index index of the AccountCollection.
	 */
	public Accounts getElement(int index) {
		return defAccounts.get(index);
	}

	/**
	 * This method returns the underlying arrayList for the AcccountCollection
	 * class.
	 * 
	 * @return The underlying ArrayList of Accounts.
	 */
	public ArrayList<Accounts> getArray() {
		return defAccounts;
	}

	/**
	 * This method returns the account of specified username and password in the
	 * AccountCollection, and null if unfound.
	 * 
	 * @param name     The username of the account being searched for.
	 * @param password The password of the account being searched for.
	 * @return The account of specified username and password, and null if unfound.
	 */
	public Accounts getAccount(String name, String password) {
		for (Accounts acct : defAccounts) {
			if (acct.getUsername().equals(name) && acct.getPassword().equals(password)) {
				return acct;
			}
		}
		return null;
	}

	/**
	 * This method returns whether or not the AccountCollection contains an account
	 * with specified username.
	 * 
	 * @param name The username being searched for in the AccountCollection.
	 * @return True if the AccountCollection contains this username, false
	 *         otherwise.
	 */
	public boolean containsName(String name) {
		for (Accounts acct : defAccounts) {
			if (acct.getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns whether or not the AccountCollection contains an
	 * equivalent account.
	 * 
	 * @param otherAcct The account being searched for.
	 * @return True if the AccountCollection contains an equivalent account, false
	 *         otherwise.
	 */
	public boolean containsAccount(Accounts otherAcct) {
		if (defAccounts.contains(otherAcct)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method clears the AccountCollection.
	 */
	public void clear() {
		defAccounts.clear();
	}
}
