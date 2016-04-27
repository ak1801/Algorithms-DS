package com.csc.assignment.interview;

import java.util.*;
public class ParkingLotClient {

	private static ArrayList<ParkingLot> list;
	private static Random random;

	public static void main(String[] args) {
		list = new ArrayList<ParkingLot>();
		
		for(int i = 0; i < 10; i++)	{
			for(int j = 0; j < 6; j++) {
				for( int k = 0; k < 9; k++) {
					ParkingLot obj = new ParkingLot(i, j, k, "Red");
					list.add(obj);
				}
			}
		}
		
		int floorIndex = genRandomInt(1, 10);
		int rowIndex = genRandomInt(1, 6);
		int colIndex = genRandomInt(1, 9);
		
		ParkingLot obj = new ParkingLot(floorIndex, rowIndex, colIndex, "Blue");

	}

	private static int genRandomInt(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}
	
	/*private enum COLOR{
		RED, BLUE
	}*/
}


class ParkingLot{
	
	ParkingLot(int floor, int row, int car, String color) {
		this.floor = floor;
		this.row = row;
		this.car = car;
		this.color = color;
	}

	private int floor, row, car;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
	}
}
