package com.example.chemcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;

import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		TextView tv = (TextView) findViewById(R.id.info);
		tv.setText(Html.fromHtml(getResources().getString(R.string.help)));
	}
}
