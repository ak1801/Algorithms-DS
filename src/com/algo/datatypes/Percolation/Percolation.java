package com.algo.datatypes.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * Percolation DataType - API
 */
public class Percolation {
	int[][] grid;
	WeightedQuickUnionUF uf;
	int size;
	int counter;
	int N;
	
	public Percolation(int N){
		//N*N+2
		grid = new int[N][N];
		size = grid[0].length;
		uf = new WeightedQuickUnionUF(N*N+2);
		this.N = N;
		
		for(int i=0; i<N; i++){
			uf.union(N*N, i);
		}
		for(int j=(N*N)-1; j>=(N*N)-N; j--){
			uf.union(N*N+1, j);
		}
	}	
	private void validate(int i, int j){
		int N = grid[0].length;
		if(i<0 || i>N){
			throw new IndexOutOfBoundsException("Index "+i+" should be between 0 & "+N);
		}
		
		if(j<0 || j>N){
			throw new IndexOutOfBoundsException("Index "+j+" should be between 0 & "+N);
		}
	}

	/*
	 * checks if a the system percolates or not!
	 */
	public boolean percolates(){
	  /*boolean percolates = false;
		for(int y=0; y<size; y++){
			int p = xyTo1D(0, y);
			for(int z=0; z<size; z++){
				int q = xyTo1D(size-1, z);
				if(uf.connected(p, q)){
					percolates=true;
				}
			}
		}
		return percolates;*/
		return uf.connected(N*N, N*N+1);
	}
	
	/*
	 * Is Open?
	 */
	public boolean isOpen(int i, int j){
		validate(i, j);
		if(grid[i][j]==1){
			return true;
		}
		else return false;
	}
	
	/*
	 * Is site open and is also a part of percolated path? 
	 */
	public boolean isFull(int i, int j){
		validate(i, j);
		if(grid[i][j]==1){
			return true;
		}
		else return false;
	}
	
	/* The open method does 3 things:
	 * 1. validate
	 * 2. mark the sight open
	 * 3. perform some sequence of WeightedQuickUnionUF operations that links the site 
	 * in question to its open neighbors.
	 * 
	 * Need to cover corner cases as well
	 */
	public void open(int i, int j){
		validate(i, j);
		if(!isOpen(i,j)){
			grid[i][j]=1;
			counter++;
		}
		checkAndConnectNeighbours(i, j);
	}
	
	private void connectLeft(int i, int j){
		int p = xyTo1D(i, j);
		if(isOpen(i, j-1)){
			int q = xyTo1D(i, j-1);
			uf.union(p, q);
		}
	}
	
	private void connectRight(int i, int j){
		int p = xyTo1D(i, j);
		if(isOpen(i, j+1)){
			int q = xyTo1D(i, j+1);
			uf.union(p, q);
		}
	}
	
	private void connectTop(int i, int j){
		int p = xyTo1D(i, j);
		if(isOpen(i-1, j)){
			int q = xyTo1D(i-1, j);
			uf.union(p, q);
		}
	}
	
	private void connectBottom(int i, int j){
		int p = xyTo1D(i, j);
		if(isOpen(i+1, j)){
			int q = xyTo1D(i+1, j);
			uf.union(p, q);
		}
	}
	
	private void checkAndConnectNeighbours(int i, int j){
		
		int p = xyTo1D(i, j);
		
		//left corner
		if(i==0 && j==0){
			connectRight(i, j);
			connectBottom(i, j);
			return;
		}
		
		//left bottom corner
		else if(i==size-1 && j==0){
			connectRight(i, j);
			connectTop(i, j);
			return;
		}
		
		//upper right corner
		else if(i==0 && j==size-1){
			connectLeft(i, j);
			connectBottom(i, j);
			return;
		}
		
		//bottom right corner
		else if(i==size-1 && j==size-1){
			connectLeft(i, j);
			connectTop(i, j);
			return;
		}
		
		//first row
		else if(i==0 && j>0 && j<size-1){
			connectLeft(i, j);
			connectRight(i, j);
			connectBottom(i, j);
			return;
		}
		
		//first column
		else if(j==0 && i>0){
			connectTop(i, j);
			connectRight(i, j);
			connectBottom(i, j);
			return;
		}
		
		//last row
		else if(i==size-1 && j>0){
			connectTop(i, j);
			connectLeft(i, j);
			connectRight(i, j);
			return;
		}
		//last column
		else if(j==size-1 && i>0){
			connectLeft(i, j);
			connectTop(i, j);
			connectBottom(i, j);
			return;
		}
		else{
			connectLeft(i, j);
			connectRight(i, j);
			connectTop(i, j);
			connectBottom(i, j);
			return;
		}
	}
	
	private int xyTo1D(int x, int y){
		//return grid[0].length*(x-1)+(y-1);
		return grid[0].length*(x)+(y);
	}
	
	public static void main(String... x){
		Percolation obj = new Percolation(4);
		obj.open(0, 1);
		obj.open(1, 1);
		obj.open(1, 2);
		obj.open(2, 2);
		obj.open(3, 2);
		
		//int p = obj.xyTo1D(1, 1);
		//int q = obj.xyTo1D(1, 2);
		
		//System.out.println(obj.uf.connected(p, q));
		System.out.println("Percolates : "+obj.percolates());
		System.out.println(obj.counter);
	}
}
