/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;

import static android.support.constraint.ConstraintSet.*;

/**
 * Created by geoff on 12/19/2017.
 */

public class NestableTextView extends ConstraintLayout {

	protected TextView text = null;
	protected ConstraintLayout child = null;

//	ConstraintLayout.LayoutParams layoutParams = new LayoutParams(ConstraintSet.MATCH_CONSTRAINT, ConstraintSet.MATCH_CONSTRAINT);

	public NestableTextView(Context context, String str) {
		super(context);

//		this.
		this.setId(View.generateViewId());
		text = new TextView(this.getContext());
		this.text.setId(View.generateViewId());
		text.setText(str);
		child = new ConstraintLayout(this.getContext());
		this.child.setId(View.generateViewId());

		ConstraintSet params = new ConstraintSet();

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

	public void addChild(String str) {
		NestableTextView newChild = new NestableTextView(this.getContext(), str);
		this.child.addView(newChild);
	}
}
