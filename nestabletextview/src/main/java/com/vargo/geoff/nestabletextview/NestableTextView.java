package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by geoff on 12/19/2017.
 */

public class NestableTextView extends RelativeLayout {

	protected TextView text = null;
	protected NestableTextView child = null;

	public NestableTextView(Context context) {
		super(context);
		text = new TextView(context);
		child = new NestableTextView(context);

		text.setText("asdf");

		child.setTop(2);
		child.setLeft(2);
	}
}
