/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.*;

/**
 * The type Gv sqrt builder.
 */
public class gvSqrtBuilder {
	private Context context;
	private String str;
	private int lineHeight;
	private int lineWidth;

	/**
	 * Sets context.
	 * @param context
	 * the context
	 * @return the context
	 */
	public gvSqrtBuilder setContext(Context context) {
		this.context = context;
		return this;
	}

	/**
	 * Sets str.
	 * @param str
	 * the str
	 * @return the str
	 */
	public gvSqrtBuilder setStr(String str) {
		this.str = str;
		return this;
	}

	/**
	 * Sets line height.
	 * @param lineHeight
	 * the line height
	 * @return the line height
	 */
	public gvSqrtBuilder setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
		return this;
	}

	/**
	 * Sets line width.
	 * @param lineWidth
	 * the line width
	 * @return the line width
	 */
	public gvSqrtBuilder setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
		return this;
	}

	/**
	 * Create gv sqrt gv sqrt.
	 * @return the gv sqrt
	 */
	public gvSqrt createGvSqrt() {
		return new gvSqrt(context, str, lineHeight, lineWidth);
	}
}