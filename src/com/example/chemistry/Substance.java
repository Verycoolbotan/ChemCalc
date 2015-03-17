package com.example.chemistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class Substance {
	private static final String SUBSTANCE_TAG = "Substance";
	private String type;
	private Element elem;
	private Radical radical;
	private int coeff;
	private int elemQ;
	private int radQ;
	
	public Substance(String type, String input){
		this.type = type;
		Log.d(SUBSTANCE_TAG, input);
		Log.d(SUBSTANCE_TAG, "Type: " + type);
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
			input = replaceMe(input, " ", "\\d?"); //����� ���� �������� �� StringBuffer/StringBuilder.
			input.trim();
			this.elem = Element.valueOf(input);
			break;
		case "water":
				this.elem = Element.H;
				this.radical = Radical.O;
				this.elemQ = 2;
				this.radQ = 1;
				break;
		default:   //������, ������� � ����
			Log.d(SUBSTANCE_TAG, input);
			int s = input.indexOf('(') + 1;
			Log.d(SUBSTANCE_TAG, "Start index: " + s);
			int e = input.indexOf(')');
			Log.d(SUBSTANCE_TAG, "End index: " + e);
			try{
				this.radical = Radical.valueOf(input.substring(s, e));//���������� �������� �� ������
			} catch(Exception exc){
				Log.d(SUBSTANCE_TAG, exc.getMessage());
			}
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) { //���� �� ����� �����, �� ��� ������ ��� ��������.
				this.radQ = input.charAt(input.length() - 1) - '0';			   //����� ������ ����� 1.
			} else {
				this.radQ = 1;
			}
			input = replaceMe(input, " ", "\\([A-Z][a-z]?\\d?\\)\\d?");//������� ������� � ������� �� �������
			input.trim();
			Log.d(SUBSTANCE_TAG, "Replaced: " + input);
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) {
				this.elemQ = input.charAt(input.length() - 1) - '0';
			} else {
				this.elemQ = 1;
			}
			input = replaceMe(input, " ", "\\d?");
			input.trim();
			this.elem = Element.valueOf(input);
			this.elem.setValency(calcValency(this.radical.getValency(), this.radQ)); //������ ����������� ��������
			break;
			}
	}
	
	private String replaceMe(String input, String replace, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.replaceAll(replace);
	}

	public String getType(){
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
