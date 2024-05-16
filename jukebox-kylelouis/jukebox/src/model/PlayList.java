/**
 * This will serialize a playlist if the
 * use wanted to and or create a new playlist
 * of song to be played to the user it can play
 * new song add song to the current playlist 
 * save current playlist and read up old playlist
 * 
 * @author Kyle Myint and Louis Romeo
 */

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import demoMediaPlayer.PlayAnMP3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class PlayList {
	private ArrayList list;
	private ObservableList<String> playlist;
	private Stage stage = new Stage();
	private PlayAnMP3 mp3;
	private boolean isPlaying = false;

	public PlayList() {
		list = new ArrayList<String>();
		playlist = FXCollections.observableArrayList();
		mp3 = new PlayAnMP3();
	}

	/**
	 * Adds a song to the playlist
	 * 
	 * @param songToAdd
	 */
	public void queueUpNextSong(String songToAdd) {
		if (playlist.size() == 0) {
			isPlaying = false;
		}
		playlist.add(songToAdd);
	}

	/**
	 * Calls the PlayAnMP3 object to play all the songs in the playlist
	 */
	public void playSongs() {
		isPlaying = true;
		mp3.play(playlist);

	}

	/**
	 * returns a boolean specifying if the playlist is playing right now
	 * 
	 * @return
	 */
	public boolean getIsPlaying() {
		return isPlaying;
	}

	/**
	 * Serializes the playlist to playlist.ser
	 */
	public void savePlayList() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream("playlist.ser");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

			list.clear();
			list.addAll(playlist);

			outFile.writeObject(list);

			list.clear();

			outFile.close();

		} catch (IOException ioe) {
			System.out.println("Write failed");
		}
	}

	/**
	 * Deserializes the playlist into an observable arraylist
	 */
	public void readPlayList() {
		try {
			FileInputStream bytesFromDisk = new FileInputStream("playlist.ser");
			ObjectInputStream inFile = new ObjectInputStream(bytesFromDisk);

			list = (ArrayList<String>) inFile.readObject();

			playlist.clear();
			playlist.addAll(list);

			inFile.close();

		} catch (IOException ioe) {
			System.out.println("read failed");
		} catch (ClassNotFoundException e) {
			System.out.println("Class cast exception");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the current playlist object
	 * 
	 * @return playlist the song in a given playlist
	 */
	public ObservableList<String> getPlayList() {
		return this.playlist;
	}

}