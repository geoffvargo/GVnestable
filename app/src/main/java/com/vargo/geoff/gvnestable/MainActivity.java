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
	public NestableEquation eqqie5;
	public NestableEquation eqqie6;
	public NestableEquation eqqie7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* BEGIN multi-line fraction */
		linnie = findViewById(R.id.linnie);
		LinearLayout linnie2 = findViewById(R.id.linnie2);

		eqqie = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).createNestableEquation();

		eqqie3 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).createNestableEquation();

		eqqie4 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).createNestableEquation();

		eqqie5 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(FRACTION).createNestableEquation();

		eqqie6 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(NORMAL).createNestableEquation();

		eqqie.setChild(eqqie3);
		eqqie3.setChild(eqqie4);
		eqqie4.setChild(eqqie5);
		eqqie5.setChild(eqqie6);

		linnie.addView(eqqie);
		/* END multi-line fraction */

		//View sqrty = new gvSqrt(this, "", 35, 50);
		//sqrty.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
		//linnie.addView(sqrty);

		/* BEGIN sqrt test */
//		eqqie2 = new NestableEquationBuilder().setContext(this).setStr("blank").setEqType(EXPONENT).setChildType(NORMAL).createNestableEquation();
		eqqie7 = new NestableEquationBuilder().setContext(this).setStr("blank").setHasText(true).setEqType(SQRT).createNestableEquation();
		linnie2.addView(eqqie7);
		/* END sqrt test */
	}
}
