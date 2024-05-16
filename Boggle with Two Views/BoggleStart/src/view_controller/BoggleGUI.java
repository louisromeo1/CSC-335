package view_controller;
/*
 * Louis Romeo
 * CSC 335 Assignment 3
 * Purpose: This program is designed as a GUI to 
 * 			display the second view of the game Boggle.
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DiceTray;

public class BoggleGUI extends Application {

    private DiceTray diceTray; 
    private TextArea userInput;
    private TextArea outputArea;

    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
    	diceTray = new DiceTray();

        userInput = new TextArea();
        userInput.setPromptText("Enter words or ZZ to quit:");

        outputArea = new TextArea();
        outputArea.setEditable(false);

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> startNewGame());

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(newGameButton, createEndGameButton());
        buttons.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(outputArea);
        root.setCenter(userInput);
        root.setBottom(buttons);

        Scene scene = new Scene(root, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Boggle Game");
        primaryStage.show();

        startNewGame();
    }

    private void startNewGame() {
        outputArea.clear();
        userInput.clear();
        diceTray = new DiceTray();
        outputArea.appendText(diceTray.toString() + "\n");
    }

    private Button createEndGameButton() {
        Button endGameButton = new Button("End Game");
        endGameButton.setOnAction(e -> {
            ArrayList<String> userWords = new ArrayList<>(Arrays.asList(userInput.getText().split("\n")));
            Boggle console = new Boggle();
            Hashtable<String, String> dict = null;
			try {
				dict = Boggle.getDictionary("BoggleWords.txt");
			} catch (FileNotFoundException e1) {
				System.out.println("ERROR: BoggleWords.txt could not be found.");
				e1.printStackTrace();
			}
			Hashtable<Integer, ArrayList<String>> scoreTable = Boggle.getScore(userWords, dict, diceTray);
            printScore(scoreTable);
        });
        return endGameButton;
    }

    // Prints the score on the GUI stage.
    private void printScore(Hashtable<Integer, ArrayList<String>> scoreTable) {
        outputArea.appendText("Your score: " + scoreTable.get(0).get(0) + "\n");

        outputArea.appendText("Words you found:\n");
        outputArea.appendText("================\n");
        for (String word : scoreTable.get(1)) {
            outputArea.appendText(word + "\n");
        }

        outputArea.appendText("\nIncorrect words:\n");
        outputArea.appendText("================\n");
        for (String word : scoreTable.get(2)) {
            outputArea.appendText(word + "\n");
        }

        outputArea.appendText("\nYou could have found " + scoreTable.get(3).size() + " more words.\n");
        outputArea.appendText("The computer found all of your words plus these:\n");
        outputArea.appendText("===============================================\n");
        for (String word : scoreTable.get(3)) {
            outputArea.appendText(word + "\n");
        }
    }
}