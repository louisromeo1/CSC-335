/**
 * This handles the properties of a song
 * 
 * @author Kyle Myint and Louis Romeo
 */
package model;

import java.io.Serializable;

public class Song implements Serializable {
	private String name, artist, playTime;

	public Song(String name, String artist, String playTime) {
		this.name = name;
		this.artist = artist;
		this.playTime = playTime;
	}

	/**
	 * get the name of a song
	 * 
	 * @return song name
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets the artist of a song
	 * 
	 * @return artist the name of an artist of a song
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * get how long a given song is
	 * 
	 * @return playTime the length of a song in min and sec
	 */
	public String getPlayTime() {
		return playTime;
	}

}