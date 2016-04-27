/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *  echo 3 2 4 5 6 7
 *  Dependencies: StdIn.java, StdOut.java
 *
 *  A generic queue data type, implemented using a linked list. Each queue
 *  element is of type Item.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*--------------------------------------------------------------------------
 * Deque uses linkedList implementation to support adding and removing items
 * from either the front or the back of the data structure.
 * 
 * @author : Akshit Mahajan
 * @date : March 15, 2016
 * -------------------------------------------------------------------------
 */
public class Deque<Item> implements Iterable<Item> {
	private Node head;	// node pointer to start of linkedList
	private Node tail;  // node pointer to end of linkedList
	private int size;

	// helper linked list class
	private class Node {
		Item item;
		Node next;
		Node previous;
		
		Node(Item item) {
			this.item = item;
		}
	}

	/**
     * Initializes an empty Deque.
     */
	public Deque() {
		head = null;
	}

	/**
     * Is this deque empty?
     * @return true if this deque is empty; false otherwise
     */
	public boolean isEmpty() {
		return head == null ;
	}

	/**
     * Returns the number of items in the queue
     * @return the number of items in the queue
     */
	public int size() {
		return size;
	}

	/**
     * Adds the item at beginning of this queue.
     * @param item the item to add
     */
	public void addFirst(Item item) {
		if(item == null) {
			throw new java.lang.NullPointerException();
		}
		
		if(size == 0) {
			head = new Node(item);
			tail = head;
		}
		
		else {
			Node node = new Node(item);
			node.next = head;
			if(head != null) {
				head.previous = node;
			}
			head = node;
		}
		size++;
	}

	/**
     * Adds the item at end of this queue.
     * @param item the item to add
     */
	public void addLast(Item item) {
		if(item == null) {
			throw new java.lang.NullPointerException();
		}

		else {
			// empty linked list
			if(head == null) {
				head = new Node(item);
				tail = head;
			}
			
			else {
				/*Node tail = head;
				while(tail.next!=null){
					tail = tail.next;
				}*/
				
				Node node = new Node(item);
				tail.next = node;
				node.next = null;
				node.previous = tail;
				tail = tail.next;
			}
			size++;
		}
		
	}

	/**
     * Removes & returns the item from beginning of this queue.
     * @return item the item removed
     * 
     * Loitering of head node not being null
     * 
     * Update tail reference when the list is empty
     * Handle single node scenario
     */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		else {
			Item item = head.item;
			if (size == 1) {
				head = null;
				tail = null;
			}
			else {
				head = head.next;
				head.previous = null;
			}
			size--;
			return item;
		}
	}

	/**
     * Removes & returns the item from end of this queue.
     * @return item the item removed
     * 
     * 
     * Handle 1 node scenario and maintain previous
     */
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		else {
			Item item = tail.item;
			if (size == 1) {
				head = null;
				tail = null;
			}
			else {
				/*Node current = head;
				Node previous = head;
				while (current.next != null) {
					previous = current;
					current = current.next;
				}
				Item item = current.item;
				previous.next = null;
				current = null;*/
				tail = tail.previous;
				tail.next = null;
			}
			size--;
			return item;
		}
	}

	/**
     * Returns an iterator to this queue that iterates through the items in LIFO order.
     * @return an iterator to this queue that iterates through the items in LIFO order.
     */
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class DequeIterator implements Iterator<Item> {
		private Node itr;
		public DequeIterator() {
			itr = head;
		}

		@Override
		public boolean hasNext() {
			return itr != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = itr.item;
			itr = itr.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
     * Unit tests the <tt>Deque</tt> data type.
     */
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		while (!StdIn.isEmpty()) {
			int item = StdIn.readInt();
			deque.addLast(new Integer(item));
		}
		deque.removeFirst();
		deque.removeLast();
		Iterator<Integer> itr = deque.iterator();
		while(itr.hasNext()) {
			StdOut.print(itr.next()+"\n");
		}	
	}
}
