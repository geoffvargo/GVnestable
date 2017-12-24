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
		ConstraintSet params = new ConstraintSet();
		params.constrainHeight(PARENT_ID, WRAP_CONTENT);
		params.constrainWidth(PARENT_ID, WRAP_CONTENT);
		params.applyTo(this);
		this.setId(View.generateViewId());
		text = new TextView(this.getContext());
		this.text.setId(View.generateViewId());
		text.setText(str);

		child = new ConstraintLayout(this.getContext());
		params.applyTo(this.child);
//		this.child.
		this.child.setId(View.generateViewId());

		this.addView(text);
		this.addView(child);
	}

	public void addChild(String str) {
		NestableTextView newChild = new NestableTextView(this.getContext(), str);
		this.child.addView(newChild);
	}
}
