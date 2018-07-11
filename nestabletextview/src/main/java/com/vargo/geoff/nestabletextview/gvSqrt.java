/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View;
import android.view.ViewGroup;

//public class gvSqrt extends View {
public class gvSqrt extends View {
	//public class gvSqrt extends android.support.v7.widget.AppCompatTextView {
	//public class gvSqrt extends NestableTextView {
//	protected float ypos = 22;
	protected float ypos = 0;
	//	protected float xpos = 110;
	protected float xpos = 36;

	protected int lineHeight = 1;
	protected int lineWidth = 0;

	protected ShapeDrawable mSqrt;
	private final PathShape temp;
	/*public gvSqrt(Context context, String str) {
		super(context);
		this.setId(View.generateViewId());
		this.setWillNotDraw(false);
	}*/

	/*public gvSqrt(Context context, String str, float lineHeight) {
		super(context);
		this.setWillNotDraw(false);
		this.setId(View.generateViewId());
		this.lineHeight = lineHeight;
	}*/

	public gvSqrt(Context context, String str, int lineHeight, int lineWidth) {
		super(context);
//		mSqrt = new ShapeDrawable(new PathShape());
		this.setId(View.generateViewId());
		this.setWillNotDraw(false);
		this.lineHeight = lineHeight;
		this.lineWidth = lineWidth;

		float x_slashbottom_right, x_2, y_slashbottom_right, y_2;
		float thickness = 5;

		Path symbol = new Path();
		Path topBar = new Path();
		topBar.moveTo(xpos + lineWidth, ypos);
		topBar.lineTo(xpos + lineWidth, ypos + thickness);
		topBar.lineTo(xpos, ypos + thickness);
		topBar.lineTo(xpos, ypos);
		topBar.lineTo(xpos + lineWidth, ypos);
//		canvas.drawPath(topBar, color1);
		symbol.addPath(topBar, xpos, ypos);

		Path slash = new Path();
		slash.moveTo(xpos, ypos);
		slash.rLineTo(thickness, 0);
		slash.rLineTo(-36, 76 + lineHeight);
//		slash.rLineTo(-36, 76 * lineHeight);

		x_slashbottom_right = xpos + thickness - 36;
		y_slashbottom_right = ypos + 76 + lineHeight;
//		y_slashbottom_right = ypos + 76 * lineHeight;

		slash.rLineTo(-thickness, 0);
		slash.lineTo(xpos, ypos);
//		canvas.drawPath(slash, color1);
		symbol.addPath(slash, xpos, ypos);

		Path backslash = new Path();
		backslash.moveTo(x_slashbottom_right, y_slashbottom_right - (thickness + 2));

		float x_backslash_bottomRight = x_slashbottom_right;
		float y_backslash_bottomRight = y_slashbottom_right - (thickness + 2);

		//// upward right-side
		backslash.rLineTo(-21, -30);

		float x_backslash_topRight = x_backslash_bottomRight - 21;
		float y_backslash_topRight = y_backslash_bottomRight - 30;

		//// top
		backslash.lineTo(x_backslash_topRight - thickness - 6, y_backslash_topRight + thickness);

		float x_backslash_topLeft = x_backslash_topRight - thickness - 6;
		float y_backslash_topLeft = y_backslash_topRight + thickness;

		backslash.rLineTo(2, 2);

		float x_backslash_lipEnd = x_backslash_topLeft + 2;
		float y_backslash_lipEnd = y_backslash_topLeft + 2;

		backslash.lineTo(x_backslash_topRight - thickness, y_backslash_topRight + thickness);

		backslash.lineTo(x_slashbottom_right - thickness, y_slashbottom_right);

		symbol.addPath(backslash, x_slashbottom_right, y_slashbottom_right);

		mSqrt = new ShapeDrawable();
		temp = new PathShape(symbol, lineWidth + 36, lineHeight + 76);
		mSqrt.setShape(temp);
//		mSqrt = temp.getDrawable();
		ScaleDrawable dtemp = new ScaleDrawable(mSqrt, 0, lineWidth + 36, lineHeight + 76);
		mSqrt = (ShapeDrawable) dtemp.getDrawable();
		assert mSqrt != null;
		mSqrt.setBounds(0, 0, lineWidth + 36, lineHeight + 76);

		mSqrt.getPaint().setColor(Color.BLACK);
		mSqrt.getPaint().setStyle(Paint.Style.FILL);
		mSqrt.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);

		this.setLayoutParams(new ViewGroup.LayoutParams(lineWidth + 36, lineHeight + 76));
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
//		Paint color1 = new Paint();
//		color1.setColor(Color.BLACK);
//		color1.setStyle(Paint.Style.FILL);
//		color1.setFlags(Paint.ANTI_ALIAS_FLAG);

		mSqrt.draw(canvas);
//		float x_slashbottom_right, x_2, y_slashbottom_right, y_2;
//		float thickness = 5;
//
//		Paint color1 = new Paint();
//		color1.setColor(Color.BLACK);
//		color1.setStyle(Paint.Style.FILL);
//		color1.setFlags(Paint.ANTI_ALIAS_FLAG);
//
//		Path topBar = new Path();
//		topBar.moveTo(xpos + lineWidth, ypos);
//		topBar.lineTo(xpos + lineWidth, ypos + thickness);
//		topBar.lineTo(xpos, ypos + thickness);
//		topBar.lineTo(xpos, ypos);
//		topBar.lineTo(xpos + lineWidth, ypos);
//		canvas.drawPath(topBar, color1);
//
//		Path slash = new Path();
//		slash.moveTo(xpos, ypos);
//		slash.rLineTo(thickness, 0);
//		slash.rLineTo(-36, 76 + lineHeight);
////		slash.rLineTo(-36, 76 * lineHeight);
//
//		x_slashbottom_right = xpos + thickness - 36;
//		y_slashbottom_right = ypos + 76 + lineHeight;
////		y_slashbottom_right = ypos + 76 * lineHeight;
//
//		slash.rLineTo(-thickness, 0);
//		slash.lineTo(xpos, ypos);
//		canvas.drawPath(slash, color1);
//
//		Path backslash = new Path();
//		backslash.moveTo(x_slashbottom_right, y_slashbottom_right - (thickness + 2));
//
//		float x_backslash_bottomRight = x_slashbottom_right;
//		float y_backslash_bottomRight = y_slashbottom_right - (thickness + 2);
//
//		//// upward right-side
//		backslash.rLineTo(-21, -30);
//
//		float x_backslash_topRight = x_backslash_bottomRight - 21;
//		float y_backslash_topRight = y_backslash_bottomRight - 30;
//
//		//// top
//		backslash.lineTo(x_backslash_topRight - thickness - 6, y_backslash_topRight + thickness);
//
//		float x_backslash_topLeft = x_backslash_topRight - thickness - 6;
//		float y_backslash_topLeft = y_backslash_topRight + thickness;
//
//		backslash.rLineTo(2, 2);
//
//		float x_backslash_lipEnd = x_backslash_topLeft + 2;
//		float y_backslash_lipEnd = y_backslash_topLeft + 2;
//
//		backslash.lineTo(x_backslash_topRight - thickness, y_backslash_topRight + thickness);
//
//		backslash.lineTo(x_slashbottom_right - thickness, y_slashbottom_right);
//
//		canvas.drawPath(backslash, color1);
//		canvas.se
	}
}
