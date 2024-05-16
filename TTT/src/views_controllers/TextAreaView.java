package views_controllers;
/**
 * This is the beginning of one view of a Tic Tac Toe game using
 * two TextField objects and one TextArea. The other two views
 * of ButtonView and DrawingView follow the same structure as this.
 * 
 * @author Rick Mercer *MODIFIED by Louis Romeo 
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.OurObserver;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements OurObserver {

	private TicTacToeGame theGame;
	private Button makeMove;
	private TextField row, col;
	private Label rowLabel, colLabel;
	private TextArea message;
	private BorderPane upperPane;
	private HBox textHBox;
	private String backgroundColor, textColor, buttonColor, upperColor;

	public TextAreaView(TicTacToeGame theModel) {
		theGame = theModel;
		initializePanel();

		makeMove.setOnAction(new MoveHandler());

	}

	private void initializePanel() {
		upperPane = new BorderPane();
		upperPane.setPadding(new Insets(30));

		row = new TextField();
		row.setMaxSize(50, 30);
		col = new TextField();
		col.setMaxSize(50, 30);

		makeMove = new Button("Make Move");
		makeMove.setMinSize(100, 30);

		rowLabel = new Label("row");
		colLabel = new Label("col");

		textHBox = new HBox(10, rowLabel, row, colLabel, col);
		textHBox.setPadding(new Insets(0, 0, 20, 0));
		upperPane.setCenter(textHBox);

		upperPane.setBottom(makeMove);
		upperPane.setAlignment(makeMove, Pos.BOTTOM_CENTER);

		message = new TextArea();
		message.setEditable(false);
		message.setText(theGame.toString());

		setTheme("L");

		this.setCenter(upperPane);
		this.setBottom(message);
	}

	/**
	 * This method updates the colors for the theme that will be changed in the menu
	 * 
	 * @param theme L for light D for dark
	 */
	public void setTheme(String theme) {
		if (theme.equals("D")) {
			backgroundColor = "#262626";
			textColor = "white";
			upperColor = "#262626";
			buttonColor = "#6f6f6f";
			// set the style of the textArea showing the game
			message.setStyle(
					"-fx-font-family: monospace; -fx-font-size: 29pt; -fx-control-inner-background: " + backgroundColor
							+ "; " + "-fx-text-fill: " + textColor + "; -fx-background-color: " + backgroundColor);
			// set the style for the row and col labels
			rowLabel.setStyle("-fx-text-fill: " + textColor);
			colLabel.setStyle("-fx-text-fill: " + textColor);
			// set the color of the textFields
			row.setStyle("-fx-background-color: " + buttonColor);
			col.setStyle("-fx-background-color: " + buttonColor);
			
			makeMove.setStyle("-fx-base: " + buttonColor); // set the color of the button
			// change the background of the upper border pane
			upperPane.setStyle("-fx-background-color: " + upperColor);
		} else {
			// if the theme is light remove any styling done by dark mode
			message.setStyle("-fx-font-family: monospace; -fx-font-size: 29pt");

			rowLabel.setStyle(null);
			colLabel.setStyle(null);

			row.setStyle(null);
			col.setStyle(null);

			makeMove.setStyle(null);
			upperPane.setStyle(null);

		}
	}

	// This method is called by Observable's notifyObservers()
	@Override
	public void update(Object observable) {
		// make sure no moves can be made unless the game isn't over
		if (theGame.stillRunning()) {
			row.setEditable(true);
			col.setEditable(true);

			makeMove.setText("Make Move");
			message.setText(theGame.toString());
		} else {
			row.setEditable(false);
			col.setEditable(false);

			makeMove.setText(checkWin(theGame));
			message.setText(theGame.toString());
		}

	}

	/**
	 * Returns the winner of the game if the game is over
	 * 
	 * @param theGame
	 * @return s - The winner of the game or an empty string if the game is over
	 */
	public String checkWin(TicTacToeGame theGame) {
		String s = "";
		if (theGame.stillRunning()) {
			return s;
		}

		if (theGame.didWin('X')) {
			s = "X Wins";
		} else if (theGame.didWin('O')) {
			s = "O Wins";
		} else if (theGame.tied()) {
			s = "Game Tied";
		}
		row.setEditable(false);
		col.setEditable(false);

		return s;
	}

	/**
	 * Handler for the button clicked to make a move
	 */
	private class MoveHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (!theGame.stillRunning()) {
				return;
			}
			// avoid number format exceptions
			String rowStr = row.getText().trim();
			String colStr = col.getText().trim();
			// remove any text from the textFields
			row.clear();
			col.clear();

			int i, j;

			// error checking to make sure the text entered is a number and a valid index on
			// the board

			if (!isInteger(rowStr) || !isInteger(colStr)) {
				makeMove.setText("Invalid Choice");
				return;
			}

			i = Integer.parseInt(rowStr);
			j = Integer.parseInt(colStr);

			if (i < 0 || i > 2 || j < 0 || j > 2 || !theGame.available(i, j)) {
				makeMove.setText("Invalid Choice");
				return;
			}
			// set the text of the button in case it was changed to invalid move
			// and add the move to the game
			makeMove.setText("Make move");
			theGame.humanMove(i, j, false);
			// check if someone won
			checkWin(theGame);

		}

		/**
		 * Checks if the given string can be an integer
		 * 
		 * @param s - the string to check
		 * @return true if the parseInt works on the string
		 */
		private boolean isInteger(String s) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return false;
			} catch (NullPointerException e) {
				return false;
			}
			// only got here if we didn't return false
			return true;
		}

	}

}