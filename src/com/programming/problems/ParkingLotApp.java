package com.programming.problems;
/******************************************************************************
 *  Compilation:  javac ParkingLotApp.java
 *  Execution:    java ParkingLotApp
 *  Dependencies: None
 *******************************************************************************/

import java.util.HashMap;
import java.util.Random;

/**
 * The ParkingLotApp program implements an application that spots and prints a
 * single Blue colored car in a lot full of red colored cars in a Parking Lot
 * 
 * Assumptions : The parking lot contains all Red colored cars and only 1 single
 * Blue colored car placed at random space in the lot. The parking lot is full.
 * Lot has 10 floors named A to J with 6 rows (1-6) and 9 cars (1-9) in each
 * rows
 * 
 * 0 represents Red colored car in array & 1 represents Blue colored car in array
 * 
 * @author Akshit Mahajan
 * @date March 06, 2016
 *
 */
public class ParkingLotApp {

	private int[][][] parkingLot; // three dimension array representing the
									// parking lot
	private int floors, rows, cars; // variables representing
									// parameters/dimensions of parking lot
	private Random random; // instance of java.util.Random class
	private HashMap<Integer, Character> floorMap; // hashmap which represents
													// floor mapping

	/**
	 * This Parameterized constructor prepares data to be used in this problem.
	 * Initializes the parking lot with all Red colored cars. Randomly places
	 * Blue colored car in the lot. Maps floors with alphabets using floorMap
	 * 
	 * @param floors
	 * @param rows
	 * @param cars
	 * @exception IllegalArgumentException
	 */
	public ParkingLotApp(int floors, int rows, int cars) {

		if (rows < 1 || rows > 6 || cars < 1 || cars > 9) {
			throw new IllegalArgumentException();
		}

		this.floors = floors;
		this.rows = rows;
		this.cars = cars;

		parkingLot = new int[floors][rows][cars];
		random = new Random();

		int floorIndex = genRandomInt(0, floors - 1);
		int rowIndex = genRandomInt(0, rows - 1);
		int colIndex = genRandomInt(0, cars - 1);

		parkingLot[floorIndex][rowIndex][colIndex] = 1;

		floorMap = new HashMap<Integer, Character>();
		floorMap.put(0, 'A');
		floorMap.put(1, 'B');
		floorMap.put(2, 'C');
		floorMap.put(3, 'D');
		floorMap.put(4, 'E');
		floorMap.put(5, 'F');
		floorMap.put(6, 'G');
		floorMap.put(7, 'H');
		floorMap.put(8, 'I');
		floorMap.put(9, 'J');
	}

	/**
	 * Traverse through the parking lot and finds index/lot number of Blue car
	 * represented by 1
	 * 
	 * @return String index
	 */
	public String spotTheSingleBlueCar() {
		String index = null;

		for (int i = 0; i < floors; i++) {
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < cars; k++) {
					if (parkingLot[i][j][k] == 1) {
						index = xyzToIndex(i, j, k);
						break;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Generates a random integer based on min and max values
	 * 
	 * @param min
	 * @param max
	 * @return int random number
	 */
	private int genRandomInt(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}

	/**
	 * Computes index corresponding to 3D - floor, row and col
	 * 
	 * @param floor
	 * @param row
	 * @param col
	 * @return String index
	 */
	private String xyzToIndex(Integer floor, Integer row, Integer col) {

		row = row + 1;
		col = col + 1;

		if (floor < 0 || floor > 9) {
			return "none";
		} else {
			return floorMap.get(floor) + row.toString() + col.toString();
		}
	}

	/**
	 * This is the main method - execution point and behaves as test client.
	 * Prints the lot/space number containing single Blue colored car.
	 * 
	 * @param args
	 *            unused.
	 */
	public static void main(String[] args) {
		ParkingLotApp st = new ParkingLotApp(10, 6, 9);
		System.out.println("Blue Car Spotted at : " + st.spotTheSingleBlueCar());
	}
}
