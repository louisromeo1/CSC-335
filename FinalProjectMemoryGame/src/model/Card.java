package model;

/**
 * Card class to represent the cards in the memory game
 * 
 * @author Kyle Myint
 */
public class Card implements java.io.Serializable {
	// Characteristics of card to differentiate
	private String type1; // was shape
	private String type2; // was color
	private Boolean revealed;

	/**
	 * The constructor for Card.
	 * 
	 * @param type2 The String representing type2 charactarization for the card.
	 * @param type1 The String representing type2 charactarization for the card.
	 */
	public Card(String type2, String type1) {
		this.type1 = type1;
		this.type2 = type2;
		revealed = false;
	}

	/**
	 * Toggles revelaed status of card
	 */
	public void toggle() {
		if (revealed) {
			revealed = false;
		} else {
			revealed = true;
		}
	}

	/**
	 * Checks if a card matches another
	 * 
	 * @param other - The other card to be checked against
	 * 
	 * @return - Boolean on if the cards match
	 */
	public Boolean checkMatch(Card other) {
		if (this.type2 == other.getType2() && this.type1 == other.getType1()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if this card matches with two other cards.
	 * 
	 * @param other1 The first other card being compared to.
	 * @param other2 The second other card being compared to.
	 * 
	 * @return True if the three cards match, false otherwise.
	 */
	public Boolean checkMatch(Card other1, Card other2) {
		if (this.type2 == other1.getType2() && this.type2 == other2.getType2() && this.type1 == other1.getType1()
				&& this.type1 == other2.getType1()) {
			return true;
		}
		return false;
	}

	/**
	 * A getter for type2 of the Card.
	 * 
	 * @return The type2 of the card.
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * A getter for the type1 on the Card.
	 * 
	 * @return The type1 on the card.
	 */
	public String getType1() {
		return type1;
	}

	/**
	 * Returns whether or not the card is currently revealed.
	 * 
	 * @return True if card is revealed now, false otherwise.
	 */
	public Boolean getRevealed() {
		return revealed;
	}

	/**
	 * Returns whether or not the card is a power card.
	 * 
	 * @return True if this Card is a power card, false otherwise.
	 */
	public Boolean isPower() {
		return this.type2 == "POWER";
	}

	/**
	 * Returns whether or not this card is a Star card.
	 * 
	 * @return True if this is a Star, false otherwise.
	 */
	public Boolean isStar() {
		return this.type2 == "POWER" && this.type1 == "Star";
	}

	/**
	 * Returns whether or not this card is a Bomb card.
	 * 
	 * @return True if this is a Bomb, false otherwise.
	 */
	public Boolean isBomb() {
		return this.type2 == "POWER" && this.type1 == "Bomb";
	}

	/**
	 * Returns whether or not this card is a Laser card.
	 * 
	 * @return True if this is a Laser, false otherwise.
	 */
	public Boolean isLaser() {
		return this.type2 == "POWER" && this.type1 == "Laser";
	}
}
