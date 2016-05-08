package com.coursera.algorithm1.week2;

public class FixedCapacityStackOfStringsClient {

	public static void main(String[] args) {
		FixedCapacityStackOfStrings fss = new FixedCapacityStackOfStrings(5);
		fss.push("a");
		fss.push("b");
		fss.push("c");
		fss.push("d");
		fss.push(null);
		fss.push(null);
		System.out.println(fss.pop());
		System.out.println(fss.pop());
	}

}
