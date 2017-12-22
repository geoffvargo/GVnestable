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
				RelativeLayout.LayoutParams relLayoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				relLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
				this.child.setLayoutParams(relLayoutParam);
				this.child.addView(new NestableEquation(this.getContext(), "sdfg", EqType.NORMAL));
				break;
			case EXPONENT:
				break;
			case ORDINAL:
				break;
		}
	}
}
