/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
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
import edu.princeton.cs.algs4.StdRandom;

/*--------------------------------------------------------------------------
 * RandomizedQueue uses linkedList implementation & is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random from items in the data structure
 * 
 * @author : Akshit Mahajan
 * @date : March 15, 2016
 * -------------------------------------------------------------------------
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] itemArray = null;
	private int size = 0;
	
	public RandomizedQueue(){
		itemArray = (Item[]) new Object[1]; 
	}
	
	/**
	 * Is this RandomizedQueue empty?
	 * 
	 * @return true if this RandomizedQueue is empty; false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of items in the queue
	 * 
	 * @return the number of items in the queue
	 */
	public int size() {
		return size;
	}
	
	private int length() {
		return itemArray.length;
	}
	
	/**
	 * Adds the item to the queue.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void enqueue(Item item) {
		
		if(item == null) {
			throw new NullPointerException();
		}
		
		if(size() > 0 && size() == length()) {
			resize(2 * length());
		}
		
		int index = StdRandom.uniform(length());
		
		while(itemArray[index] != null) {
			index = StdRandom.uniform(length());
		}
		
		itemArray[index] = item;
		size++;
	}
	
	private void resize(int capacity) {

		Item[] copy = (Item[]) new Object[capacity];
		
		int j=0;
		for(int i=0; i<length(); j++,i++) {
			if(itemArray[i]!=null) {
				copy[j] = itemArray[i];
			}
			//else continue;
		}
		itemArray = copy;
	}

	/**
	 * Removes & returns a random item from of this queue.
	 * 
	 * @return item the item removed
	 */
	public Item dequeue() {

		if (size() > 0) {
			
			if(size() == length()/4) resize(length()/2);	
			int index = StdRandom.uniform(length());
			
			while(itemArray[index] == null) {
				index = StdRandom.uniform(length());
			}

			Item item = itemArray[index];
			itemArray[index] = null;
			
			size--;
			return item;
			
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Return (but do not remove) a random item in the queue
	 * 
	 * @return item a random item
	 */
	public Item sample() {
		
		if (size() > 0) {
			int index = StdRandom.uniform(length());
			
			while(itemArray[index] == null) {
				index = StdRandom.uniform(length());
			}

			return itemArray[index];
		}
		else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Return an independent iterator over items in random order that iterates
	 * through the items in LIFO order.
	 * 
	 * @return an independent iterator over items in random order that iterates
	 *         through the items in LIFO order.
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	// an independent iterator over items in razndom order, doesn't implement
	// remove() since it's optional
	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item temp[];
		private int index = 0;

		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			
			if(itemArray.length > 0) {
				int j=0;
				for(int i=0; i<length(); j++,i++) {
					if(itemArray[i]!=null) {
						temp[j] = itemArray[i];
					}
				}
				itemArray = temp;
				StdRandom.shuffle(itemArray);
			}
		}

		@Override
		public boolean hasNext() {
			if(size() == 0 || length() == 0) {
				return false;
			}
			if(itemArray != null) {
				return index < itemArray.length;
			}
			return false;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();

			return temp[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * Unit tests the <tt>RandomizedQueue</tt> data type.
	 */
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		
		
		while (!StdIn.isEmpty()) {
			int item = StdIn.readInt();
			rq.enqueue(new Integer(item));
		}

		StdOut.print("sample " + rq.sample() + "\n");
		StdOut.print("removed " + rq.dequeue() + "\n");

		Iterator<Integer> itr = rq.iterator();
		while (itr.hasNext()) {
			StdOut.print(itr.next() + "\n");
		}
	}
}