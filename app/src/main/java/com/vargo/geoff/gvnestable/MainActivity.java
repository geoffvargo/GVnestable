package com.vargo.geoff.gvnestable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargo.geoff.nestabletextview.NestableEquation;
import com.vargo.geoff.nestabletextview.NestableTextView;

import static com.vargo.geoff.nestabletextview.EqType.FRACTION;
import static com.vargo.geoff.nestabletextview.EqType.NORMAL;

public class MainActivity extends AppCompatActivity {

	public NestableTextView nesty;
	public NestableEquation eqqie;
	public LinearLayout linnie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		linnie = findViewById(R.id.linnie);

		eqqie = new NestableEquation(this, "asdf", FRACTION);
		linnie.addView(eqqie);
//
//		nesty = new NestableTextView(this, "asdf");
//		linnie.addView(nesty);
//
//		nesty.addChild("cvbn");
//
//		TextView tv1 = new TextView(this);
//		tv1.setText("kljhiuh");
//		linnie.addView(tv1);

	}
}
