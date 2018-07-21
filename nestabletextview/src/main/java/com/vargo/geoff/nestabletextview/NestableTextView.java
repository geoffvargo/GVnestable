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
import static android.support.constraint.ConstraintSet.LEFT;
import static android.support.constraint.ConstraintSet.PARENT_ID;
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
	protected View text = null;
	/**
	 * The Child.
	 */
	protected ConstraintLayout child = null;
	/**
	 * The Is text.
	 */
	protected boolean isText = true;
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
	 * Instantiates a new Nestable text view.
	 *
	 * @param context
	 * 		the context
	 */
	public NestableTextView(Context context) {
		super(context);
	}

	/**
	 * Instantiates a new Nestable text view.
	 *
	 * @param context
	 * 		the context
	 * @param str
	 * 		the str
	 * @param isText
	 * 		the is text
	 */
	public NestableTextView(Context context, String str, boolean isText) {
		super(context);

		this.setId(View.generateViewId());

		if (this.isText) {
			text = new TextView(this.getContext());
			this.text.setId(View.generateViewId());
			((TextView) text).setText(str);
			((TextView) text).setTextColor(BLACK);
		} else {
			text = new View(this.getContext());
			this.text.setId(View.generateViewId());
			((TextView) text).setTextColor(BLACK);
		}

		child = new ConstraintLayout(this.getContext());

		ConstraintSet cset = new ConstraintSet();
		this.child.setId(View.generateViewId());

		cset.constrainHeight(child.getId(), MATCH_PARENT);
		cset.constrainWidth(child.getId(), MATCH_PARENT);
		child.setConstraintSet(cset);

		this.text.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		this.neqWidth = this.text.getMeasuredWidth();
		this.neqHeight = this.text.getMeasuredHeight();

		params.constrainHeight(this.getId(), WRAP_CONTENT);
		params.constrainWidth(this.getId(), WRAP_CONTENT);

		params.constrainHeight(this.text.getId(), WRAP_CONTENT);
		params.constrainWidth(this.text.getId(), WRAP_CONTENT);

		params.connect(this.getId(), TOP, PARENT_ID, TOP, 0);
		params.connect(this.getId(), LEFT, PARENT_ID, LEFT, 0);

		params.constrainHeight(this.child.getId(), 0);
		params.constrainWidth(this.child.getId(), 0);

		params.connect(this.text.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.text.getId(), LEFT, this.getId(), LEFT, 0);

		params.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.child.getId(), LEFT, this.getId(), LEFT, 0);

		params.applyTo(this);

		this.addView(text);
		this.addView(child);
	}

/*
	public NestableTextView(Context context, String str) {
		super(context);

		this.setId(View.generateViewId());

		if (this.isText) {
			text = new TextView(this.getContext());
			this.text.setId(View.generateViewId());
			((TextView) text).setText(str);
			((TextView) text).setTextColor(BLACK);
		} else {
			text = new View(this.getContext());
			this.text.setId(View.generateViewId());
			((TextView) text).setTextColor(BLACK);
		}

		child = new ConstraintLayout(this.getContext());

		ConstraintSet cset = new ConstraintSet();
		this.child.setId(View.generateViewId());

		cset.constrainHeight(child.getId(), MATCH_PARENT);
		cset.constrainWidth(child.getId(), MATCH_PARENT);
		child.setConstraintSet(cset);

		this.text.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		this.neqWidth = this.text.getMeasuredWidth();
		this.neqHeight = this.text.getMeasuredHeight();

		params.constrainHeight(this.getId(), WRAP_CONTENT);
		params.constrainWidth(this.getId(), WRAP_CONTENT);

		params.constrainHeight(this.text.getId(), WRAP_CONTENT);
		params.constrainWidth(this.text.getId(), WRAP_CONTENT);

		params.connect(this.getId(), TOP, PARENT_ID, TOP, 0);
		params.connect(this.getId(), LEFT, PARENT_ID, LEFT, 0);

		params.constrainHeight(this.child.getId(), 0);
		params.constrainWidth(this.child.getId(), 0);

		params.connect(this.text.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.text.getId(), LEFT, this.getId(), LEFT, 0);

		params.connect(this.child.getId(), TOP, this.getId(), TOP, 0);
		params.connect(this.child.getId(), LEFT, this.getId(), LEFT, 0);

		params.applyTo(this);

		this.addView(text);
		this.addView(child);
	}
*/

	/**
	 * Gets nest text.
	 *
	 * @return the nest text
	 */
	public View getNestText() {
		return text;
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

		this.text = view;

		this.addView(this.text, 0);
	}
}
