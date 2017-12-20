package com.vargo.geoff.gvnestable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vargo.geoff.nestabletextview.NestableTextView;

public class MainActivity extends AppCompatActivity {

	public NestableTextView nesty;
//	public

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nesty = new NestableTextView(this);
	}
}
