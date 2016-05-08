package com.coursera.algorithm1.week2;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStringsTestClient {

	public static void main(String[] args) {

		StackOfStrings stack = new StackOfStrings();
		
		Scanner in = new Scanner(System.in);
		
		while(!StdIn.isEmpty()){
			
			String s = StdIn.readString();
			//String s = in.nextLine();
			if (s.equals("-")) StdOut.print(stack.pop());
			else stack.push(s);
		}
	}

}
