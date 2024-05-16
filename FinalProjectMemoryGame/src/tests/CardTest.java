/**
 * Tests the Card method
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package tests;

import model.Card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Board;

class CardTest {

	@Test
	public void testInit() {
		Card testCard = new Card("Red", "Square");
		assertEquals("Red", testCard.getType2());
		assertEquals("Square", testCard.getType1());
		assertEquals(false, testCard.getRevealed());
	}
	
	@Test
	public void testToggle() {
		Card testCard = new Card("A", "1");
		assertEquals(false, testCard.getRevealed());
		testCard.toggle();
		assertEquals(true, testCard.getRevealed());
	}
	
	@Test
	public void testMatch() {
		Card testCard = new Card("Red", "Square");
		Card testCard2 = new Card("Red", "Square");
		Card testCard3 = new Card("Green", "Circle");
		assertEquals(true, testCard.checkMatch(testCard));
		assertEquals(true, testCard.checkMatch(testCard2));
		assertEquals(false, testCard.checkMatch(testCard3));
		assertEquals(false, testCard2.checkMatch(testCard3));
	}
	
	@Test
	public void testAll() {
		Card testCard = new Card("Red", "Square");
		Card testCard2 = new Card("Red", "Square");
		Card testCard5 = new Card("Red", "Square");
		Card testCard3 = new Card("Green", "Circle");
		Card testCard4 = new Card("Red", "Circle");
		assertEquals(true, testCard.checkMatch(testCard2));
		assertEquals(false, testCard.checkMatch(testCard3));
		assertEquals(false, testCard2.checkMatch(testCard3));
		assertEquals(false, testCard.checkMatch(testCard4));
		testCard2.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
		testCard.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
		testCard.toggle();
		assertEquals(true, testCard.checkMatch(testCard2));
		assertEquals(true, testCard.checkMatch(testCard2, testCard5));
		assertEquals(false, testCard.checkMatch(testCard2, testCard3));
	}
	
	@Test
	public void testPower() {
		Card powerCard = new Card("POWER", "POWER");
		Card nonPowerCard = new Card("Color", "Shape");
		
		assertEquals(true, powerCard.isPower());
		assertFalse(nonPowerCard.isPower());
	}
	
	@Test
	public void testIsStar() {
		Card starCard = new Card("POWER", "Star");
        Card nonStarCard = new Card("POWER", "Shape");
        
        assertTrue(starCard.isStar());
        assertFalse(nonStarCard.isStar());
	}
	
	@Test
	public void testIsBomb() {
		Card bombCard = new Card("POWER", "Bomb");
        Card nonBombCard = new Card("POWER", "Shape");
        
        assertTrue(bombCard.isBomb());
        assertFalse(nonBombCard.isBomb());
	}
	
	@Test
	public void testIsLaser() {
		Card laserCard = new Card("POWER", "Laser");
        Card nonLaserCard = new Card("POWER", "Shape");
        
        assertTrue(laserCard.isLaser());
        assertFalse(nonLaserCard.isLaser());
	}
	
}
