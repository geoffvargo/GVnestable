package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * Created by geoff on 12/19/2017.
 */

public class NestableTextView extends RelativeLayout {

	protected TextView text = null;
	protected RelativeLayout child = null;

	public NestableTextView(Context context, String str) {
		super(context);

		this.setRight(10);
		this.setBottom(10);

		text = new TextView(this.getContext());
		text.setText(str);

		child = new RelativeLayout(this.getContext());
		child.setPadding(20,20,0,0);
		child.setTop(25);
		child.setLeft(25);

		this.addView(text);
		this.addView(child);
	}

	public void addChild(String str) {
		NestableTextView newChild = new NestableTextView(this.getContext(), str);
		this.child.addView(newChild);
	}
}
