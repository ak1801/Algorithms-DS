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
			
			if(sum == 0) {
				if(indexMap.get(sum)!=null) {
					ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(i);
					list.add(i);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					indexMap.put(sum, list);
				}
			}

			if(hashing.get(sum)!=null) {
				
				ArrayList<Integer> elements = hashing.get(sum);
				
				for(int elem : elements) {
					
					if(indexMap.get(elem+1)!=null) {
						ArrayList<Integer> list = (ArrayList<Integer>) indexMap.get(elem+1);
						list.add(i);
					} else {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(i);
						indexMap.put(elem+1, list);
					}
					//indexMap.put(elem+1, i);
				}

				hashing.get(sum).add(i);
			} else {
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
		int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
		filterZeroSumSubArrays(arr);
		print();
	}
}