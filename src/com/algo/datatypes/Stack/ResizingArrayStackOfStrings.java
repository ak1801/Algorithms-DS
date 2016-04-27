package com.algo.datatypes.Stack;
 import java.util.LinkedList;
/*
 * Doubles the array when it reaches the length
 */
public class ResizingArrayStackOfStrings {

	private String[] strArray;
	private int N=0;
	
	public ResizingArrayStackOfStrings(){
		strArray = new String[1];
	}
	
	public void push(String item){
		if(strArray.length==N)	resize(2 * strArray.length);
		strArray[N++] = item;
	}
	
	private void resize(int capacity){
		String[] copy = new String[capacity];			// init a copy array
		for(int i=0; i<strArray.length; i++){			// copy all the elements from main array to copy array
			copy[i] = strArray[i];
		}
		strArray = copy;								// change the reference of strArray to copy array
	}
	
	public String pop(){
		String s = strArray[--N];
		strArray[N] = null;
		if( N == strArray.length/4 )	resize(strArray.length/2);		// decrease the array to half when the elements are 1/4
		return s;
	}
}
