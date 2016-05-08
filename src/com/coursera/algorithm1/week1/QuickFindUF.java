package com.coursera.algorithm1.week1;

/*
 * QuickFind is an eager approach/data structure to solve Union-Find problem.
 * This is inefficient in terms of time complexity - O(N^2) 
 */
public class QuickFindUF {

	int[] array;
	
	/*
	 * initializes union-find data structure with N objects 0 to N-1.
	 * Creates an array and initializes the array with it's index value.
	 */
	public QuickFindUF(int N){
		array = new int[N];
		for(int i=0; i<N; i++){
			array[i] = i;
		}
	}
	
	/*
	 * Adds a connection between p & q by copying value of index at q to value of index at p
	 * We also need to check if there are any other indexes with same value as p, and need to replace them too
	 * This operation is expensive. 
	 */
	public void union(int p, int q){
		int pid = array[p];
		int qid = array[q];
		
		for(int i=0; i<array.length; i++){
			if(array[i]==pid){
				array[i]=qid;
			}
		}
		printArray();
		System.out.println();
	}
	
	/*
	 * Returns a boolean value
	 * Are p & q in the same component?
	 */
	boolean connected(int p, int q){
		return array[p]==array[q];
	}
	
	/*
	 * component identifier for p (0 to N-1)
	 */
	int find(int p){
		return array[p];
	}
	
	/*
	 * return the number of components;
	 * TODO
	 */
	public int count(){
		int count = 0;
		for(int i=0; i<array.length; i++){
			
		}
		return count;
	}
	
	public void printArray(){
		for(int i : this.array){
			System.out.print(i+" ");
		}
	}
}
