/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import static com.vargo.geoff.nestabletextview.EqType.*;

public class NestableEquationBuilder {
	private Context context;
	private String str;
	private EqType eqType = NORMAL;
	private EqType childType = NORMAL;
	private ConstraintLayout child = null;

	public NestableEquationBuilder setContext(Context context) {
		this.context = context;
		return this;
	}

	public NestableEquationBuilder setStr(String str) {
		this.str = str;
		return this;
	}

	public NestableEquationBuilder setEqType(EqType eqType) {
		this.eqType = eqType;
		return this;
	}

	public NestableEquationBuilder setChildType(EqType childType) {
		this.childType = childType;
		return this;
	}

	public NestableEquation createNestableEquation() {
		return new NestableEquation(context, str, eqType, childType);
	}
}