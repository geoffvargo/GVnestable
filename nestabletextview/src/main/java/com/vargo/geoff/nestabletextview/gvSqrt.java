/*
 * Copyright (c) 2018. Geoff Vargo
 */

package com.vargo.geoff.nestabletextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class gvSqrt extends View {
//public class gvSqrt extends NestableTextView {
	protected float ypos = 22;
	protected float xpos = 110;

	protected float hfactor = 1;
	protected float lineWidth = 0;

	public gvSqrt(Context context, String str) {
		super(context);
//		super(context, str);
//		this.layout(0, 0, 0, 0);
	}

	public gvSqrt(Context context, String str, float hfactor) {
		super(context);
//		super(context, str);
//		this.layout(0, 0, 0, 0);
		this.hfactor = hfactor;
	}

	public gvSqrt(Context context, String str, float hfactor, float lineWidth) {
		super(context);
//		super(context, str);
//		this.layout(0, 0, 0, 0);
		this.hfactor = hfactor;
		this.lineWidth = lineWidth;
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

		Paint color1 = new Paint();
		color1.setColor(Color.BLACK);
		color1.setStyle(Paint.Style.FILL);
		color1.setFlags(Paint.ANTI_ALIAS_FLAG);

		Path p1 = new Path();

		//// underside of top-bar
		p1.moveTo(xpos + lineWidth, ypos);
		p1.lineTo(xpos, ypos);
		p1.rLineTo(-25, 0);

		//// slash-down
		p1.rLineTo(-36, 76 * hfactor);

		//// bottom of check-mark
		p1.rLineTo(-1, 1);
		p1.rLineTo(-3, 2);
		p1.rLineTo(-2, -1);

		////  backslash-up
		p1.rLineTo(-21, -36);

		//// backslash-lip underside
		p1.rLineTo(-7, 5);

		//// backslash-lip end
		p1.rLineTo(-2, -2);

		//// backslash-lip top
		p1.rLineTo(12, -11);

		//// backslash-down
		p1.rLineTo(20, 33);

		//// slash-up
		p1.lineTo((xpos) - 31, ypos - 5);

		//// top edge of top-bar
		p1.lineTo(xpos + lineWidth, ypos - 5);

		canvas.drawPath(p1, color1);
	}
}
