package com.programming.problems;
//I    
interface Test{
	  //mI
    	public void display();
    }
    
    public class TestGenerics implements Test{

    	//mC
	@Override
	public void display() {
		System.out.println("done");
	}
	
	public static void main(String args[]){
		TestGenerics ts = new TestGenerics();
		ts.display();
	}
    }

