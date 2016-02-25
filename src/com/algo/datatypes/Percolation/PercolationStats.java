package com.algo.datatypes.Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	int T;
	int N;
	static double[] thresholdArray;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		this.T=T;
		this.N=N;
		thresholdArray = new double[T]; 
		for(int x=0; x<T; x++){
			Percolation percolationClient = new Percolation(N);
			while(!percolationClient.percolates()){
				int i = StdRandom.uniform(N);
				int j = StdRandom.uniform(N);
				percolationClient.open(i, j);
			}
			thresholdArray[x]=(double)percolationClient.counter/(N*N);
		}
	}     
	
	public double mean(){
		// sample mean of percolation threshold
		return StdStats.mean(thresholdArray);
	}
	   
	public double stddev(){
		// sample standard deviation of percolation threshold
		return StdStats.stddev(thresholdArray);
	}
	   
	public double confidenceLo(){
		// low  endpoint of 95% confidence interval
		double d = (1.96)*stddev()/Math.sqrt(T);
		return mean()-d;
	}
	   
	public double confidenceHi(){
		// high endpoint of 95% confidence interval
		double d = (1.96)*stddev()/Math.sqrt(T);
		return mean()+d;
	}

	public static void main(String[] args){
		int N = 200;//Integer.parseInt(args[0]);
		int T = 100;//Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(N, T);
		System.out.println("mean = "+stats.mean());
		System.out.println("stddev = "+stats.stddev());
		System.out.println("95% confidence interval = "+stats.confidenceLo()+","+stats.confidenceHi());
	}
}
