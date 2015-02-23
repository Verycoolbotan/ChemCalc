package com.example.chemcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

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
		EditText firstSub = (EditText) findViewById(R.id.firstSubstance);
		EditText secondSub = (EditText) findViewById(R.id.secondSubstance);
		TextView result = (TextView) findViewById(R.id.result);
		switch (v.getId()) {
		case R.id.enter:
			Recogniser rec = new Recogniser();
			firstSubType = rec
					.recognise((String) firstSub.getText().toString());
			Substance firstSubstance = new Substance((firstSub.getText().toString()), firstSubType);
			secondSubType = rec.recognise((String) secondSub.getText()
					.toString());
			Substance secondSubstance = new Substance((secondSub.getText().toString()), secondSubType);
			result.setText((CharSequence) (firstSubType + " + " + secondSubType));
			break;
		case R.id.clear:
			firstSubType = "";
			secondSubType = "";
			result.setText("");
			break;
		case R.id.about:
			Intent intent = new Intent(MainActivity.this, AboutActivity.class);
			startActivity(intent);
			break;
		}

	}
}
