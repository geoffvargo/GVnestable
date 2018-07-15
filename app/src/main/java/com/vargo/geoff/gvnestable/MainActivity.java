/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.gvnestable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.vargo.geoff.nestabletextview.NestableEquation;
import com.vargo.geoff.nestabletextview.NestableEquationBuilder;

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

		eqqie = new NestableEquationBuilder().setContext(this).setStr("blank").setEqType(FRACTION).createNestableEquation();
		linnie.addView(eqqie);

//		View sqrty = new gvSqrt(this, "", 35, 50);
//		sqrty.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
//		linnie.addView(sqrty);

		eqqie2 = new NestableEquationBuilder().setContext(this).setStr("blank").setEqType(EXPONENT).createNestableEquation();
		linnie2.addView(eqqie2);

		eqqie3 = new NestableEquationBuilder().setContext(this).setStr("asdf").setEqType(SQRT).createNestableEquation();
		linnie.addView(eqqie3);
//
//		eqqie4 = new NestableEquation(this, "blank", EXPONENT);

	}
}
