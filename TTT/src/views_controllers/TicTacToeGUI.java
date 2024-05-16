package views_controllers;

/**
 * Play TicTacToe the computer that can have different AIs to beat you. 
 * Select the Options menus to begin a new game, switch strategies for 
 * the computer player (BOT or AI), and to switch between the two views.
 * 
 * This class represents an event-driven program with a graphical user 
 * interface as a controller between the view and the model. It has 
 * event handlers to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two 
 * views every time the state of the tic tac toe game changes:
 * 
 *    1) whenever you make a move by clicking a button or an area of either view
 *    2) whenever the computer AI makes a move
 *    3) whenever there is a win or a tie
 *    
 * You can also select two different strategies to play against from the menus
 * 
 * @author Rick Mercer *MODIFIED by: Louis Romeo
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.IntermediateAI;
import model.OurObserver;
import model.RandomAI;
import model.TicTacToeGame;

public class TicTacToeGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private TicTacToeGame theGame;
	private MenuBar menuBar;
	private Menu menu, strategies, views, theme;
	private MenuItem newGame, random, intermediate, button, textArea, light, dark;

	private OurObserver currentView;
	private OurObserver buttonView;
	private OurObserver textAreaView;
	private OurObserver drawingView;

	private BorderPane window;
	public static final int width = 254;
	public static final int height = 360;

	public void start(Stage stage) {
		stage.setTitle("Tic Tac Toe");
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		initializeGameForTheFirstTime();
		registerHandlers();

		// Set up the views
		buttonView = new ButtonView(theGame);
		textAreaView = new TextAreaView(theGame);
		//drawingView = new DrawingView(theGame);
		theGame.addObserver(buttonView);
		theGame.addObserver(textAreaView);
		// theGame.addObserver(drawingView);
		setViewTo(textAreaView);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Set the game to the default of an empty board and the random AI.
	 */
	public void initializeGameForTheFirstTime() {
		theGame = new TicTacToeGame();
		initializeMenu();
		window.setTop(menuBar);
		// This event driven program will always have
		// a computer player who takes the second turn
		theGame.setComputerPlayerStrategy(new RandomAI());
	}

	/**
	 * Sets the view to either textArea or Button
	 */
	private void setViewTo(OurObserver newView) {
		window.setCenter(null);
		currentView = newView;
		window.setCenter((Node) currentView);
	}

	/**
	 * Sets up all menus that will be displayed
	 */
	private void initializeMenu() {
		menuBar = new MenuBar();

		menu = new Menu("Options");
		strategies = new Menu("Strategies");
		views = new Menu("Views");
		theme = new Menu("Theme");

		newGame = new MenuItem("New Game");

		random = new MenuItem("Random AI");
		intermediate = new MenuItem("Intermediate AI");

		textArea = new MenuItem("TextArea View");
		button = new MenuItem("Button View");

		light = new MenuItem("Light");
		dark = new MenuItem("Dark");

		strategies.getItems().addAll(random, intermediate);
		views.getItems().addAll(textArea, button);
		theme.getItems().addAll(light, dark);

		menu.getItems().addAll(newGame, strategies, views, theme);

		menuBar.getMenus().addAll(menu);

	}

	/**
	 * Register the event handlers for the items in the menu
	 */
	private void registerHandlers() {
		newGame.setOnAction(new NewHandler());

		random.setOnAction(new RandomHandler());
		intermediate.setOnAction(new IntermediateHandler());

		textArea.setOnAction(new TextAreaViewHandler());
		button.setOnAction(new ButtonViewHandler());
		light.setOnAction(new LightHandler());
		dark.setOnAction(new DarkHandler());

	}

	/**
	 * Handler for clicking the new game menu item
	 */
	private class NewHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			theGame.startNewGame();

		}

	}

	/**
	 * Handler for clicking the randomAI menu item
	 */
	private class RandomHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			theGame.setComputerPlayerStrategy(new RandomAI());

		}

	}

	/**
	 * Handler for clicking the intermediate AI menu item
	 */
	private class IntermediateHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			theGame.setComputerPlayerStrategy(new IntermediateAI());

		}

	}

	/**
	 * Handler for switching the view to text area view
	 */
	private class TextAreaViewHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			setViewTo(textAreaView);

		}

	}

	/**
	 * Handler for switching the view to button view
	 */
	private class ButtonViewHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			setViewTo(buttonView);

		}

	}

	/**
	 * Handler for switching the theme to light mode
	 */
	private class LightHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			((TextAreaView) textAreaView).setTheme("L");
			((ButtonView) buttonView).setTheme("L");

			textAreaView.update(theGame);
			buttonView.update(theGame);
			// remove any styling done by dark mode
			menuBar.setStyle(null);
		}

	}

	/**
	 * Handler for switching the theme to dark mode
	 */
	private class DarkHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			((TextAreaView) textAreaView).setTheme("D");
			((ButtonView) buttonView).setTheme("D");

			textAreaView.update(theGame);
			buttonView.update(theGame);

			menuBar.setStyle("-fx-background-color: #6f6f6f");

		}

	}

}