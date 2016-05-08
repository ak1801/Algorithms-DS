package com.coursera.algorithm1.week2;

public class FixedCapacityStackOfStrings {

	private String[] array;
	private int N = 0;
	
	public FixedCapacityStackOfStrings(int capacity) {
		array = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(String item){
		array[N++] = item;
	}
	
	public String pop(){
		
		if(isEmpty()){
			throw new IllegalAccessError();
		}
		else{
			String item = array[--N];
			array[N] = null;
			return item;
		}
	}
}
