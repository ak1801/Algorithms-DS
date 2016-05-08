package com.coursera.algorithm1.week1;

/*
 * QuickUnion is a lazy algorithm which ensures that union operation in Union find problem
 * is quick with O(1)
 */
public class QuickUnionUF {
	
	int[] array;
	
	public QuickUnionUF(int N){
		array = new int[N];
		for(int i=0; i<array.length; i++){
			array[i] = i;
		}
	}
	
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		array[i] = j;
	}
	
	public int root(int i){
	
		while(i!=array[i]){
			i = array[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q){
		return root(p)==root(q);
	}
}
	