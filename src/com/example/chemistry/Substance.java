package com.example.chemistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;
import android.widget.Toast;

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
			input = replaceMe(input, "", "\\d?"); 
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

	//=====================================================================================================
	
	public String getType(){
		return this.type;
	}
	
	public int calcValency(int v, int c){
		return (v*c)/this.elemQ;
	}
	
<<<<<<< HEAD
	public static int gcd(int a,int b) { 
=======
	public static int gcd(int a,int b) { //Расставляем коэффициенты
>>>>>>> 1bb8d7f8615e854e249da74ac7889fc5cf97e895
        while (b !=0) {					 
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
	
<<<<<<< HEAD
	public void setCoeff(int c){ 
=======
	public void setCoeff(int c){ //Это понадобится для выравнивания реакций. Пока что это самые бесполезные строки в проекте.
>>>>>>> 1bb8d7f8615e854e249da74ac7889fc5cf97e895
		this.coeff = c;
	}
	
	@Override
	public String toString(){  
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
	
	static String divide(String input){
		String output = "";
		Pattern pattern = Pattern.compile("([A-Z](?>[a-z])?(?>\\d*)?)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find() == true) {
			String temp = matcher.group();
			if (Character.isDigit(temp.charAt(temp.length()-1))) {
				int q = Integer.parseInt(temp.replaceAll("[A-Za-z]", ""));
				for (int i = 0; i < q; i++) {
					output += temp.replaceAll("\\d", "") + " ";
				}
			} else {
				output += temp + " ";
			}
		}
		return output;
	}
	
	static String bakeResult(String a, String b){
		String input = a + b;
		input = divide(input);
		String output = "";
		String[] regex = new String[]{"[^H]", "[^C]", "[^P]", "[^N]", "(?!.*Si).*", "(?!.*Cl).*", "[^S]", "[^O]"};
		String[] elem = new String[]{"H", "C", "P", "N", "Si", "Cl", "S", "O"};
		String[] tmp;
		
		
		for(int i = 0; i < regex.length; i++){
			tmp = input.replaceAll(regex[i], " ").trim().split(" ");
			if(tmp.length >= 1 && tmp[0].isEmpty() == false && tmp[tmp.length - 1].isEmpty() != true){
				output += (elem[i] + tmp.length).replaceAll("1", "");
			}
		}
		
		if(output.equals("HO")){
			output = "OH";
		}
		
		Log.d(SUBSTANCE_TAG, "result" + output);
		return output;
	}
	
	//================================================================================================================
	
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
		case "saltbase":
		case "basesalt":
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
				result = s.toString() + " + H2↑";
			break;
			}
		break;
		case "oxideoxide":
			if((a.elem.isMetal() == true ^ b.elem.isMetal() == true) && (a.elem.isMetal() == false ^ b.elem.isMetal() == false)){
				s = a.elem.isMetal() == true ? a : b;
				s.radical = Radical.valueOf(bakeResult(a.radical.toString(), b.toString()));
				setIndexes(s);
				result = s.toString();
			}
		break;
		case "oxidewater":
		case "wateroxide":
			if(a.type.equals("water")){
				s = a;
				s.radical = Radical.valueOf(bakeResult("O", b.toString()));
			} else {
				s = b;
				s.radical = Radical.valueOf(bakeResult("O", a.toString()));
			}
			setIndexes(s);
			result = s.toString();
		break;
		case "oxideacid":
		case "acidoxide":
			if(a.type.equals("acid")){
				b.radical = a.radical;
				setIndexes(b);
				result = b.toString() + " + H2O";
			} else {
				a.radical = b.radical;
				setIndexes(a);
				result = a.toString() + " + H2O";
			}
		break;
		case "oxidebase":
		case "baseoxide":
			if(a.type.equals("base")){
				s = a;
				s.radical = Radical.valueOf(bakeResult(b.toString(), "O"));
			} else {
				s = b;
				s.radical = Radical.valueOf(bakeResult(a.toString(), "O"));
			}
			setIndexes(s);
			result =  s.toString() + " + H2O";
		break;
		default:
			result = "Я так не умею";
		break;
		}
		
		return result;
		
	}

}
