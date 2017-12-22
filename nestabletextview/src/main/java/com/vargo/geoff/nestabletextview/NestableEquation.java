/*
 * Copyright (c) 2017. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.widget.RelativeLayout;

import static android.widget.RelativeLayout.LayoutParams.*;
import static com.vargo.geoff.nestabletextview.EqType.*;

/**
 * Created by geoff on 12/21/2017.
 */

public class NestableEquation extends NestableTextView {

	public NestableEquation(Context context, String str, EqType eqType) {
		super(context, str);
		eqTyper(eqType);
	}

	protected void eqTyper(EqType eqType) {
		NestableEquation eqNew = null;
		switch (eqType) {
			case NORMAL:
				break;
			case FRACTION:
				RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
				params1.addRule(BELOW, this.text.getId());

				System.out.printf("text id: %d\n", this.text.getId());

				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);

				this.child.setLayoutParams(params1);
				this.child.addView(eqNew);
				break;
			case EXPONENT:
				RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
				params2.addRule(ABOVE, this.text.getId());
				params2.addRule(RIGHT_OF, this.text.getId());
				this.child.setLayoutParams(params2);

				eqNew = new NestableEquation(this.getContext(), "blank", NORMAL);

				this.child.addView(eqNew);
				break;
			case ORDINAL:
				break;
		}
	}
}
