package com.coursera.algorithm1.week2;
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

	private Item[] items = null;
	private int N = 0;
	
	public RandomizedQueue(){
		items = (Item[]) new Object[1]; 
	}
	
	/**
	 * Is this RandomizedQueue empty?
	 * 
	 * @return true if this RandomizedQueue is empty; false otherwise
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * Returns the number of items in the queue
	 * 
	 * @return the number of items in the queue
	 */
	public int size() {
		return N;
	}
	
	private int length() {
		return items.length;
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
		
		if(N == items.length) resize(2 * N);
		items[N++] = item;

		// logic to shuffle randomly
		int random = StdRandom.uniform(N);
		Item randomItem = items[random];
		items[random] = items[N-1];
		items[N-1] = randomItem;
		
		/*int index = StdRandom.uniform(length());
		while(itemArray[index] != null) {
			index = StdRandom.uniform(length());
		}
		items[index] = item;
		N++;
		*/
	}
	
	private void resize(int capacity) {

		Item[] copy = (Item[]) new Object[capacity];
		
		for(int i=0; i<N; i++) {
			copy[i] = items[i];
		}
		items = copy;
	}

	/**
	 * Removes & returns a random item from of this queue.
	 * 
	 * @return item the item removed
	 */
	public Item dequeue() {

		if(isEmpty()) throw new NoSuchElementException();

		Item item = items[N-1];
		items[N-1] = null;
		N--;
		
		if(N > 0 && N == items.length/4) resize(items.length/2);
		
		/*int index = StdRandom.uniform(length());
		while(items[index] == null) {
			index = StdRandom.uniform(length());
		}

		Item item = items[index];
		items[index] = null;
		N--;
		*/	
		return item;
	}

	/**
	 * Return (but do not remove) a random item in the queue
	 * 
	 * @return item a random item
	 */
	public Item sample() {
		
		if(isEmpty()) throw new NoSuchElementException();
		
		int index = StdRandom.uniform(N);
			
			/*while(items[index] == null) {
				index = StdRandom.uniform(length());
			}*/

		return items[index];
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
			
			if(N > 0 && items.length > 0) {
				temp = (Item[]) new Object[N];
				for(int i=0; i<N; i++) {
					temp[i] = items[i];
				}
				StdRandom.shuffle(temp);
			}
			//items = temp;
			
			//temp = null;
		}

		@Override
		public boolean hasNext() {
			if(N == 0 || temp.length == 0) {
				return false;
			}
			if(items != null) {
				return index < temp.length;
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