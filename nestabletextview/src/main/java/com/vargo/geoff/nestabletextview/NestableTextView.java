/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;

import static android.graphics.Color.BLACK;
import static android.support.constraint.ConstraintSet.BOTTOM;
import static android.support.constraint.ConstraintSet.LEFT;
import static android.support.constraint.ConstraintSet.PARENT_ID;
import static android.support.constraint.ConstraintSet.RIGHT;
import static android.support.constraint.ConstraintSet.TOP;
import static android.support.constraint.ConstraintSet.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by geoff on 12/19/2017.
 */
public class NestableTextView extends ConstraintLayout {

	/**
	 * The Text.
	 */
	protected View expr = null;
	/**
	 * The Child.
	 */
	protected ConstraintLayout child = null;
	/**
	 * The Is expr.
	 */
	protected boolean isText = false;
	/**
	 * The Neq width.
	 */
	protected int neqWidth = 0;
	/**
	 * The Neq height.
	 */
	protected int neqHeight = 0;
	/**
	 * The Params.
	 */
	protected ConstraintSet params = new ConstraintSet();

	/**
	 * Instantiates a new Nestable expr view.
	 *
	 * @param context
	 * 		the context
	 */
	public NestableTextView(Context context) {
		super(context);
	}

	/**
	 * Instantiates a new Nestable expr view.
	 *
	 * @param context
	 * 		the context
	 * @param str
	 * 		the str
	 * @param isText
	 * 		the is expr
	 */
	public NestableTextView(Context context, String str, boolean isText) {
		super(context);

		this.setId(View.generateViewId());
		this.isText = isText;

		if (this.isText) {
			expr = new TextView(this.getContext());
			this.expr.setId(View.generateViewId());
			((TextView) expr).setText(str);
			((TextView) expr).setTextColor(BLACK);
		} else {
			expr = new View(this.getContext());
			this.expr.setId(View.generateViewId());
//			((TextView) expr).setTextColor(BLACK);
		}

		child = new ConstraintLayout(this.getContext());

		ConstraintSet cset = new ConstraintSet();
		this.child.setId(View.generateViewId());

		cset.constrainHeight(child.getId(), MATCH_PARENT);
		cset.constrainWidth(child.getId(), MATCH_PARENT);
		child.setConstraintSet(cset);

		this.expr.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		this.neqWidth = this.expr.getMeasuredWidth();
		this.neqHeight = this.expr.getMeasuredHeight();

		params.constrainHeight(this.getId(), WRAP_CONTENT);
		params.constrainWidth(this.getId(), WRAP_CONTENT);

		if (this.isText) {
			params.constrainHeight(this.expr.getId(), WRAP_CONTENT);
			params.constrainWidth(this.expr.getId(), WRAP_CONTENT);
		} else {
			params.constrainHeight(this.expr.getId(), MATCH_PARENT);
			params.constrainWidth(this.expr.getId(), MATCH_PARENT);
		}

		params.connect(this.getId(), TOP, PARENT_ID, TOP, 0);
		params.connect(this.getId(), LEFT, PARENT_ID, LEFT, 0);

		params.constrainHeight(this.child.getId(), 0);
		params.constrainWidth(this.child.getId(), 0);

		params.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.expr.getId(), LEFT, this.getId(), LEFT, 0);
		params.connect(this.expr.getId(), RIGHT, this.getId(), RIGHT, 0);
		params.connect(this.expr.getId(), BOTTOM, this.getId(), BOTTOM, 0);

		params.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.child.getId(), LEFT, this.getId(), LEFT, 0);

		params.applyTo(this);

		this.addView(expr);
		this.addView(child);
	}

/*
	public NestableTextView(Context context, String str) {
		super(context);

		this.setId(View.generateViewId());

		if (this.isText) {
			expr = new TextView(this.getContext());
			this.expr.setId(View.generateViewId());
			((TextView) expr).setText(str);
			((TextView) expr).setTextColor(BLACK);
		} else {
			expr = new View(this.getContext());
			this.expr.setId(View.generateViewId());
			((TextView) expr).setTextColor(BLACK);
		}

		child = new ConstraintLayout(this.getContext());

		ConstraintSet cset = new ConstraintSet();
		this.child.setId(View.generateViewId());

		cset.constrainHeight(child.getId(), MATCH_PARENT);
		cset.constrainWidth(child.getId(), MATCH_PARENT);
		child.setConstraintSet(cset);

		this.expr.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		this.neqWidth = this.expr.getMeasuredWidth();
		this.neqHeight = this.expr.getMeasuredHeight();

		params.constrainHeight(this.getId(), WRAP_CONTENT);
		params.constrainWidth(this.getId(), WRAP_CONTENT);

		params.constrainHeight(this.expr.getId(), WRAP_CONTENT);
		params.constrainWidth(this.expr.getId(), WRAP_CONTENT);

		params.connect(this.getId(), TOP, PARENT_ID, TOP, 0);
		params.connect(this.getId(), LEFT, PARENT_ID, LEFT, 0);

		params.constrainHeight(this.child.getId(), 0);
		params.constrainWidth(this.child.getId(), 0);

		params.connect(this.expr.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.expr.getId(), LEFT, this.getId(), LEFT, 0);

		params.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.child.getId(), LEFT, this.getId(), LEFT, 0);

		params.applyTo(this);

		this.addView(expr);
		this.addView(child);
	}
*/

	/**
	 * Gets nest expr.
	 *
	 * @return the nest expr
	 */
	public View getNestText() {
		return expr;
	}

	/**
	 * Gets child.
	 *
	 * @return the child
	 */
	public ConstraintLayout getChild() {
		return child;
	}

	/**
	 * Gets neq width.
	 *
	 * @return the neq width
	 */
	public int getNeqWidth() {
		return neqWidth;
	}

	/**
	 * Gets neq height.
	 *
	 * @return the neq height
	 */
	public int getNeqHeight() {
		return neqHeight;
	}

	/**
	 * Change root view.
	 *
	 * @param view
	 * 		the view
	 */
	public void changeRootView(View view) {
		this.removeViewAt(0);

		this.expr = view;

		this.addView(this.expr, 0);
	}
}
