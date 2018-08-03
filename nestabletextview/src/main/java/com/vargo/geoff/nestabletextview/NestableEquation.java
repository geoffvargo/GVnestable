/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.widget.TextView;

import static android.support.constraint.ConstraintSet.BOTTOM;
import static android.support.constraint.ConstraintSet.LEFT;
import static android.support.constraint.ConstraintSet.PARENT_ID;
import static android.support.constraint.ConstraintSet.RIGHT;
import static android.support.constraint.ConstraintSet.TOP;
import static android.support.constraint.ConstraintSet.WRAP_CONTENT;
import static com.vargo.geoff.nestabletextview.EqType.NORMAL;
import static com.vargo.geoff.nestabletextview.EqType.SQRT;

/**
 * Created by geoff on 12/21/2017.
 */
public class NestableEquation extends NestableTextView {

	/**
	 * The Value.
	 */
	protected String value = "";
	/**
	 * The Params 1.
	 */
	ConstraintSet params1 = new ConstraintSet();
	/**
	 * The Params 2.
	 */
	ConstraintSet params2 = new ConstraintSet();
	private EqType eqType;

	/**
	 * Instantiates a new Nestable equation.
	 *
	 * @param context
	 * 		the context
	 * @param str
	 * 		the str
	 * @param eqType
	 * 		the eq type
	 */
	public NestableEquation(Context context, String str, EqType eqType) {
		super(context, str, false);
		value = str;
		this.eqType = eqType;
		eqTyper(this.eqType, this.eqType);
	}

	/**
	 * Eq typer.
	 *
	 * @param parentType
	 * 		the parent type
	 * @param childType
	 * 		the child type
	 */
	protected void eqTyper(EqType parentType, EqType childType) {
		NestableTextView eqNew = null;

		params1.constrainHeight(this.text.getId(), WRAP_CONTENT);
		params1.constrainWidth(this.text.getId(), WRAP_CONTENT);

		switch (parentType) {
			case NORMAL:
				// TODO: rewrite the 'NORMAL' section to handle non-text operands
				params1.constrainHeight(this.getId(), WRAP_CONTENT);
				params1.constrainHeight(this.getId(), WRAP_CONTENT);

				params1.connect(this.text.getId(), TOP, this.getId(), TOP, 0);
				params1.connect(this.text.getId(), LEFT, this.getId(), LEFT, 0);

				params1.constrainHeight(this.child.getId(), 0);
				params1.constrainWidth(this.child.getId(), 0);

				params1.applyTo(this);
				break;
			case FRACTION:
				// TODO: rewrite the 'FRACTION' section to handle non-text operands
				eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr("blank").setEqType(NORMAL).createNestableEquation();

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
				// TODO: rewrite the 'EXPONENT' section to handle non-text operands
				params2.constrainHeight(this.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.getId(), WRAP_CONTENT);

				eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr("").setEqType(NORMAL).createNestableEquation();
				if (eqNew.text.getClass().equals(TextView.class)) {
					((TextView) eqNew.text).setTextSize(8);
				}
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
				ConstraintSet radical = new ConstraintSet();
				ConstraintSet radicand = new ConstraintSet();

				radical.constrainHeight(this.getId(), WRAP_CONTENT);
				radical.constrainWidth(this.getId(), WRAP_CONTENT);

				radical.constrainHeight(this.child.getId(), WRAP_CONTENT);
				radical.constrainWidth(this.child.getId(), WRAP_CONTENT);

				eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr(value).setEqType(NORMAL).createNestableEquation();
				this.child.addView(eqNew);

				//// Draw sqrt symbol
				gvSqrt testvee = new gvSqrt(this.getContext(), "", eqNew.text.getMeasuredHeight(), eqNew.text.getMeasuredWidth() + 36);
				this.changeRootView(testvee);

				radical.constrainHeight(testvee.getId(), WRAP_CONTENT);
				radical.constrainWidth(testvee.getId(), WRAP_CONTENT);

				radical.constrainHeight(eqNew.getId(), WRAP_CONTENT);
				radical.constrainWidth(eqNew.getId(), WRAP_CONTENT);

				radical.connect(testvee.getId(), LEFT, this.getId(), LEFT, 0);
				radical.connect(testvee.getId(), TOP, this.getId(), TOP, 0);

				radical.connect(this.child.getId(), RIGHT, this.getId(), RIGHT, 0);
				radical.connect(this.child.getId(), BOTTOM, this.getId(), BOTTOM, 0);

				radical.applyTo(this);
				testvee.setLayoutParams(new LayoutParams((int) testvee.getTotalWidth(), (int) testvee.getTotalHeight()));

				break;
		}
	}

	/**
	 * Create new child.
	 *
	 * @param type
	 * 		the type
	 */
	public void createNewChild(EqType type) {
		NestableEquation nChild = null;
		switch (type) {
			case NORMAL:
			case FRACTION:
			case EXPONENT:
			case ORDINAL:
				break;
			case SQRT:
				nChild = new NestableEquation(this.getContext(), "", SQRT);
				break;
		}

		if (nChild != null) {
			this.addChild(nChild);
		}
	}

	/**
	 * Add child.
	 *
	 * @param child
	 * 		the child
	 */
	public void addChild(NestableEquation child) {
		this.child.removeAllViews();
		this.child.addView(child);
		eqTyper(this.eqType, child.eqType);
	}

	/**
	 * Gets equation type.
	 *
	 * @return the equation type enum
	 */
	public EqType getEqType() {
		return eqType;
	}

	/**
	 * Sets eq type.
	 *
	 * @param eqType
	 * 		the eq type
	 */
	public void setEqType(EqType eqType) {
		this.eqType = eqType;
		eqTyper(this.eqType, this.eqType);
	}

	/**
	 * Sets child eq type.
	 *
	 * @param eqType
	 * 		the eq type
	 */
	public void setChildEqType(EqType eqType) {
	}

	/**
	 * Gets the NestableEquation nested within this NestableEquation.
	 *
	 * @return the nested NestableEquation
	 */
	public NestableEquation getNestText() {
		return (NestableEquation) this.text;
	}
}
