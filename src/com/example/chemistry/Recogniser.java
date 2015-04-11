package com.example.chemistry;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import android.util.Log;

public class Recogniser {
	
	private static final String SUBSTANCE_TAG = "Recogniser";

	private String result;
	static boolean isSimple = false;
	static boolean isOxide = false;
	static boolean isAcid = false;
	static boolean isBase = false;
	static boolean isSalt = false;
	static boolean isWater = false;

	Pattern simple = Pattern.compile("^[A-Z][a-z]?\\d?$");
	Pattern water = Pattern.compile("^H2O$");
	Pattern oxide = Pattern.compile("^([^H][a-z]?\\d?)\\((O)+\\)\\d?");
	Pattern base = Pattern.compile("^([^H][a-z]?\\d?)\\((OH)+\\)\\d?");
	Pattern acid = Pattern.compile("^(H+\\d?)\\(([A-Z][a-z]?\\d?){1,4}\\)\\d?");
	Pattern salt = Pattern.compile("^([^H][a-z]?\\d?)\\(([A-Z][a-z]?\\d?){1,4}\\)\\d?");

	public String recognise(String input) {
		Matcher mSimple = simple.matcher(input);
		Matcher mOxide = oxide.matcher(input);
		Matcher mAcid = acid.matcher(input);
		Matcher mBase = base.matcher(input);
		Matcher mSalt = salt.matcher(input);
		Matcher mWater = water.matcher(input);

		isSimple = mSimple.find();
		Log.d(SUBSTANCE_TAG, "isSimple " + isSimple );
		isOxide = mOxide.find();
		Log.d(SUBSTANCE_TAG, "isOxide " + isOxide );
		isBase = mBase.find();
		Log.d(SUBSTANCE_TAG, "isBase " + isBase );
		isAcid = mAcid.find();
		Log.d(SUBSTANCE_TAG, "isAcid " + isAcid );
		isSalt = mSalt.find();
		Log.d(SUBSTANCE_TAG, "isSalt " + isSalt );
		isWater = mWater.find();
		Log.d(SUBSTANCE_TAG, "isWater " + isWater );

		if ((isOxide == true) && (isAcid == false) && (isBase == false) && (isSimple == false)) {
			result = "oxide";
		} else if ((isOxide == false) && (isAcid == true) && (isBase == false) && (isSalt == false) && (isSimple == false)) {
			result = "acid";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == true) && (isSimple == false)) { //костыль
			result = "base";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false) && (isSalt == true)) {
			result = "salt";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false) && (isSalt == false) && (isSimple == true)) {
			result = "simple";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false) && (isSalt == false) && (isSimple == false) && (isWater == false)) {
			result = "NaS";
		} else if ((isOxide == false) && (isAcid == false) && (isBase == false) && (isSalt == false) && (isWater == true)) { //второй костыль
			result = "water";
		} else {
			result = "NaS";
		}

		return result;
	}

}
