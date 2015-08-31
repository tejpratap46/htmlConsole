package com.imoviesong.htmlconsole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

@SuppressLint({ "SetJavaScriptEnabled", "HandlerLeak" })
public class websource extends Activity implements OnClickListener {
	TabHost th;
	Button bgo1, bcopy, bviewtext;
	Button bgo2;
	Button bgob;
	Button bgof;
	Button bre;
	EditText et1;
	EditText etHtml;
	WebView browser;
	AlertDialog.Builder alert;
	TextView webTitle;

	private void toast(String str) {
		// toast
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	private void viewInTexView() {
		if (etHtml.getText().length() > 1) {
			alert = new AlertDialog.Builder(this);
			alert.setTitle(browser.getTitle());
			alert.setIcon(R.drawable.ic_launcher);
			alert.setMessage("HTML Source Code");
			TextView tvDialog = new TextView(this);
			tvDialog.setText(etHtml.getText().toString());
			tvDialog.setTextSize(20);
			ScrollView sv = new ScrollView(this);
			sv.addView(tvDialog);
			alert.setView(sv);
			Spannable s = syntax1(etHtml.getText().toString(), "<[^>]*>");
			tvDialog.setText(s);
			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to invoke NO event
							dialog.dismiss();
						}
					});
			alert.show();
		} else {
			toast("please get the html first to view in read mode");
		}
	}

	private class gethtml {

		private void getResponseThread(final String url) {
			new Thread(new Runnable() {
				public void run() {
					String cadHTTP = getResponse(url);
					Message msg = new Message();
					msg.obj = cadHTTP;
					handlerHTTP.sendMessage(msg);
				}
			}).start();
		}

		private String getResponse(String url) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet del = new HttpGet(url);
			del.setHeader("content-type", "application/json");

			String respStr;
			try {
				HttpResponse resp = httpClient.execute(del);
				respStr = EntityUtils.toString(resp.getEntity());
			} catch (Exception ex) {
				Log.e("RestService", "Error!", ex);
				respStr = "";
			}

			Log.e("getResponse", respStr);
			return respStr;
		}

		private Handler handlerHTTP = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String res = (String) msg.obj;
				etHtml.setText(res);
				// CONTINUE HERE
			}
		};
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.websource);
		toggleFullscreen(true);
		th = (TabHost) findViewById(R.id.thWebhtm);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Web Browser");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("HTML");
		th.addTab(ts);

		bgo1 = (Button) findViewById(R.id.bWebGo);
		changebcolor(bgo1);
		bgo2 = (Button) findViewById(R.id.bWebGo1);
		changebcolor(bgo2);
		bgob = (Button) findViewById(R.id.bWebBack);
		changebcolor(bgob);
		bgof = (Button) findViewById(R.id.bWebForward);
		changebcolor(bgof);
		bre = (Button) findViewById(R.id.bWebRefresh);
		changebcolor(bre);
		bcopy = (Button) findViewById(R.id.bWebSourceCopy);
		bviewtext = (Button) findViewById(R.id.bViewInText);
		et1 = (EditText) findViewById(R.id.etWeburl);
		etHtml = (EditText) findViewById(R.id.etWebhtml);
		browser = (WebView) findViewById(R.id.wvWebhtml);
		webTitle = (TextView) findViewById(R.id.tvGetWebTitle);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adViewWeb);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		browser.setWebViewClient(new webviewclient());
		browser.getSettings().setJavaScriptEnabled(true);
		bgo1.setOnClickListener(this);
		bgo2.setOnClickListener(this);
		bgob.setOnClickListener(this);
		bgof.setOnClickListener(this);
		bre.setOnClickListener(this);
		bcopy.setOnClickListener(this);
		bviewtext.setOnClickListener(this);

		browser.setWebViewClient(new WebViewClient() {

			public void onPageFinished(WebView view, String url) {
				et1.setText(browser.getUrl());
				webTitle.setText(browser.getTitle());
			}
		});

		et1.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					onClick(bgo1);
				}
				return false;
			}
		});

		etHtml.addTextChangedListener(new TextWatcher() {

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
				syntax(etHtml.getText().toString(), "<[^>]*>");
			}

		});

		browser.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype,
					long contentLength) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bWebGo:
			if (et1.getText().toString().contains("http://")
					|| et1.getText().toString().contains("https://"))
				browser.loadUrl(et1.getText().toString());
			else {
				browser.loadUrl("http://www." + et1.getText().toString());
			}
			toast("Loading...");
			break;
		case R.id.bWebGo1:
			gethtml te = new gethtml();
			et1.setText(browser.getUrl());
			if (!URLUtil.isValidUrl(et1.getText().toString())) {
				Context context = getApplicationContext();
				CharSequence text = "Please Enter a URL First";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				break;
			}
			te.getResponseThread(et1.getText().toString());
			toast("Loading...");
			break;
		case R.id.bWebBack:
			browser.goBack();
			toast("Loading...");
			break;
		case R.id.bWebForward:
			browser.goForward();
			toast("Loading...");
			break;
		case R.id.bWebRefresh:
			browser.reload();
			toast("Loading...");
			break;
		case R.id.bWebSourceCopy:
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(etHtml.getText().toString());
			toast("copied to clipboard");
			break;
		case R.id.bViewInText:
			viewInTexView();
			break;
		}
	}

	private void syntax(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = etHtml.getText();
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

	private Spannable syntax1(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		Spannable str = etHtml.getText();
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();

			if (regex.contains("<[^>]*>")) {
				str.setSpan(new ForegroundColorSpan(Color.BLUE),
						startSelection, endSelection,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
		return str;
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
