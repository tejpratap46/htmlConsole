package com.imoviesong.htmlconsole;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.*;

public class help extends Activity {
	TextView tvHelp;
	Button sendEmail, website;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		toggleFullscreen(true);
		tvHelp = (TextView) findViewById(R.id.tvHelp);
		sendEmail = (Button) findViewById(R.id.bHelpSendEmail);
		website = (Button) findViewById(R.id.bHelpWebsite);
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		tvHelp.setText("INSTRUCTION'S:\n\n\n1.If you press back key at first page, then the app is minimize (not closed), It is done to prevent from pressing back button accedently.\n\n2.If you want to add images to webpage, then please give full URL of image (image from file is not supported yet).\n\n3.Support for HTML5 tags depend upon which version of android you are using.\n\n4.While downloading webpage source, you have to first navigate to that page and then download the source.");

		sendEmail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "tejpratap46@gmail.com" });
				email.putExtra(Intent.EXTRA_SUBJECT, "Review html console");
				email.putExtra(Intent.EXTRA_TEXT, "message");
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email,
						"Choose an Email client :"));
			}

		});

		website.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String url = "http://www.usingmtech.blogspot.com";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}

		});
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
