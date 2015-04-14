package com.example.chemcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chemistry.*;

public class MainActivity extends Activity implements OnClickListener {

	String firstSubType;
	String secondSubType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button enter = (Button) findViewById(R.id.enter);
		Button clear = (Button) findViewById(R.id.clear);
		Button about = (Button) findViewById(R.id.about);
		enter.setOnClickListener(this);
		clear.setOnClickListener(this);
		about.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		EditText firstSub = (EditText) findViewById(R.id.first_substance);
		EditText secondSub = (EditText) findViewById(R.id.second_substance);
		TextView result = (TextView) findViewById(R.id.result);
		switch (v.getId()) {
		case R.id.enter:
			Recogniser rec = new Recogniser();
			firstSubType = rec.recognise((String) undoReplacing(firstSub.getText().toString()));
			Substance firstSubstance = new Substance(firstSubType, undoReplacing(firstSub.getText().toString()));
			secondSubType = rec.recognise((String) undoReplacing(secondSub.getText().toString()));
			Substance secondSubstance = new Substance(secondSubType, undoReplacing(secondSub.getText().toString()));
			try {
				result.setText((CharSequence) replaceIndexes(Substance.react(firstSubstance, secondSubstance)));
				firstSub.setText((CharSequence) replaceIndexes(firstSub.getText().toString()));
				secondSub.setText(((CharSequence) replaceIndexes(secondSub.getText().toString())));
			} catch (Exception e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
			Toast.makeText(this, firstSubstance.getType() + " + " + secondSubstance.getType(), Toast.LENGTH_LONG).show();

			break;
		case R.id.clear:
			firstSubType = "";
			secondSubType = "";
			result.setText("");
			firstSub.setText("");
			secondSub.setText("");
			break;
		case R.id.about:
			startActivity(new Intent(MainActivity.this, AboutActivity.class));
		}
	}
	
	public static String replaceIndexes(String input){
		String[] indexes = new String[]{"₀","₁","₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉"};
		for(int i = 2; i <= 9; i++){
			String tmp = "" + i;
			input = input.replaceAll(tmp, indexes[i]);
		}
		return input;
	}
	
	public static String undoReplacing(String input){
		String[] indexes = new String[]{"₀","₁","₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉"};
		for(int i = 2; i <= 9; i++){
			String tmp = "" + i;
			input = input.replaceAll(indexes[i], tmp);
		}
		return input;
	}
}
