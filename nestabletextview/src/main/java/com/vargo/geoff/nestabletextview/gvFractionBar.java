/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.*;
import android.view.*;

public class gvFractionBar extends View {

	/**
	 * The total width of the fraction bar.
	 */
	protected int totalWidth = 0;

	/**
	 * The actual shape of the fraction bar.
	 */
	protected ShapeDrawable mBar;

	private int thickness;

	private PathShape temp;

	/**
	 * Simple constructor to use when creating a view from code.
	 *
	 * @param context
	 * 		The Context the view is running in, through which it can
	 * 		access the current theme, resources, etc.
	 */
	public gvFractionBar(Context context, int totalWidth) {
		super(context);

		this.setId(View.generateViewId());

		thickness = 5;

		Path symbol = new Path();

		symbol.addRect(0, 0, totalWidth, thickness, Path.Direction.CW);

		mBar = new ShapeDrawable();
		temp = new PathShape(symbol, totalWidth, thickness);
		mBar.setShape(temp);

		//// This is to make our fraction bar scalable
		ScaleDrawable dtemp = new ScaleDrawable(mBar, 0, totalWidth, thickness);
		mBar = (ShapeDrawable) dtemp.getDrawable();
		assert mBar != null;
		mBar.setBounds(0, 0, totalWidth, thickness);

		mBar.getPaint().setColor(Color.BLACK);
		mBar.getPaint().setStyle(Paint.Style.FILL);
		mBar.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);

		this.setLayoutParams(new ViewGroup.LayoutParams(totalWidth, thickness));
	}

	/**
	 * Implement this to do your drawing.
	 *
	 * @param canvas
	 * 		the canvas on which the background will be drawn
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mBar.draw(canvas);
	}
}
