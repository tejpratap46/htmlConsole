package com.imoviesong.htmlconsole;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class file_list extends ListActivity {

	private String path;
	int pos;
	String a;
	EditText etSearch;
	ArrayAdapter adapter;
	String task[] = { "delete", "create" };

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_files);
		etSearch = (EditText) findViewById(R.id.etFileSearch);
		toggleFullscreen(true);
		String newFolder = "HTML Console";
		String extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();
		File myNewFolder = new File(extStorageDirectory + newFolder);
		myNewFolder.mkdir();

		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		// Use the current directory as title
		if (getIntent().hasExtra("fback")) {
			a = getIntent().getStringExtra("fback");
		}
		path = "/sdcard/HTML Console/";
		if (getIntent().hasExtra("path")) {
			path = getIntent().getStringExtra("path");
		}
		setTitle(path);

		// Read all files sorted into the values-array
		List values = new ArrayList();
		File dir = new File(path);
		if (!dir.canRead()) {
			setTitle(getTitle() + " (inaccessible)");
		}
		String[] list = dir.list();
		if (list != null) {
			for (String file : list) {
				if (!file.startsWith(".")) {
					values.add(file);
				}
			}
		}
		Collections.sort(values);

		// Put the data into the list
		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2,
				android.R.id.text1, values);
		setListAdapter(adapter);

		etSearch.addTextChangedListener(new TextWatcher() {

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
				file_list.this.adapter.getFilter().filter(arg0);
			}

		});

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		pos = position;
		String filename = (String) getListAdapter().getItem(position);
		if (path.endsWith(File.separator)) {
			filename = path + filename;
		} else {
			filename = path + File.separator + filename;
		}
		if (new File(filename).isDirectory()) {
			Intent intent = new Intent(this, file_list.class);
			intent.putExtra("path", filename);
			startActivity(intent);
			finish();
		} else {
			Intent intent = new Intent(this, textfile.class);
			intent.putExtra("path", filename);
			startActivity(intent);
			finish();
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

	public void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);

		fileOrDirectory.delete();
	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(file_list.this, textfile.class);
		i.putExtra("fback", a);
		startActivity(i);
		finish();
	}

}