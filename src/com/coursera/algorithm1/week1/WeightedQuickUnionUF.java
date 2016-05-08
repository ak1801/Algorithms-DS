package com.coursera.algorithm1.week1;

/*
 * Weighted Quick Union with path compression
 */
public class WeightedQuickUnionUF {

	private int[] array;
	private int[] size;
	private int count;
	
	public WeightedQuickUnionUF(int N){
		count=N;
		array = new int[N];
		size = new int[N];
		for(int i=0; i<array.length; i++){
			array[i] = i;
			size[i] = 1;
		}
	}	
	
	public int root(int i){
		validate(i);
		while(i!=array[i]){
			i = array[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q){
		return root(p)==root(q);
	}
	
	public int count(){
		return count;
	}
	
	public void union(int p, int q){
		int rootP = root(p);
		int rootQ = root(q);
		
		if(rootP==rootQ) return;
		
		if(size[rootP]<size[rootQ]){
			//first tree with rootP comes under second
			array[rootP]=rootQ;
			size[rootQ] += size[rootP];
		}
		else{
			//second tree comes under first
			array[rootQ]=rootP;
			size[rootP] += size[rootQ];
		}
		count--;
	}
	
	private void validate(int p){
		int N = array.length;
		if(p<0 || p>N){
			throw new IndexOutOfBoundsException("Index "+p+" is not between 0 & "+N);
		}
	}
	
	public void printArray(){
		for(int i : this.array){
			System.out.print(i+" ");
		}
	}
}
