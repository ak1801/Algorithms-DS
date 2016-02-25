package com.week1.exercise;

public class WorstCaseComplexityLoops {

	public static void main(String[] args) {
		int sum = 0;
		int sum1 = 0;
		int sum0 = 0;
		/*for(int i=1; i<=10; i=i*2){
			sum++;
		}*/
		/*for(int i=1; i<=10*10; i=i*2){
			sum++;
			for(int j=0; j<i; j++){
				sum1++;
			}
		}*/
		int N=1000;
		//int sum = 0;
		/*
		for (int i = 1; i <= N; i++){
			sum0++;
		    for (int j = 1; j <= N*N; j++){
		    	sum++;
		        for (int k = 1; k <= j*j; k++)
		            sum1++;
		    }
		}*/
		
		for (int i = N; i > 0; i--){
			sum0++;
		    for (int j = 0; j < i; j++)
		        sum++;
		}
		System.out.println(sum0);
		System.out.println(sum);
		System.out.println(sum1);
	}

}
