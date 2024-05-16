import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implement a GUI that allows users to search through and list of names/emails.
 * Selected emails can be removed by clicking the remove button at the bottom.
 * You can search using a string filter in the textField at the top.
 *
 * Programmers: Rick Mercer
 * *MODIFIED BY: Louis Romeo
 */
public class ContactsStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // ListView is a JavaFX Node that can display a scrollable list of elements in a
    // graphical manner. It looks like a one column table with white and light gray
    // rows.
    private ListView<String> listView;
    // Important method: setItems(ObservableList<String>).

    // A collection class that notifies the ListView whenever
    // an element is added or removed. ListView is notified when the list changes.
    private ObservableList<String> contacts = FXCollections.observableArrayList();
    // Methods we'll use: add, remove, enhanced for loop

    // Will be adding to to the Top, Middle, and Bottom
    private BorderPane all;

    @Override
    public void start(Stage stage) throws Exception {
        createListOfContacts(); // Given below
        layoutGUI();
        registerListeners();
        Scene scene = new Scene(all, 360, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Place Nodes in the BorderPane named all
    private void layoutGUI() {
        // Use the instance variable all to add the three nodes
        all = new BorderPane();
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        Button removeButton = new Button("Remove Selected Email");

        VBox topBox = new VBox(searchField);
        all.setTop(topBox);

        listView = new ListView<>(contacts);
        all.setCenter(listView);

        VBox bottomBox = new VBox(removeButton);
        all.setBottom(bottomBox);
    }

    // Start the model as the default model, with no removals
    // or load up the persisted object.
    private void createListOfContacts() {
        contacts.add("Peter Parker <spiderman@dailybugle.com>");
        contacts.add("Steve Rogers <captainamerica@shield.gov>");
        contacts.add("JT Lye <jtl@bogus.arizona.edu>");
        contacts.add("Nick Forbes <niv@bogus.arizona.edu>");
        contacts.add("Niven Francis <nf@bogus.arizona.edu>");
        contacts.add("Patrick Homer <homer@cs.arizona.edu>");
        contacts.add("Tony Stark <ironman@starkindustries.com>");
        contacts.add("Beichuan Zhang <bz@cs.arizona.edu>");
        contacts.add("Rick Schlichting <rick@cs.arizona.edu>");
        contacts.add("Pete Downey <pete@cs.arizona.edu>");
        contacts.add("John Kececioglu <kece@cs.arizona.edu>");
        contacts.add("Natasha Romanoff <natalie@starkindustries.com>");
        contacts.add("Clark Kent <superman@dailyplanet.com>");
        contacts.add("Rick Snodgrass <rts@cs.arizona.edu>");
        contacts.add("Logan <wolverine@xmen.edu>");
        contacts.add("Hal Jordan <greenlantern@justiceleague.com>");
        contacts.add("Rick Mercer <mercer@cs.arizona.edu>");
        contacts.add("Sean Stephens <sean@bogus.arizona.edu>");
        contacts.add("Jeremy Mowery <mowery@bogus.arizona.edu>");
    }

    private void registerListeners() {
        // Add setOnAction messages to a textfield for the filter at the top
        // and to the remove button at the bottom

        TextField searchField = (TextField) ((VBox) all.getTop()).getChildren().get(0);
        searchField.setOnAction(e -> {
            String filter = searchField.getText();
            ObservableList<String> subList = getSubList(filter);
            listView.setItems(subList);
        });

        Button removeButton = (Button) ((VBox) all.getBottom()).getChildren().get(0);
        removeButton.setOnAction(e -> {
            String selectedEmail = listView.getSelectionModel().getSelectedItem();
            if (selectedEmail != null) {
                contacts.remove(selectedEmail);
            }
        });
    }

    // Use this to filter the substring in TextField at the top and setItems
    // to the return value that is an ObservableList<String>
    private ObservableList<String> getSubList(String searchSubstring) {
        ObservableList<String> subList = FXCollections.observableArrayList();
        for (String str : contacts) {
            if (str.toLowerCase().contains(searchSubstring.toLowerCase()))
                subList.add(str);
        }
        return subList;
    }
}
