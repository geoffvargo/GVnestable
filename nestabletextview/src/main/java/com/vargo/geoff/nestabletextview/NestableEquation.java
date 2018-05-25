/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;

import static android.support.constraint.ConstraintSet.BOTTOM;
import static android.support.constraint.ConstraintSet.LEFT;
import static android.support.constraint.ConstraintSet.PARENT_ID;
import static android.support.constraint.ConstraintSet.RIGHT;
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
		NestableTextView eqNew = null;
		ConstraintSet params1 = new ConstraintSet();
		ConstraintSet params2 = new ConstraintSet();

		params1.constrainHeight(this.text.getId(), WRAP_CONTENT);
		params1.constrainWidth(this.text.getId(), WRAP_CONTENT);

		switch (eqType) {
			case NORMAL:
				params1.constrainHeight(this.getId(), WRAP_CONTENT);
				params1.constrainHeight(this.getId(), WRAP_CONTENT);
				params1.connect(this.text.getId(), TOP, this.getId(), TOP, 0);
				params1.connect(this.text.getId(), LEFT, this.getId(), LEFT, 0);
				params1.constrainHeight(this.child.getId(), 0);
				params1.constrainWidth(this.child.getId(), 0);
				params1.applyTo(this);
				break;
			case FRACTION:
				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);

				this.child.addView(eqNew);
				params1.connect(this.getId(), TOP, PARENT_ID, BOTTOM, 0);
				params1.connect(this.text.getId(), TOP, this.getId(), TOP, 0);

				params1.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params1.constrainWidth(this.child.getId(), WRAP_CONTENT);
				params1.connect(this.child.getId(), TOP, this.text.getId(), BOTTOM, 0);
				params1.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				params1.applyTo(this);
				break;
			case EXPONENT:
//				this.setClipChildren(false);

				params2.constrainHeight(this.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.getId(), WRAP_CONTENT);

				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);
				eqNew.text.setTextSize(8);
				eqNew.setClipChildren(false);
				this.child.addView(eqNew);

				params2.connect(this.child.getId(), LEFT, this.text.getId(), RIGHT, 0);
				params2.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
				params2.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.child.getId(), WRAP_CONTENT);

				params2.connect(eqNew.getId(), TOP, ((ConstraintLayout) eqNew.getParent()).getId(), TOP, 0);
				params2.connect(eqNew.getId(), LEFT, ((ConstraintLayout) eqNew.getParent()).getId(), LEFT, 0);

				params2.applyTo(this);
				break;
			case ORDINAL:
				break;
			case SQRT:
				params2.constrainHeight(this.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.getId(), WRAP_CONTENT);

				View testvee = new gvSqrt(this.getContext(), "blank");

				this.child.addView(testvee);

				// TODO: set layout constraints for alignment

				break;
		}
	}
}
