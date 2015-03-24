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
		case "NaS":		//Не вещество. Создаём объект с null-значениями везде.
			this.elem = null;  
			this.radical = null;
			break;
		case "simple":
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) { //Если на конце цифра, то это индекс при элементе.
				this.elemQ = input.charAt(input.length() - 1) - '0';			   //Иначе индекс равен 1.
			} else {
				this.elemQ = 1;
			}
			input = replaceMe(input, "", "\\d?"); //Когда лень возиться со StringBuffer/StringBuilder.
			input.trim();
			this.elem = Element.valueOf(input);
			break;
		case "water":
				this.elem = Element.H;
				this.radical = Radical.O;
				this.elemQ = 2;
				this.radQ = 1;
				break;
		default:   //Оксиды, кислоты и соли
			Log.d(SUBSTANCE_TAG, input);
			int s = input.indexOf('(') + 1;
			Log.d(SUBSTANCE_TAG, "Start index: " + s);
			int e = input.indexOf(')');
			Log.d(SUBSTANCE_TAG, "End index: " + e);
			try{
				this.radical = Radical.valueOf(input.substring(s, e));//Извлечение радикала из скобок
			} catch(Exception exc){
				Log.d(SUBSTANCE_TAG, exc.getMessage());
			}
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) { //Если на конце цифра, то это индекс при радикале.
				this.radQ = input.charAt(input.length() - 1) - '0';			   //Иначе индекс равен 1.
			} else {
				this.radQ = 1;
			}
			input = replaceMe(input, "", "\\(([A-Z][a-z]?\\d?){1,3}\\)\\d?");//Убираем радикал и смотрим на элемент
			input.trim();
			Log.d(SUBSTANCE_TAG, "Replaced: " + input);
			if (Character.isDigit(input.charAt(input.length() - 1)) == true) {
				this.elemQ = input.charAt(input.length() - 1) - '0';
			} else {
				this.elemQ = 1;
			}
			input = replaceMe(input, "", "\\d?");
			input.trim();
			this.elem = Element.valueOf(input);
			this.elem.setValency(calcValency(this.radical.getValency(), this.radQ)); //Ставим валентность элементу
			Log.d(SUBSTANCE_TAG, "Elem valency:" + this.elem.getValency());
			Log.d(SUBSTANCE_TAG, "Rad valency:" + this.radical.getValency());
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
	
	public static int gcd(int a,int b) { //нагло скопированная реализация алгоритма Евклида (не хочу изобретать велосипед снова). 
        while (b !=0) {					 //Это понадобится для расставления коэффициентов.
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }
	
	public static int lcm(int a, int b) {
		return (a*b)/gcd(a,b);
	}
	
	public static void setIndexes(Substance a){
		int aLCM = lcm(a.elem.getValency(), a.radical.getValency());
		a.elemQ = aLCM/a.elem.getValency();
		a.radQ = aLCM/a.radical.getValency();
	}
	
	public void setCoeff(int c){ //Эта фича понадобится для выравнивания реакций. Пока что это самые бесполезные строки в проекте.
		this.coeff = c;
	}
	
	@Override
	public String toString(){  //Заглушка. Позже добавлю доп. условия и форматирование вывода.
		String result;
		result = this.elem.toString();
		if(this.elemQ == 1){
			//ничего не делаем
		} else {
			result += this.elemQ;
		}
		if(this.radQ > 1){
			result += "(" + this.radical.toString() + ")" + this.radQ;
		} else {
			result += this.radical.toString();
		}
		return result;
	}
	
	public static String react(Substance a, Substance b){
		Element eTMP;
		Radical rTMP;
		Substance s;
		String result = null;
		switch(a.type.concat(b.type)){
		case "saltsalt":
		case "saltacid":
		case "acidsalt":
			rTMP = b.radical;
			b.radical = a.radical;
			a.radical = rTMP;
			setIndexes(a);
			setIndexes(b);
			result = a.toString() + " + " + b.toString();
		break;
		case "baseacid":
		case "acidbase":
			if(a.type.equals("base")){
				a.radical = b.radical;
				setIndexes(a);
				result = a.toString() + " + H2O";
			} else {
				b.radical = a.radical;
				setIndexes(b);
				result = b.toString() + " + H2O";
			}
		break;
		case "simplewater":
		case "watersimple":
			s = (a.elem.isMetal() == true ? a : b);
			switch(s.elem.toString()){
			case "Li":
			case "K":
			case "Na":
			case "Ba":
			case "Ca":
				s.radical = Radical.OH;
				setIndexes(s);
				result = s.toString();
			break;
			}
		break;
		case "oxideoxide":
			s = (a.elem.isMetal() == true ? a : b);
			
		break;
		}
		return result;
		
	}

}
