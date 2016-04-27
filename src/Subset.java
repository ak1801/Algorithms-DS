/******************************************************************************
 *  Compilation:  javac Subset.java
 *  Execution:
 *  % echo A B C D E F G H I | java Subset 3 
 *  % echo AA BB BB BB BB BB CC CC | java Subset 8
 *  
 *  Dependencies: StdIn.java, StdOut.java
 *
 *  Subset.java takes a command-line integer k; reads in a sequence of N strings 
 *  from standard input using StdIn.readString(); and prints out exactly k of them, 
 *  uniformly at random. Each item from the sequence can be printed out at most once. 
 *  Assumption 0 ≤ k ≤ N, where N is the number of string on standard input.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*--------------------------------------------------------------------------
 * Subset is a test client which uses RandomizedQueue Data Structure.
 * 
 * @author : Akshit Mahajan
 * @date : March 15, 2016
 * -------------------------------------------------------------------------
 */
public class Subset {
	
	/**
     * Test client
     * 
     * @param command-line argument
     */
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);	// takes a command-line integer k
		int count = 0;	// keeps the count of string items added to the queue
		
		RandomizedQueue<String> randomQ = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			randomQ.enqueue(item);
			count++;
		}
		
		if (k < 0 || k > count) {
			throw new IllegalArgumentException();
		}
		
		for(int j = 0; j < k; j++) {
			StdOut.print(randomQ.dequeue()+'\n');
		}
	}
}
