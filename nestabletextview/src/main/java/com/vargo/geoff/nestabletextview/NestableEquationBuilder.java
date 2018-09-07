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
	private EqType childType = NULL;
	private ConstraintLayout child = null;
	private boolean blankExpr = false;
	private boolean hasText = false;
	private int nestLevel = 0;

	public NestableEquationBuilder setNestLevel(int nestLevel) {
		this.nestLevel = nestLevel;
		return this;
	}

	public NestableEquationBuilder setHasText(boolean hasText) {
		this.hasText = hasText;
		return this;
	}

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

	public NestableEquationBuilder setBlankExpr(boolean blankExpr) {
		this.blankExpr = blankExpr;
		return this;
	}

	public NestableEquation createNestableEquation() {
		return new NestableEquation(context, str, eqType, childType, blankExpr, hasText, nestLevel);
	}
}