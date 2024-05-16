/*
 * Louis Romeo
 * CSC 335 Assignment 1
 * 1/12/2023
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class MySetTest {
	 @Test
	    public void testAdd() {
	        MySet<Integer> set = new MySet<>();
	        assertTrue(set.isEmpty());
	        assertEquals(0, set.size());

	        set.add(1);
	        set.add(2);

	        assertFalse(set.isEmpty());
	        assertEquals(2, set.size());
	        assertTrue(set.contains(1));
	        assertTrue(set.contains(2));
	    }


	    @Test
	    public void testRemove() {
	        MySet<String> set = new MySet<>();
	        set.add("apple");
	        set.add("banana");
	        set.add("orange");

	        assertEquals(3, set.size());

	        set.remove("banana");
	        assertFalse(set.contains("banana"));
	        assertEquals(2, set.size());

	        set.remove("grape");  // Removing non-existing element
	        assertEquals(2, set.size());
	    }

	    @Test
	    public void testContains() {
	        MySet<Character> set = new MySet<>();
	        set.add('a');
	        set.add('b');

	        assertTrue(set.contains('a'));
	        assertTrue(set.contains('b'));
	        assertFalse(set.contains('c'));
	    }

	    @Test
	    public void testIsEmpty() {
	        MySet<Double> set = new MySet<>();
	        assertTrue(set.isEmpty());

	        set.add(3.14);
	        assertFalse(set.isEmpty());

	        set.remove(3.14);
	        assertTrue(set.isEmpty());
	    }
	    
	    // Given code
	    @Test
	    
	    public void testIterator() {

	      MySet<String> set = new MySet<>();

	      assertTrue(set.add("one"));

	      assertTrue(set.add("two"));

	      assertTrue(set.add("three"));

	      assertTrue(set.add("four"));
	      assertFalse(set.add("four"));  // "four" already exists

	     

	      String allElements = "";

	      Iterator<String> itr = set.iterator();

	      while(itr.hasNext()) {

	        allElements += itr.next() + "_";

	      }
	    }
	    
	    
	    @Test
	    public void testIteratorNoElements() {
	        MySet<String> set = new MySet<>();
	        Iterator<String> iterator = set.iterator();
	        assertFalse(iterator.hasNext());
	        assertThrows(java.util.NoSuchElementException.class, iterator::next);
	    }

	    @Test
	    public void testRemoveNonExistingElement() {
	        MySet<String> set = new MySet<>();
	        assertFalse(set.remove("nonexistent"));
	        assertEquals(0, set.size());
	    }

	    @Test
	    public void testIteratorNextNoElements() {
	        MySet<String> set = new MySet<>();
	        Iterator<String> iterator = set.iterator();

	        assertFalse(iterator.hasNext());

	        // Ensure that calling next() throw exception
	        assertThrows(java.util.NoSuchElementException.class, iterator::next);
	    }

	    @Test
	    public void testIteratorNextEmptySet() {
	        MySet<String> set = new MySet<>();
	        Iterator<String> iterator = set.iterator();

	        assertFalse(iterator.hasNext());
	        
	        // Calling next() when hasNext() is false throws NoSuchElementException
	        assertThrows(java.util.NoSuchElementException.class, iterator::next);
	    }    	
}
