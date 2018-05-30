/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.gvnestable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.vargo.geoff.nestabletextview.NestableEquation;

import static com.vargo.geoff.nestabletextview.EqType.EXPONENT;
import static com.vargo.geoff.nestabletextview.EqType.FRACTION;
import static com.vargo.geoff.nestabletextview.EqType.SQRT;

public class MainActivity extends AppCompatActivity {

	public LinearLayout linnie;
	public NestableEquation eqqie;
	public NestableEquation eqqie2;
	public NestableEquation eqqie3;
	public NestableEquation eqqie4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		linnie = findViewById(R.id.linnie);
		LinearLayout linnie2 = findViewById(R.id.linnie2);

		eqqie = new NestableEquation(this, "blank", FRACTION);
		linnie.addView(eqqie);

		eqqie2 = new NestableEquation(this, "blank", EXPONENT);
		linnie.addView(eqqie2);

		eqqie3 = new NestableEquation(this, "asdf", SQRT);
		linnie2.addView(eqqie3);

		eqqie4 = new NestableEquation(this, "blank", EXPONENT);
		linnie.addView(eqqie4);

	}
}
