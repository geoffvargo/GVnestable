/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.*;
import android.support.constraint.*;

import static android.support.constraint.ConstraintSet.*;
import static com.vargo.geoff.nestabletextview.EqType.*;

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
	ConstraintSet params3 = new ConstraintSet();
	private EqType eqType = NORMAL;
	private EqType childEqType = NORMAL;

	/**
	 * Instantiates a new Nestable equation.
	 *
	 * @param context
	 * 		the context
	 * @param str
	 * 		the str
	 * @param eqType
	 * @param childType
	 * @param blankExpr
	 * @param hasText
	 */
	public NestableEquation(Context context, String str, EqType eqType, EqType childType, boolean blankExpr, boolean hasText) {
		super(context, str, hasText, blankExpr);
		value = str;
		this.eqType = eqType;
		this.childEqType = childType;
		eqTyper(this.eqType, this.childEqType);
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
		NestableEquation eqNew = null;
//		NestableTextView eqNew = null;

		params1.constrainHeight(this.expr.getId(), WRAP_CONTENT);
		params1.constrainWidth(this.expr.getId(), WRAP_CONTENT);

		switch (parentType) {
			case NORMAL:
				params1.constrainHeight(this.getId(), WRAP_CONTENT);
				params1.constrainHeight(this.getId(), WRAP_CONTENT);

				params1.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);
				params1.connect(this.expr.getId(), LEFT, this.getId(), LEFT, 0);

				params1.constrainHeight(this.child.getId(), 0);
				params1.constrainWidth(this.child.getId(), 0);

				params1.applyTo(this);
				break;
			case FRACTION:
				if (childType != NULL) {
					params1.constrainHeight(this.child.getId(), WRAP_CONTENT);
					params1.constrainWidth(this.child.getId(), WRAP_CONTENT);
					eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr("blank").setEqType(childType).createNestableEquation();

					this.child.addView(eqNew);
					params1.connect(this.getId(), TOP, PARENT_ID, BOTTOM, 0);
					params1.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);

					params1.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				} else {
					params1.connect(this.child.getId(), RIGHT, this.expr.getId(), RIGHT, 0);
					params1.connect(this.child.getId(), BOTTOM, this.expr.getId(), TOP, 0);
				}
				params1.connect(this.child.getId(), TOP, this.expr.getId(), BOTTOM, 0);

				params1.applyTo(this);

				break;
			case EXPONENT:
				params2.constrainHeight(this.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.getId(), WRAP_CONTENT);

				if (childType != NULL) {
					// Create NestableEquation for child
					eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr("blank").setEqType(childType).createNestableEquation();

					// Set the pivot point to (0, 0) for scaling down eqNew
					eqNew.setPivotX(0);
					eqNew.setPivotY(0);

					// Scale down eqNew to superscript size
					eqNew.setScaleX(0.5f);
					eqNew.setScaleY(0.5f);

					this.child.addView(eqNew);

					params2.connect(eqNew.getId(), LEFT, this.child.getId(), RIGHT, 0);
					params2.connect(eqNew.getId(), TOP, this.child.getId(), TOP, 0);
				}

				params2.connect(this.child.getId(), LEFT, this.expr.getId(), RIGHT, 0);
				params2.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
				params2.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params2.constrainWidth(this.child.getId(), WRAP_CONTENT);

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

				eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr(value).setEqType(childEqType).createNestableEquation();
//				eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr(value).setEqType(NORMAL).createNestableEquation();

				this.child.addView(eqNew);

				this.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

				int tempHt = eqNew.getMeasuredHeightAndState();
				int tempWdth = eqNew.getMeasuredWidthAndState();

				//// Draw sqrt symbol
				gvSqrt testvee;
				if (eqNew.getEqType() == NORMAL) {
					testvee = new gvSqrt(this.getContext(), "", eqNew.expr.getMeasuredHeight(), eqNew.expr.getMeasuredWidth() + 36);
				} else {
					testvee = new gvSqrt(this.getContext(), "asdf", tempHt - 10, tempWdth + 36);
				}

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
			case NULL:
//				params1.constrainHeight(this.getId(), WRAP_CONTENT);
//				params1.constrainHeight(this.getId(), WRAP_CONTENT);
//
//				params1.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);
//				params1.connect(this.expr.getId(), LEFT, this.getId(), LEFT, 0);
//
//				params1.constrainHeight(this.child.getId(), 0);
//				params1.constrainWidth(this.child.getId(), 0);
//
//				params1.applyTo(this);
				break;
		}
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
				nChild = new NestableEquation(this.getContext(), "", SQRT, NULL, false, false);
				break;
		}

		if (nChild != null) {
			this.setChild(nChild);
		}
	}

	public void eqConstrainor(EqType parent, EqType curr) {
		switch (parent) {
			case NORMAL:
			case FRACTION:
				params1 = new ConstraintSet();
				params1.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params1.constrainWidth(this.child.getId(), WRAP_CONTENT);

				params1.connect(this.getId(), TOP, PARENT_ID, BOTTOM, 0);

				params1.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				params1.connect(this.child.getId(), TOP, this.expr.getId(), BOTTOM, 0);
				params1.applyTo(this);
				break;
			case EXPONENT:
			case ORDINAL:
			case SQRT:
			case NULL:
				break;
		}
	}

	/**
	 * Add child.
	 *
	 * @param child
	 * 		the child
	 */
	public void setChild(NestableEquation child) {
		this.child.removeAllViews();
//		this.child.rem
		this.child.addView(child);
//		eqTyper(this.eqType, child.eqType);
		eqConstrainor(this.eqType, child.eqType);
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
		return (NestableEquation) this.expr;
	}
}
