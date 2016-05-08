package com.programming.problems;

public class TestSomeCode {

	static Node head;
	static Node tail;
	int size;
	
	class Node {
		Node next;
		Node previous;
		int item;
	}
	
	public TestSomeCode() {
		head = null;
	}
	
	public static void main(String[] args) {
	
		TestSomeCode obj = new TestSomeCode();
		obj.enqueue(1);
		obj.enqueue(2);
		obj.enqueue(3);
		obj.enqueue(4);
		obj.enqueue(5);
		obj.enqueue(6);
		
		int index = 4;
		
		Node current = head;
		Node prev = head; 
		
		for(int i = 0; i < index; i++) {
			prev = current;
			current = current.next;
		}
		
		System.out.println(current.item);
		prev.next = current.next;
		tail = current.next;
		tail.previous = prev;
		current = null;
	}
	
	public void enqueue(int item) {
		if(size > 0){
			/*Node node = new Node();
			node.item = item;
			
			Node temp = head;
			while(temp.next!=null) {
				temp = temp.next;
			}
			
			temp = node;*/
			
			Node node = new Node();
			node.item = item;
			tail.next = node;
			node.previous = tail;
			tail = tail.next;
			
		}
		else {
			head = new Node();
			head.item = item;
			tail = head;
		}
		size++;
	}

}
