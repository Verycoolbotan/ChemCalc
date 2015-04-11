package com.example.chemistry;

public enum Element {
	H(1, 1.007, 1, false, "DNonmetal"),
	Li(3, 6.941, 1, true, "AMetal"),
	Be(4, 9.012, 2, false, "AEMetal"),
	B(5, 10.811, 3, false, "Metalloid"),
	C(6, 12.011, false, "PNonmetal"),
	N(7, 14.007, false, "DNonmetal"),
	O(8, 15.999, 2, false, "DNonmetal"),
	F(9, 18.998, false, "DNonmetal"),
	Na(11, 22.99, 1, true, "AMetal"),
	Mg(12, 24.305, 2, true, "AEMetal"),
	Al(13, 26.9816, 3, true, "OMetal"),
	Si(14, 20.086, false, "Metalloid"),
	P(15, 30,974, false, "PNonmetal"),
	S(16, 32.066, false, "PNonmetal"),
	Cl(17, 35.453, false, "DNonmetal"),
	K(19, 39.098, 1, true, "AMetal"),
	Ca(20, 40.08, 2, true, "AEMetal"),
	Cr(24, 51.996, true, "TMetal"),
	Mn(25, 54.938, 2, true, "TMetal"),
	Fe(26, 55.847, true, "TMetal"),
	Co(27, 58.933, true, "TMetal"),
	Ni(28, 58,7 , true, "TMetal"),
	Cu(29, 63.546, true, "TMetal"),
	Zn(30, 65.39, 2, true, "TMetal"),
	As(33, 74.992, false, "Metalloid"),
	Se(34, 78,96, false, "PNonmetal"),
	Br(35, 79.904, false, "DNonmetal"),
	Ag(47, 107.868, true, "TMetal"),
	Sn(50, 118.71, true, "OMetal"),
	I(53, 126.9045, false, "DNonmetal"),
	Ba(56, 137.33, 2, true, "AEMetal"),
	Pt(78, 195.08, true, "TMetal"),
	Au(79, 196.967, true, "TMetal"),
	Hg(80, 200.59, true, "TMetal");
	
	private int num;
	private double mass;
	private int val;
	private boolean isMetal;
	private String type;
	
	public int getValency(){
		return this.val;
	}
	
	public void setValency(int val){
		if(this.val == 0){
			this.val = val;
		}
	}
	
	public boolean isMetal(){
		return this.isMetal;
	}
	
	public double mass(){
		return this.mass;
	}
	
	public String getType(){
		return type;
	}
	
	public Element getElement(String s){
		return Element.valueOf(s);
	}
	
	private Element(int num, double mass, int val, boolean isMetal, String type){
		this.num = num;
		this.mass = mass;
		this.val = val;
		this.isMetal = isMetal;
		this.type = type;
	}
	
	private Element(int num, double mass, boolean isMetal, String type){
		this.num = num;
		this.mass = mass;
		this.isMetal = isMetal;
		this.type = type;
	}
}
