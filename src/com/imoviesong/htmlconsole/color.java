package com.imoviesong.htmlconsole;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class color extends Activity {
	SeekBar sbr, sbg, sbb;
	TextView tvrv, tvgv, tvbv, tvResult, tvHex;
	Button copy;
	int alpha = 255, red = 0, green = 0, blue = 0;
	String hexr = "0", hexg = "0", hexb = "0";
	String hexValue;

	private void toast(String str) {
		// toast
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color);
		toggleFullscreen(true);
		sbr = (SeekBar) findViewById(R.id.sbRed);
		sbg = (SeekBar) findViewById(R.id.sbGreen);
		sbb = (SeekBar) findViewById(R.id.sbBlue);
		tvrv = (TextView) findViewById(R.id.tvColorRedValue);
		tvgv = (TextView) findViewById(R.id.tvColorGreenValue);
		tvbv = (TextView) findViewById(R.id.tvColorBlueValue);
		tvResult = (TextView) findViewById(R.id.tvColorTest);
		tvHex = (TextView) findViewById(R.id.tvColorHex);
		copy = (Button) findViewById(R.id.bColorCopy);

		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));

		tvResult.setBackgroundColor(Color.argb(alpha, red, green, blue));

		sbr.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tvrv.setText(Integer.toString(arg1));
				red = arg1;
				tvResult.setBackgroundColor(Color.argb(alpha, red, green, blue));
				hexr = Integer.toHexString(red);
				tvHex.setText("#" + hexr + hexg + hexb);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

		});

		sbg.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tvgv.setText(Integer.toString(arg1));
				green = arg1;
				tvResult.setBackgroundColor(Color.argb(alpha, red, green, blue));
				hexg = Integer.toHexString(green);
				tvHex.setText("#" + hexr + hexg + hexb);
				// tvHex.setText("#" + Integer.toString(red)
				// + Integer.toString(green) + Integer.toString(blue));
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

		});

		sbb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tvbv.setText(Integer.toString(arg1));
				blue = arg1;
				tvResult.setBackgroundColor(Color.argb(alpha, red, green, blue));
				hexb = Integer.toHexString(blue);
				tvHex.setText("#" + hexr + hexg + hexb);
				// tvHex.setText("#" + Integer.toString(red)
				// + Integer.toString(green) + Integer.toString(blue));
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

		});

		copy.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toast("code copied to clipboard");
				int sdk = android.os.Build.VERSION.SDK_INT;
				if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					clipboard.setText(tvHex.getText().toString());
				} else {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					android.content.ClipData clip = android.content.ClipData
							.newPlainText("color code", tvHex.getText()
									.toString());
					clipboard.setPrimaryClip(clip);
				}
				finish();
			}

		});

	}

}
