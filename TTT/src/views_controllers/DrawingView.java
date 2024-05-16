package views_controllers;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.TicTacToeGame;
/*
 * Louis Romeo
 * CSC 335 Assignment 4
 * Purpose: //
 */
public class DrawingView extends VBox {

    private TicTacToeGame gameModel;
    private ImageView[][] squares;
    private Text statusText;

    public DrawingView() {
        gameModel = new TicTacToeGame();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        squares = new ImageView[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView square = new ImageView();
                square.setFitWidth(100);
                square.setFitHeight(100);
                square.setOnMouseClicked(e -> handleSquareClick(square, i, j));
                squares[i][j] = square;
                gridPane.add(square, j, i);
            }
        }

        statusText = new Text();

        HBox statusBar = new HBox(statusText);
        statusBar.setAlignment(Pos.CENTER);

        this.getChildren().addAll(gridPane, statusBar);

        gameModel.addObserver(null);
    }

    private void handleSquareClick(ImageView square, int row, int col) {
        if (gameModel.stillRunning() && gameModel.available(row, col)) {
            gameModel.humanMove(row, col, false);
        }
    }

    private void updateView() {
        char[][] board = gameModel.getTicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    squares[i][j].setImage(new Image("x.png")); // x drawing
                } else if (board[i][j] == 'O') {
                    squares[i][j].setImage(new Image("o.png")); // o drawing
                }
            }
        }

        if (!gameModel.stillRunning()) {
            if (gameModel.didWin('X')) {
                showAlert("X wins!");
            } else if (gameModel.didWin('O')) {
                showAlert("O wins!");
            } else {
                showAlert("It's a tie!");
            }
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public TicTacToeGame getGameModel() {
        return gameModel;
    }
}
