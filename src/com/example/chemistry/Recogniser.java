package com.example.chemistry;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Recogniser {

	private String result;
	static boolean isSimple = false;
	static boolean isOxide = false;
	static boolean isAcid = false;
	static boolean isBase = false;
	static boolean isSalt = false;
	static boolean isWater = false;

	Pattern simple = Pattern.compile("^[A-Z][a-z]?$");
	Pattern water = Pattern.compile("^H2O$");
	Pattern oxide = Pattern.compile("^[A-Z][a-z]?\\d?(\\Q(\\EO\\Q)\\E)+\\d?$");
	Pattern acid = Pattern.compile("^H+\\d?\\Q(\\E([A-Z&&[^H]][a-z&&[^H]]?\\d?){1,4}\\Q)\\E\\d?$");
	Pattern base = Pattern.compile("^[A-Z&&[^H]][a-z&&[^H]]?\\d?\\Q(\\E(OH)+\\Q)\\E\\d?$");
	Pattern salt = Pattern.compile("^[A-Z&&[^H]][a-z&&[^H]]?\\d?\\Q(\\E([A-Z][a-z]?\\d?)\\Q)\\E\\d?$");

	public String recognise(String input) {
		Matcher mSimple = simple.matcher(input);
		Matcher mOxide = oxide.matcher(input);
		Matcher mAcid = acid.matcher(input);
		Matcher mBase = base.matcher(input);
		Matcher mSalt = salt.matcher(input);
		Matcher mWater = water.matcher(input);

		isSimple = mSimple.find();
		isOxide = mOxide.find();
		isBase = mBase.find();
		isAcid = mAcid.find();
		isSalt = mSalt.find();
		isWater = mWater.find();

		if ((isOxide == true) && (isAcid == false) && (isBase == false)
				&& (isSalt == false) && (isSimple == false)) {
			result = "oxide";
		} else if (((isOxide == false) && (isAcid == true) && (isBase == false)
				&& (isSalt == false) && (isSimple == false))) {
			result = "acid";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == true)
				&& (isSalt == false) && (isSimple == false)) {
			result = "base";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false)
				&& (isSalt == true)) {
			result = "salt";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false)
				&& (isSalt == false) && (isSimple == true)) {
			result = "simple_substance";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false)
				&& (isSalt == false) && (isSimple == false)) {
			result = "NaS";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false)
				&& (isSalt == false) && (isSimple == false)
				&& (isWater == true)) {
			result = "water";
		} else {
			result = "NaS";
		}

		return result;
	}

}
