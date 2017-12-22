/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by geoff on 12/21/2017.
 */

public class NestableEquation extends NestableTextView {

	public NestableEquation(Context context, String str, EqType eqType) {
		super(context, str);
		eqTyper(eqType);
	}

	protected void eqTyper(EqType eqType) {
		switch (eqType) {
			case NORMAL:
				break;
			case FRACTION:
				RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

				params1.addRule(RelativeLayout.BELOW, this.text.getId());

				System.out.printf("text id: %d\n", this.text.getId());

				NestableEquation eqNew = new NestableEquation(this.getContext(), "sdfg", EqType.NORMAL);

				this.child.setLayoutParams(params1);
				this.child.addView(eqNew);
				break;
			case EXPONENT:
				break;
			case ORDINAL:
				break;
		}
	}
}
