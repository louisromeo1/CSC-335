/*
 * Louis Romeo
 * CSC 335 Assignment 2
 * 1/21/2024
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SetStatsGUI extends Application {

    private MySet<Double> numberSet = new MySet<>();
    private ObservableList<Double> setElements = FXCollections.observableArrayList();

    private Label maxLabel = new Label("Max: ");
    private Label minLabel = new Label("Min: ");
    private Label avgLabel = new Label("Average: ");
    
    private Font font = Font.font("Serif", FontWeight.BOLD, 16);
    
    public static void main(String[] args) {
        launch(args);
    }

    // Main GUI method
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SetStats");

        Label enterNumberLabel = new Label("Enter a number: ");
        TextField inputField = new TextField();
        inputField.setPromptText("0.0");

        Button addButton = new Button("Enter");
        addButton.setOnAction(e -> handleAddButton(inputField.getText()));

        ListView<Double> listView = new ListView<>(setElements);
        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(enterNumberLabel, inputField, 
        		addButton, listView, maxLabel, minLabel, avgLabel);

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    
    // Logic for handling errors
    private void handleAddButton(String input) {
        try {
            double number = Double.parseDouble(input);
            if (numberSet.add(number)) {
                updateListView();
                updateStatsLabels();
            } else {
                showAlert("Number already exists in the set!");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid input! Please enter a valid number.");
        }
    }

    private void updateListView() {
        setElements.clear();
        for (Double element : numberSet) {
            setElements.add(element);
        }
    }

    // Updates the Max, Min, and Avg values
    private void updateStatsLabels() {
        if (!numberSet.isEmpty()) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            double sum = 0;

            for (Double element : numberSet) {
                max = Math.max(max, element);
                min = Math.min(min, element);
                sum += element;
            }

            double average = sum / numberSet.size();

            maxLabel.setText("Max: " + max);
            minLabel.setText("Min: " + min);
            avgLabel.setText("Average: " + average);
            
            maxLabel.setFont(font);
            minLabel.setFont(font);
            avgLabel.setFont(font);
        }
    }

    // Alert method for invalid input
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}