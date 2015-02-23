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

	private void setElemQ(int q) {
		this.elemQuantity = q;
	}

	private void setRadQ(int q) {
		this.radQuantity = q;
	}

	private Substance react(Substance s) {
			
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
		if (Character.isDigit(input.charAt(input.length()-1)) == true) {
			this.setRadQ(input.charAt(input.length()) - '0');
			Pattern.compile(this.radical + "\\d").matcher(input).replaceAll("");
		} else {
			this.setRadQ(1);
			Pattern.compile(this.radical).matcher(input).replaceAll("");
		}
	}

	public Substance(String input, String subType) {
		this.subType = subType;
		if (this.subType.equals("simple")) {
			this.subType = subType;
			if (Character.isDigit(input.charAt(input.length())) == true) {
				this.elemQuantity = input.charAt(input.length()) - '0';
				Pattern.compile("\\d").matcher(input).replaceAll("");
			} else {
				this.elemQuantity = 1;
				this.radQuantity = 0;
			}
			this.elem = Element.getElemBySym(input);
			this.radical = null;
		} else if (this.subType.equals("water")) {
			this.elem = Element.getElemBySym("H");
			this.elemQuantity = 2;
			this.radical = Radical.O.toString();
			this.radQuantity = 1;
		} else {
			this.subType = subType;
			SetRadWithQuantity(input);
			if (Character.isLetter(input.charAt(input.length())) == true) {
				this.elem = Element.getElemBySym(input);
			} else {
				this.elemQuantity = input.charAt(input.length()) - '0';
				Pattern.compile("\\d").matcher(input).replaceAll("");
				this.elem = Element.getElemBySym(input);
			}
		}

	}
}
