/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;

public class NestableEquationBuilder {
	private Context context;
	private String str;
	private EqType eqType;

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

	public NestableEquation createNestableEquation() {
		return new NestableEquation(context, str, eqType);
	}
}