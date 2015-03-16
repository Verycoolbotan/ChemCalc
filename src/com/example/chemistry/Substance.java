package com.example.chemistry;

import java.util.regex.Pattern;

public class Substance {
	private String type;
	private Element elem;
	private Radical radical;
	private int coeff;
	private int elemQ;
	private int radQ;
	
	public Substance(String type, String input){
		this.type = type;
		switch(type){
		case "NaS":		//�� ��������. ������ ������ � null-���������� �����.
			this.elem = null;  
			this.radical = null;
			break;
		case "simple":
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) { //���� �� ����� �����, �� ��� ������ ��� ��������.
				this.elemQ = input.charAt(input.length() - 1) - '0';			   //����� ������ ����� 1.
			} else {
				this.elemQ = 1;
			}
			Pattern.compile("\\d?").matcher(input).replaceAll(""); //����� ���� �������� �� StringBuffer/StringBuilder.
			this.elem = Element.valueOf(input);
			break;
		case "water":
				this.elem = Element.H;
				this.radical = Radical.O;
				this.elemQ = 2;
				this.radQ = 1;
				break;
		default:   //������, ������� � ����
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) { //���� �� ����� �����, �� ��� ������ ��� ��������.
				this.radQ = input.charAt(input.length() - 1) - '0';			   //����� ������ ����� 1.
			} else {
				this.radQ = 1;
			}
			this.radical = Radical.valueOf(input.substring((input.indexOf('(') + 1), (input.indexOf(')'))));//���������� �������� �� ������
			Pattern.compile("\\([A-Z][a-z]?\\)\\d?").matcher(input).replaceAll("");//������� ������� � ������� �� �������
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) {
				this.elemQ = input.charAt(input.length() - 1) - '0';
			} else {
				this.elemQ = 1;
			}
			Pattern.compile("\\d?").matcher(input).replaceAll("");
			this.elem = Element.valueOf(input);
			this.elem.setValency(calcValency(this.radical.getValency(), this.radQ)); //������ ����������� ��������
			break;
			}
	}

	public String getType() {
		return this.type;
	}
	
	public int calcValency(int v, int c){
		return (v*c)/this.elemQ;
	}
	
	public void setCoeff(int c){
		this.coeff = c;
	}
	
	@Override
	public String toString(){  //��������. ����� ������� ���. ������� � �������������� ������.
		return this.elem.toString() + this.elemQ + this.radical.toString() + this.radQ;
	}
	
	public static String react(Substance a, Substance b){
		Element eTMP;
		Radical rTMP;
		String result = null;
		if(a.type.equals("salt") && b.type.equals("salt")){
			rTMP = b.radical;
			b.radical = a.radical;
			a.radical = rTMP;
			result = a.toString() + " + " + b.toString();
		}
		return result;
	}

}
