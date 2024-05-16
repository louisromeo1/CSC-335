package demoMediaPlayer;

/**
 * Demo to play three songs
 * 
 * @author Kyle Myint and Louis Romeo
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PlayList;

public class PlayThreeSongs extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		PlayList playList = new PlayList();
		playList.queueUpNextSong("Capture.mp3");
		playList.queueUpNextSong("SwingCheese.mp3");
		playList.queueUpNextSong("Capture.mp3");

		BorderPane pane = new BorderPane();
		pane.setCenter(new Label("Play three songs"));
		// Put the pane in a sized Scene and show the GUI
		Scene scene = new Scene(pane, 255, 85);
		stage.setScene(scene);
		// Don't forget to show the running app:
		stage.show();

	}

}