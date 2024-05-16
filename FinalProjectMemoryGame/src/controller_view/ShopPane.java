package controller_view;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.text.Font;
import model.AccountCollection;
import model.Accounts;
import model.shopCollection;
import model.shopItem;

/**
 * This class represents the shop pane in our MemoryGame. It'll allow users to
 * buy new game modes and powers with in-game currency so they may experience
 * more features and get a sense of progression.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class ShopPane extends BorderPane {

	private BorderPane pane;

	private static ObservableList<shopItem> items = FXCollections.observableArrayList();
	private Label shopLabel;
	private TableView<shopItem> table;
	private TableColumn<shopItem, String> nameColumn;
	private TableColumn<shopItem, Integer> priceColumn;

	private Label balanceLabel;
	private Label userBalance;
	private Button buyButton;

	private shopCollection shopCollection;
	private shopCollection croppedCollection;

	private Accounts userAccount;

	/**
	 * This method is the constructor for ShopPane, initializing the Pane given a
	 * shopCollection of shop Items.
	 * 
	 * @param shopCollection The collection of shop items to be displayed within the
	 *                       shop.
	 */
	public ShopPane(shopCollection shopCollection) {

		// Initialize UI components.
		pane = new BorderPane();
		shopLabel = new Label("Feature Shop");
		buyButton = new Button("Buy Selected Item");
		balanceLabel = new Label("User Balance: ");
		balanceLabel.setFont(new Font(20));
		table = new TableView<>();
		nameColumn = new TableColumn<>("Item Name");
		priceColumn = new TableColumn<>("Price");

		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		table.getStyleClass().add("row-hover-effect");

		this.shopCollection = shopCollection;

		nameColumn.setCellValueFactory(new PropertyValueFactory<shopItem, String>("itemName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<shopItem, Integer>("Price"));

		croppedCollection = new shopCollection();

		for (int i = 0; i < shopCollection.getSize(); i++) {
			croppedCollection.add(shopCollection.getElement(i));
		}

		table.setItems(items);
		table.getColumns().addAll(nameColumn, priceColumn);

		addAllItems();
		resortTable();

		registerHandlers();
		layoutShop();
	}

	/**
	 * This method sets the user account within this class to currAcct, allowing for
	 * changing of user balance.
	 * 
	 * @param currAcct An Account being passed in to be set as this class' current
	 *                 account.
	 */
	public void setUserAccount(Accounts currAcct) {
		this.userAccount = currAcct;

		this.adjustUserBalance();
	}

	/**
	 * This method sets the balance label to the user's balance.
	 * 
	 */
	public void adjustUserBalance() {
		balanceLabel.setText("User Balance: " + userAccount.getBalance());
	}

	/**
	 * This method sets the default sort column to the price column and sorts the
	 * table in ascending order based on the prices.
	 */
	public void resortTable() {
		table.getSortOrder().add(priceColumn);
		priceColumn.setSortType(TableColumn.SortType.ASCENDING);
		table.sort();
	}

	/**
	 * This method sets the layout of the shop table within the pane
	 */
	public void layoutShop() {
		// Set preferred size for the table.
		table.setPrefSize(100, 100);

		// Set preferred widths for the columns.
		nameColumn.setPrefWidth(220);
		priceColumn.setPrefWidth(220);

		// Set padding for the pane and apply styling to the shop title.
		pane.setPadding(new Insets(80, 100, 100, 80));
		shopLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black");

		// Set the title label at the top and the table at the center of the pane.
		pane.setTop(shopLabel);
		pane.setRight(balanceLabel);
		pane.setBottom(buyButton);
		pane.setCenter(table);

		pane.setMargin(table, new Insets(20, 20, 20, 0));

		// Set this pane as the center content of the parent container.
		this.setCenter(pane);
	}

	/**
	 * This method clears the shopItem list and then iterates through the shopItem
	 * collection, adding each item to the item list. This will help populate the
	 * table
	 */
	private void addAllItems() {
		items.clear();

		for (int i = 0; i < croppedCollection.getSize(); i++) {
			items.add(croppedCollection.getElement(i));
		}
	}

	/**
	 * This method registers event handlers, in this case, just the event of a click
	 * of the buy item button.
	 */
	private void registerHandlers() {
		// Handle buyButton based on selected entry in table
		buyButton.setOnAction(event -> {
			shopItem selectedItem = table.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				if (userAccount.hasUnlockedItem(selectedItem)) {
					Alert alert = new Alert(AlertType.INFORMATION, "You already own this item.", ButtonType.OK);
					alert.showAndWait();
				} else if (userAccount.getBalance() < selectedItem.getPrice()) {
					Alert alert = new Alert(AlertType.WARNING, "Insufficient balance.", ButtonType.OK);
					alert.showAndWait();
				} else {
					// Deduct the price from the user's balance
					userAccount.deductBalance(selectedItem.getPrice());
					// Add the item to the unlocked list
					userAccount.addUnlockedItem(selectedItem);
					// Update balance label
					this.adjustUserBalance();
					// Inform the user about the purchase
					Alert alert = new Alert(AlertType.CONFIRMATION,
							"You have successfully bought " + selectedItem.getItemName() + ".", ButtonType.OK);
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Please select an item to buy.", ButtonType.OK);
				alert.showAndWait();
			}
		});
	}
}
