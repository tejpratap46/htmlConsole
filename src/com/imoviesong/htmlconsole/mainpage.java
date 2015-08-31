package com.imoviesong.htmlconsole;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class mainpage extends Activity implements OnClickListener {
	Button bnew, btemp, bfile, bexample, bgetwebsource;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
		toggleFullscreen(true);
		bnew = (Button) findViewById(R.id.bMainNew);
		btemp = (Button) findViewById(R.id.bMainTemp);
		bfile = (Button) findViewById(R.id.bMainFile);
		bexample = (Button) findViewById(R.id.bMainExample);
		bgetwebsource = (Button) findViewById(R.id.bMainWebSource);
		bnew.setOnClickListener(this);
		btemp.setOnClickListener(this);
		bfile.setOnClickListener(this);
		bexample.setOnClickListener(this);
		bgetwebsource.setOnClickListener(this);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bMainNew:
			Intent inew = new Intent(mainpage.this, textfile.class);
			startActivity(inew);
			break;
		case R.id.bMainTemp:
			Intent itemp = new Intent(mainpage.this, textfile.class);
			itemp.putExtra("temp", "template");
			startActivity(itemp);
			break;
		case R.id.bMainFile:
			Intent ifile = new Intent(mainpage.this, file_list.class);
			startActivity(ifile);
			break;
		case R.id.bMainExample:
			Intent iExample = new Intent(mainpage.this, examples.class);
			startActivity(iExample);
			break;
		case R.id.bMainWebSource:
			Intent iWebSource = new Intent(mainpage.this, websource.class);
			startActivity(iWebSource);
			break;
		}
	}

	public void toggleFullscreen(boolean fullscreen) {
		WindowManager.LayoutParams attrs = getWindow().getAttributes();
		if (fullscreen) {
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		} else {
			attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		}
		getWindow().setAttributes(attrs);
	}

}
