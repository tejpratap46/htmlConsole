package com.imoviesong.htmlconsole;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class tagsclass extends Activity implements OnItemClickListener {
	String etags[] = { " -->", "<!DOCTYPE>", " </a>", " </abbr>", " </b>",
			" </body>", "<br />", " </button>", " </div>", " </font>",
			" </form>", " </h1>", " </head>", " </html>", " </i>",
			" </iframe>", "<img > ", "<input />", " </label>", "  <link  /> ",
			" </menu>", "<meta  />", " </option>", " </p>", " </q>", " </s>",
			" </script>", " </select>", " </style>", " </tr>", " </td>",
			"</textarea>", " </title>" };
	String stags[] = { "<!-- ", "<!DOCTYPE>", " <a>", " <abbr>", " <b>",
			" <body>", "<br />", " <button>", " <div>", " <font>", " <form>",
			" <h1>", " <head>", " <html>", " <i>", " <iframe>", "<img > ",
			"<input />", " <label>", "  <link  /> ", " <menu>", "<meta  />",
			" <option>", " <p>", " <q>", " <s>", " <script>", " <select>",
			" <style>", " <tr>", " <td>", "<textarea>", " <title>" };
	String all[] = { "<!-- -->", "<!DOCTYPE>", "<a> ", "/a>", "<abbr> </abbr>",
			"<b> ", "/b>", "<body> </body>", "<br />", "<button> </button>",
			"<div> </div>", "<font> </font>", ",<h1> </h1>", "<head> ",
			"</head>", "<html> </html>", "<i> </i>", "<iframe> </iframe>",
			"  <img > ", "<input />", "<label  ></label>", "  <link  /> ",
			"<menu> </menu>", "<meta  />", "<option> ", "/option>", "<p> </p>",
			"<q> </q>", "<s> </s>", "<script  ></script>",
			"<select> </select>", "<style >  </style>", "<tr>  </tr>",
			"<td>  </td>", "<textarea >  </textarea>", "<title>  </title>" };
	int sdk = android.os.Build.VERSION.SDK_INT;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tags);
		toggleFullscreen(true);
		TabHost th = (TabHost) findViewById(R.id.thtags);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("All Tags");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Start Tags");
		th.addTab(ts);

		ts = th.newTabSpec("tab3");
		ts.setContent(R.id.tab3);
		ts.setIndicator("End Tags");
		th.addTab(ts);

		ListView lvall = (ListView) findViewById(R.id.lvAll);
		ListView lvetag = (ListView) findViewById(R.id.lvetags);
		ListView lvstag = (ListView) findViewById(R.id.lvstags);
		ArrayAdapter<String> adapterall = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, all);

		lvall.setAdapter(adapterall);

		ArrayAdapter<String> adapteretag = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, etags);

		lvetag.setAdapter(adapteretag);

		ArrayAdapter<String> adapterstag = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, stags);

		lvstag.setAdapter(adapterstag);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		lvall.setOnItemClickListener(this);
		lvetag.setOnItemClickListener(this);
		lvstag.setOnItemClickListener(this);
		// TODO Auto-generated method stub

	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.lvAll:
			String tagall = all[arg2];
			if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(tagall);
			} else {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData
						.newPlainText("tag", tagall);
				clipboard.setPrimaryClip(clip);
			}
			break;
		case R.id.lvetags:
			String tage = etags[arg2];
			if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(tage);
			} else {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData
						.newPlainText("tag", tage);
				clipboard.setPrimaryClip(clip);
			}
			break;
		case R.id.lvstags:
			String tags = stags[arg2];
			if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(tags);
			} else {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData
						.newPlainText("tag", tags);
				clipboard.setPrimaryClip(clip);
			}
			break;
		}
		Context context = getApplicationContext();
		CharSequence text = "tag Copied To Clipboard";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		Thread timer1 = new Thread() {
			public void run() {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					finish();
					// Intent itextfile = new
					// Intent(tagsclass.this,textfile.class);
					// startActivity(itextfile);
				}
			}
		};
		timer1.start();

		/*
		 * Bundle basket = new Bundle(); basket.putString("kall", tagall);
		 * Intent iall = new Intent(tagsclass.this,textfile.class);
		 * iall.putExtras(basket); startActivity(iall);
		 */
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
