package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.shopItem;

class shopItemTest {

	@Test
	void testConstructorAndGetters() {
		shopItem item = new shopItem("Item 1", 10);
		assertEquals("Item 1", item.getItemName());
		assertEquals(10, item.getPrice());
	}

	@Test
	void testEquals() {
		shopItem item1 = new shopItem("Item 1", 10);
		shopItem item2 = new shopItem("Item 1", 20);
		shopItem item3 = new shopItem("Item 2", 30);
		
		assertTrue(item1.equals(item1));
		
		assertTrue(item1.equals(item2));
		assertTrue(item2.equals(item1));
		
		assertTrue(item1.equals(item2));
		assertFalse(item2.equals(item3));
		assertFalse(item1.equals(item3));
		
		assertFalse(item1.equals(null));
		assertFalse(item1.equals("Item 1"));
		
		assertFalse(item1.equals(new shopItem("Different Item", 10)));
		assertTrue(item1.equals(new shopItem("Item 1", 20)));
	}
}
