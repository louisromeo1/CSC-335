/**
 * this is how a user will log in it will 
 * have text fields so they may login and
 * it will check if they excitst and if the
 * pasword is correct it will deplay messages
 * and allert to the user based on if the information
 * is correct or not
 * 
 * @author Kyle Myint and Louis Romeo
 */

package controller_view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.JukeboxAccount;

public class LoginPane extends GridPane {
	private boolean loggedIn = false;
	private String userName;
	private String password;
	private Label loginLabel = new Label("Login first. Login with a unique username to create account.");
	private Label accountLabel = new Label("Account Name");
	private Label passwordLabel = new Label("Password");
	private Button login = new Button("Login");
	private Button loginOut = new Button("Log out");
	private JukeboxAccount user;

	private TextField loginField = new TextField();
	private PasswordField passwordsField = new PasswordField();

	/**
	 * this will create the pane where a user can see if they are logged in or not
	 * and if they where successful at logging in there are two action event to
	 * handel a user wanting to log in and a user wanting to log out
	 */
	public LoginPane() {
		// these are the buttons, labels, and text feild in the pane
		this.setHgap(10);
		this.setVgap(10);
		this.add(loginLabel, 16, 0);
		this.add(accountLabel, 15, 1);
		this.add(loginField, 16, 1);
		this.add(login, 17, 1);
		this.add(passwordLabel, 15, 2);
		this.add(passwordsField, 16, 2);
		this.add(loginOut, 17, 2);

		// this is an action event to log in a user
		login.setOnAction(event -> {
			// this statement check if a user is currently logged in or not
			// if no user is logged in it will get the user name and password
			// and create or log in the user
			if (loggedIn == false) {
				userName = loginField.getText();
				password = passwordsField.getText();
				// check to make sure there is info in the text fields
				if (userName != "" && password != "") {
					user = new JukeboxAccount(userName, password);
					loginField.setText("");
					passwordsField.setText("");
					// shows a message to the user if log in was successful
					if (user.getLogin() == true) {
						loggedIn = true;
						loginLabel.setText("Welcome " + userName + ". Don't forget to log out when done");
					} else {
						Alert failLogAlert = new Alert(AlertType.CONFIRMATION);
						failLogAlert.setHeaderText(
								"Username Taken or Incorrect Password\nInput Correct Password or Sign Up With Different Username");
						failLogAlert.setContentText("Click OK or Cancel to continue");
						failLogAlert.showAndWait();
						loginField.setText("");
						passwordsField.setText("");
					}
				}
				// show a message to the user if the log in failed
				else {
					Alert emptyAlert = new Alert(AlertType.CONFIRMATION);
					emptyAlert.setHeaderText(
							"No Username or Password in Input\nPlease Input Valid Username and Password");
					emptyAlert.setContentText("Click OK or Cancel to continue");
					emptyAlert.showAndWait();
					loginField.setText("");
					passwordsField.setText("");
				}
			}
			// an alert that show if a user tryes to log in before one has loged out
			else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("A user is currently logged in please wait");
				alert.setContentText("Click OK or Cancel to continue");

				Optional<ButtonType> result = alert.showAndWait();
				// if user want old file this will read it from the todo class and show it to
				// the user
				if (result.get() == ButtonType.OK) {
					loginField.setText("");
					passwordsField.setText("");
				}
				if (result.get() == ButtonType.CANCEL) {
					loginField.setText("");
					passwordsField.setText("");
				}
			}

		});

		// this is an action event for when the log out button his it
		// this will set the loggedIn to false so a new user can access the jukebox
		loginOut.setOnAction(event -> {
			loggedIn = false;
			loginLabel.setText("Login first. Login with a unique username to create account.");
			loginField.setText("");
			passwordsField.setText("");
		});
	}
	
	// Getter for loginLabel
	public Label getLoginLabel() {
		return loginLabel;
	}

	/**
	 * this will return the current user to the caller
	 * 
	 * @return user a person within the jukebox account
	 */
	public JukeboxAccount getUser() {
		if (loggedIn == true) {
			return user;
		}
		return null;
	}

	/**
	 * this will return if a person is currently logged in or not
	 * 
	 * @return true if the user is logged in false if the user is not logged in
	 */
	public boolean getLoggedIn() {
		if (loggedIn == true)
			return true;
		return false;
	}
}