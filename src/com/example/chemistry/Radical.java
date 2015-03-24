package com.example.chemistry;

public enum Radical {
	O(2, new Element[]{Element.O}, new int[]{1}),
	OH(1, new Element[]{Element.O, Element.H}, new int[]{1, 1}),
	NO2(1, new Element[]{Element.N, Element.O}, new int[]{1, 2}),
	NO3(1, new Element[]{Element.N, Element.O}, new int[]{1, 3}),
	F(1, new Element[]{Element.N}, new int[]{1}),
	Cl(1, new Element[]{Element.Cl}, new int[]{1}),
	ClO3(1, new Element[]{Element.Cl, Element.O}, new int[]{1, 3}),
	ClO4(1, new Element[]{Element.Cl, Element.O}, new int[]{1, 4}),
	Br(1, new Element[]{Element.Br}, new int[]{1}),
	I(1, new Element[]{Element.I}, new int[]{1}),
	S(2, new Element[]{Element.S}, new int[]{1}),
	HSO3(1, new Element[]{Element.H, Element.S, Element.O}, new int[]{1, 1, 3}),
	SO3(2, new Element[]{Element.S, Element.O}, new int[]{1, 3}),
	HSO4(1, new Element[]{Element.H, Element.S, Element.O}, new int[]{1, 1, 4}),
	SO4(2, new Element[]{Element.S, Element.O}, new int[]{1, 4}),
	HCO3(1, new Element[]{Element.H, Element.C, Element.O}, new int[]{1, 1, 3}),
	CO3(2, new Element[]{Element.C, Element.O}, new int[]{1, 3}),
	SiO3(2, new Element[]{Element.Si, Element.O}, new int[]{1, 3}),
	HPO4(2, new Element[]{Element.H, Element.P, Element.O}, new int[]{1, 1, 4}),
	PO4(3, new Element[]{Element.P, Element.O}, new int[]{1 ,4}),
	MnO4(1, new Element[]{Element.Mn, Element.O}, new int[]{1, 4});
	
	private int valency;
	private Element[] cPart; //Понадобится при совершенствовании системы реакций
	private int Quantity[];
	
	public int getValency(){
		return this.valency;
	}
	
	public Element[] getParts(){
		return this.cPart;
	}
	
	private Radical(int valency, Element[] cPart, int[] Quantity){
		this.valency = valency;
		this.cPart = cPart;
		this.Quantity = Quantity;
	}
}