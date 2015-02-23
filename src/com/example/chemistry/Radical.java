package com.example.chemistry;

public enum Radical {
	O, OH, NO2, NO3, F, Cl, ClO3, ClO4, Br, I, S, SO3, HSO4, SO4, HCO3, CO3, SiO3, HPO4, PO4, MnO4, CH3COO;
	static Radical radRaw[] = Radical.values();
	
	public static String getRad(String input){
		int i;
		for(i = 0; i < radRaw.length; i++){
			if(input.equals(radRaw[i].toString()))
				break;
		}
		return radRaw[i].toString();
	}
}