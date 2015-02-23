package com.example.chemistry;

public class Element {
	private int number;
	private String name;
	private String symbol;
	private int numberOfVals;
	private int[] vals = new int[numberOfVals];
	private double mass;
	private boolean isMetal;
	public static Element[] periodicTable = new Element[]{
		new Element(1, "Hydrogen", "H", 1, new int[] {1}, 1.0079, false),
		new Element(2, "Helium", "He", 1, new int[] {1}, 4.0026, false),
		new Element(3, "Lithium", "Li", 1, new int[] {1}, 6.941, true),
		new Element(4, "Beryllium", "Be", 1, new int[] {2}, 9.01218, true),
		new Element(5, "Boron", "B", 1, new int[] {3}, 10.81, true),
		new Element(6, "Carbon", "C", 2, new int[] {2, 4}, 12.011, false),
		new Element(7, "Nitrogen", "N", 5, new int[] {1, 2, 3, 4, 5}, 17.0067, false),
		new Element(8, "Oxygen", "O", 1, new int[] {2}, 15.9994, false),
		new Element(9, "Fluorine", "F", 1, new int[] {1}, 18.9984, false),
		new Element(11, "Sodium", "Na", 1, new int[] {1}, 22.9898, true),
		new Element(12, "Magnesium", "Mg", 1, new int[] {2}, 24.305, true),
		new Element(13, "Aluminium", "Al", 1, new int[] {3}, 26.9815, true),
		new Element(14, "Silicon", "Si", 2, new int[] {2, 4}, 28.0855, true),
		new Element(15, "Phosphorus", "P", 2, new int[] {3, 5}, 30.9738, false),
		new Element(16, "Sulfur", "S", 3, new int[] {2, 4, 6}, 32.06, false),
		new Element(17, "Chlorine", "Cl", 1, new int[] {1}, 35.5, false),
		new Element(19, "Potassium", "K", 1, new int[] {1}, 39.0983, true),
		new Element(20, "Calcium", "Ca", 1, new int[] {2}, 40.08, true),
		new Element(24, "Chromium", "Cr", 2, new int[] {2, 3, 6}, 51.996, true),
		new Element(25, "Manganese", "Mn", 1, new int[] {2}, 54.938, true),
		new Element(26, "Iron", "Fe", 2, new int[] {2, 3}, 55.847, true),
		new Element(29, "Copper", "Cu", 2, new int[] {2, 4}, 63.546, true),
		new Element(30, "Zinc", "Zn", 1, new int[] {2}, 65.38, true),
		new Element(33, "Arsenic", "As", 2, new int[] {5, 3}, 74.9216, false),
		new Element(35, "Bromine", "Br", 4, new int[] {1, 3, 5, 7}, 79.904, false),
		new Element(47, "Silver", "Ar", 2, new int[] {1, 2}, 107.868, true),
		new Element(50, "Tin", "Sn", 2, new int[] {2, 4}, 118.69, true),
		new Element(53, "Iodine", "I", 4, new int[] {1, 3, 5, 7}, 126.904, false),
		new Element(56, "Barium", "Ba", 1, new int[] {2}, 137.33, true),
		new Element(79, "Gold", "Au", 3, new int[] {1, 3, 5}, 196.967, true),
		new Element(80, "Mercury", "Hg", 2, new int[] {1, 2}, 200.59, true)
	};

	public int getNum(Element e) {
		return e.number;
	}

	public static String getName(Element e) {
		return e.name;
	}

	public static String getSym(Element e) {
		return e.symbol;
	}

	public static double getMass(Element e) {
		return e.mass;
	}

	public static boolean isMetal(Element e) {
		return e.isMetal;
	}
	
	public static Element getElemBySym(String input){
		Element elem = null;
		for(Element i : periodicTable){
			if(i.symbol.equals(input)){
				elem = i;
			}
		}
		return elem;
	}
	

	Element(int number, String name, String symbol, int numOfVals,
			int[] sentVals, double mass, boolean isMetal) {
		this.number = number;
		this.name = name;
		this.symbol = symbol;
		this.numberOfVals = numOfVals;
		this.mass = mass;
		this.isMetal = isMetal;
		for (int i = 0; i < numberOfVals; i++) {
			this.vals[i] = sentVals[i];
		}
	}
	
	
	
	

}
