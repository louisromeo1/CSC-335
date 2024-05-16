package controller_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Accounts;

/**
 * The StatsPane class is a BorderPane that is added to memoryGUI to view game
 * statistics of the current user.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class StatsPane extends BorderPane {

	private Accounts accounts;
	private Label username;
	private Label totalGamesPlayed;
	private Label usernameLabel;
	private Label gamesPlayedLabel;

	private int best2Norm;
	private int best2Streak;
	private int best3Odd;
	private int best3ThreeKind;
	private int best3Power;
	private int best3Streak;
	private int best4Norm;
	private int best4Power;
	private int best4Streak;
	private int best5Odd;
	private int best5Power;
	private int best5Streak;
	private int best6Norm;
	private int best6ThreeKind;
	private int best6Power;
	private int best6Streak;
	private Label str2Norm;
	private Label str2Streak;
	private Label str3Odd;
	private Label str3ThreeKind;
	private Label str3Power;
	private Label str3Streak;
	private Label str4Norm;
	private Label str4Power;
	private Label str4Streak;
	private Label str5Odd;
	private Label str5Power;
	private Label str5Streak;
	private Label str6Norm;
	private Label str6ThreeKind;
	private Label str6Power;
	private Label str6Streak;

	private memoryGUI gui;
	private BorderPane pane;
	private GridPane grid;

	private Label userStats;

	private Image logoImage;

	private Canvas canvas;
	private GraphicsContext gc;

	/**
	 * The constructor for StatsPane, simply sets the gui instance variable.
	 * 
	 * @param gui The memoryGUI for referencing of methods.
	 */
	public StatsPane(memoryGUI gui) {
		this.gui = gui;
	}

	/**
	 * This method lays out the StatsPane by creating a GridPane with numerous
	 * labels that describe the current User's memory game statistics.
	 */
	public void layoutStatsPane() {
		Accounts curAcct = gui.getCurrAcct();

		this.username = new Label(curAcct.getUsername());
		this.totalGamesPlayed = new Label(String.valueOf(curAcct.getGamesPlayed()));

		this.best2Norm = curAcct.getBestScore(2, 0);
		this.best2Streak = curAcct.getBestStreak(2);
		this.best3Odd = curAcct.getBestScore(3, 1);
		this.best3ThreeKind = curAcct.getBestScore(3, 2);
		this.best3Power = curAcct.getBestScore(3, 3);
		this.best3Streak = curAcct.getBestStreak(3);
		this.best4Norm = curAcct.getBestScore(4, 0);
		this.best4Power = curAcct.getBestScore(4, 3);
		this.best4Streak = curAcct.getBestStreak(4);
		this.best5Odd = curAcct.getBestScore(5, 1);
		this.best5Power = curAcct.getBestScore(5, 3);
		this.best5Streak = curAcct.getBestStreak(5);
		this.best6Norm = curAcct.getBestScore(6, 0);
		this.best6ThreeKind = curAcct.getBestScore(6, 2);
		this.best6Power = curAcct.getBestScore(6, 3);
		this.best6Streak = curAcct.getBestStreak(6);

		str2Norm = new Label("Best 2x2 Normal Score: ");
		str2Streak = new Label("Best 2x2 Guess Streak: ");
		str3Odd = new Label("Best 3x3 Odd One Out Score:");
		str3ThreeKind = new Label("Best 3x3 Three of a Kind Score: ");
		str3Power = new Label("Best 3x3 Power Score: ");
		str3Streak = new Label("Best 3x3 Guess Streak: ");
		str4Norm = new Label("Best 4x4 Normal Score: ");
		str4Power = new Label("Best 4x4 Power Score: ");
		str4Streak = new Label("Best 4x4 Guess Streak: ");
		str5Odd = new Label("Best 5x5 Odd One Out Score: ");
		str5Power = new Label("Best 5x5 Power Score: ");
		str5Streak = new Label("Best 5x5 Guess Streak: ");
		str6Norm = new Label("Best 6x6 Normal Score: ");
		str6ThreeKind = new Label("Best 6x6 Three of a Kind Score: ");
		str6Power = new Label("Best 6x6 Power Score: ");
		str6Streak = new Label("Best 6x6 Guess Streak: ");

		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

		logoImage = new Image("file:StatsLogo.png");
		canvas = new Canvas(800, 200);
		gc = canvas.getGraphicsContext2D();

		gc.drawImage(logoImage, 300, 0, 160, 160);

		userStats = new Label("User Stats");
		userStats.setStyle("-fx-font-size: 50; -fx-font-weight: bold");
		userStats.setPadding(new Insets(-20, 0, 20, 0));

		usernameLabel = new Label("Username: ");
		gamesPlayedLabel = new Label("Games Played: ");

		usernameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		gamesPlayedLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str2Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str2Streak.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str3Odd.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str3ThreeKind.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str3Power.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str3Streak.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str4Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str4Power.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str4Streak.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str5Odd.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str5Power.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str5Streak.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str6Norm.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str6ThreeKind.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str6Power.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		str6Streak.setStyle("-fx-font-size: 15; -fx-font-weight: bold");

		username.setStyle("-fx-font-size: 15;");
		totalGamesPlayed.setStyle("-fx-font-size: 15;");
		Label lbl2Norm = new Label(String.valueOf(best2Norm));
		if (lbl2Norm.getText().equals("-1")) {
			lbl2Norm.setText("N/A");
		}
		lbl2Norm.setStyle("-fx-font-size: 15;");
		Label lbl2Streak = new Label(String.valueOf(best2Streak));
		if (lbl2Streak.getText().equals("-1")) {
			lbl2Streak.setText("N/A");
		}
		lbl2Streak.setStyle("-fx-font-size: 15;");
		Label lbl3Odd = new Label(String.valueOf(best3Odd));
		if (lbl3Odd.getText().equals("-1")) {
			lbl3Odd.setText("N/A");
		}
		lbl3Odd.setStyle("-fx-font-size: 15;");
		Label lbl3ThreeKind = new Label(String.valueOf(best3ThreeKind));
		if (lbl3ThreeKind.getText().equals("-1")) {
			lbl3ThreeKind.setText("N/A");
		}
		lbl3ThreeKind.setStyle("-fx-font-size: 15;");
		Label lbl3Power = new Label(String.valueOf(best3Power));
		if (lbl3Power.getText().equals("-1")) {
			lbl3Power.setText("N/A");
		}
		lbl3Power.setStyle("-fx-font-size: 15;");
		Label lbl3Streak = new Label(String.valueOf(best3Streak));
		if (lbl3Streak.getText().equals("-1")) {
			lbl3Streak.setText("N/A");
		}
		lbl3Streak.setStyle("-fx-font-size: 15;");
		Label lbl4Norm = new Label(String.valueOf(best4Norm));
		if (lbl4Norm.getText().equals("-1")) {
			lbl4Norm.setText("N/A");
		}
		lbl4Norm.setStyle("-fx-font-size: 15;");
		Label lbl4Power = new Label(String.valueOf(best4Power));
		if (lbl4Power.getText().equals("-1")) {
			lbl4Power.setText("N/A");
		}
		lbl4Power.setStyle("-fx-font-size: 15;");
		Label lbl4Streak = new Label(String.valueOf(best4Streak));
		if (lbl4Streak.getText().equals("-1")) {
			lbl4Streak.setText("N/A");
		}
		lbl4Streak.setStyle("-fx-font-size: 15;");
		Label lbl5Odd = new Label(String.valueOf(best5Odd));
		if (lbl5Odd.getText().equals("-1")) {
			lbl5Odd.setText("N/A");
		}
		lbl5Odd.setStyle("-fx-font-size: 15;");
		Label lbl5Power = new Label(String.valueOf(best5Power));
		if (lbl5Power.getText().equals("-1")) {
			lbl5Power.setText("N/A");
		}
		lbl5Power.setStyle("-fx-font-size: 15;");
		Label lbl5Streak = new Label(String.valueOf(best5Streak));
		if (lbl5Streak.getText().equals("-1")) {
			lbl5Streak.setText("N/A");
		}
		lbl5Streak.setStyle("-fx-font-size: 15;");
		Label lbl6Norm = new Label(String.valueOf(best6Norm));
		if (lbl6Norm.getText().equals("-1")) {
			lbl6Norm.setText("N/A");
		}
		lbl6Norm.setStyle("-fx-font-size: 15;");
		Label lbl6ThreeKind = new Label(String.valueOf(best6ThreeKind));
		if (lbl6ThreeKind.getText().equals("-1")) {
			lbl6ThreeKind.setText("N/A");
		}
		lbl6ThreeKind.setStyle("-fx-font-size: 15;");
		Label lbl6Power = new Label(String.valueOf(best6Power));
		if (lbl6Power.getText().equals("-1")) {
			lbl6Power.setText("N/A");
		}
		lbl6Power.setStyle("-fx-font-size: 15;");
		Label lbl6Streak = new Label(String.valueOf(best6Streak));
		if (lbl6Streak.getText().equals("-1")) {
			lbl6Streak.setText("N/A");
		}
		lbl6Streak.setStyle("-fx-font-size: 15;");

		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(8);

		// grid.add(userStats, 0, 0);
		grid.add(usernameLabel, 1, 1);
		grid.add(username, 2, 1);
		grid.add(gamesPlayedLabel, 1, 2);
		grid.add(totalGamesPlayed, 2, 2);
		grid.add(str2Norm, 0, 3);
		grid.add(lbl2Norm, 1, 3);
		grid.add(str2Streak, 2, 3);
		grid.add(lbl2Streak, 3, 3);
		grid.add(str3Odd, 0, 4);
		grid.add(lbl3Odd, 1, 4);
		grid.add(str3ThreeKind, 0, 5);
		grid.add(lbl3ThreeKind, 1, 5);
		grid.add(str3Power, 0, 6);
		grid.add(lbl3Power, 1, 6);
		grid.add(str3Streak, 2, 4);
		grid.add(lbl3Streak, 3, 4);
		grid.add(str4Norm, 0, 7);
		grid.add(lbl4Norm, 1, 7);
		grid.add(str4Power, 0, 8);
		grid.add(lbl4Power, 1, 8);
		grid.add(str4Streak, 2, 5);
		grid.add(lbl4Streak, 3, 5);
		grid.add(str5Odd, 0, 9);
		grid.add(lbl5Odd, 1, 9);
		grid.add(str5Power, 0, 10);
		grid.add(lbl5Power, 1, 10);
		grid.add(str5Streak, 2, 6);
		grid.add(lbl5Streak, 3, 6);
		grid.add(str6Norm, 0, 11);
		grid.add(lbl6Norm, 1, 11);
		grid.add(str6ThreeKind, 0, 12);
		grid.add(lbl6ThreeKind, 1, 12);
		grid.add(str6Power, 0, 13);
		grid.add(lbl6Power, 1, 13);
		grid.add(str6Streak, 2, 7);
		grid.add(lbl6Streak, 3, 7);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth(180);
		grid.getColumnConstraints().add(col1);

		grid.setPadding(new Insets(-30, 0, 0, -180));

		pane = new BorderPane();
		pane.setTop(userStats);
		pane.setCenter(grid);

		pane.setPadding(new Insets(-50, 0, 0, 270));

		this.setTop(canvas);
		this.setCenter(pane);

		this.setPadding(new Insets(0, 20, 20, 20));
		// top, right, bottom, left
	}
}
