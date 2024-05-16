package controller_view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.AccountCollection;
import model.Accounts;

/**
 * The LoginPane class is a BorderPane that displays the login menu on the
 * memoryGUI.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class LoginPane extends BorderPane {
	private GridPane loginGrid;

	private Accounts currAcct;
	private AccountCollection accounts;

	private Button loginButton;
	private Button createAccount;
	private TextField accountNameField;
	private PasswordField passwordField;
	private Label loginStatus;

	private Image logoImage;

	private Canvas canvas;
	private GraphicsContext gc;

	private memoryGUI gui;

	/**
	 * This method lays out the BorderPane with a login menu.
	 * 
	 * @param currAcct the current user's account
	 * @param accounts the collection of accounts that exist on the app.
	 * @param gui      the memoryGUI for reference of methods
	 */
	public LoginPane(Accounts currAcct, AccountCollection accounts, memoryGUI gui) {
		layoutPane();

		this.currAcct = currAcct;
		this.accounts = accounts;
		this.gui = gui;

		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

		logoImage = new Image("file:MemoryGameLogo.png");
		canvas = new Canvas(800, 200);
		gc = canvas.getGraphicsContext2D();

		loginGrid.setPadding(new Insets(45, 50, 50, 270));

		gc.drawImage(logoImage, 240, 100, 350, 100);

		// Set the canvas as the top component of the BorderPane
		this.setTop(canvas);
		this.setCenter(loginGrid);
		this.registerHandlers();
	}

	/**
	 * This method creates the GridPane that houses the elements of the login menu.
	 */
	public void layoutPane() {
		loginGrid = new GridPane();
		loginGrid.setPrefWidth(300);
		loginGrid.setPrefHeight(300);
		loginGrid.setHgap(8);
		loginGrid.setVgap(8);

		Label accountName = new Label("Username:");
		accountName.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

		accountNameField = new TextField("");

		Label password = new Label("Password:");
		password.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

		passwordField = new PasswordField();

		loginButton = new Button("Login");
		createAccount = new Button("Create new Account");

		loginStatus = new Label("Login or Create an Account!");
		loginStatus.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

		loginGrid.add(loginStatus, 1, 0);
		loginGrid.add(accountName, 0, 1);
		loginGrid.add(accountNameField, 1, 1);
		loginGrid.add(password, 0, 2);
		loginGrid.add(passwordField, 1, 2);
		loginGrid.add(loginButton, 0, 3);
		loginGrid.add(createAccount, 1, 3);

	}

	/**
	 * This method registers handlers of the login menu, correctly accepting clicks
	 * of the loginButton and createAccount button.
	 */
	public void registerHandlers() {
		loginButton.setOnAction(event -> {
			Accounts loginTrial = new Accounts(accountNameField.getText(), passwordField.getText());
			if (accounts.getAccount(loginTrial.getUsername(), loginTrial.getPassword()) != null) {
				this.currAcct = accounts.getAccount(loginTrial.getUsername(), loginTrial.getPassword());
				gui.onSuccessfulLogin(loginTrial);
			} else {
				loginStatus.setText("Sorry, these credentials are incorrect!");
				accountNameField.setText("");
				passwordField.setText("");
			}
		});

		createAccount.setOnAction(event -> {
			if (!accounts.containsName(accountNameField.getText())) {
				Accounts newAcct = new Accounts(accountNameField.getText(), passwordField.getText());
				accounts.add(newAcct);
				loginStatus.setText("Account created, now login!");
			} else {
				loginStatus.setText("Sorry, this username is taken!");
				accountNameField.setText("");
				passwordField.setText("");
			}

		});
	}

	/**
	 * This method logs out the current user.
	 */
	public void logout() {
		this.currAcct = null;
		accountNameField.setText("");
		passwordField.setText("");
	}
}
