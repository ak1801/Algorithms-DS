import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*---------------------------------------------------------
 * Percolation data type API. To model a percolation system.
 * 
 * @author : Akshit Mahajan
 * @date : February 28, 2016
 * --------------------------------------------------------
 */
public class Percolation {
	private int[][] grid;				// 2D array to store grid data
	private WeightedQuickUnionUF wquf;	// reference of WeightedQuickUnionFind
	private int rowSize;				// size of the grid's row
	private int N;						// number of sites.
	
	/*
	 * Initializes N-by-N grid, with all sites blocked
	 * 
	 * Sets Virtual Top and Virtual Bottom
	 * Complexity : N^2
	 */
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException("N cannot be less than or equal to 0");
		}
		grid = new int[N+1][N+1];
		rowSize = grid[0].length;
		wquf = new WeightedQuickUnionUF( ( N+1 ) * ( N+1 ) );
		this.N = N;
		
		if (N != 1){
			setVirtualTopAndBottom();
		}
	}	

	/*
	 * Sets Virtual Top and Virtual Bottom
	 */
	private void setVirtualTopAndBottom() {
		// virtual top
		for (int i = N + 2; i <= N + N + 1; i++) {
			wquf.union(0, i);
		}
		
		// virtual bottom
		for (int j = N * (N + 1) + 1; j <= N * (N + 1) + N; j++) {
			wquf.union(1, j);
		}
	}
	
	/*
	 * Validates if the row and column indices are in range or not
	 * 
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	private void validate(int row, int col) {
		if (row < 1 || row > N) {
			throw new IndexOutOfBoundsException("Index "+row+" should be between 1 & "+N);
		}
		
		if (col < 1 || col > N) {
			throw new IndexOutOfBoundsException("Index "+col+" should be between 1 & "+N);
		}
	}

	/*
	 * Checks if the system percolates or not & returns boolean
	 * 
	 * @return true if percolates, else false
	 */
	public boolean percolates() {
		return wquf.connected(0, 1);
	}
	
	/*
	 * Checks if site is open or not and returns boolean
	 * 
	 * @param row
	 * @param col
	 * @return true if site is open, else false
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	public boolean isOpen(int row, int col) {
		validate(row, col);
		if( grid[row][col] == 1) {
			return true;
		}
		else return false;
	}
	
	/*
	 * Checks if the site is open and is also a part of percolated path.
	 * 
	 * @param row
	 * @param col
	 * @return true if site is connected to top, else false
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	public boolean isFull(int row, int col) {
		validate(row, col);
		
		if( grid[row][col] == 1 && wquf.connected(0, xyTo1D ( row, col)) ) {
			return true;
		}
		else return false;
	}
	
	/* 
	 * Opens the connection at index row and column
	 * 
	 * @param row
	 * @param col
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	public void open(int row, int col) {
		
		validate(row, col);
		grid[row][col] = 1;
		if(N == 1) {
			setVirtualTopAndBottom();
		}
		else {
			checkAndConnectNeighbours(row, col);
		}
	}
	
	/*
	 * Makes a connection with left node(if open)
	 * 
	 * @param row
	 * @param col
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	private void connectLeft(int i, int j) {
		int p = xyTo1D(i, j);
		if(isOpen( i, j-1 )) {
			int q = xyTo1D(i, j-1);
			wquf.union(p, q);
		}
	}
	
	/*
	 * Makes a connection with right node(if open)
	 * 
	 * @param row
	 * @param col
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	private void connectRight(int i, int j) {
		int p = xyTo1D(i, j);
		if(isOpen(i, j+1)) {
			int q = xyTo1D(i, j+1);
			wquf.union(p, q);
		}
	}
	
	/*
	 * Makes a connection with top node(if open)
	 * 
	 * @param row
	 * @param col
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	private void connectTop(int i, int j) {
		int p = xyTo1D(i, j);
		if(isOpen(i-1, j)) {
			int q = xyTo1D(i-1, j);
			wquf.union(p, q);
		}
	}
	
	/*
	 * Makes a connection with bottom node(if open)
	 * 
	 * @param row
	 * @param col
	 * @throws IndexOutOfBoundsException unless 1<p<N
	 */
	private void connectBottom(int i, int j) {
		int p = xyTo1D(i, j);
		if(isOpen(i+1, j)) {
			int q = xyTo1D(i+1, j);
			wquf.union(p, q);
		}
	}
	
	/*
	 * Checks whether the 4 immediate nodes or neighbors of i(row) and j(column) are open or not &
	 * if open, makes the connection with corresponding neighbor
	 * 
	 * @param row represents row value in grid
	 * @param col represents column value in grid
	 */
	private void checkAndConnectNeighbours(int row, int col) {
		//int p = xyTo1D(row, col);
		//left corner
		if(row == 1 && col == 1) {
			connectRight(row, col);
			connectBottom(row, col);
			return;
		}
		
		//left bottom corner
		else if(row == rowSize-1 && col == 1) {
			connectRight(row, col);
			connectTop(row, col);
			return;
		}
		
		//upper right corner
		else if(row == 1 && col == rowSize-1) {
			connectLeft(row, col);
			connectBottom(row, col);
			return;
		}
		
		//bottom right corner
		else if(row == rowSize-1 && col == rowSize-1) {
			connectLeft(row, col);
			connectTop(row, col);
			return;
		}
		
		//first row
		else if(row == 1 && col > 1 && col < rowSize-1) {
			connectLeft(row, col);
			connectRight(row, col);
			connectBottom(row, col);
			return;
		}
		
		//first column
		else if(col == 1 && row > 1) {
			connectTop(row, col);
			connectRight(row, col);
			connectBottom(row, col);
			return;
		}
		
		//last row
		else if(row == rowSize-1 && col > 1){
			connectTop(row, col);
			connectLeft(row, col);
			connectRight(row, col);
			return;
		}
		//last column
		else if(col == rowSize-1 && row > 1){
			connectLeft(row, col);
			connectTop(row, col);
			connectBottom(row, col);
			return;
		}
		else{
			connectLeft(row, col);
			connectRight(row, col);
			connectTop(row, col);
			connectBottom(row, col);
			return;
		}
	}
	
	/*
	 * Returns 1D value for corresponding x(row) and y(column) of the Grid
	 * 
	 * @param row represents the x coordinate
	 * @param col represents the y coordinate
	 * @return 1D value for row and column
	 */
	private int xyTo1D(int row, int col) {
		return grid[0].length*(row) + (col);
	}
}