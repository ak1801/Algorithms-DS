package com.programming.problems;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class MaxRevenueProblem {
    
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(maxRevenue(arr, m, 0));
  }
 
  public static int maxRevenue(int rate[], int totalAvailableTickets, int soldTickets)
  {
    if (totalAvailableTickets == soldTickets)
    {
      return 0;
    }
    Arrays.sort(rate);
    int sum = 0;
    for (int index = rate.length - 1; index >= 1; index--)
    {
      while (rate[index - 1] <= rate[index] && soldTickets < totalAvailableTickets)
      {
        sum = sum + rate[index];
        rate[index]--;
        soldTickets++;
      }
      if (soldTickets == totalAvailableTickets)
        return sum;
    }
    return sum + maxRevenue(rate, totalAvailableTickets, soldTickets);
  }
}