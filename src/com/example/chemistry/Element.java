package com.example.chemistry;

public enum Element {
	H(1, 1.007, new int[]{1}),
	Li(3, 6.941, new int[]{1}),
	Be(4, 9.012, new int[]{2}),
	B(5, 10.811, new int[]{2}),
	C(6, 12.011, new int[]{2, 4}),
	N(7, 14.007, new int[]{}),
	O(8, 15.999, new int[]{2}),
	F(9, 18.998, new int[]{}),
	Na(11, 22.99, new int[]{1}),
	Mg(12, 24.305, new int[]{2}),
	Al(13, 26.9816, new int[]{3});
	
	private int num;
	private double mass;
	private int[] val;
	
	private Element(int num, double mass, int[] val){
		this.num = num;
		this.mass = mass;
		this.val = val;
	}
}
