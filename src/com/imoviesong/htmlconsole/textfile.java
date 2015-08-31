package com.imoviesong.htmlconsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

@SuppressWarnings("deprecation")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint({ "SetJavaScriptEnabled", "SdCardPath" })
public class textfile extends Activity implements View.OnClickListener {
	String all[] = { "<!-- -->", "<!DOCTYPE>", "<a> </a>", "<abbr> </abbr>",
			"<b> </b>", "<body> </body>", "<br />", "<button> </button>",
			"<div> </div>", "<font> </font>", ",<h1> </h1>", "<head> </head>",
			"<html> </html>", "<i> </i>", "<iframe> </iframe>", "  <img > ",
			"<input />", "<label  ></label>", "  <link  /> ", "<menu> </menu>",
			"<meta  />", "<option> ", "/option>", "<p> </p>", "<q> </q>",
			"<s> </s>", "<script  ></script>", "<select> </select>",
			"<style >  </style>", "<tr>  </tr>", "<td>  </td>",
			"<textarea >  </textarea>", "<title>  </title>" };
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
	String tag[] = { "All", "Start tags", "End tags" };
	String istart[] = { "simple html", "Email form", "simple form" };
	String itext1 = "<!DOCTYPE html>\n<html>\n<head>\n<title>simple page</title>\n</head>\n<body>\n<h1>My First Heading</h1>\n<p>My first paragraph.</p>\n</body>\n</html>";
	String itext2 = "<!DOCTYPE html>\n<html>\n<body>\n<h3>Send e-mail to someone@example.com:</h3>\n<form action=\"MAILTO:someone@example.com\" method=\"post\" enctype=\"text/plain\">\nName:<br>\n<input type=\"text\" name=\"name\" value=\"your name\"><br>\nE-mail:<br>\n<input type=\"text\" name=\"mail\" value=\"your email\"><br>\nComment:<br>\n<input type=\"text\" name=\"comment\" value=\"your comment\" size=\"50\"><br><br>\n<input type=\"submit\" value=\"Send\">\n<input type=\"reset\" \nalue=\"Reset\">\n</form>\n</body>\n</html>";
	String itext3 = "<!DOCTYPE html>\n<html>\n<body>\n<form name=\"input\" action=\"html_form_action.asp\" method=\"get\">\nFirst name: <input type=\"text\" name=\"FirstName\" value=\"Mickey\"><br>\nLast name: <input type=\"text\" name=\"LastName\" value=\"Mouse\"><br>\n<input type=\"checkbox\" name=\"vehicle\" value=\"Bike\">I have a bike<br>\n<input type=\"checkbox\" name=\"vehicle\" value=\"Car\">I have a car <br>\n<input type=\"radio\" name=\"sex\" value=\"male\">Male<br>\n<input type=\"radio\" name=\"sex\" value=\"female\">Female<br>\n<input type=\"submit\" value=\"Submit\">\n</form> \n<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"html_form_action.asp\".</p>\n</body>\n</html>";
	ArrayList<String> listItems = new ArrayList<String>();
	TabHost th;
	Context cont = this;
	Button ButtRun;
	WebView WebViewLoad;
	EditText ed;
	Button bViewHtml, bpaste, bcopy;
	Button cAll, bgoback, bgoforward;
	Button save;
	Button undo;
	Button gtitle;
	Button full;
	LinearLayout l5, l2;
	int i;
	private String[] mPlanetTitles = { "1. Example", "2. Tags",
			"3. Saved Files", "4. Get Web Source", "5. Text To Code",
			"6. Encode-Decode", "7. Color Picker", "8. Regex Tester", "9. Help" };
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	ArrayAdapter<String> arrayAdapter = null;

