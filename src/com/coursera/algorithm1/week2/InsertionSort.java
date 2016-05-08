package com.coursera.algorithm1.week2;

public class InsertionSort {

	public static boolean less(Comparable p, Comparable q) {
		return p.compareTo(q) < 0; 
	}
	
	public static void exchange(Comparable a[], int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static void sort(Comparable a[]) {
		for(int i = 0; i < a.length;  i++) {
			for(int j = i; j > 0; j--) {
				if(less(a[j], a[j-1])) {
					exchange(a, j, j-1);
				}else break;
			}
		}
	}
	
	public static void main(String[] args) {
		Comparable[] a = new Comparable[]{100 ,3, 15, 6, 2, 0, 4, 7, 5, 8,1};
		sort(a);
		
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i]+" ");
		}

	}

}
