package controller_view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Accounts;
import model.shopItem;

/**
 * This is the SettingsPane, which will be displayed on the GUI for the user to
 * be able to change the settings of their next game, including dimension,
 * gameMode, and gameTheme.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class SettingsPane extends BorderPane {
	private ComboBox<String> dimensions;
	private ComboBox<String> gameMode;
	private ComboBox<String> gameTheme;
	private memoryGUI gui;
	private GridPane grid;

	private Label dimensionLbl;
	private Label gameModeLbl;
	private Label gameThemeLbl;
	private Label title;

	private Button saveSettingsBtn;
	
	private Accounts currUser;

	/**
	 * The constructor for the SettingsPane.
	 * 
	 * @param gui A reference to the MemoryGUI.
	 */
	public SettingsPane(memoryGUI gui) {
		this.gui = gui;
		
		this.currUser = gui.getCurrAcct();

		layoutPane();

		registerHandlers();

		this.setTop(title);

		this.setCenter(grid);

		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

		this.setPadding(new Insets(20, 0, 0, 270));
		// top, x, x, left
	}

	/**
	 * This method lays out the Settings Pane with components including
	 * its title, its various option combo boxes and labels, and a save button.
	 */
	private void layoutPane() {
		title = new Label("Game Settings");
		title.setStyle("-fx-font-size: 40; -fx-font-weight: bold");
		title.setPadding(new Insets(0, 10, 10, 0));

		grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 50));

		grid.setHgap(8);
		grid.setVgap(8);

		dimensions = new ComboBox<>();
		dimensions.getItems().addAll("2x2", "3x3", "4x4", "5x5");
		shopItem sixBySix = new shopItem("6x6 Game Mode", 0);
		if(this.currUser.hasUnlockedItem(sixBySix)) {
			dimensions.getItems().add("6x6");
		}
		
		dimensions.setValue("2x2");
		dimensionLbl = new Label("Dimension");
		dimensionLbl.setStyle("-fx-font-size: 15;");

		gameMode = new ComboBox<>();
		gameMode.getItems().addAll("Normal");
		gameMode.setValue("Normal");
		gameModeLbl = new Label("Game Mode");
		gameModeLbl.setStyle("-fx-font-size: 15;");

		gameTheme = new ComboBox<>();
		gameTheme.getItems().addAll("Shapes");
		shopItem animals = new shopItem("Card Design: Animals", 0);
		shopItem space = new shopItem("Card Design: Space", 0);
		if(this.currUser.hasUnlockedItem(animals)) {
			gameTheme.getItems().add("Animals");
		}
		if(this.currUser.hasUnlockedItem(space)) {
			gameTheme.getItems().add("Space");
		}
		
		gameTheme.setValue("Shapes");
		gameThemeLbl = new Label("Game Theme");
		gameThemeLbl.setStyle("-fx-font-size: 15;");

		grid.add(dimensionLbl, 0, 0);
		grid.add(dimensions, 1, 0);
		grid.add(gameModeLbl, 0, 1);
		grid.add(gameMode, 1, 1);
		grid.add(gameThemeLbl, 0, 2);
		grid.add(gameTheme, 1, 2);

		saveSettingsBtn = new Button("Save Settings");
		grid.add(saveSettingsBtn, 0, 3);
	}

	/**
	 * This method registers handlers for change of the dimensions combo box,
	 * as well as the click of save settings.
	 */
	private void registerHandlers() {
		dimensions.setOnAction(event -> {
			String newValue = dimensions.getValue();

			gameMode.getItems().clear();
			
			shopItem power = new shopItem("Powers Game Mode", 0);

			if (newValue.equals("2x2")) {
				gameMode.getItems().addAll("Normal");
				gameMode.setValue("Normal");
			} else if (newValue.equals("3x3")) {
				gameMode.getItems().addAll("Odd Card Out", "3 of a Kind");
				if(this.currUser.hasUnlockedItem(power)) {
					gameMode.getItems().add("Power");
				}
				gameMode.setValue("Odd Card Out");
			} else if (newValue.equals("4x4")) {
				gameMode.getItems().addAll("Normal");
				if(this.currUser.hasUnlockedItem(power)) {
					gameMode.getItems().add("Power");
				}
				gameMode.setValue("Normal");
			} else if (newValue.equals("5x5")) {
				gameMode.getItems().addAll("Odd Card Out");
				if(this.currUser.hasUnlockedItem(power)) {
					gameMode.getItems().add("Power");
				}
				gameMode.setValue("Odd Card Out");
			} else if (newValue.equals("6x6")) {
				gameMode.getItems().addAll("Normal", "3 of a Kind");
				if(this.currUser.hasUnlockedItem(power)) {
					gameMode.getItems().add("Power");
				}
				gameMode.setValue("Normal");
			}
		});

		saveSettingsBtn.setOnAction(event -> {
			String dim = dimensions.getValue();
			String mode = gameMode.getValue();
			String theme = gameTheme.getValue();

			int dimNum;
			int modeNum;
			int themeNum;

			if (dim.equals("2x2")) {
				dimNum = 2;
			} else if (dim.equals("3x3")) {
				dimNum = 3;
			} else if (dim.equals("4x4")) {
				dimNum = 4;
			} else if (dim.equals("5x5")) {
				dimNum = 5;
			} else if (dim.equals("6x6")) {
				dimNum = 6;
			} else {
				dimNum = 2;
			}

			if (mode.equals("Normal")) {
				modeNum = 0;
			} else if (mode.equals("Odd Card Out")) {
				modeNum = 1;
			} else if (mode.equals("3 of a Kind")) {
				modeNum = 2;
			} else if (mode.equals("Power")) {
				modeNum = 3;
			} else {
				modeNum = 0;
			}

			if (theme.equals("Shapes")) {
				themeNum = 0;
			} else if (theme.equals("Animals")) {
				themeNum = 1;
			} else {
				themeNum = 2;
			}

			gui.setupGameSettings(dimNum, modeNum, themeNum);
		});
	}

}
