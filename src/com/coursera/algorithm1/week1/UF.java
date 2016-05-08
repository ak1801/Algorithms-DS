package com.coursera.algorithm1.week1;

/*
 * Union Find data type (API)
 */
public class UF {

	int[] array;
	
	/*
	 * initializes union-find data structure with N objects 0 to N-1.
	 * Creates an array and initializes the array with it's index value.
	 */
	public UF(int N){
		array = new int[N];
		for(int i=0; i<N-1; i++){
			array[i] = i;
		}
	}
	
	/*
	 * Adds a connection between p & q by copying value of index at q to value of index at p
	 * We also need to check if there are any other indexes with same value as p, and need to replace them too
	 * This operation is expensive. 
	 */
	void union(int p, int q){
		
	}
	
	/*
	 * Returns a boolean value
	 * Are p & q in the same component?
	 */
	boolean connected(int p, int q){
		
		boolean _isConnected = false;
		return _isConnected;
	}
	
	/*
	 * component identifier for p (0 to N-1)
	 */
	int find(int p){
		
		return 0;
	}
	
	/*
	 * return the number of components;
	 */
	int count(){
		
		return 1;
	}
}
