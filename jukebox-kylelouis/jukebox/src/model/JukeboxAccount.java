/**
 * This will manage all the users that exist 
 * for the jukebox. It will tell them if they 
 * have any songs left to play for a given day
 * if the user exist or need to be created
 * or if the information given was wrong and 
 * the user does not exist
 * 
 * @author Kyle Myint and Louis Romeo
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

// This class name is just a suggestion. 
// The account will need to use the type LocalDate
public class JukeboxAccount {

	private LocalDate today;
	private Hashtable<String, ArrayList<Object>> accountsTable;
	private int numSongsPlayed;
	private boolean login = false;
	private String user;

	public JukeboxAccount(String name, String password) {
		today = LocalDate.now();
		numSongsPlayed = 0;
		accountsTable = new Hashtable<String, ArrayList<Object>>();
		// This reads a serialized hashtable of all users and their passwords
		readAccountsTable();

		// check if the user exists and if they don't add them to the table
		// I don't know where the password comes from yet so for now its hardcoded
		if (!userExists(name, password)) {
			// save the changes to the table
			writeTable();
		} else {
			System.out.println("There is already a user with this username! or used wrong password");
		}
		// System.out.println("Hey " + name + ", this is just a suggestion on " + today
		// );
	}

	/**
	 * this will check to see if the person who is logged in
	 * 
	 * @return a true
	 */
	public boolean getLogin() {
		return login;
	}

	/**
	 * this will see if a song can be played and if so it will add to the count of
	 * songs played by a user and if the date are not the same it will reset the
	 * date and set the song played to one
	 * 
	 * @return true if the song can be played false if the user hit the dayley limit
	 */
	public boolean canPlaySong() {
		// check the date is todays date aguenst the list of
		ArrayList<Object> curUser = accountsTable.get(user);
		// this will check if the date has changed and if so
		// it will update that user number of songs played at index 1 to be 1
		// since they are wanting to add a song and the date to be the current day
		// instead of the past day they logged in and update the infor by wrinting it to
		// the table
		if (!curUser.get(2).equals(LocalDate.now())) {
			curUser.set(1, 1);
			curUser.set(2, LocalDate.now());
			writeTable();
			return true;
		} else if (curUser.get(2).equals(LocalDate.now())) {
			// check to see if the user hit the max for songs played
			numSongsPlayed = (int) curUser.get(1);
			if (numSongsPlayed < 3) {
				numSongsPlayed += 1;
				curUser.set(1, numSongsPlayed);
				writeTable();
				return true;
			} else {
				return false;
			}
		}
		// resets the date and number of songs played if day is not the same
		return false;

	}
	
	// Getter for # of songs played
	public int songsPlayed() {
		return numSongsPlayed;
	}

	/*
	 * Checks if a username already exists in the hashtable
	 */
	private boolean userExists(String name, String pass) {
		if (accountsTable.get(name) != null) {
			ArrayList<Object> curUser = accountsTable.get(name);
			if (curUser.get(0).equals(pass)) {
				user = name;
				login = true;
				return true;
			}
		} else {
			login = true;
			ArrayList<Object> info = new ArrayList<Object>();
			info.add(pass);
			info.add(numSongsPlayed);
			info.add(today);
			accountsTable.put(name, info);
			user = name;
		}
		return false;
	}

	/**
	 * Serializes the hashtable of user accounts
	 */
	private void writeTable() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream("accounts.ser");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

			outFile.writeObject(accountsTable);

			outFile.close();

		} catch (IOException ioe) {
			System.out.println("Write failed");
		}
	}

	/**
	 * Reads a serialized hashtable of user accounts
	 */
	private void readAccountsTable() {
		try {
			FileInputStream bytesFromDisk = new FileInputStream("accounts.ser");
			ObjectInputStream inFile = new ObjectInputStream(bytesFromDisk);

			accountsTable = (Hashtable<String, ArrayList<Object>>) inFile.readObject();

			inFile.close();

		} catch (IOException ioe) {
			System.out.println("read failed");
		} catch (ClassNotFoundException e) {
			System.out.println("Class cast exception");
			e.printStackTrace();
		}
	}

}