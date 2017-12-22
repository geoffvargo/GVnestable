/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by geoff on 12/19/2017.
 */

public class NestableTextView extends RelativeLayout {

	protected TextView text = null;
	protected RelativeLayout child = null;

	public NestableTextView(Context context, String str) {
		super(context);

		this.setId(View.generateViewId());
		text = new TextView(this.getContext());
		this.text.setId(View.generateViewId());
		text.setText(str);

		child = new RelativeLayout(this.getContext());
		this.child.setId(View.generateViewId());

		this.addView(text);
		this.addView(child);
	}

	public void addChild(String str) {
		NestableTextView newChild = new NestableTextView(this.getContext(), str);
		this.child.addView(newChild);
	}
}