	private void toast(String str) {
		// toast
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	private void onstart() {
		// Alert dialog
		AlertDialog.Builder builderInner = new AlertDialog.Builder(
				textfile.this);
		builderInner.setIcon(R.drawable.ic_launcher);
		builderInner.setTitle("Select template");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				textfile.this, android.R.layout.simple_list_item_1, istart);
		builderInner.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderInner.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						if (strName.contains("simple html"))
							ed.setText(itext1);
						else if (strName.contains("Email form"))
							ed.setText(itext2);
						else if (strName.contains("simple form"))
							ed.setText(itext3);
						else {
						}
					}
				});
		builderInner.show();

	}

	private void listdialog()

	{
		// Alert dialog
		AlertDialog.Builder builderInner = new AlertDialog.Builder(
				textfile.this);
		builderInner.setTitle("Select tag type");
		builderInner.setIcon(R.drawable.ic_launcher);
		final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(
				textfile.this, android.R.layout.simple_list_item_1, tag);
		builderInner.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderInner.setAdapter(arrayAdapter1,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						{

							AlertDialog.Builder builderSingle = new AlertDialog.Builder(
									textfile.this);
							builderSingle.setIcon(R.drawable.ic_launcher);
							builderSingle.setTitle("Select One Name:-");

							String strName = arrayAdapter1.getItem(which);
							if (strName.contains("All"))
								arrayAdapter = new ArrayAdapter<String>(
										textfile.this,
										android.R.layout.simple_list_item_1,
										all);
							else if (strName.contains("Start tags"))
								arrayAdapter = new ArrayAdapter<String>(
										textfile.this,
										android.R.layout.simple_list_item_1,
										stags);
							else if (strName.contains("End tags"))
								arrayAdapter = new ArrayAdapter<String>(
										textfile.this,
										android.R.layout.simple_list_item_1,
										etags);
							else {
							}

							builderSingle.setNegativeButton("cancel",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});

							builderSingle.setAdapter(arrayAdapter,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											String strName = arrayAdapter
													.getItem(which);
											ed.getSelectionStart();
											int start = Math.max(
													ed.getSelectionStart(), 0);
											int end = Math.max(
													ed.getSelectionEnd(), 0);
											ed.getText().replace(
													Math.min(start, end),
													Math.max(start, end),
													strName, 0,
													strName.length());
										}
									});
							builderSingle.show();
						}
					}
				});
		builderInner.show();

	}

	public void savedialog(final String operation) {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		if (operation.contains("open"))
			alert.setTitle("open");
		else if (operation.contains("save"))
			alert.setTitle("Save");
		else
			alert.setTitle("delete");
		alert.setMessage("Enter Title");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				if (operation.contains("open"))
					open(value.toString());
				else if (operation.contains("save"))
					save(value.toString());
				else
					deletefile(value.toString());
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

	public void save(String value) {
		try {
			if (value.isEmpty()) {
				toast("enter some name");
			} else {
				createfolder("/HTML Console");
				File myFile = new File("/sdcard/HTML Console/" + value
						+ ".html");
				myFile.createNewFile();
				FileOutputStream fOut = new FileOutputStream(myFile);
				OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
				myOutWriter.append(ed.getText());
				myOutWriter.close();
				fOut.close();
				toast("saved");
			}
		} catch (Exception e) {
			toast(e.getMessage());
		}

	}

	public void open(String value) {
		try {

			File myFile = new File("/sdcard/HTML Console/" + value + ".html");
			FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(new InputStreamReader(
					fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow;
			}
			ed.setText(aBuffer);
			myReader.close();
			toast("opened");
		} catch (Exception e) {
			toast(e.getMessage());
		}
	}

	public void deletefile(String value) {
		File file = new File("/sdcard/HTML Console/" + value + ".html");
		try {
			file.delete();
			toast("file deleted");
		} catch (Exception e) {
			toast(e.getMessage());
		} finally {

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textfile);
		toggleFullscreen(true);
		cAll = (Button) findViewById(R.id.bCAll);
		changebcolor(cAll);
		ButtRun = (Button) findViewById(R.id.buttonReload);
		bpaste = (Button) findViewById(R.id.bTextFilePaste);
		bcopy = (Button) findViewById(R.id.bTextFileCopy);
		bgoback = (Button) findViewById(R.id.bTextFileGoBack);
		bgoforward = (Button) findViewById(R.id.bTextFileGoForward);
		l5 = (LinearLayout) findViewById(R.id.layout5);
		l2 = (LinearLayout) findViewById(R.id.layout2);
		changebcolor(ButtRun);
		th = (TabHost) findViewById(android.R.id.tabhost);

		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("html");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Output");
		th.addTab(ts);

		WebViewLoad = (WebView) findViewById(R.id.webViewRun);
		WebViewLoad.setWebViewClient(new webviewclient());
		WebViewLoad.getSettings().setJavaScriptEnabled(true);
		ed = (EditText) findViewById(R.id.etHtmlCode);

		final ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));

		if (getIntent().hasExtra("path")) {
			String path;
			path = getIntent().getStringExtra("path");
			try {

				File myFile = new File(path);
				FileInputStream fIn = new FileInputStream(myFile);
				BufferedReader myReader = new BufferedReader(
						new InputStreamReader(fIn));
				String aDataRow = "";
				String aBuffer = "";
				while ((aDataRow = myReader.readLine()) != null) {
					aBuffer += aDataRow;
				}
				ed.setText(aBuffer);
				myReader.close();
				toast("opened");
			} catch (Exception e) {
				toast(e.getMessage());
			}
		} else if (getIntent().hasExtra("fback")) {
			ed.setText(getIntent().getStringExtra("fback"));
		} else {
			if (getIntent().hasExtra("temp"))
				onstart();
		}
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adViewMain);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		save = (Button) findViewById(R.id.bSave);
		changebcolor(save);
		full = (Button) findViewById(R.id.bfull);
		changebcolor(full);
		bViewHtml = (Button) findViewById(R.id.bGoHtml);
		changebcolor(bViewHtml);
		gtitle = (Button) findViewById(R.id.bgtitle);
		changebcolor(gtitle);
		undo = (Button) findViewById(R.id.bUndo);
		changebcolor(undo);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_launcher, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle("html console");
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("Navigate");
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mPlanetTitles));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent iExample = new Intent(cont, examples.class);
					startActivity(iExample);
					break;
				case 1:
					Intent itags = new Intent(textfile.this, tagsclass.class);
					startActivity(itags);
					break;
				case 2:
					String a = ed.getText().toString();
					Intent iShowfiles = new Intent(cont, file_list.class);
					iShowfiles.putExtra("fback", a);
					startActivity(iShowfiles);
					finish();
					break;
				case 3:
					Intent iwebsource = new Intent(textfile.this,
							websource.class);
					startActivity(iwebsource);
					break;
				case 4:
					Intent iconv = new Intent(textfile.this, texthtml.class);
					startActivity(iconv);
					break;
				case 5:
					Intent ifull = new Intent(textfile.this, full.class);
					startActivity(ifull);
					break;
				case 6:
					Intent icolor = new Intent(textfile.this, color.class);
					startActivity(icolor);
					break;
				case 7:
					Intent iregex = new Intent(textfile.this, RegexTester.class);
					startActivity(iregex);
					break;
				case 8:
					Intent iHelp = new Intent(textfile.this, help.class);
					startActivity(iHelp);
					break;
				}
			}

		});

		bViewHtml.setOnClickListener(this);
		cAll.setOnClickListener(this);
		ButtRun.setOnClickListener(this);
		save.setOnClickListener(this);
		gtitle.setOnClickListener(this);
		undo.setOnClickListener(this);
		full.setOnClickListener(this);
		bpaste.setOnClickListener(this);
		bcopy.setOnClickListener(this);
		bgoback.setOnClickListener(this);
		bgoforward.setOnClickListener(this);

		undo.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				if (listItems.isEmpty()) {
					toast("cannot do undo!");
				} else {
					ed.setText(listItems.get(0));
					ed.setSelection(ed.getText().length());
				}
				return false;
			}
		});

		ed.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				listItems.add(ed.getText().toString());
				i = listItems.size() - 1;
				return false;
			}
		});

		ed.addTextChangedListener(new TextWatcher() {

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
				syntax(ed.getText().toString(), "<[^>]*>");
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.menurun).setVisible(!drawerOpen);
		menu.findItem(R.id.menuHide).setVisible(!drawerOpen);
		menu.findItem(R.id.menudelete).setVisible(!drawerOpen);
		menu.findItem(R.id.menuopen).setVisible(!drawerOpen);
		menu.findItem(R.id.menuReplace).setVisible(!drawerOpen);
		menu.findItem(R.id.menutffind).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@SuppressLint("InlinedApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.menuopen:
			savedialog("open");
			break;
		case R.id.menudelete:
			savedialog("delete");
			break;
		case R.id.menurun:
			WebViewLoad.loadData(ed.getText().toString(), "text/html", "UTF-8");
			th.setCurrentTab(1);
			toast("Loading...");
			break;
		case R.id.menuReplace:
			replacetext();
			break;
		case R.id.menutffind:
			searchtext();
			break;
		case R.id.menuHide:
			if (l5.getVisibility() == View.VISIBLE) {
				l5.setVisibility(View.GONE);
				l2.setVisibility(View.GONE);
			} else {
				l5.setVisibility(View.VISIBLE);
				l2.setVisibility(View.VISIBLE);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bGoHtml:
			th.setCurrentTab(0);
			break;
		case R.id.buttonReload:
			WebViewLoad.loadData(ed.getText().toString(), "text/html", "UTF-8");
			th.setCurrentTab(1);
			toast("Loading...");
			break;
		case R.id.bCAll:
			ed.setText("");
			break;
		case R.id.bSave:
			savedialog("save");
			break;
		case R.id.bgtitle:
			WebView WebViewLoad = (WebView) findViewById(R.id.webViewRun);
			toast(WebViewLoad.getTitle());
			break;
		case R.id.bUndo:
			if (listItems.isEmpty()) {
				toast("cannot do undo!");
				break;
			}
			if (i == 0) {
				ed.setText(listItems.get(0));
				listItems.remove(1);
			} else if (i > 0) {
				ed.setText(listItems.get(i));
				i = i - 1;
				ed.setSelection(ed.getText().length());
				listItems.remove(i + 1);
			}
			// createfolder();
			/*
			 * String newFolder = "/sdcard/HTML"; String extStorageDirectory =
			 * Environment.getExternalStorageDirectory().toString(); File
			 * myNewFolder = new File(extStorageDirectory + newFolder);
			 * DeleteRecursive(myNewFolder); toast("folder created");
			 */
			break;
		case R.id.bfull:
			listdialog();
			break;
		case R.id.bTextFilePaste:
			@SuppressWarnings("deprecation")
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			if (clipboard.hasText()) {
				int start = Math.max(ed.getSelectionStart(), 0);
				int end = Math.max(ed.getSelectionEnd(), 0);
				ed.getText().replace(Math.min(start, end),
						Math.max(start, end), clipboard.getText(), 0,
						clipboard.getText().length());
			}
			break;
		case R.id.bTextFileCopy:
			@SuppressWarnings("deprecation")
			ClipboardManager clipboard1 = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			clipboard1.setText(ed.getText().toString());
			toast("copied to clipboard");
			break;
		case R.id.bTextFileGoBack:
			WebViewLoad = (WebView) findViewById(R.id.webViewRun);
			WebViewLoad.goBack();
			break;
		case R.id.bTextFileGoForward:
			WebViewLoad = (WebView) findViewById(R.id.webViewRun);
			WebViewLoad.goForward();
			break;
		}

	}

	public void createfolder(String str) {
		String newFolder = str;
		String extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();
		File myNewFolder = new File(extStorageDirectory + newFolder);
		myNewFolder.mkdir();
	}

	public void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);

		fileOrDirectory.delete();
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

	public void changebcolor(Button b) {
		b.setTextColor(Color.parseColor("white")); // set button text colour to
													// be blue
		// b.setBackgroundColor(Color.parseColor("green")); // set button
		// background colour to be green
	}

	private void replacetext() {
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
				int a = replacecount(input.getText().toString());
				total.setText("Total Found : " + a);
			}
		});
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// String as = "\\<(.*?)\\>";
				String value1 = input1.getText().toString();
				String value = input.getText().toString();
				int a = replacecount(value);
				String s = ed.getText().toString().replaceAll(value, value1);
				ed.setText(s);
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

	private void searchtext() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		LinearLayout lila1 = new LinearLayout(this);
		lila1.setOrientation(1);
		alert.setTitle("Search");
		ed.setText(ed.getText().toString());
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
				int a = replacecount(input.getText().toString());
				total.setText("Total Found : " + a);
			}
		});
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				printMatches(ed.getText().toString(), input.getText()
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

	private int replacecount(String str) {
		String regex_script = str;
		int a = 0;
		String line = ed.getText().toString();
		Pattern p = Pattern.compile(regex_script); // Create a pattern to match
		Matcher m = p.matcher(line); // Create a matcher with an input string
		while (m.find()) {
			a = a + 1;
		}
		return a;
	}

	public void printMatches(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = ed.getText();
			str.setSpan(new BackgroundColorSpan(0xFFFFFF00), startSelection,
					endSelection, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			/*
			 * str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
			 * startSelection, endSelection,
			 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			 */
		}
	}

	private void syntax(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		// Check all occurrences
		while (matcher.find()) {
			int startSelection = matcher.start();
			int endSelection = matcher.end();
			Spannable str = ed.getText();
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

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public boolean isConnected(Context ctx) {
		ConnectivityManager conMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
				|| conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
			return true;
		} else if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
				|| conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
			return false;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		if (ed.length() == 0)
			finish();
		else
			moveTaskToBack(true);
	}
}
