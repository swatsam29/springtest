package com.example.springtest;

public class Parent {


public void print() {
	System.out.println("Screen");
	}

public static void main(String[] args) {
	
	Parent p = new Parent();
	p.print();
	Child C = new Child();
	C.print();
	((Parent)C).print(); 
}

}

class Child extends Parent{
	public void print() {
		System.out.println("File");
		}

}

