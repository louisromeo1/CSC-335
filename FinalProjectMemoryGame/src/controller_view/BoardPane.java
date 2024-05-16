package controller_view;

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Accounts;
import model.Card;
import model.MemoryGame;
import model.OurObserver;

/**
 * The BoardPane class is a BorderPane that displays the MemoryGame and allows
 * for user interaction with the game. Live stats and game messages are
 * displayed at the bottom of the pane.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class BoardPane extends BorderPane implements OurObserver {
	private Canvas canvas;
	private GraphicsContext gc;
	private MemoryGame game;
	private BorderPane messageArea;
	private GridPane liveStatsGrid;

	private Label scoreLbl;
	private Label curScore;
	private Label gamePrompt;
	private Label subPrompt;

	private int cardSize;
	private int startX;
	private int startY;
	private int cardGap;

	private Image cardBack;
	private Image cardFront;
	private Image starPower;
	private Image bombPower;
	private Image laserPower;
	private Image a1;
	private Image a2;
	private Image a3;
	private Image b1;
	private Image b2;
	private Image b3;
	private Image c1;
	private Image c2;
	private Image c3;
	private Image d1;
	private Image d2;
	private Image d3;
	private Image e1;
	private Image e2;
	private Image e3;
	private Image f1;
	private Image f2;
	private Image f3;

	private memoryGUI gui;

	/**
	 * The Board Pane constructor, which lays out the game board visually and
	 * registers event handlers.
	 * 
	 * @param gui A reference to the memoryGUI so its methods may be used.
	 * 
	 */
	public BoardPane(memoryGUI gui) {
		layoutBoard();
		this.gui = gui;

		this.registerHandlers();
	}

	/**
	 * This method draws the cards of the memory game to the canvas, taking into
	 * account their state and card value.
	 * 
	 */
	public void drawCards() {
		clearCanvas();
		int boardSize = game.getSize();

		initImages();

		if (boardSize == 2) {
			cardSize = 180;
			startX = 210;
			startY = 40;
			cardGap = 20;
		} else if (boardSize == 3) {
			cardSize = 140;
			startX = 180;
			startY = 10;
			cardGap = 15;
		} else if (boardSize == 4) {
			cardSize = 100;
			startX = 175;
			startY = 15;
			cardGap = 15;
		} else if (boardSize == 5) {
			cardSize = 75;
			startX = 180;
			startY = 10;
			cardGap = 15;
		} else if (boardSize == 6) {
			cardSize = 60;
			startX = 180;
			startY = 15;
			cardGap = 15;
		}

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				int thisStartX = startX + (col * (cardSize + cardGap));
				int thisStartY = startY + (row * (cardSize + cardGap));
				Card thisCard = game.getBoard().getCard(row, col);

				if (thisCard.getRevealed()) {
					drawCard(thisCard, thisStartX, thisStartY);
				} else {
					gc.drawImage(cardBack, thisStartX, thisStartY, cardSize, cardSize);
				}
			}
		}

	}

	/**
	 * This function initializes all of the image files that will be need to be
	 * displayed on the GUI based on the gameTheme.
	 * 
	 */
	private void initImages() {
		cardBack = new Image("file:img/BasicCardBack.png");
		cardFront = new Image("file:img/BasicCardInside.png");
		starPower = new Image("file:img/power1.png");
		bombPower = new Image("file:img/bombPower.png");
		laserPower = new Image("file:img/laserPower.png");

		int gameTheme = game.getTheme();

		if (gameTheme == 0) {
			a1 = new Image("file:img/RedSquare.png");
			a2 = new Image("file:img/RedCircle.png");
			a3 = new Image("file:img/RedPlus.png");
			b1 = new Image("file:img/BlueSquare.png");
			b2 = new Image("file:img/BlueCircle.png");
			b3 = new Image("file:img/BluePlus.png");
			c1 = new Image("file:img/GreenSquare.png");
			c2 = new Image("file:img/GreenCircle.png");
			c3 = new Image("file:img/GreenPlus.png");
			d1 = new Image("file:img/BlackSquare.png");
			d2 = new Image("file:img/BlackCircle.png");
			d3 = new Image("file:img/BlackPlus.png");
			e1 = new Image("file:img/YellowSquare.png");
			e2 = new Image("file:img/YellowCircle.png");
			e3 = new Image("file:img/YellowPlus.png");
			f1 = new Image("file:img/OrangeSquare.png");
			f2 = new Image("file:img/OrangeCircle.png");
			f3 = new Image("file:img/OrangePlus.png");
		} else if (gameTheme == 1) {
			a1 = new Image("file:img/Ape.png");
			a2 = new Image("file:img/Bear.png");
			a3 = new Image("file:img/Beaver.png");
			b1 = new Image("file:img/Bulldog.png");
			b2 = new Image("file:img/ClimbMonkey.png");
			b3 = new Image("file:img/Deer.png");
			c1 = new Image("file:img/Dog.png");
			c2 = new Image("file:img/Duck.png");
			c3 = new Image("file:img/Elephant.png");
			d1 = new Image("file:img/FlySquirrel.png");
			d2 = new Image("file:img/Frog.png");
			d3 = new Image("file:img/Goose.png");
			e1 = new Image("file:img/Lion.png");
			e2 = new Image("file:img/Monkey.png");
			e3 = new Image("file:img/Pig.png");
			f1 = new Image("file:img/Raccoon.png");
			f2 = new Image("file:img/Sheep.png");
			f3 = new Image("file:img/Tiger.png");
		} else if (gameTheme == 2) {
			a1 = new Image("file:img/Alien.png");
			a2 = new Image("file:img/alienShip.png");
			a3 = new Image("file:img/asteroid.png");
			b1 = new Image("file:img/Earth.png");
			b2 = new Image("file:img/FullShip.png");
			b3 = new Image("file:img/Jupiter.png");
			c1 = new Image("file:img/Mars.png");
			c2 = new Image("file:img/Neptune.png");
			c3 = new Image("file:img/opAlien.png");
			d1 = new Image("file:img/orangeAlien.png");
			d2 = new Image("file:img/orangeAstronaut.png");
			d3 = new Image("file:img/pbAlien.png");
			e1 = new Image("file:img/rbRocket.png");
			e2 = new Image("file:img/robot.png");
			e3 = new Image("file:img/rover.png");
			f1 = new Image("file:img/spaceMachine.png");
			f2 = new Image("file:img/Uranus.png");
			f3 = new Image("file:img/whiteAstronaut.png");
		}
	}

	/**
	 * This function draws a card to the board, given a start X and start Y
	 * coordinate determined by the board size and card index, as well as the card
	 * itself, properly drawing the specific card type considering its revealed
	 * state.
	 * 
	 * @param thisCard   The card to be drawn to the canvas.
	 * @param thisStartX The start X coordinate of the card to be drawn.
	 * @param thisStartY The start Y coordinate of the card to be drawn.
	 */
	public void drawCard(Card thisCard, int thisStartX, int thisStartY) {
		if ("A".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(a1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("A".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(a2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("A".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(a3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("B".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(b1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("B".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(b2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("B".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(b3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("C".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(c1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("C".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(c2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("C".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(c3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("D".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(d1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("D".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(d2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("D".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(d3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("E".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(e1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("E".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(e2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("E".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(e3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("F".equals(thisCard.getType2()) && "1".equals(thisCard.getType1())) {
			gc.drawImage(f1, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("F".equals(thisCard.getType2()) && "2".equals(thisCard.getType1())) {
			gc.drawImage(f2, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("F".equals(thisCard.getType2()) && "3".equals(thisCard.getType1())) {
			gc.drawImage(f3, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("POWER".equals(thisCard.getType2()) && "Star".equals(thisCard.getType1())) {
			gc.drawImage(starPower, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("POWER".equals(thisCard.getType2()) && "Bomb".equals(thisCard.getType1())) {
			gc.drawImage(bombPower, thisStartX, thisStartY, cardSize, cardSize);
		} else if ("POWER".equals(thisCard.getType2()) && "Laser".equals(thisCard.getType1())) {
			gc.drawImage(laserPower, thisStartX, thisStartY, cardSize, cardSize);
		} else {
			System.out.println("Drawing generic...");
			gc.drawImage(cardFront, thisStartX, thisStartY, cardSize, cardSize);
		}
	}

	/**
	 * This method clears the canvas before redrawing of a new game.
	 */
	public void clearCanvas() {
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (this.game == null) {
			gamePrompt.setText("Start a New Game!");
		} else {
			gamePrompt.setText("You are doing great!");
		}
		subPrompt.setText("Bonuses Viewed Here!");
		curScore.setText("0");
	}

	/**
	 * This method creates the canvas for the game and adds the game prompt and live
	 * score to the pane.
	 */
	public void layoutBoard() {
		this.setMinWidth(850);
		this.setMinHeight(500);

		canvas = new Canvas(850, 480);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		liveStatsGrid = new GridPane();
		messageArea = new BorderPane();

		scoreLbl = new Label("Score:");
		scoreLbl.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		curScore = new Label("0");
		curScore.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
		gamePrompt = new Label("Start a New Game!");
		gamePrompt.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: blue");
		subPrompt = new Label("View Bonuses Here!");
		subPrompt.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		subPrompt.setPadding(new Insets(0, 0, 10, 10));
		liveStatsGrid.setHgap(5);

		liveStatsGrid.add(scoreLbl, 0, 0);
		liveStatsGrid.add(curScore, 1, 0);

		messageArea.setTop(gamePrompt);
		messageArea.setCenter(liveStatsGrid);
		messageArea.setBottom(subPrompt);
		liveStatsGrid.setPadding(new Insets(0, 0, 0, 60));
		messageArea.setPadding(new Insets(0, 0, 0, 310));

		this.setTop(canvas);
		this.setCenter(messageArea);
	}

	/**
	 * This method updates the current user's Account with game information called
	 * when game is finished.
	 * 
	 */
	private void updateScores() {
		if (game != null && !game.gameActive()) {
			System.out.println("Game has ended!");
			System.out.println("Best guess streak: " + game.getBestStreak());
			Accounts currAcct = gui.getCurrAcct();
			currAcct.setNewStreak(game.getBestStreak(), game.getSize());
			currAcct.incrementGamesPlayed();
			currAcct.setNewBestScore(game.getScore(), game.getSize(), game.getGameMode());
		}
	}

	/**
	 * This method specifies the score bonus for a 3x3 game given score and mode.
	 * 
	 * @param score The user's score on the game.
	 * @param mode  The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int scoreBonus3(int score, int mode) {
		if (mode != 3) {
			if (score <= 50) {
				return 100;
			} else if (score <= 60) {
				return 50;
			} else if (score <= 80) {
				return 25;
			}
		} else {
			if (score <= 40) {
				return 100;
			} else if (score <= 50) {
				return 50;
			} else if (score <= 60) {
				return 25;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the streak bonus for a 3x3 game given streak and mode.
	 * 
	 * @param streak The user's best streak in the game.
	 * @param mode   The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int streakBonus3(int streak, int mode) {
		if (mode != 3) {
			if (streak >= 4) {
				return 100;
			} else if (streak >= 3) {
				return 50;
			}
		} else {
			if (streak >= 4) {
				return 70;
			} else if (streak >= 3) {
				return 30;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the score bonus for a 4x4 game given score and mode.
	 * 
	 * @param score The user's score on the game.
	 * @param mode  The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int scoreBonus4(int score, int mode) {
		if (mode != 3) {
			if (score <= 100) {
				return 500;
			} else if (score <= 110) {
				return 300;
			} else if (score <= 120) {
				return 150;
			} else if (score <= 150) {
				return 100;
			}
		} else {
			if (score <= 90) {
				return 500;
			} else if (score <= 100) {
				return 300;
			} else if (score <= 110) {
				return 150;
			} else if (score <= 140) {
				return 100;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the streak bonus for a 4x4 game given streak and mode.
	 * 
	 * @param streak The user's best streak in the game.
	 * @param mode   The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int streakBonus4(int streak, int mode) {
		if (mode != 3) {
			if (streak >= 7) {
				return 700;
			} else if (streak >= 5) {
				return 350;
			} else if (streak >= 3) {
				return 100;
			}
		} else {
			if (streak >= 6) {
				return 500;
			} else if (streak >= 4) {
				return 250;
			} else if (streak >= 3) {
				return 80;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the score bonus for a 5x5 game given score and mode.
	 * 
	 * @param score The user's score on the game.
	 * @param mode  The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int scoreBonus5(int score, int mode) {
		if (mode != 3) {
			if (score <= 170) {
				return 1000;
			} else if (score <= 190) {
				return 750;
			} else if (score <= 210) {
				return 500;
			} else if (score <= 240) {
				return 250;
			}
		} else {
			if (score <= 150) {
				return 1000;
			} else if (score <= 170) {
				return 750;
			} else if (score <= 190) {
				return 500;
			} else if (score <= 210) {
				return 250;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the streak bonus for a 5x5 game given streak and mode.
	 * 
	 * @param streak The user's best streak in the game.
	 * @param mode   The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int streakBonus5(int streak, int mode) {
		if (mode != 3) {
			if (streak >= 8) {
				return 1500;
			} else if (streak >= 5) {
				return 900;
			} else if (streak >= 3) {
				return 250;
			}
		} else {
			if (streak >= 8) {
				return 1000;
			} else if (streak >= 5) {
				return 650;
			} else if (streak >= 3) {
				return 250;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the score bonus for a 6x6 game given score and mode.
	 * 
	 * @param score The user's score on the game.
	 * @param mode  The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int scoreBonus6(int score, int mode) {
		if (mode != 3) {
			if (score <= 260) {
				return 2000;
			} else if (score <= 280) {
				return 1250;
			} else if (score <= 310) {
				return 700;
			} else if (score <= 360) {
				return 350;
			}
		} else {
			if (score <= 230) {
				return 2000;
			} else if (score <= 250) {
				return 1250;
			} else if (score <= 270) {
				return 700;
			} else if (score <= 330) {
				return 350;
			}
		}

		return 0;
	}

	/**
	 * This method specifies the streak bonus for a 6x6 game given streak and mode.
	 * 
	 * @param streak The user's best streak in the game.
	 * @param mode   The mode of the game.
	 * @return An integer representing the bonus allocated based on score and mode.
	 */
	private int streakBonus6(int streak, int mode) {
		if (mode != 3) {
			if (streak >= 10) {
				return 5000;
			} else if (streak >= 8) {
				return 3000;
			} else if (streak >= 6) {
				return 1500;
			} else if (streak >= 3) {
				return 350;
			}
		} else {
			if (streak >= 10) {
				return 4000;
			} else if (streak >= 8) {
				return 2500;
			} else if (streak >= 6) {
				return 1000;
			} else if (streak >= 3) {
				return 150;
			}
		}

		return 0;
	}

	/**
	 * This method allocates currency winnings to the current Account based on how
	 * they performed in a game.
	 */
	private void payoutWinnings() {
		int size = game.getSize();
		int score = game.getScore();
		int mode = game.getGameMode();
		int streak = game.getBestStreak();

		int streakBonus = 0;
		int scoreBonus = 0;

		if (size == 2) {
			if (score <= 20) {
				scoreBonus = 25;
			}
		} else if (size == 3) {
			scoreBonus = this.scoreBonus3(score, mode);
			streakBonus = this.streakBonus3(streak, mode);
		} else if (size == 4) {
			scoreBonus = this.scoreBonus4(score, mode);
			streakBonus = this.streakBonus4(streak, mode);
		} else if (size == 5) {
			scoreBonus = this.scoreBonus5(score, mode);
			streakBonus = this.streakBonus5(streak, mode);
		} else {
			scoreBonus = this.scoreBonus6(score, mode);
			streakBonus = this.streakBonus6(streak, mode);
		}

		gui.getCurrAcct().incrementBalance(scoreBonus + streakBonus);

		String bonusString = "This game's bonuses: \n" + "Streak Bonus: " + streakBonus + "\n" + "Score Bonus: "
				+ scoreBonus + "\n" + "Total: " + (streakBonus + scoreBonus);

		subPrompt.setText(bonusString);
	}

	/**
	 * This method registers handlers for the BoardPane, in this case specifically,
	 * it handles clicks on the canvas. Calculations are made based on game mode and
	 * board size to handle clicks of cards properly, and updating of account
	 * statistics occurs on ending of a game.
	 */
	public void registerHandlers() {

		canvas.setOnMousePressed(event -> {
			if (game != null && game.gameActive()) {
				double curX = event.getSceneX() - 1.6;
				double curY = event.getSceneY() - 22.4;
				int gameSize = game.getSize();

				int xBorder = startX + ((cardSize + cardGap) * (gameSize - 1)) + cardSize;
				int yBorder = startY + ((cardSize + cardGap) * (gameSize - 1)) + cardSize;

				int curRow = -1;
				int curCol = -1;

				if (curX >= startX && curX <= xBorder && curY >= startY && curY <= yBorder) {
					if (curX <= startX + cardSize) {
						curCol = 0;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 1 * (cardSize + cardGap))
							&& curX <= (startX + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
						curCol = 1;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 2 * (cardSize + cardGap))
							&& curX <= (startX + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
						curCol = 2;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 3 * (cardSize + cardGap))
							&& curX <= (startX + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
						curCol = 3;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 4 * (cardSize + cardGap))
							&& curX <= (startX + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
						curCol = 4;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					} else if (curX >= (startX + 5 * (cardSize + cardGap))
							&& curX <= (startX + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
						curCol = 5;
						if (curY <= startY + cardSize) {
							curRow = 0;
						} else if (curY >= (startY + 1 * (cardSize + cardGap))
								&& curY <= (startY + 1 * (cardSize + cardGap) + cardSize) && 1 < gameSize) {
							curRow = 1;
						} else if (curY >= (startY + 2 * (cardSize + cardGap))
								&& curY <= (startY + 2 * (cardSize + cardGap) + cardSize) && 2 < gameSize) {
							curRow = 2;
						} else if (curY >= (startY + 3 * (cardSize + cardGap))
								&& curY <= (startY + 3 * (cardSize + cardGap) + cardSize) && 3 < gameSize) {
							curRow = 3;
						} else if (curY >= (startY + 4 * (cardSize + cardGap))
								&& curY <= (startY + 4 * (cardSize + cardGap) + cardSize) && 4 < gameSize) {
							curRow = 4;
						} else if (curY >= (startY + 5 * (cardSize + cardGap))
								&& curY <= (startY + 5 * (cardSize + cardGap) + cardSize) && 5 < gameSize) {
							curRow = 5;
						}
					}
					if (curRow == -1 | curCol == -1) {
						System.out.println("Registered invalid click");
					} else {
						game.cardClicked(curRow, curCol, 1);
					}
				}
			}

		});

	}

	/**
	 * This method creates a new MemoryGame, adding this Pane as an observer,
	 * initializes the game, and sets the game prompt.
	 * 
	 * @param gameMode  the integer representing the current game's game mode.
	 * @param boardSize the integer representing the current game's board size.
	 * @param gameTheme The integer representing the theme of the game, for visual
	 *                  purposes.
	 */
	public void startNewGame(int gameMode, int boardSize, int gameTheme) {
		game = new MemoryGame(gameMode, boardSize, gameTheme, gui.getCurrAcct(), subPrompt);
		game.addObserver(this);
		game.initGame();
		gamePrompt.setText("Click Cards to Begin!");
		gui.getCurrAcct().setNewGame(game);
	}

	/**
	 * This method sets up a previously existing game as the current game.
	 * 
	 * @param game The previously existing game being set up on the BoardPane.
	 */
	public void setGame(MemoryGame game) {
		this.game = game;
		this.game.addObserver(this);
		drawCards();
		gamePrompt.setText("Resume Your Game!");
		subPrompt.setText("View Bonuses Here!");
		curScore.setText("" + game.getScore());
	}

	@Override
	/**
	 * The method that is called on change of state of the MemoryGame, redrawing
	 * cards.
	 * 
	 * @param theObserved represents the MemoryGame being observed by this Pane.
	 */
	public void update(Object theObserved) {
		drawCards();
		curScore.setText("" + game.getScore());
		if (!game.gameActive()) {
			gamePrompt.setText("Congrats! You won!");
			updateScores();
			payoutWinnings();
			gui.getCurrAcct().endCurrGame();
		}
	}

}
