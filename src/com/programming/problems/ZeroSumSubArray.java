package com.programming.problems;

import java.util.*;
class ZeroSumSubArray{

	static HashMap<Integer, ArrayList<Integer>> hashing = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, List<Integer>> indexMap = new HashMap<Integer, List<Integer>>();

	public static void filterZeroSumSubArrays(int[] arr) {
		int sum = 0;
		
		for(int i=0; i<arr.length; i++) {
			sum+=arr[i];
			
			if(arr[i] == 0 ){
				if(indexMap.get(i)!=null) {
					ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(i);
					list.add(i);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					indexMap.put(i, list);
				}
			}

			if(hashing.get(sum)!=null) {
				
				if(sum == 0) {
					ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(0);
					list.add(i);
				}
				
				ArrayList<Integer> indexes = hashing.get(sum);
				
				for(int index : indexes) {
					
					if(indexMap.get(index+1)!=null) {
						ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(index+1);
						list.add(i);
					} else {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(i);
						indexMap.put(index+1, list);
					}
					//indexMap.put(elem+1, i);
				}

				hashing.get(sum).add(i);
			} else {
				if(sum == 0) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					indexMap.put(0, list);
				}
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				hashing.put(sum, list);
			}
		}
	}
	
	private static void print(){
		for(Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet()){
			
			ArrayList<Integer> list = (ArrayList<Integer>) entry.getValue();
			for(int i : list) {
				System.out.println("SubArray with zero sum is between indexes "+entry.getKey()+" - "+i);
			}
		}
	}
	
	public static void main(String args[]) {
		int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7, 1, -1};
		//int[] arr = {-1, -3, 4, -2, 2, 4};
		//int[] arr = {3, 4, -7, -3, -4, 7};
		filterZeroSumSubArrays(arr);
		print();
	}
}