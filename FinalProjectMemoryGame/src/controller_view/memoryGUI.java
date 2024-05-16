/**
 * This is the main GUI for the project, which displays various panes
 * and menus within it as the application is used.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package controller_view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Queue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AccountCollection;
import model.shopCollection;
import model.Accounts;
import model.MemoryGame;
import model.shopItem;

public class memoryGUI extends Application {

	private LoginPane loginPane;
	private BoardPane boardPane;
	private StatsPane statsPane;
	private ShopPane shopPane;
	private SettingsPane settingsPane;
	private LeaderboardPane leaderboard2x2;
	private LeaderboardPane leaderboardPane;
	private LeaderboardPane leaderboard3x3;
	private LeaderboardPane leaderboard4x4;
	private LeaderboardPane leaderboard5x5;
	private LeaderboardPane leaderboard6x6;

	private MenuBar menuBar;
	private MenuItem logout;
	private MenuItem newGame;
	private MenuItem leaderboard;
	private MemoryGame game;
	private MenuItem userStats;
	private MenuItem gameSettings;
	private MenuItem itemShop;

	private Accounts currAcct;

	private AccountCollection accountCollection;
	private shopCollection shopCollection;

	private int gameDim;
	private int gameMode;
	private int gameTheme;

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane all;

	@Override
	/**
	 * This method starts the GUI, calling methods to layout the GUI, adding the
	 * menu, and registering handlers.
	 */
	public void start(Stage primaryStage) throws Exception {
		accountCollection = new AccountCollection();
		shopCollection = new shopCollection();

		getSavedDataOrNot();
		loginPane = new LoginPane(currAcct, accountCollection, this);
		boardPane = new BoardPane(this);
		statsPane = new StatsPane(this);
		initShopCollection();
		shopPane = new ShopPane(shopCollection);
		// settingsPane = new SettingsPane(this);

		LayoutGUI();

		addMenu();

		// addTestAccounts(); // TODO - remove this for final production.

		setupGameSettings(2, 0, 0);

		Scene scene = new Scene(all, 850, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();

		registerHandlers(primaryStage);
	}

	/**
	 * Sets up GUI and memoryGame data with saved data if the user would like to
	 * load saved data.
	 */
	private void getSavedDataOrNot() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Start Up Option");
		alert.setHeaderText("Load saved data?");
		alert.setContentText("Click cancel to start new!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				File file = new File("objects.ser");

				if (file.exists()) {
					FileInputStream rawBytes = new FileInputStream("objects.ser");
					ObjectInputStream inFile = new ObjectInputStream(rawBytes);

					AccountCollection inAccounts = (AccountCollection) inFile.readObject();
					MemoryGame inGame = (MemoryGame) inFile.readObject();

					accountCollection = inAccounts;
					game = inGame;

				}
			} catch (IOException io) {
				System.out.println("File IO Exception");
			} catch (ClassNotFoundException c) {
				System.out.println("Class Not Found Exception");
			}
		} else {
			accountCollection = new AccountCollection();
			addTestAccounts();
		}
	}

	/**
	 * This method lays out the gui by setting the loginPane to the center
	 * initially.
	 */
	private void LayoutGUI() {
		all = new BorderPane();

		all.setCenter(loginPane);
	}

	/**
	 * A getter for the accountCollection.
	 * 
	 * @return the collection of accounts in the application
	 */
	private AccountCollection getAcctCollection() {
		return accountCollection;
	}

	/**
	 * The method that handles a successful login, setting up a menu and clear
	 * canvas, as well as setting the current user.
	 * 
	 * @param loginAcct The account that just successfully logged in.
	 */
	public void onSuccessfulLogin(Accounts loginAcct) {
		boardPane.clearCanvas();
		all.setTop(menuBar);
		all.setCenter(boardPane);
		this.currAcct = accountCollection.getAccount(loginAcct.getUsername(), loginAcct.getPassword());
		if (currAcct.getCurrGame() != null) {
			boardPane.setGame(currAcct.getCurrGame());
		}

		System.out.println("Current User: " + currAcct.getUsername());
	}

	/**
	 * A getter for the current user's account.
	 * 
	 * @return The current user's account.
	 */
	public Accounts getCurrAcct() {
		return this.currAcct;
	}

	/**
	 * This method adds default accounts for testing.
	 * 
	 * Other accounts will exist to show leaderboard functionality.
	 */
	private void addTestAccounts() {
		Accounts account1 = new Accounts("Seth", "Seth123");
		account1.setNewBestScore(20, 2, 0);
		account1.setNewBestScore(90, 3, 1);
		account1.setNewBestScore(100, 3, 2);
		account1.setNewBestScore(80, 3, 3);
		account1.setNewBestScore(90, 4, 0);
		account1.setNewBestScore(60, 4, 3);
		account1.setNewBestScore(130, 5, 1);
		account1.setNewBestScore(110, 5, 3);
		account1.setNewBestScore(250, 6, 0);
		account1.setNewBestScore(240, 6, 2);
		account1.setNewBestScore(280, 6, 3);

		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		account2.setNewBestScore(50, 2, 0);
		account2.setNewBestScore(70, 3, 1);
		account2.setNewBestScore(60, 3, 2);
		account2.setNewBestScore(90, 3, 3);
		account2.setNewBestScore(100, 4, 0);
		account2.setNewBestScore(160, 4, 3);
		account2.setNewBestScore(180, 5, 1);
		account2.setNewBestScore(130, 5, 3);
		account2.setNewBestScore(210, 6, 0);
		account2.setNewBestScore(200, 6, 2);
		account2.setNewBestScore(220, 6, 3);

		Accounts account3 = new Accounts("Kyle", "Kyle123");
		account3.setNewBestScore(40, 2, 0);
		account3.setNewBestScore(80, 3, 1);
		account3.setNewBestScore(50, 3, 2);
		account3.setNewBestScore(100, 3, 3);
		account3.setNewBestScore(120, 4, 0);
		account3.setNewBestScore(120, 4, 3);
		account3.setNewBestScore(190, 5, 1);
		account3.setNewBestScore(100, 5, 3);
		account3.setNewBestScore(230, 6, 0);
		account3.setNewBestScore(250, 6, 2);
		account3.setNewBestScore(260, 6, 3);

		Accounts master = new Accounts("Admin", "1");
		shopItem bomb = new shopItem("Unlock Power Card: Bomb", 0);
		shopItem laser = new shopItem("Unlock Power Card: Laser", 0);
		master.setBalance(10000);

		accountCollection.add(account1);
		accountCollection.add(account2);
		accountCollection.add(account3);
		accountCollection.add(master);
	}

	/**
	 * This method sets up the Menu for the GUI.
	 */
	private void addMenu() {
		newGame = new MenuItem("New Game");
		leaderboard = new MenuItem("Leaderboard");
		logout = new MenuItem("Logout");

		Menu options = new Menu("Options");
		itemShop = new MenuItem("Item Shop");
		userStats = new MenuItem("User Stats");
		gameSettings = new MenuItem("Game Settings");

		options.getItems().addAll(newGame, leaderboard, itemShop, userStats, gameSettings, logout);

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(options);
	}

	/**
	 * A getter for newGame.
	 * 
	 * @return The new game.
	 */
	public MenuItem getNewGame() {
		return newGame;
	}

	/**
	 * Given dimension, game mode, and theme, this function sets the game settings
	 * in the instance variables of this class.
	 * 
	 * @param dim   The integer representing dimension (2 for 2x2, etc.) of the
	 *              current game.
	 * @param mode  The integer representing gameMode of the game.
	 * @param theme The integer representing theme of the game.
	 */
	public void setupGameSettings(int dim, int mode, int theme) {
		this.gameDim = dim;
		this.gameMode = mode;
		this.gameTheme = theme;
	}

	/**
	 * Initializes the shopCollection with the items for the memoryGame
	 */
	private void initShopCollection() {
		shopCollection.add(new shopItem("6x6 Game Mode", 500));
		shopCollection.add(new shopItem("Powers Game Mode", 1000));
		shopCollection.add(new shopItem("Card Design: Animals", 500));
		shopCollection.add(new shopItem("Card Design: Space", 750));
		shopCollection.add(new shopItem("Unlock Power Card: Bomb", 1250));
		shopCollection.add(new shopItem("Unlock Power Card: Laser", 1000));
	}

	/**
	 * This method registers event handlers, including logout, click of UserStats in
	 * menu, clicks of new game for each game mode,and clicks of leaderboard for
	 * each game mode.
	 * 
	 * @param stage The stage of the GUI.
	 */
	private void registerHandlers(Stage stage) {
		itemShop.setOnAction(event -> {
			shopPane.layoutShop();
			shopPane.setUserAccount(currAcct);
			all.setCenter(shopPane);
		});

		logout.setOnAction(event -> {
			currAcct = null;
			all.setTop(null);
			all.setCenter(loginPane);
			loginPane.logout();
		});

		userStats.setOnAction(event -> {
			statsPane.layoutStatsPane();
			all.setCenter(statsPane);
		});

		gameSettings.setOnAction(event -> {
			settingsPane = new SettingsPane(this);
			all.setCenter(settingsPane);
		});

		leaderboard.setOnAction(event -> {
			// When leaderboard is clicked in the menu, switch leaderboardPane to be the
			// center

			leaderboardPane = new LeaderboardPane(accountCollection);

			all.setCenter(leaderboardPane);

		});

		newGame.setOnAction(event -> {
			if (currAcct != null) {
				all.setCenter(boardPane);
				int curDim = this.gameDim;
				int curMode = this.gameMode;
				int curTheme = this.gameTheme;

				boardPane.startNewGame(curMode, curDim, curTheme);
			}
		});

		stage.setOnCloseRequest(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Shut Down");
			alert.setHeaderText("Save data?");
			alert.setContentText("Press cancel to not save data.");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				String filename = "objects.ser";
				try {
					FileOutputStream bytesToDisk = new FileOutputStream(filename);
					ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

					// Save AccountCollection
					AccountCollection currAccounts = accountCollection;

					// Save MemoryGame
					MemoryGame currGame = game;

					outFile.writeObject(currAccounts);
					outFile.writeObject(currGame);
					outFile.close();
				} catch (IOException io) {
					System.out.println("File IO Exception");
					System.out.println(io);
				}
			}

			Platform.exit();
			System.exit(0);
		});
	}
}