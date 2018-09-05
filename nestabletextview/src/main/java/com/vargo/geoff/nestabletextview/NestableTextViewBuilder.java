/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;

public class NestableTextViewBuilder {
	private Context context;
	private String str;
	private boolean isText = false;
	private boolean blankExpr = false;

	public NestableTextViewBuilder() {
	}

	public NestableTextViewBuilder setContext(Context context) {
		this.context = context;
		return this;
	}

	public NestableTextViewBuilder setStr(String str) {
		this.str = str;
		return this;
	}

	public NestableTextViewBuilder setIsText(boolean isText) {
		this.isText = isText;
		return this;
	}

	private NestableTextViewBuilder setBlankExpr(boolean blankExpr) {
		this.blankExpr = blankExpr;
		return this;
	}

	public NestableTextView createNestableTextView() {
		return new NestableTextView(context, str, isText, blankExpr);
	}
}