package com.imoviesong.htmlconsole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import com.google.android.gms.ads.*;

public class texthtml extends Activity implements OnClickListener {

	TabHost th;
	Button save;
	Button conv;
	Button copy;
	Button clear;
	Button bold;
	Button italic;
	Button normal;
	Button underline;
	Button boldpitalic;
	EditText ettext;
	EditText ethtml;
	TextToSpeech tss;

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
		toggleFullscreen(true);
		setContentView(R.layout.texthtml);
		th = (TabHost) findViewById(R.id.thTextHtml);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Text");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("HTML");
		th.addTab(ts);
		EditText e = new EditText(this);
		e.setText("U");
		Spannable str = e.getText();
		str.setSpan(new UnderlineSpan(), 0, str.length(), 0);

		ettext = (EditText) findViewById(R.id.etthtext);
		ethtml = (EditText) findViewById(R.id.etthhtml);
		save = (Button) findViewById(R.id.bthsave);
		changebcolor(save);
		copy = (Button) findViewById(R.id.bthcopy);
		changebcolor(copy);
		clear = (Button) findViewById(R.id.bthclear);
		changebcolor(clear);
		conv = (Button) findViewById(R.id.bthconvert);
		changebcolor(conv);
		bold = (Button) findViewById(R.id.bbold);
		changebcolor(bold);
		italic = (Button) findViewById(R.id.bitalic);
		changebcolor(italic);
		normal = (Button) findViewById(R.id.bnormal);
		changebcolor(normal);
		boldpitalic = (Button) findViewById(R.id.bboldpitalic);
		changebcolor(boldpitalic);
		underline = (Button) findViewById(R.id.bunderline);
		underline.setText(str);
		changebcolor(underline);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adViewTextHtml);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		tss = new TextToSpeech(texthtml.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							tss.setLanguage(Locale.ENGLISH);
						}
					}
				});

		normal.setOnLongClickListener(new OnLongClickListener() {

			@SuppressLint("NewApi")
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				tss.speak(ettext.getText().toString(),
						TextToSpeech.QUEUE_FLUSH, null);
				return false;
			}

		});

		ethtml.addTextChangedListener(new TextWatcher() {

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
				syntax(ethtml.getText().toString(), "<[^>]*>");
			}

		});

		save.setOnClickListener(this);
		copy.setOnClickListener(this);
		clear.setOnClickListener(this);
		conv.setOnClickListener(this);
		bold.setOnClickListener(this);
		italic.setOnClickListener(this);
		normal.setOnClickListener(this);
		boldpitalic.setOnClickListener(this);
		underline.setOnClickListener(this);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int i = th.getCurrentTab();
		if (i == 0) {
			switch (arg0.getId()) {
			case R.id.bthclear:
				ettext.setText("");
				toast("text cleared");
				break;
			case R.id.bthconvert:
				ethtml.setText(Html.toHtml(ettext.getText()));
				th.setCurrentTab(1);
				break;
			case R.id.bthcopy:
				copy(ettext);
				toast("text copied to clipboard");
				break;
			case R.id.bthsave:
				savedialog(ettext);
				break;
			case R.id.bbold:
				bold();
				break;
			case R.id.bitalic:
				italic();
				break;
			case R.id.bnormal:
				normal();
				break;
			case R.id.bboldpitalic:
				boldpitalic();
				break;
			case R.id.bunderline:
				underline();
				break;
			}
		} else {
			switch (arg0.getId()) {
			case R.id.bthclear:
				ethtml.setText("");
				toast("text cleared");
				break;
			case R.id.bthconvert:
				ettext.setText(Html.fromHtml(ethtml.getText().toString()),
						BufferType.SPANNABLE);
				th.setCurrentTab(0);
				break;
			case R.id.bthcopy:
				copy(ethtml);
				toast("HTML code copied to clipboard");
				break;
			case R.id.bthsave:
				savedialog(ethtml);
				break;
			}
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mhtmltext, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int i = th.getCurrentTab();
		if (i == 0) {
			switch (item.getItemId()) {
			case R.id.menuhtfind:
				searchtext(ettext);
				break;
			case R.id.menuhtreplace:
				replacetext(ettext);
				break;
			}
		} else {
			switch (item.getItemId()) {
			case R.id.menuhtfind:
				searchtext(ethtml);
				break;
			case R.id.menuhtreplace:
				replacetext(ethtml);
				break;
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void copy(EditText a) {
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(a.getText().toString());
		} else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			android.content.ClipData clip = android.content.ClipData
					.newPlainText("text to code", a.getText().toString());
			clipboard.setPrimaryClip(clip);
		}
	}

	public void savedialog(final EditText e) {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Save");
		alert.setMessage("Enter Title");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				save(value.toString(), e);
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
		// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
	}

	@SuppressLint("SdCardPath")
	public void save(String value, EditText v) {
		try {
			File myFile = new File("/sdcard/HTML Console/" + value + ".html");
			myFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
			myOutWriter.append(v.getText());
			myOutWriter.close();
			fOut.close();
			toast("saved");
		} catch (Exception e) {
			toast(e.getMessage());
		}
	}

	public void changebcolor(Button b) {
		b.setTextColor(Color.parseColor("white")); // set button text colour to
													// be blue
		// b.setBackgroundColor(Color.parseColor("blue")); // set button
		// background colour to be green
	}

	public void biu() {
		// Get our EditText object.
		// Set the EditText's
		ettext.setText("Italic, highlighted, bold.");

		// If this were just a TextView, we could do:
		// vw.setText("Italic, highlighted, bold.",
		// TextView.BufferType.SPANNABLE);
		// to force it to use Spannable storage so styles can be attached.
		// Or we could specify that in the XML.
		// Get the EditText's internal text storage
		Spannable str = ettext.getText();

		// Create our span sections, and assign a format to each.

		/*
		 * str.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0, 7,
		 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); str.setSpan(new
		 * BackgroundColorSpan(0xFFFFFF00), 8, 19,
		 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); str.setSpan(new
		 * StyleSpan(android.graphics.Typeface.BOLD), 21, str.length() - 1,
		 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		 */

		str.setSpan(new UnderlineSpan(), 0, str.length(), 0);
		str.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(), 0);
		str.setSpan(new StyleSpan(Typeface.ITALIC), 0, str.length(), 0);
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

	public void bold() {
		int startSelection = ettext.getSelectionStart();
		int endSelection = ettext.getSelectionEnd();
		Spannable str = ettext.getText();
		str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
				startSelection, endSelection,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (tss != null) {
			tss.stop();
			tss.shutdown();

		}
	}

	public void italic() {
		int startSelection = ettext.getSelectionStart();
		int endSelection = ettext.getSelectionEnd();
		Spannable str = ettext.getText();
		str.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC),
				startSelection, endSelection,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	public void normal() {
		int startSelection = ettext.getSelectionStart();
		int endSelection = ettext.getSelectionEnd();
		Spannable str = ettext.getText();
		StyleSpan[] ss = str.getSpans(startSelection, endSelection,
				StyleSpan.class);
		for (int i = 0; i < ss.length; i++) {
			str.removeSpan(ss[i]);
		}
	}

	public void boldpitalic() {
		int startSelection = ettext.getSelectionStart();
		int endSelection = ettext.getSelectionEnd();
		Spannable str = ettext.getText();
		str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
				startSelection, endSelection,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	public void underline() {
		int startSelection = ettext.getSelectionStart();
		int endSelection = ettext.getSelectionEnd();
		Spannable str = ettext.getText();
		str.setSpan(new UnderlineSpan(), startSelection, endSelection,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	private void syntax(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = ethtml.getText();
			if (regex.contains("<[^>]*>")) {
				str.setSpan(new ForegroundColorSpan(Color.BLUE),
						startSelection, endSelection,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
				// startSelection, endSelection,
				// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			// str.setSpan(new BackgroundColorSpan(0xFFFFFF00),
			// startSelection,endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			// str.setSpan(new
			// StyleSpan(android.graphics.Typeface.BOLD),startSelection,
			// endSelection,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}

	private void replacetext(final EditText et) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
		lila1.setOrientation(1);
		alert.setTitle("Replace");

		// Set an EditText view to get user input
		final TextView total = new TextView(this);
		final EditText input = new EditText(this);
		input.setHint("Enter Search Text");

		final EditText input1 = new EditText(this);
		input1.setHint("Enter Replace Text");
		lila1.addView(total);
		lila1.addView(input);
		lila1.addView(input1);
		alert.setView(lila1);
		input.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int a = replacecount(et, input.getText().toString());
				total.setText("Total Found : " + a);
			}
		});
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				String value1 = input1.getText().toString();
				String value = input.getText().toString();
				int a = replacecount(et, value);
				String s = et.getText().toString().replaceAll(value, value1);
				et.setText(s);
				toast("Total Replacement's made : " + Integer.toString(a));
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}

	private void searchtext(final EditText et) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
		lila1.setOrientation(1);
		alert.setTitle("Search");
		et.setText(et.getText().toString());
		// Set an EditText view to get user input
		final TextView total = new TextView(this);
		final EditText input = new EditText(this);
		input.setHint("Enter Search Text");
		lila1.addView(total);
		lila1.addView(input);
		alert.setView(lila1);
		input.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int a = replacecount(et, input.getText().toString());
				total.setText("Total Found : " + a);
			}
		});
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				printMatches(et, et.getText().toString(), input.getText()
						.toString());
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.

					}
				});

		alert.show();
	}

	private int replacecount(EditText et, String str) {
		String regex_script = str;
		int a = 0;
		String line = et.getText().toString();
		Pattern p = Pattern.compile(regex_script); // Create a pattern to match
		Matcher m = p.matcher(line); // Create a matcher with an input string
		while (m.find()) {
			a = a + 1;
		}
		return a;
	}

	private void printMatches(EditText et, String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = et.getText();
			str.setSpan(new BackgroundColorSpan(0xFFFFFF00), startSelection,
					endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			/*
			 * str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
			 * startSelection, endSelection,
			 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			 */
		}
	}

}
