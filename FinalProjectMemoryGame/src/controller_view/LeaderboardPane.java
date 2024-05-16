package controller_view;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.AccountCollection;
import model.Accounts;

/**
 * This class represents a leaderboard pane in a game application.
 * 
 * The LeaderboardPane class provides functionality to display a leaderboard
 * with scores of accounts based on different game modes. It extends BorderPane
 * and contains UI components including labels, a table view, and table columns
 * for displaying usernames and scores. The class includes method to initialize
 * the leaderboard, add usernames, layout the components, and resort the table
 * based on scores.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class LeaderboardPane extends BorderPane {

	private BorderPane pane;
	private BorderPane outerPane;

	private Label leaderBoardTitle;

	private TableView<Accounts> table;
	private static ObservableList<Accounts> accounts = FXCollections.observableArrayList();

	private TableColumn<Accounts, String> usernameColumn;
	private TableColumn<Accounts, Integer> scoreColumn;

	private AccountCollection accountCollection;
	private AccountCollection croppedCollection;

	private Canvas canvas;
	private GraphicsContext gc;

	private Image leaderboardImage;

	private GridPane settingsGrid;
	private ComboBox<String> dimensionSelection;
	private Label dimensionLbl;
	private ComboBox<String> gameModeSelection;
	private Label gameModeLbl;
	private Button viewLeaderboardBtn;

	private int curDim;
	private int curMode;

	/**
	 * This is a public method/constructor that constructs a new LeaderboardPane
	 * instance. The constructor initializes a new leaderboard pane with the
	 * provided account collection and game mode. The leaderboard displays the
	 * scores of accounts according to the specified gameMode.
	 * 
	 * 
	 * @param accountCollection The collection of accounts to be displayed on the
	 *                          leaderboard.
	 */
	public LeaderboardPane(AccountCollection accountCollection) {

		// Initialize UI components.
		pane = new BorderPane();
		leaderBoardTitle = new Label("Leaderboard");

		canvas = new Canvas(850, 150);
		gc = canvas.getGraphicsContext2D();

		leaderboardImage = new Image("file:LeaderBoardLogo.png");
		gc.drawImage(leaderboardImage, 380, 20, 100, 100);

		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

		// Set provided parameters.
		this.accountCollection = accountCollection;

		curDim = 2;
		curMode = 0;

		setupLeaderboard();

		// Layout the UI components.
		layoutLeaderboard();

	}

	/**
	 * This function sets up the leaderboard, which is a tableView, to be displayed
	 * on the leaderboardPane, based on the current dimension and current mode being
	 * inquired about by the user.
	 */
	private void setupLeaderboard() {
		table = new TableView<>();
		usernameColumn = new TableColumn<>("Username");
		if (curMode != 4) {
			scoreColumn = new TableColumn<>("Score");
		} else {
			scoreColumn = new TableColumn<>("Streak");
		}

		table.getStyleClass().add("row-hover-effect");

		usernameColumn.setCellValueFactory(new PropertyValueFactory<Accounts, String>("Username"));

		croppedCollection = new AccountCollection();

		// Set up the columns based on game mode.
		if (curDim == 2) {
			if (curMode == 0) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2Normal"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get2Normal() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 4) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2Streak"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get2Streak() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			}
		} else if (curDim == 3) {
			if (curMode == 1) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3Odd"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get3Odd() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 2) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3ThreeKind"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get3ThreeKind() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 3) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3Power"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get3Power() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 4) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("3Streak"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get3Streak() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			}
		} else if (curDim == 4) {
			if (curMode == 0) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("4Normal"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get4Normal() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 3) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("4Power"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get4Power() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 4) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("4Streak"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get4Streak() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			}
		} else if (curDim == 5) {
			if (curMode == 1) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("5Odd"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get5Odd() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 3) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("5Power"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get5Power() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 4) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("5Streak"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get5Streak() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			}

		} else if (curDim == 6) {
			if (curMode == 0) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6Normal"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get6Normal() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 2) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6ThreeKind"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get6ThreeKind() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 3) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6Power"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get6Power() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			} else if (curMode == 4) {
				scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("6Streak"));
				croppedCollection.clear();
				for (int i = 0; i < accountCollection.getSize(); i++) {
					if (accountCollection.getElement(i).get6Streak() >= 0) {
						croppedCollection.add(accountCollection.getElement(i));
					}
				}
			}

		} else {
			scoreColumn.setCellValueFactory(new PropertyValueFactory<Accounts, Integer>("2Normal"));
			croppedCollection.clear();
			for (int i = 0; i < accountCollection.getSize(); i++) {
				if (accountCollection.getElement(i).get2Normal() >= 0) {
					croppedCollection.add(accountCollection.getElement(i));
				}
			}
		}

		addAllUsernames();

		// Set up table data and columns.
		table.setItems(accounts);
		table.getColumns().clear();
		table.getColumns().addAll(usernameColumn, scoreColumn);

		// Populate the table with account data and perform initial sorting (ASCENDING).

		resortTable();
	}

	/**
	 * This is a public method that sorts the table based on the score column in
	 * ascending order.
	 * 
	 * This method sets the sets the default sort column to the score column and
	 * sorts the table in ascending order based on the scores.
	 */
	public void resortTable() {
		// Set default sort column to score column.
		table.getSortOrder().add(scoreColumn);

		// Set sort type to ascending.
		if (curMode != 4) {
			scoreColumn.setSortType(TableColumn.SortType.ASCENDING);
		} else {
			scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
		}

		// Perform sorting.
		table.sort();
	}

	/**
	 * This method clears the accounts list and then iterates through the account
	 * collection, adding each account to the accounts list. It is used to populate
	 * the leaderboard table with usernames before sorting and displaying scores.
	 */
	private void addAllUsernames() {
		// Clear the accounts list to prepare for adding new usernames.
		accounts.clear();

		// Iterate through the content collection and add each account to the account
		// list.
		for (int i = 0; i < croppedCollection.getSize(); i++) {
			accounts.add(croppedCollection.getElement(i));
		}
	}

	/**
	 * This is a public method that layouts the components of the leaderboard within
	 * the pane.
	 * 
	 * This method sets the preferred sizes and positions of the table, columns, and
	 * title label within the pane. It applies styling to the title label.
	 * 
	 */
	public void layoutLeaderboard() {
		// Set preferred size for the table.
		table.setPrefSize(100, 100);
		table.setMaxWidth(450);

		// Set preferred widths for the columns.
		usernameColumn.setPrefWidth(220);
		scoreColumn.setPrefWidth(220);

		// Set padding for the pane and apply styling to the leaderboard title.
		pane.setPadding(new Insets(80, 100, 100, 80));
		leaderBoardTitle.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: black");

		dimensionSelection = new ComboBox<>();
		dimensionSelection.getItems().addAll("2x2", "3x3", "4x4", "5x5", "6x6");
		dimensionLbl = new Label("Dimension");

		gameModeSelection = new ComboBox<>();
		gameModeLbl = new Label("Game Mode");

		showCurrentSettings();

		viewLeaderboardBtn = new Button("View Leaderboard");

		settingsGrid = new GridPane();
		settingsGrid.setHgap(10);
		settingsGrid.add(dimensionLbl, 0, 0);
		settingsGrid.add(dimensionSelection, 1, 0);
		settingsGrid.add(gameModeLbl, 2, 0);
		settingsGrid.add(gameModeSelection, 3, 0);
		settingsGrid.add(viewLeaderboardBtn, 4, 0);

		settingsGrid.setPadding(new Insets(0, 0, 10, 190));
		// top, right, bottom, left

		leaderBoardTitle.setPadding(new Insets(20, 0, 0, 300));

		outerPane = new BorderPane();

		pane.setTop(settingsGrid);
		pane.setCenter(table);

		pane.setPadding(new Insets(20, 20, 20, 20));

		outerPane.setTop(leaderBoardTitle);
		outerPane.setCenter(pane);

		outerPane.setPadding(new Insets(-45, 0, 0, 0));

		this.setTop(canvas);
		this.setCenter(outerPane);

		registerHandlers();
	}

	/**
	 * This method displays the currently selected game size and mode on the GUI
	 * when viewing the leaderboard.
	 */
	private void showCurrentSettings() {
		dimensionSelection.getSelectionModel().select(curDim - 2);

		String newValue = dimensionSelection.getValue();

		gameModeSelection.getItems().clear();

		if (newValue.equals("2x2")) {
			gameModeSelection.getItems().addAll("Normal", "Streak");
			if (curMode == 0) {
				gameModeSelection.setValue("Normal");
			} else if (curMode == 4) {
				gameModeSelection.setValue("Streak");
			}
		} else if (newValue.equals("3x3")) {
			gameModeSelection.getItems().addAll("Odd Card Out", "3 of a Kind", "Power", "Streak");
			if (curMode == 1) {
				gameModeSelection.setValue("Odd Card Out");
			} else if (curMode == 2) {
				gameModeSelection.setValue("3 of a Kind");
			} else if (curMode == 3) {
				gameModeSelection.setValue("Power");
			} else if (curMode == 4) {
				gameModeSelection.setValue("Streak");
			}
		} else if (newValue.equals("4x4")) {
			gameModeSelection.getItems().addAll("Normal", "Power", "Streak");
			if (curMode == 0) {
				gameModeSelection.setValue("Normal");
			} else if (curMode == 3) {
				gameModeSelection.setValue("Power");
			}
		} else if (newValue.equals("5x5")) {
			gameModeSelection.getItems().addAll("Odd Card Out", "Power", "Streak");
			if (curMode == 1) {
				gameModeSelection.setValue("Odd Card Out");
			} else if (curMode == 3) {
				gameModeSelection.setValue("Power");
			} else if (curMode == 4) {
				gameModeSelection.setValue("Streak");
			}
		} else if (newValue.equals("6x6")) {
			gameModeSelection.getItems().addAll("Normal", "3 of a Kind", "Power", "Streak");
			if (curMode == 0) {
				gameModeSelection.setValue("Normal");
			} else if (curMode == 2) {
				gameModeSelection.setValue("3 of a Kind");
			} else if (curMode == 3) {
				gameModeSelection.setValue("Power");
			} else if (curMode == 4) {
				gameModeSelection.setValue("Streak");
			}

		}
	}

	/**
	 * This method registers handlers for change of game dimension and click of the
	 * view leaderboard button.
	 */
	private void registerHandlers() {
		dimensionSelection.setOnAction(event -> {
			String dim = dimensionSelection.getValue();

			if (dim.equals("2x2")) {
				curDim = 2;
				curMode = 0;
			} else if (dim.equals("3x3")) {
				curDim = 3;
				curMode = 1;
			} else if (dim.equals("4x4")) {
				curDim = 4;
				curMode = 0;
			} else if (dim.equals("5x5")) {
				curDim = 5;
				curMode = 1;
			} else if (dim.equals("6x6")) {
				curDim = 6;
				curMode = 0;
			}

			showCurrentSettings();

		});

		viewLeaderboardBtn.setOnAction(event -> {
			String mode = gameModeSelection.getValue();

			if (mode.equals("Normal")) {
				curMode = 0;
			} else if (mode.equals("Odd Card Out")) {
				curMode = 1;
			} else if (mode.equals("3 of a Kind")) {
				curMode = 2;
			} else if (mode.equals("Power")) {
				curMode = 3;
			} else if (mode.equals("Streak")) {
				curMode = 4;
			}

			setupLeaderboard();
			layoutLeaderboard();
		});
	}
}
