package com.example.chemistry;

import java.util.regex.Pattern;

public class Substance {
	private String subType;
	private Element elem;
	private String radical;
	private int elemQuantity;
	private int radQuantity;

	public void setType(String s) {
		this.subType = s;
	}

	public String getType() {
		return this.subType;
	}

	private void setRadQ(int q) {
		this.radQuantity = q;
	}
	
	// Извлечение радикала в скобках
	public String extractRadical(String input) {
		int start = input.indexOf('(') + 1;
		int end = input.indexOf(')');
		return input.substring(start, end);
	}

	// Извлечь радикал в скобках и его индекс
	public void SetRadWithQuantity(String input) {
		this.radical = extractRadical(input);
		if (Character.isDigit(input.charAt(input.length() - 1)) == true) {
			this.setRadQ(input.charAt(input.length() - 1) - '0');
			Pattern.compile(this.radical + "\\d").matcher(input).replaceAll("");
		} else {
			this.setRadQ(1);
			Pattern.compile(this.radical).matcher(input).replaceAll("");
		}
	}

	public Substance(String input, String subType) {
		this.subType = subType;
		switch (subType) {
		case "simple":
			this.subType = subType;
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) {
				this.elemQuantity = input.charAt(input.length() - 1) - '0';
				Pattern.compile("\\d").matcher(input).replaceAll("");
			} else {
				this.elemQuantity = 1;
				this.radQuantity = 0;
			}
			this.elem = Element.valueOf(input);
			this.radical = null;
			break;
		case "water":
			this.elem = Element.valueOf("H");
			this.elemQuantity = 2;
			this.radical = Radical.O.toString();
			this.radQuantity = 1;
			break;
		case "NaS":
			this.elem = null;
			this.elemQuantity = 0;
			this.radical = null;
			this.radQuantity = 0;
			break;
		default:
			this.subType = subType;
			SetRadWithQuantity(input);
			if (Character.isLetter(input.charAt(input.length() - 1)) == true) {
				this.elem = Element.valueOf(input);
			} else {
				this.elemQuantity = input.charAt(input.length() - 1) - '0';
				Pattern.compile("\\d").matcher(input).replaceAll("");
				this.elem = Element.valueOf(input);
				break;
			}
		}
	}
}
