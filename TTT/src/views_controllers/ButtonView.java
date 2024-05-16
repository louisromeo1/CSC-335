/*
 * Louis Romeo
 * CSC 335 Assignment 4
 * Purpose: This program creates a view of the TicTacToe 
 * 			model using buttons that the user can click to place the x.
 */

package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.OurObserver;
import model.TicTacToeGame;

public class ButtonView extends BorderPane implements OurObserver {
	
	private TicTacToeGame theGame;
	private Button one, two, three, four, five, six, seven, eight, nine;
	private GridPane grid;
	private Label bottomText;
	private Button[][] buttons;
	private String xColor, oColor, uColor, backgroundColor, textColor, buttonColor;
	private boolean isLight;

	public ButtonView(TicTacToeGame theGame) {
		this.theGame = theGame;
		initializePanel();
		buttons = new Button[3][3];

		one.setOnAction(new ClickHandler());
		buttons[0][0] = one;

		two.setOnAction(new ClickHandler());
		buttons[0][1] = two;

		three.setOnAction(new ClickHandler());
		buttons[0][2] = three;

		four.setOnAction(new ClickHandler());
		buttons[1][0] = four;

		five.setOnAction(new ClickHandler());
		buttons[1][1] = five;

		six.setOnAction(new ClickHandler());
		buttons[1][2] = six;

		seven.setOnAction(new ClickHandler());
		buttons[2][0] = seven;

		eight.setOnAction(new ClickHandler());
		buttons[2][1] = eight;

		nine.setOnAction(new ClickHandler());
		buttons[2][2] = nine;

		setTheme("L");

	}

	private void initializePanel() {
		this.setPadding(new Insets(19));
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		// create 9 buttons and set them to _
		one = new Button("_");
		one.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		two = new Button("_");
		two.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		three = new Button("_");
		three.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");

		four = new Button("_");
		four.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		five = new Button("_");
		five.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		six = new Button("_");
		six.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");

		seven = new Button("_");
		seven.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		eight = new Button("_");
		eight.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");
		nine = new Button("_");
		nine.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt");

		grid.add(one, 0, 0);
		grid.add(two, 1, 0);
		grid.add(three, 2, 0);

		grid.add(four, 0, 1);
		grid.add(five, 1, 1);
		grid.add(six, 2, 1);

		grid.add(seven, 0, 2);
		grid.add(eight, 1, 2);
		grid.add(nine, 2, 2);

		this.setTop(grid);

		bottomText = new Label("Click to Make a Move");
		bottomText.setStyle("-fx-font-size: 13pt");
		this.setBottom(bottomText);
		this.setAlignment(bottomText, Pos.CENTER);

	}

	/**
	 * This class switches the color scheme between light and dark when the option
	 * is choses from the menu
	 * 
	 * @param theme L for light or D for dark
	 */
	public void setTheme(String theme) {
		if (theme.equals("L")) {
			isLight = true;
			xColor = "red";
			oColor = "blue";
			uColor = "black";
			textColor = "black";
		} else {
			isLight = false;
			xColor = "#a50000";
			oColor = "#2b96ca";
			uColor = "white";
			backgroundColor = "#262626";
			textColor = "white";
			buttonColor = "#6f6f6f";
		}

	}

	@Override
	public void update(Object theObserved) {
		if (theGame.stillRunning()) {
			bottomText.setText("Click to Make a Move");
			bottomText.setStyle("-fx-font-size: 13pt; -fx-text-fill: " + textColor);
		} else {
			// if the game is over set the text of the bottom label
			// to the winner
			checkWin(theGame);
		}

		char[][] board = theGame.getTicTacToeBoard();
		// loop through the board and update any buttons that have changed
		if (isLight) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].setText("" + board[i][j]);
					if (board[i][j] == 'X') {
						buttons[i][j]
								.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: " + xColor);
					} else if (board[i][j] == 'O') {
						buttons[i][j]
								.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: " + oColor);
					} else {
						buttons[i][j]
								.setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: " + uColor);
					}
				}
			}
			// remove the styling on the border pane
			this.setStyle(null);
			// if the theme is dark some extra colors need to be set
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].setText("" + board[i][j]);
					if (board[i][j] == 'X') {
						buttons[i][j].setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: "
								+ xColor + "; -fx-background-color : " + buttonColor);
					} else if (board[i][j] == 'O') {
						buttons[i][j].setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: "
								+ oColor + "; -fx-background-color : " + buttonColor);
					} else {
						buttons[i][j].setStyle("-fx-font-family: monospace; -fx-font-size: 25pt; -fx-text-fill: "
								+ uColor + "; -fx-background-color : " + buttonColor);
					}
				}
			}
			// set the background color of the border pane
			this.setStyle("-fx-background-color: " + backgroundColor);
		}

	}

	/**
	 * Sets the text of the label at the bottom of the border pane to the winner of
	 * the game if the game is over
	 * 
	 * @param theGame the current tic tac toe game
	 */
	public void checkWin(TicTacToeGame theGame) {
		if (theGame.stillRunning()) {
			return;
		}

		if (theGame.didWin('X')) {
			bottomText.setText("X Wins");
			bottomText.setStyle("-fx-font-size: 13pt; -fx-text-fill: " + xColor);

		} else if (theGame.didWin('O')) {
			bottomText.setText("O Wins");
			bottomText.setStyle("-fx-font-size: 13pt; -fx-text-fill: " + oColor);
		} else if (theGame.tied()) {
			bottomText.setText("Game Tied");
			bottomText.setStyle("-fx-font-size: 13pt; -fx-text-fill: " + uColor);
		}
	}

	/**
	 * Handler for a button being clicked
	 */
	private class ClickHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// if the game is over don't allow new moves but update the display
			// to the winning move
			if (!theGame.stillRunning()) {
				update(theGame);
				return;
			}
			Button buttonClicked = (Button) event.getSource();
			// find the index of the button that was clicked
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] == buttonClicked) {
						// if this is a valid spot make a new
						// move there
						if (!foundError(i, j, buttonClicked)) {
							theGame.humanMove(i, j, false);
							update(theGame.getTicTacToeBoard());
							break;
						}
					}
				}

			}
			// after every move check if the game is won
			checkWin(theGame);
		}

		// return false if the spot is available
		private boolean foundError(int i, int j, Button button) {
			if (!theGame.available(i, j)) {
				return true;
			}
			return false;
		}
	}
}