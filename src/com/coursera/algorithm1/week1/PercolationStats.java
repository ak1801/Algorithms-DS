package com.coursera.algorithm1.week1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*----------------------------------------------------------------------------
 * This class performs Monte Carlo simulation 
 * to estimate the percolation threshold for a given grid of nodes.
 * It performs a number of computation experiments for percolation problem.
 * 
 * 
 * @author : Akshit Mahajan
 * @date : February 28, 2016
 * ----------------------------------------------------------------------------
 */
public class PercolationStats {

	private int T;								// number of Computation Experiments
	private static double[] thresholdArray;		// array to store threshold values for further analysis

	/*
	 *  Initializes the T (No of experiments to execute) & 
	 *  perform T independent experiments on an N-by-N grid.
	 *  
	 *  Store the threshold values captured from above experiments
	 *  
	 *  @param N size of grid
	 *  @param T number of experiments
	 */
	public PercolationStats(int N, int T) {
		
		if(N<=0 || T<=0){
			throw new IllegalArgumentException();
		}
		
		this.T = T;
		thresholdArray = new double[T];
		for (int x = 0; x < T; x++) {
			int opened = 0;										// keeps track of open sites
			Percolation percolationClient = new Percolation(N);
			while (!percolationClient.percolates()) {
				int row = StdRandom.uniform(1, N + 1);
				int col = StdRandom.uniform(1, N + 1);
				if(!percolationClient.isOpen(row, col)){
					percolationClient.open(row, col);
					opened++;
				}
			}
			thresholdArray[x] = (double) opened / (N * N);
		}
	}

	/*
	 * Returns mean of percolation threshold
	 * 
	 * @return mean of T threshold values
	 */
	public double mean() {
		return StdStats.mean(thresholdArray);
	}

	/*
	 * Returns standard deviation of percolation threshold
	 * 
	 * @return standard deviation
	 */
	public double stddev() {
		return StdStats.stddev(thresholdArray);
	}

	/*
	 * Returns low endpoint of 95% confidence interval
	 * 
	 * @return the low endpoint of 95% confidence interval
	 */
	public double confidenceLo() {
		double d = (1.96) * stddev() / Math.sqrt(T);
		return mean() - d;
	}

	/*
	 * Returns high endpoint of 95% confidence interval
	 * 
	 * @return the high endpoint of 95% confidence interval
	 */
	public double confidenceHi() {
		double d = (1.96) * stddev() / Math.sqrt(T);
		return mean() + d;
	}

	/*
	 * Test client to evaluate threshold values for N-by-N Grid
	 * and by running computation T times. Prints the values of
	 * Mean, Standard Deviation and 95% confidence interval
	 * 
	 * @param args[0] N
	 * @param args[1] T
	 */
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(N, T);

		System.out.println("mean                     = " + stats.mean());
		System.out.println("stddev                   = " + stats.stddev());
		System.out.println("95% confidence interval  = " + stats.confidenceLo()
				+ ", " + stats.confidenceHi());
	}
}
