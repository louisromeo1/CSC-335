// MemoryGame will be tested visually through the GUI

package model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * The MemoryGame class holds info for the MemoryGame. This is the class that
 * the GUI will interact with to show the data.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class MemoryGame extends OurObservable implements java.io.Serializable {

	private Board board;
	private int gameMode;
	private int moves;
	private int numCards;
	private int score;
	private ArrayList<Card> revealedCards = new ArrayList<>();
	private int numMatches;
	private boolean isGameActive;
	private int gameTheme; // 0 - Shapes, 1 - Animals, 2 - space
	private Timer timer;
	private int starsRevealed;
	private int lastClickedX = 0;
	private int lastClickedY = 0;
	private Boolean allowGuiClicks = true;
	private int currStreak = 0;
	private int bestStreak = 0;
	private Accounts gameUser;
	private Label gameSubLabel;

	/**
	 * The constructor for memory game, creating a new board and initializing game
	 * stats.
	 * 
	 * @param gameMode     The initialized game's game mode.
	 * @param size         The board size for the MemoryGame.
	 * @param gameTheme    The theme of the game being initialized.
	 * @param user         The user playing the game.
	 * @param gameSubLabel The label displaying bonuses within the BoardPane.
	 */
	public MemoryGame(int gameMode, int size, int gameTheme, Accounts user, Label gameSubLabel) {
		board = new Board(size);
		numCards = size * size;
		this.gameMode = gameMode;
		moves = 0;
		score = 0;
		this.gameTheme = gameTheme;
		this.starsRevealed = 0;
		this.gameUser = user;
	}

	/**
	 * An alternative constructor for memory game, which calls the primary
	 * constructor with a null gameSubLabel
	 * 
	 * @param gameMode  The initialized game's game mode.
	 * @param size      The board size for the MemoryGame.
	 * @param gameTheme The theme of the game being initialized.
	 * @param user      The user playing the game.
	 */
	public MemoryGame(int gameMode, int size, int gameTheme, Accounts user) {
		this(gameMode, size, gameTheme, user, null);
	}

	/**
	 * This method initializes the board to start the game, setting game mode,
	 * shuffling the board, and notifying observers.
	 */
	public void initGame() {
		board.changeMode(gameMode);
		board.initBoard(gameUser);
		board.shuffle();
		this.isGameActive = true;
		notifyObservers(this);
	}

	/**
	 * Returns the theme of this MemoryGame.
	 * 
	 * @return The theme of this MemoryGame.
	 */
	public int getTheme() {
		return this.gameTheme;
	}

	/**
	 * A getter for the game Board.
	 * 
	 * @return The Board for the game.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Method returns whether or not the game is active.
	 * 
	 * @return True if game is active, false otherwise.
	 */
	public boolean gameActive() {
		return this.isGameActive;
	}

	/**
	 * A getter for the number of moves in the game.
	 * 
	 * @return The number of moves in the MemoryGame.
	 */
	public int getNumMoves() {
		return moves;
	}

	/**
	 * A getter for the current score of the game.
	 * 
	 * @return The current score of the MemoryGame.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * A getter for the number of cards currently revealed.
	 * 
	 * @return The number of cards currently revealed.
	 */
	public int getNumCardsSelected() {
		return revealedCards.size();
	}

	/**
	 * A getter for the total number of cards in the game.
	 * 
	 * @return The total number of cards in the MemoryGame.
	 */
	public int getNumCards() {
		return numCards;
	}

	/**
	 * A getter for the game mode of the game.
	 * 
	 * @return The game mode of MemoryGame.
	 */
	public int getGameMode() {
		return gameMode;
	}

	/**
	 * A getter for the number of matches in the game.
	 * 
	 * @return The current number of matches in the MemoryGame.
	 */
	public int getNumMatches() {
		return numMatches;
	}

	/**
	 * Logs a correct guess by adding to current streak and resetting best streak if
	 * applicable.
	 */
	private void correctGuess() {
		this.currStreak += 1;
		if (this.currStreak > this.bestStreak) {
			this.bestStreak = this.currStreak;
		}
	}

	/**
	 * Logs an incorrect guess by zero-ing out the current streak.
	 */
	private void incorrectGuess() {
		this.currStreak = 0;
	}

	/**
	 * Reveals all adjacent cards around a bomb power up for 1.5 seconds.
	 * 
	 * @param rowBomb The row of the bomb card.
	 * @param colBomb The column of the bomb card.
	 */
	private void revealAdjacent(int rowBomb, int colBomb) {
		int lowerRowLim;
		int lowerColLim;

		ArrayList<Card> peakCards = new ArrayList<>();

		if (colBomb - 1 < 0) {
			lowerColLim = colBomb;
		} else {
			lowerColLim = colBomb - 1;
		}

		if (rowBomb - 1 < 0) {
			lowerRowLim = rowBomb;
		} else {
			lowerRowLim = rowBomb - 1;
		}

		for (int i = lowerRowLim; i < rowBomb + 2; i++) {
			for (int j = lowerColLim; j < colBomb + 2; j++) {
				if (i < this.getSize() && j < this.getSize()) {
					Card thisCard = board.getCard(i, j);
					if (!thisCard.getRevealed()) {
						thisCard.toggle();
						peakCards.add(thisCard);
					}
				}
			}
		}

		notifyObservers(this);

		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
		pause.setOnFinished(event -> {

			for (int c = 0; c < peakCards.size(); c++) {
				peakCards.get(c).toggle();
			}

			allowGuiClicks = true;
			notifyObservers(this);
		});
		pause.play();

	}

	/**
	 * Reveals all cards in the column of a laser power up for 1.5 seconds.
	 * 
	 * @param laserCol The column of the laser card.
	 */
	private void revealColumn(int laserCol) {
		ArrayList<Card> peakCards = new ArrayList<>();

		for (int i = 0; i < this.getSize(); i++) {
			Card thisCard = board.getCard(i, laserCol);
			if (!thisCard.getRevealed()) {
				thisCard.toggle();
				peakCards.add(thisCard);
			}
		}

		notifyObservers(this);

		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
		pause.setOnFinished(event -> {

			for (int c = 0; c < peakCards.size(); c++) {
				peakCards.get(c).toggle();
			}

			allowGuiClicks = true;
			notifyObservers(this);
		});
		pause.play();
	}

	/**
	 * A method that flips two cards to their back and removes them from revealed
	 * cards, implementing a 1 second pause animation.
	 * 
	 * @param maxCardsClicked The maximum number of cards clicked at once based on
	 *                        the game mode.
	 */
	private void flipCardsBackBasic(int maxCardsClicked) {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			for (int i = 0; i < maxCardsClicked; i++) {
				revealedCards.get(i).toggle();
			}
			revealedCards.clear();
			notifyObservers(this);
		});
		pause.play();
	}

	/**
	 * This method reveals matching cards, as specified by the star power up, using
	 * a one second animation pause.
	 * 
	 * @param matchRow The row of the matching card to be revealed.
	 * @param matchCol The column of the matching card to be revealed.
	 */
	private void starPowerReveal(int matchRow, int matchCol) {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {

			this.cardClicked(matchRow, matchCol, 0);
			allowGuiClicks = true;
			notifyObservers(this);
		});
		pause.play();
	}

	/**
	 * Returns the best guess streak from this MemoryGame.
	 * 
	 * @return The best guess streak from this MemoryGame.
	 */
	public int getBestStreak() {
		return this.bestStreak;
	}

	/**
	 * This method updates the game sub prompt to a specified prompt.
	 * 
	 * @param prompt The string which is the prompt to be updated to.
	 */
	private void updateGamePrompt(String prompt) {
		if (gameSubLabel != null) {
			gameSubLabel.setText(prompt);
			notifyObservers(this);
		}
	}

	/**
	 * Handles a card being clicked by revealing the card, adding the card to the
	 * array of revealed cards, creating an animation to flip cards back over if
	 * they don't match, and ending the game when appropriate. Handles all game
	 * modes processing clicks according to the methodology of the gameMode.
	 * 
	 * @param row      The number specifying row of card clicked.
	 * @param col      The number specifying col of card clicked.
	 * @param guiClick Whether or not the click being registered is from the GUI (1)
	 *                 or from the computer (0) for power game mode.
	 */
	public void cardClicked(int row, int col, int guiClick) {
		Card clickedCard = board.getCard(row, col);
		if (!clickedCard.isPower()) {
			this.lastClickedX = row;
			this.lastClickedY = col;
		}

		int maxClicked;
		if (this.getGameMode() == 0 | this.getGameMode() == 1 | this.getGameMode() == 3) {
			maxClicked = 2;
		} else if (this.getGameMode() == 2) {
			maxClicked = 3;
		} else {
			maxClicked = 2;
		}

		if ((guiClick == 1 && allowGuiClicks == true) || guiClick == 0) {
			if (this.revealedCards.size() < maxClicked && !clickedCard.getRevealed()
					|| (this.getGameMode() == 3 && this.revealedCards.size() == 2 && this.starsRevealed == 1)) {
				clickedCard.toggle();
				this.updateGamePrompt("");
				revealedCards.add(board.getCard(row, col));

				if (this.getGameMode() != 3) {
					if (this.revealedCards.size() == maxClicked) {
						moves++;
						int match = 0;
						if (maxClicked == 2) {
							if (this.checkMatch(revealedCards.get(0), revealedCards.get(1))) {
								// CORRECT GUESS
								this.correctGuess();
								revealedCards.clear();
								match = 1;
								numMatches++;
							}
						} else if (maxClicked == 3) {
							if (this.checkMatch(revealedCards.get(0), revealedCards.get(1), revealedCards.get(2))) {
								// CORRECT GUESS ( 3 of a Kind )
								this.correctGuess();
								revealedCards.clear();
								match = 1;
								numMatches++;
							}
						}
						if (match == 0) {
							// INCORRECT GUESS
							this.incorrectGuess();
							this.flipCardsBackBasic(maxClicked);
						}
					} else if (gameMode == 1 && (numMatches * 2) + revealedCards.size() == numCards) {
						// Counts a move for the last card in 3 of a Kind
						// CORRECT GUESS
						this.correctGuess();
						moves++;
					}
				} else { // Game mode is Power
					if (clickedCard.isStar()) {
						this.starsRevealed += 1;
						if (revealedCards.size() == 2) { // Indicates second click is Star
							if (revealedCards.get(0).isStar() && revealedCards.get(1).isStar()) {
								// First and second click is Star, flip both back over
								// NEUTRAL GUESS - don't add to streak
								moves++;
								this.flipCardsBackBasic(maxClicked);
								starsRevealed = 0;
							} else { // First click normal, second click star, use star power
								Card regCard;
								int[] matchCardCoords;
								if (revealedCards.get(0).isPower()) {
									regCard = revealedCards.get(1);
									matchCardCoords = this.board.findMatch(row, col);
								} else {
									regCard = revealedCards.get(0);
									matchCardCoords = this.board.findMatch(this.lastClickedX, this.lastClickedY);
								}

								allowGuiClicks = false;
								this.starPowerReveal(matchCardCoords[0], matchCardCoords[1]);
							}

						}
					} else if (clickedCard.isBomb()) {
						if (revealedCards.size() == 2
								&& (revealedCards.get(0).isStar() | revealedCards.get(1).isStar())) {
							// First click star, second click bomb
							// For now, just flip both back over?
							this.updateGamePrompt("You found 2 Power Cards! +10 BONUS");
							this.gameUser.incrementBalance(10);
							moves++;
							this.flipCardsBackBasic(maxClicked);
							starsRevealed = 0;
						} else {
							// First click regular, second click bomb OR first click bomb
							moves++;
							allowGuiClicks = false;
							this.revealAdjacent(row, col);
							if (revealedCards.get(0).isBomb()) {
								revealedCards.remove(0);
							} else {
								revealedCards.remove(1);
							}
						}
					} else if (clickedCard.isLaser()) {
						if (revealedCards.size() == 2
								&& (revealedCards.get(0).isStar() | revealedCards.get(1).isStar())) {
							// First click star, second click laser
							// For now, just flip both back over?
							this.updateGamePrompt("You found 2 Power Cards! +10 BONUS");
							this.gameUser.incrementBalance(10);
							moves++;
							this.flipCardsBackBasic(maxClicked);
							starsRevealed = 0;
						} else {
							// First click regular, second click laser OR first click laser
							moves++;
							allowGuiClicks = false;
							this.revealColumn(col);
							if (revealedCards.get(0).isLaser()) {
								revealedCards.remove(0);
							} else {
								revealedCards.remove(1);
							}
						}

					} else { // Power game mode, card clicked isn't power
						if (revealedCards.size() == 2 && this.starsRevealed == 0) { // 2 normal cards clicked
							// normal case - two regular cards clicked
							moves++;
							if (this.checkMatch(revealedCards.get(0), revealedCards.get(1))) {
								// CORRECT GUESS
								this.correctGuess();
								revealedCards.clear();
								numMatches++;
							} else {
								// INCORRECT GUESS
								this.incorrectGuess();
								this.flipCardsBackBasic(maxClicked);
							}
						} else if (revealedCards.size() == 2 && this.starsRevealed == 1) {
							// First click power, second regular
							Card regCard;
							int[] matchCardCoords;
							if (revealedCards.get(0).isPower()) {
								regCard = revealedCards.get(1);
								matchCardCoords = this.board.findMatch(row, col);
							} else {
								regCard = revealedCards.get(0);
								matchCardCoords = this.board.findMatch(this.lastClickedX, this.lastClickedY);
							}

							allowGuiClicks = false;
							this.starPowerReveal(matchCardCoords[0], matchCardCoords[1]);
						} else if (revealedCards.size() == 3 && this.starsRevealed == 1) {
							// CORRECT GUESS
							// (non-gui click) Pair has been completed by a power
							this.correctGuess();
							moves++;
							revealedCards.clear();
							numMatches++;
							this.starsRevealed = 0;
							notifyObservers(this);
						}
					}
				}
			}
		}

		if (gameMode == 0) {
			if (numMatches == (numCards / 2)) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		} else if (gameMode == 1) {
			if ((numMatches * 2) + revealedCards.size() == numCards) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		} else if (gameMode == 2) {
			if ((numMatches * 3) == numCards) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		} else if (gameMode == 3) {
			int powerCount = this.getSize() - 2;
			int otherCount = (this.getSize() * this.getSize()) - powerCount;
			int possibleMatches = otherCount / 2;

			if (numMatches == possibleMatches) {
				System.out.println("Congrats, you matched them all in " + this.moves + " total moves!");
				this.isGameActive = false;
			}
		}

		updateScore();
		notifyObservers(this);
	}

	/**
	 * Updates the score of the game.
	 */
	public void updateScore() {
		this.score = moves * 10;
	}

	/**
	 * Determines whether or not two cards match based on attribute values.
	 * 
	 * @param card1 The first card being compared.
	 * @param card2 The second card being compared.
	 * @return True if the cards match in attribute value, false otherwise.
	 */
	public Boolean checkMatch(Card card1, Card card2) {
		return card1.checkMatch(card2);
	}

	/**
	 * Checks whether 3 cards match.
	 * 
	 * @param card1 The first Card being checked.
	 * @param card2 The second Card being checked.
	 * @param card3 The third Card being checked.
	 * 
	 * @return True if all three cards match, false otherwise.
	 */
	public Boolean checkMatch(Card card1, Card card2, Card card3) {
		return card1.checkMatch(card2, card3);
	}

	/**
	 * Prints a string representation of the MemoryGame board.
	 */
	public void printBoard() {
		System.out.println(board.toString());
	}

	/**
	 * A getter for the size of the MemoryGame board.
	 * 
	 * @return The size of the MemoryGame board.
	 */
	public int getSize() {
		return board.getSize();
	}
}
