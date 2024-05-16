package tests;

import org.junit.jupiter.api.Test;

import model.Accounts;
import model.MemoryGame;
import model.shopItem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AccountsTest {

	@Test
	public void testConstructor() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		assertEquals("Mustafa", account.getUsername());
        assertEquals("Mustafa123", account.getPassword());
        
        assertEquals(0, account.getGamesPlayed());
        
        assertEquals(-1, account.get2Normal().intValue());
        assertEquals(-1, account.get3Odd().intValue());
        assertEquals(-1, account.get4Normal().intValue());
        assertEquals(-1, account.get5Odd().intValue());
        assertEquals(-1, account.get6Normal().intValue());
	}
	
	@Test
	public void testSetNewBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2, 0);
		assertEquals(100, account.get2Normal().intValue());
		
		account.setNewBestScore(80, 3, 1);
		assertEquals(80, account.get3Odd().intValue());
		
		account.setNewBestScore(90, 3, 2);
		assertEquals(90, account.get3ThreeKind().intValue());
		
		account.setNewBestScore(50, 3, 3);
		assertEquals(50, account.get3Power().intValue());
		
		account.setNewBestScore(60, 4, 0);
		assertEquals(60, account.get4Normal().intValue());
		
		account.setNewBestScore(70, 4, 3);
		assertEquals(70, account.get4Power().intValue());
		
		account.setNewBestScore(40, 5, 1);
		assertEquals(40, account.get5Odd().intValue());
		
		account.setNewBestScore(120, 5, 3);
		assertEquals(120, account.get5Power().intValue());
		
		account.setNewBestScore(20, 6, 0);
		assertEquals(20, account.get6Normal().intValue());
		
		account.setNewBestScore(200, 6, 2);
		assertEquals(200, account.get6ThreeKind().intValue());
		
		account.setNewBestScore(40, 6, 3);
		assertEquals(40, account.get6Power().intValue());
	}
	
	@Test
	public void testSetGetStreak() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewStreak(3, 2);
		assertEquals(3, account.get2Streak());
		assertEquals(3, account.getBestStreak(2));
		
		account.setNewStreak(4, 3);
		assertEquals(4, account.get3Streak());
		assertEquals(4, account.getBestStreak(3));
		
		account.setNewStreak(5, 4);
		assertEquals(5, account.get4Streak());
		assertEquals(5, account.getBestStreak(4));
		
		account.setNewStreak(6, 5);
		assertEquals(6, account.get5Streak());
		assertEquals(6, account.getBestStreak(5));
		
		account.setNewStreak(7, 6);
		assertEquals(7, account.get6Streak());
		assertEquals(7, account.getBestStreak(6));
		
		assertEquals(-1, account.getBestStreak(7));
	}
	
	@Test
	public void testNewGame() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		MemoryGame newGame = new MemoryGame(0, 2, 0, account);
		newGame.initGame();
		
		account.setNewGame(newGame);
		assertEquals(newGame, account.getCurrGame());
		
		account.endCurrGame();
		assertEquals(null, account.getCurrGame());
	}
	
	@Test
	public void testIncrementGamesPlayed () {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.incrementGamesPlayed();
		assertEquals(1, account.getGamesPlayed());
	}
	
	@Test
	public void testGetBestScore() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		account.setNewBestScore(100, 2, 0);
		assertEquals(100, account.getBestScore(2, 0));
		
		account.setNewBestScore(150, 3, 1);
		assertEquals(150, account.getBestScore(3, 1));
		
		account.setNewBestScore(160, 3, 2);
		assertEquals(160, account.getBestScore(3, 2));
		
		account.setNewBestScore(170, 3, 3);
		assertEquals(170, account.getBestScore(3, 3));
		
		account.setNewBestScore(200, 4, 0);
		assertEquals(200, account.getBestScore(4, 0));
		
		account.setNewBestScore(210, 4, 3);
		assertEquals(210, account.getBestScore(4, 3));
		
		account.setNewBestScore(250, 5, 1);
		assertEquals(250, account.getBestScore(5, 1));
		
		account.setNewBestScore(240, 5, 3);
		assertEquals(240, account.getBestScore(5, 3));
		
		account.setNewBestScore(300, 6, 0);
		assertEquals(300, account.getBestScore(6, 0));
		
		account.setNewBestScore(310, 6, 2);
		assertEquals(310, account.getBestScore(6, 2));
		
		account.setNewBestScore(320, 6, 3);
		assertEquals(320, account.getBestScore(6, 3));
		
		assertEquals(-2, account.getBestScore(1, 0));
	}
	
	@Test
	public void testGetBestScoreNotPlayedMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-1, account.getBestScore(4, 0));
	}
	
	@Test
	public void testGetBestScoreInvalidMode() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		
		assertEquals(-2, account.getBestScore(7, 0));
	}
	
	@Test 
	public void testSetBalance() {
		 Accounts account = new Accounts("username", "password");
	     assertEquals(0, account.getBalance());
	        
	     account.setBalance(100);
	     assertEquals(100, account.getBalance());
	        
	     account.setBalance(-50); // Test negative balance
	     assertEquals(-50, account.getBalance());
	}
	
	
	@Test
	public void testBalancemanipulation() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
        assertEquals(0, account.getBalance());
        account.incrementBalance(100);
        assertEquals(100, account.getBalance());
        account.deductBalance(50);
        assertEquals(50, account.getBalance());
	}
	
	@Test
	public void testUnlockedItems() {
		 Accounts account = new Accounts("Mustafa", "Mustafa123");
	     shopItem item = new shopItem("Item 1", 10);
	     assertFalse(account.hasUnlockedItem(item));
	     account.addUnlockedItem(item);
	     assertTrue(account.hasUnlockedItem(item));
	}
	
	
	@Test
	public void testHasUnlockedItem() {
		Accounts account = new Accounts("Mustafa", "Mustafa123");
		shopItem item1 = new shopItem("Item 1", 10);
        shopItem item2 = new shopItem("Item 2", 20);
        shopItem item3 = new shopItem("Item 3", 30);
        
        assertFalse(account.hasUnlockedItem(item1));

        account.addUnlockedItem(item1);
        account.addUnlockedItem(item2);
        assertTrue(account.hasUnlockedItem(item1));

        assertFalse(account.hasUnlockedItem(item3));
	}
	
	@Test
	public void testGetUnlockedItems() {
		Accounts account = new Accounts("username", "password");
        shopItem item1 = new shopItem("Item 1", 10);
        shopItem item2 = new shopItem("Item 2", 20);

        account.addUnlockedItem(item1);
        account.addUnlockedItem(item2);

        List<shopItem> unlockedItems = account.getUnlockedItems();

        assertEquals(2, unlockedItems.size());
        assertTrue(unlockedItems.contains(item1));
        assertTrue(unlockedItems.contains(item2));
	}
	
	@Test
	public void testEquals() {
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Mustafa", "Mustafa123");
		Accounts account3 = new Accounts("Seth", "Seth123");
		
		assertTrue(account1.equals(account2));
		assertFalse(account1.equals(account3));
	}
}
