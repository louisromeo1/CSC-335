import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/*
 * Louis Romeo
 * CSC 335 Assignment 7
 * Purpose: Program that runs a TODO list GUI. Includes buttons to 
 * 			reposition entries within the list.
 */
public class TODOStart extends Application {

    private ListView<String> todoListView;
    private ObservableList<String> todoItems;
    private TextField todoField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Priority TODO List");

        // Initialize the list
        todoItems = FXCollections.observableArrayList();
        todoListView = new ListView<>(todoItems);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(todoListView);

        VBox buttonBox = createButtonBox();
        borderPane.setBottom(buttonBox);

        primaryStage.setScene(new Scene(borderPane, 400, 300));
        primaryStage.show();
    }

    private VBox createButtonBox() {
        VBox buttonBox = new VBox(5);
        buttonBox.setPadding(new Insets(10));

        Button addButton = new Button("Save Current List");
        addButton.setOnAction(event -> addNewTodoItem());
        todoField = new TextField();
        todoField.setPromptText("Enter TODO");

        // Buttons for list movement.
        HBox priorityButtons = new HBox(5);
        Button topButton = new Button("Top");
        topButton.setOnAction(event -> moveToTop());
        Button bottomButton = new Button("Bottom");
        bottomButton.setOnAction(event -> moveToBottom());
        Button raiseButton = new Button("Raise");
        raiseButton.setOnAction(event -> raiseSelectedItem());
        Button lowerButton = new Button("Lower");
        lowerButton.setOnAction(event -> lowerSelectedItem());
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> removeSelectedItem());

        priorityButtons.getChildren().addAll(topButton, bottomButton, raiseButton, lowerButton, removeButton);

        buttonBox.getChildren().addAll(todoField, addButton, priorityButtons);

        return buttonBox;
    }

    // Helper method to add new list entry.
    private void addNewTodoItem() {
        String todo = todoField.getText();
        if (!todo.isEmpty()) {
            todoItems.add(todo);
            todoField.clear();
        }
    }

    // Method that repositions entries to the top.
    private void moveToTop() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            String selectedItem = todoItems.remove(selectedIndex);
            todoItems.add(0, selectedItem);
            todoListView.getSelectionModel().select(0);
        }
    }

    // Method that repositions entries to the bottom.
    private void moveToBottom() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        int lastIndex = todoItems.size() - 1;
        if (selectedIndex < lastIndex) {
            String selectedItem = todoItems.remove(selectedIndex);
            todoItems.add(selectedItem);
            todoListView.getSelectionModel().select(lastIndex);
        }
    }

    private void raiseSelectedItem() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            String selectedItem = todoItems.remove(selectedIndex);
            todoItems.add(selectedIndex - 1, selectedItem);
            todoListView.getSelectionModel().select(selectedIndex - 1);
        }
    }

    private void lowerSelectedItem() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        int lastIndex = todoItems.size() - 1;
        if (selectedIndex >= 0 && selectedIndex < lastIndex) {
            String selectedItem = todoItems.remove(selectedIndex);
            todoItems.add(selectedIndex + 1, selectedItem);
            todoListView.getSelectionModel().select(selectedIndex + 1);
        }
    }

    // Responsible for removing list entries.
    private void removeSelectedItem() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            todoItems.remove(selectedIndex);
        }
    }
}
