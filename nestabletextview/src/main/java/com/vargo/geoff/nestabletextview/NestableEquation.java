/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintSet;

import static android.support.constraint.ConstraintSet.BOTTOM;
import static android.support.constraint.ConstraintSet.LEFT;
import static android.support.constraint.ConstraintSet.PARENT_ID;
import static android.support.constraint.ConstraintSet.TOP;
import static android.support.constraint.ConstraintSet.WRAP_CONTENT;
import static com.vargo.geoff.nestabletextview.EqType.NORMAL;

/**
 * Created by geoff on 12/21/2017.
 */

public class NestableEquation extends NestableTextView {

	public NestableEquation(Context context, String str, EqType eqType) {
		super(context, str);
		eqTyper(eqType);
	}

	protected void eqTyper(EqType eqType) {
		NestableEquation eqNew = null;
		switch (eqType) {
			case NORMAL:
				break;
			case FRACTION:
				ConstraintSet params1 = new ConstraintSet();
//				ConstraintLayout.LayoutParams params1 = new ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//				params1.addRule(BELOW, this.text.getId());
//				System.out.printf("text id: %d\n", this.text.getId());

				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);

//				this.child.setLayoutParams(params1);

				this.child.addView(eqNew);
				params1.connect(this.text.getId(), TOP, PARENT_ID, TOP, 0);
				params1.connect(this.text.getId(), LEFT, PARENT_ID, LEFT, 0);
				params1.constrainHeight(this.text.getId(), WRAP_CONTENT);
				params1.constrainWidth(this.text.getId(), WRAP_CONTENT);
				params1.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params1.constrainWidth(this.child.getId(), WRAP_CONTENT);
				params1.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				params1.connect(this.child.getId(), BOTTOM, PARENT_ID, BOTTOM, 0);
				params1.applyTo(this);
				break;
			case EXPONENT:
//				ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//				params2.addRule(RIGHT_OF, this.text.getId());
//				this.child.setLayoutParams(params2);

//				ConstraintLayout.LayoutParams params3 = new ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//				params3.addRule(BELOW, this.child.getId());
//				this.text.setLayoutParams(params3);

				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);
				eqNew.text.setTextSize(8);

				this.child.addView(eqNew);
				break;
			case ORDINAL:
				break;
		}
	}
}
