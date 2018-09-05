/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.gvnestable;

import android.os.*;
import android.support.v7.app.*;
import android.widget.*;

import com.vargo.geoff.nestabletextview.*;

import static com.vargo.geoff.nestabletextview.EqType.*;

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

//		eqqie = new NestableEquationBuilder().setContext(this).setStr("blank").setEqType(FRACTION).createNestableEquation();
//		eqqie = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).setChildType(SQRT).createNestableEquation();
		eqqie = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).createNestableEquation();
		eqqie3 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(NORMAL).createNestableEquation();
		eqqie.setChild(eqqie3);
		linnie.addView(eqqie);

//		View sqrty = new gvSqrt(this, "", 35, 50);
//		sqrty.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
//		linnie.addView(sqrty);

//		eqqie2 = new NestableEquationBuilder().setContext(this).setStr("blank").setEqType(EXPONENT).setChildType(NORMAL).createNestableEquation();
		eqqie2 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(EXPONENT).createNestableEquation();

//		eqqie2 = new NestableEquationBuilder().setContext(this).setHasText(true).setStr("blank").setEqType(EXPONENT).setChildType(SQRT).createNestableEquation();
		linnie2.addView(eqqie2);

//		eqqie3 = new NestableEquationBuilder().setContext(this).setStr("asdf").setEqType(SQRT).createNestableEquation();
////		linnie.addView(eqqie3);
//		eqqie2.setChild(eqqie3);

//		eqqie4 = new NestableEquation(this, "blank", EXPONENT);

	}
}
