/*
 * Louis Romeo
 * CSC 335 Assignment 1
 * 1/12/2023
 */

import java.util.Iterator;

public class MySet<T> implements GenericSet<T>, Iterable<T> {

	private Node head;
    private int size;
	
	public MySet () {
		head = null;
        size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(T element) {
		if (!contains(element)) {
            Node newNode = new Node(element);
            newNode.next = head;
            head = newNode;
            size++;
            return true;
        }
        return false;
	}

	@Override
	public boolean contains(T element) {
	      Node current = head;
	      while (current != null) {
	            if (current.data.equals(element)) {
	                return true;
	            }
	            current = current.next;
	        }

	        return false;
	}

	@Override
	public boolean remove(T element) {
		  Node current = head;
	        Node prev = null;
	        while (current != null) {
	            if (current.data.equals(element)) {
	                if (prev == null) {
	                    head = current.next;
	                } else {
	                    prev.next = current.next;
	                }
	                size--;
	                return true;
	            }
	            prev = current;
	            current = current.next;
	}
	        return false;     
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SetIter();
	  }
	
	 private class SetIter implements Iterator<T> {
		 
		 private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public T next() {
			if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
	}
	
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    } 
}
