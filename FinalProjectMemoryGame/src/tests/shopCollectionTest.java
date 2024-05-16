package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.shopCollection;
import model.shopItem;

class shopCollectionTest {

	@Test
	public void testAddAndGetElement() {
		shopCollection shopCollection = new shopCollection();
		shopItem item1 = new shopItem("Item 1", 10);
		
		shopCollection.add(item1);
		assertEquals(item1, shopCollection.getElement(0));
	}
	
	@Test
	public void testGetSize() {
		shopCollection shopCollection = new shopCollection();
		shopItem item1 = new shopItem("Item 1", 10);
		shopItem item2 = new shopItem("Item 2", 20);
		
		shopCollection.add(item1);
		shopCollection.add(item2);
		
		assertEquals(2, shopCollection.getSize());
	}
	
	@Test
	public void testGetArrat() {
		shopCollection shopCollection = new shopCollection();
		shopItem item1 = new shopItem("Item 1", 10);
		shopCollection.add(item1);

		ArrayList<shopItem> array = shopCollection.getArray();
		assertEquals(1, array.size());
		assertEquals(item1, array.get(0));
	}
	
	@Test
	public void testClear() {
		shopCollection shopCollection = new shopCollection();
		shopItem item1 = new shopItem("Item 1", 10);
		
		shopCollection.add(item1);
		assertEquals(1, shopCollection.getSize());
		
		shopCollection.clear();
		assertEquals(0, shopCollection.getSize());
	}

}
