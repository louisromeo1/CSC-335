package demoMediaPlayer;

/**
 * Demo to play a song 
 * 
 * @author Kyle Myint and Louis Romeo
 */
import java.io.File;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayAnMP3 extends Application {
	private int songsPlayed = 0;
	private String mp3Path;
	private ObservableList<String> playlist;
	private int currentIndex = 0;
	private MediaPlayer mediaPlayer;
	private Media media;
	private Stage stage = new Stage();

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Plays a given playlist in FIFO order using a media player
	 * 
	 * @param playlist - the list of songs to play
	 */
	public void play(ObservableList<String> playlist) {
		// To avoid null pointer exception
		if (playlist.isEmpty()) {
			return;
		}
		this.playlist = playlist;

		String path = "jukebox/songfiles/" + playlist.get(0);

		// Need a File and URI object so the path works on all OSs
		File file = new File(path);
		URI uri = file.toURI();
		System.out.println(uri);
		// Play one mp3 and and have code run when the song ends
		media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new Waiter());

		System.out.println("You may need to shut this App down");
	}

	@Override
	public void start(Stage stage) throws Exception {
		String path = "jukebox/songfiles/" + playlist.get(0);

		// Need a File and URI object so the path works on all OSs
		File file = new File(path);
		URI uri = file.toURI();
		System.out.println(uri);
		// Play one mp3 and and have code run when the song ends
		media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new Waiter());

		System.out.println("You may need to shut this App down");
	}

	private class Waiter implements Runnable {
		@Override
		public void run() {
			songsPlayed++;
			playlist.remove(0);

			if (playlist.size() == 0) {
				System.out.println("Song ended, play song #" + songsPlayed);
				return;
//          Platform.exit();
			}
			try {
				TimeUnit.SECONDS.sleep(2);
				play(playlist);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}