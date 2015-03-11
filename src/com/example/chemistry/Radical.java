package com.example.chemistry;

public enum Radical {
	O(2),
	OH(1),
	NO2(1),
	NO3(1),
	F(1),
	Cl(1),
	ClO3(1),
	ClO4(1),
	Br(1),
	I(1),
	S(2),
	HSO3(1),
	SO3(2),
	HSO4(1),
	SO4(2),
	HCO3(1),
	CO3(2),
	SiO3(2),
	HPO4(2),
	PO4(3),
	MnO4(1),
	CH3COO(1);
	
	private int valency;
	
	public int getValency(){
		return this.valency;
	}
	
	private Radical(int valency){
		this.valency = valency;
	}
}