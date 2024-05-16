/**
 * This is the GUI of the jukebox it will
 * show all the song available to the user to be
 * played on the left side of the screen 
 * then it will show all the song in the queue on the
 * right side of the screen and in the middle is a button
 * with add on it that will allow a user to pick a song to
 * add to the list and on the bottom is the area to login a user
 * 
 * @author Kyle Myint and Louis Romeo
 */

package controller_view;

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.JukeboxAccount;
import model.PlayList;
import model.Song;

public class JukeboxGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	LoginPane loginPane;
	private BorderPane everything;
	private PlayList playlist;
	private ListView<Song> listview;
	private Label songQueue, songList;
	private HBox hbox;
	private Button add1;
	private TableView<Song> table;
	private ObservableList<Song> observableSongs;

	@Override
	public void start(Stage primaryStage) throws Exception {
		addSongs();
		registerHandlers();
		LayoutGUI();
		loadPlayListOrNot();
		Scene scene = new Scene(everything, 850, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Jukebox");
		primaryStage.show();
		// an event on the closing the window
		primaryStage.setOnCloseRequest((event) -> {
			saveChangesOrNot();
		});
	}

	/**
	 * this will set the pane of the gui to match the description in the header
	 * comment
	 */
	private void LayoutGUI() {
		everything = new BorderPane();
		table = new TableView();

		loginPane = new LoginPane();
		everything.setPadding(new Insets(10, 10, 10, 10));

		playlist = new PlayList();
		listview = new ListView(playlist.getPlayList());
		// this set the hight and width of the playlist
		listview.setMaxHeight(450);
		listview.setMinWidth(300);
		// this set the hight of the loginPane
		loginPane.setMaxHeight(200);

		// makes the list view not edible
		listview.setMouseTransparent(true);
		listview.setFocusTraversable(false);

		// this set the height of the loginPane
		loginPane.setMaxHeight(200);

		songList = new Label("Song List");
		songQueue = new Label("Song Queue");
		songList.setStyle("-fx-font-size: 20; -fx-font-family: Verdana");
		songQueue.setStyle("-fx-font-size: 20; -fx-font-family: Verdana");

		hbox = new HBox(430, songList, songQueue);
		hbox.setPadding(new Insets(10));

		everything.setTop(hbox);

		everything.setRight(listview);
		everything.setBottom(loginPane);

		everything.setCenter(add1);

		table.setItems(observableSongs);

		TableColumn<Song, String> titleCol = new TableColumn<Song, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory("name"));

		TableColumn<Song, String> artistCol = new TableColumn<Song, String>("Artist");
		artistCol.setCellValueFactory(new PropertyValueFactory("artist"));

		TableColumn<Song, String> timeCol = new TableColumn<Song, String>("Time");
		timeCol.setCellValueFactory(new PropertyValueFactory("playTime"));

		titleCol.setMinWidth(175);
		artistCol.setMinWidth(175);
		timeCol.setMinWidth(20);

		table.getColumns().setAll(titleCol, artistCol, timeCol);

		table.setMinWidth(400);
		table.setMaxSize(400, 450);

		everything.setLeft(table);

	}

	/**
	 * This will add song objects to an observable list of songs so that the table
	 * view can access them
	 */
	private void addSongs() {
		observableSongs = FXCollections.observableArrayList();

		Song capture = new Song("Capture", "Pikachu", "0:05");
		Song danse = new Song("Danse Macabre Violin Hook", "Kevin MacLeod", "0:37");
		Song determined = new Song("Determined Tumbao", "FreePlay Music", "0:20");
		Song longing = new Song("Longing In Their Hearts", "Michael O'Keefe/Bonnie Raitt", "4:48");
		Song loop = new Song("Loping Sting", "Kevin MacLeod", "0:05");
		Song swing = new Song("Swing Cheese", "FreePlay Music", "0:15");
		Song curtain = new Song("The Curtain Rises", "Kevin MacLeod", "0:28");
		Song fire = new Song("Untameable Fire", "Pierre Langer", "4:42");

		observableSongs.add(capture);
		observableSongs.add(danse);
		observableSongs.add(determined);
		observableSongs.add(longing);
		observableSongs.add(loop);
		observableSongs.add(swing);
		observableSongs.add(curtain);
		observableSongs.add(fire);

	}

	/**
	 * this will handle the action event for when the user wants to add a song to
	 * the queue
	 */
	private void registerHandlers() {
		add1 = new Button("Add Selected");

		add1.setOnAction(event -> {
			// check to see if someone is logged in so songs can be added
			if (loginPane.getLoggedIn() == true) {
				JukeboxAccount user = loginPane.getUser();
				// check to see if the user has songs left to play
				if (user.canPlaySong() == true) {
					int currentIndex = table.getSelectionModel().getSelectedIndex();
					String song = observableSongs.get(currentIndex).getName();

					playlist.queueUpNextSong(String.join("", song.split(" ")) + ".mp3");
					loginPane.getLoginLabel().setText("Songs played today: " + user.songsPlayed() + "/3");

					if (!playlist.getIsPlaying()) {
						// this will highlight the first thing in the playlist
						listview.getSelectionModel().selectFirst();
						playlist.playSongs();
					}
				}
				// Alerts user that they have use all their songs
				else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("You Have Played All Your Songs For Today\nLog Out And Come Back Tomorrow");
					alert.setContentText("Click OK or Cancel to continue");

					Optional<ButtonType> result = alert.showAndWait();

				}
			} else {
				// Alerts user they aren't logged in
				Alert logAlert = new Alert(AlertType.CONFIRMATION);
				logAlert.setHeaderText("Must Be Logged In To Add Songs");
				logAlert.setContentText("Click OK or Cancel to continue");
				logAlert.showAndWait();
			}

		});

	}

	/**
	 * this method has an alert that happens before the user is shown the GUI that
	 * will ask the user if they want to start a new to play list or if they want to
	 * load up an old one or show an empty to play list if they want to start a new
	 * one
	 */
	private void loadPlayListOrNot() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Click cancel to start with an empty PlayList");
		alert.setContentText("Click OK to Load Previous Playlist");

		Optional<ButtonType> result = alert.showAndWait();
		// if user want old file this will read it from the todo class and show it to
		// the user
		if (result.get() == ButtonType.OK) {
			playlist.readPlayList();
			listview = new ListView(playlist.getPlayList());

			// this set the height and width of the playlist
			listview.setMaxHeight(450);
			listview.setMinWidth(300);
			// makes the list view not edible
			listview.setMouseTransparent(true);
			listview.setFocusTraversable(false);

			everything.setRight(listview);
			if (!playlist.getIsPlaying()) {
				// this will highlight the first thing in the playlist
				listview.getSelectionModel().selectFirst();
				playlist.playSongs();
			}
		}

	}

	/**
	 * this method has an alert that will prompt the user when trying to close the
	 * program to save the changes made to the .ser file or to not save the changes
	 */
	private void saveChangesOrNot() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Click cancel to not save Changes");
		alert.setContentText("Click OK to save Changes");

		Optional<ButtonType> result = alert.showAndWait();
		// this will write to the .ser file if wanting to save
		if (result.get() == ButtonType.OK) {
			playlist.savePlayList();
		}

	}

}