package com.coursera.algorithm1.week1;

import java.util.Scanner;


public class DynamicConnectivityClient {

	public static void main(String... x){
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		/*QuickFindUF uf = new QuickFindUF(N);
		uf.printArray();
		System.out.println();
		
		//8-7 9-6 2-7 9-0 0-4 4-5 
		uf.union(7,9);
		uf.union(9,6);
		uf.union(0,8);
		uf.union(1,5);
		uf.union(7,0);
		uf.union(4,5);
		uf.printArray();*/
		
			
		//ques - 2-0 3-9 7-1 9-1 0-4 2-8 5-4 8-3 9-6 
		//ans - 2 7 2 2 2 2 2 3 2 3
		
		WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(N);
		wuf.union(2, 0);
		wuf.union(3, 9);
		wuf.union(7, 1);
		wuf.union(9, 1);
		wuf.union(0, 4);
		wuf.union(2, 8);
		wuf.union(5, 4);
		wuf.union(8, 3);
		wuf.union(9, 6);

		wuf.printArray();
		System.out.println();
		System.out.println(wuf.count());
	}
}
