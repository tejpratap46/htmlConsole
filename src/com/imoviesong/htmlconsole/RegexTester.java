package com.imoviesong.htmlconsole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class RegexTester extends Activity implements OnClickListener {

	EditText etregex, etreplace, ettext;
	Button btest, breplace;
	TextView tvcount;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regex_tester);
		toggleFullscreen(true);
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		ActionBar bar = getActionBar();
		bar.setTitle("Regex Tester");
		bar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));

		etregex = (EditText) findViewById(R.id.etRegexTest);
		etreplace = (EditText) findViewById(R.id.etRegexReplace);
		ettext = (EditText) findViewById(R.id.etRegexMainTest);
		btest = (Button) findViewById(R.id.bRegexTest);
		breplace = (Button) findViewById(R.id.bRegexReplace);
		tvcount = (TextView) findViewById(R.id.tvRegexCount);

		ettext.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				int a = replacecount();
				tvcount.setText("Total Match Found : " + Integer.toString(a));
			}

		});

		etregex.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				int a = replacecount();
				tvcount.setText("Total Match Found : " + Integer.toString(a));
			}

		});

		btest.setOnClickListener(this);
		breplace.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bRegexTest:
			printMatches(ettext.getText().toString(), etregex.getText()
					.toString());
			break;
		case R.id.bRegexReplace:
			ettext.setText(ettext
					.getText()
					.toString()
					.replaceAll(etregex.getText().toString(),
							etreplace.getText().toString()));
			break;
		}
	}

	public void printMatches(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = ettext.getText();
			str.setSpan(new BackgroundColorSpan(0xFF4E86C3), startSelection,
					endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}

	private int replacecount() {
		try {
			String regex_script = etregex.getText().toString();
			int a = 0;
			String line = ettext.getText().toString();
			Pattern p = Pattern.compile(regex_script); // Create a pattern to
														// match
			Matcher m = p.matcher(line); // Create a matcher with an input
											// string
			while (m.find()) {
				a = a + 1;
			}
			return a;
		} catch (Exception e) {

		}
		return 0;
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
