package com.imoviesong.htmlconsole;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.*;

public class full extends Activity implements View.OnClickListener {

	EditText etEncode, etDecode;
	Button bEnDeClear, bEnDeCopy;

	private void toast(String str) {
		// toast
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full);
		toggleFullscreen(true);
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		etEncode = (EditText) findViewById(R.id.etEncode);
		etDecode = (EditText) findViewById(R.id.etDecode);
		bEnDeClear = (Button) findViewById(R.id.bEnDeClear);
		changebcolor(bEnDeClear);
		bEnDeCopy = (Button) findViewById(R.id.bEnDeCopy);
		changebcolor(bEnDeCopy);
		bEnDeClear.setOnClickListener(this);
		bEnDeCopy.setOnClickListener(this);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		etEncode.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@SuppressLint("NewApi")
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				String s;
				s = Html.escapeHtml(etEncode.getText().toString());
				etDecode.setText(s);
			}

		});

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bEnDeClear:
			etEncode.setText("");
			toast("Text Cleared");
			break;
		case R.id.bEnDeCopy:
			int sdk = android.os.Build.VERSION.SDK_INT;
			if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
				@SuppressWarnings("deprecation")
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(etDecode.getText().toString());
			} else {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData
						.newPlainText("Decode", etDecode.getText().toString());
				clipboard.setPrimaryClip(clip);
			}
			toast("Text Copied to clipboard!");
			break;
		}
	}

	public void changebcolor(Button b) {
		b.setTextColor(Color.parseColor("white")); // set button text colour to
													// be blue
		// b.setBackgroundColor(Color.parseColor("green")); // set button
		// background colour to be green
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
