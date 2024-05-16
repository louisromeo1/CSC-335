package tests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import model.AccountCollection;
import model.Accounts;

public class AccountCollectionTest {
	
	@Test
	public void testAdd() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		
		accountCollection.add(account1);
		assertEquals(1, accountCollection.getSize());
		assertTrue(accountCollection.containsAccount(account1));
	}
	
	@Test
	public void testGetSize() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		assertEquals(0, accountCollection.getSize());
		
		accountCollection.add(account1);
		assertEquals(1, accountCollection.getSize());
		
		accountCollection.add(account2);
		assertEquals(2, accountCollection.getSize());
	}
	
	@Test
	public void testGetElement() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		
		assertEquals(account1, accountCollection.getElement(0));
		assertEquals(account2, accountCollection.getElement(1));
	}
	
	@Test
	public void testGetArray() {
		AccountCollection accountCollection = new AccountCollection();
		ArrayList<Accounts> array = accountCollection.getArray();
		
		assertTrue(array.isEmpty());
	}
	
	@Test
	public void testGetAccount() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		
		assertEquals(account1, accountCollection.getAccount("Mustafa", "Mustafa123"));
		assertEquals(account2, accountCollection.getAccount("Seth", "Seth123"));
		
		assertNull(accountCollection.getAccount("Guest1", "Guest123"));
	}
	
	@Test
	public void testContainName() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		
		assertTrue(accountCollection.containsName("Mustafa"));
		assertFalse(accountCollection.containsName("Guest1"));
	}
	
	@Test
	public void testContainsAccount() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		accountCollection.add(account1);
	
		assertTrue(accountCollection.containsAccount(account1));
		assertFalse(accountCollection.containsAccount(account2));
	}
	
	@Test
	public void testClear() {
		AccountCollection accountCollection = new AccountCollection();
		Accounts account1 = new Accounts("Mustafa", "Mustafa123");
		Accounts account2 = new Accounts("Seth", "Seth123");
		
		accountCollection.add(account1);
		accountCollection.add(account2);
		
		accountCollection.clear();
		assertEquals(0, accountCollection.getSize());
		
		assertFalse(accountCollection.containsAccount(account1));
		assertFalse(accountCollection.containsAccount(account2));
	}
	
}
