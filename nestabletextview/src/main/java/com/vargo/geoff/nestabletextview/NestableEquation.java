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

	private int nestLevel = 0;
	private NestableEquation parentNode = null;

	/**
	 * Instantiates a new Nestable equation.
	 * @param context
	 * the context
	 * @param str
	 * the str
	 * @param eqType
	 * @param childType
	 * @param blankExpr
	 * @param hasText
	 * @param nestLevel
	 */
	public NestableEquation(Context context, String str, EqType eqType, EqType childType, boolean blankExpr, boolean hasText, int nestLevel) {
		super(context, str, hasText, blankExpr);
		value = str;
		this.eqType = eqType;
		this.childEqType = childType;
		this.nestLevel = nestLevel;
		eqTyper(this.eqType, this.childEqType);
	}

	/**
	 * Eq typer.
	 * @param parentType
	 * the parent type
	 * @param childType
	 * the child type
	 */
	protected void eqTyper(EqType parentType, EqType childType) {
		NestableEquation eqNew = null;

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
				this.expr.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

				int width = this.expr.getMeasuredWidthAndState();
				gvFractionBar bar = new gvFractionBar(this.getContext(), width + 15);

				this.addView(bar, 1);

				params1.constrainHeight(bar.getId(), 5);
				params1.constrainWidth(bar.getId(), bar.getTotalWidth());

				params1.connect(bar.getId(), TOP, this.expr.getId(), BOTTOM, 0);
				//				params1.connect(bar.getId(), LEFT, this.getId(), LEFT, 0);

				params1.centerHorizontally(this.expr.getId(), this.getId());
				params1.centerHorizontally(bar.getId(), this.getId());

				if (childType != NULL) {
					params1.constrainHeight(this.child.getId(), WRAP_CONTENT);
					params1.constrainWidth(this.child.getId(), WRAP_CONTENT);
					params1.centerHorizontally(this.child.getId(), this.getId());

					eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr("blank").setEqType(childType).createNestableEquation();

					this.child.addView(eqNew);

					params1.connect(this.getId(), TOP, PARENT_ID, BOTTOM, 0);
					params1.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);

					params1.centerHorizontally(eqNew.getId(), this.getId());

					params1.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				} else {
					params1.connect(this.child.getId(), RIGHT, this.expr.getId(), RIGHT, 0);
					params1.connect(this.child.getId(), BOTTOM, this.expr.getId(), TOP, 0);
				}
				params1.connect(this.child.getId(), TOP, bar.getId(), BOTTOM, 0);

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

				if (childType != NULL) {
					eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr(value).setEqType(childEqType).createNestableEquation();
					radical.constrainHeight(this.child.getId(), WRAP_CONTENT);
					radical.constrainWidth(this.child.getId(), WRAP_CONTENT);
					this.child.addView(eqNew);
				} else {
					eqNew = new NestableEquationBuilder().setContext(this.getContext()).setStr(value).setEqType(NORMAL).createNestableEquation();
					radical.constrainHeight(this.child.getId(), WRAP_CONTENT);
					radical.constrainWidth(this.child.getId(), WRAP_CONTENT);
					this.child.addView(eqNew);
				}

				this.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

				int tempHt = eqNew.getMeasuredHeightAndState();
				int tempWdth = eqNew.getMeasuredWidthAndState();

				//// Draw sqrt symbol
				gvSqrt testvee;
				if (eqNew.getEqType() == NORMAL) {
					int lineWidth = eqNew.expr.getMeasuredWidth() + 36;
					int measuredHeight = eqNew.expr.getMeasuredHeight();
					testvee = new gvSqrtBuilder().setContext(this.getContext()).setStr("").setLineHeight(measuredHeight).setLineWidth(lineWidth).createGvSqrt();
				} else {
					testvee = new gvSqrtBuilder().setContext(this.getContext()).setStr("").setLineHeight(tempHt - 10).setLineWidth(tempWdth + 36).createGvSqrt();
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
	 * @return the equation type enum
	 */
	public EqType getEqType() {
		return eqType;
	}

	/**
	 * Sets eq type.
	 * @param eqType
	 * the eq type
	 */
	public void setEqType(EqType eqType) {
		this.eqType = eqType;
		eqTyper(this.eqType, this.eqType);
	}

	/**
	 * Add child.
	 * @param child
	 * the child
	 */
	public void setChild(NestableEquation child) {
		child.nestLevel = this.getNestLevel() + 1;
		child.setParentNode(this);
		this.child.removeAllViews();
		this.child.addView(child);
		//		eqTyper(this.eqType, child.eqType);
		eqConstrainor(this.eqType, child.eqType, child);
	}

	public int getNestLevel() {
		return nestLevel;
	}

	public void setNestLevel(int nestLevel) {
		this.nestLevel = nestLevel;
	}

	public void eqConstrainor(EqType parent, EqType currType, NestableEquation curr) {
		switch (parent) {
			case NORMAL:
				break;
			case FRACTION:
				params3 = new ConstraintSet();
				params3.constrainHeight(this.child.getId(), WRAP_CONTENT);
				params3.constrainWidth(this.child.getId(), WRAP_CONTENT);

				params3.connect(this.getId(), TOP, PARENT_ID, BOTTOM, 0);

				params3.connect(this.child.getId(), LEFT, PARENT_ID, LEFT);
				params3.connect(this.child.getId(), TOP, this.expr.getId(), BOTTOM, 0);

				params3.centerHorizontally(this.child.getId(), PARENT_ID);

				if (currType == FRACTION) {
					gvFractionBar parentBar = (gvFractionBar) this.getChildAt(1);
					int tempwidth = parentBar.getTotalWidth();

					params3.constrainWidth(parentBar.getId(), tempwidth + 30);
					params3.constrainHeight(parentBar.getId(), 5);

					parentBar.resize(tempwidth + 30);

					params3.connect(parentBar.getId(), TOP, this.expr.getId(), BOTTOM, 0);
					params3.centerHorizontally(parentBar.getId(), this.getId());

					//// resize all the parent fraction bars
					NestableEquation cursor = this.getParentNode();
					while (cursor != null) {
						if (cursor.getEqType() == FRACTION) {
							gvFractionBar tpBar = (gvFractionBar) cursor.getChildAt(1);

							int csrWidth = tpBar.getTotalWidth();

							ConstraintSet ptemp = new ConstraintSet();

							ptemp.constrainWidth(tpBar.getId(), csrWidth + 30);
							ptemp.constrainHeight(tpBar.getId(), 5);

							tpBar.resize(csrWidth + 30);

							ptemp.connect(tpBar.getId(), TOP, cursor.expr.getId(), BOTTOM, 0);
							ptemp.centerHorizontally(tpBar.getId(), this.getId());

							ptemp.applyTo(cursor);
						}

						cursor = cursor.getParentNode();
					}
				}

				params3.applyTo(this);
				break;
			case EXPONENT:
			case ORDINAL:
			case SQRT:
				break;
			case NULL:
				break;
		}
	}

	public NestableEquation getParentNode() {
		return parentNode;
	}

	public void setParentNode(NestableEquation parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * Sets child eq type.
	 * @param eqType
	 * the eq type
	 */
	public void setChildEqType(EqType eqType) {
	}

	/**
	 * Gets the NestableEquation nested within this NestableEquation.
	 * @return the nested NestableEquation
	 */
	public NestableEquation getNestText() {
		return (NestableEquation) this.expr;
	}
}
