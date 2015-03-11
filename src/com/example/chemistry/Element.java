package com.example.chemistry;

public enum Element {
	H(1, 1.007, 1, false),
	Li(3, 6.941, 1, true),
	Be(4, 9.012, 2, false),
	B(5, 10.811, 3, false),
	C(6, 12.011, false),
	N(7, 14.007, false),
	O(8, 15.999, 2, false),
	F(9, 18.998, false),
	Na(11, 22.99, 1, true),
	Mg(12, 24.305, 2, true),
	Al(13, 26.9816, 3, true),
	Si(14, 20.086, false),
	P(15, 30,974, false),
	S(16, 32.066, false),
	Cl(17, 35.453, false),
	K(19, 39.098, 1, true),
	Ca(20, 40.08, 2, true),
	Cr(24, 51.996, true),
	Mn(25, 54.938, 2, true),
	Fe(26, 55.847, true),
	Co(27, 58.933, true),
	Ni(28, 58,7 , true),
	Cu(29, 63.546, true),
	Zn(30, 65.39, 2, true),
	As(33, 74.992, false),
	Se(34, 78,96, false),
	Br(35, 79.904, false),
	Ag(47, 107.868, true),
	Sn(50, 118.71, true),
	I(53, 126.9045, false),
	Ba(56, 137.33, 2, true),
	Pt(78, 195.08, true),
	Au(79, 196.967, true),
	Hg(80, 200.59, true);
	
	private int num;
	private double mass;
	private int val;
	private boolean isMetal;
	
	public int getValency(){
		return this.val;
	}
	
	public void setValency(int val){
		this.val = val;
	}
	
	public boolean isMetal(){
		return this.isMetal;
	}
	
	public double mass(){
		return this.mass;
	}
	
	private Element(int num, double mass, int val, boolean isMetal){
		this.num = num;
		this.mass = mass;
		this.val = val;
		this.isMetal = isMetal;
	}
	
	private Element(int num, double mass, boolean isMetal){
		this.num = num;
		this.mass = mass;
		this.isMetal = isMetal;
	}
}
