package com.imoviesong.htmlconsole;


import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewclient extends WebViewClient {
	public boolean shouldOverrideUrl(WebView v,String url){
		v.loadUrl(url);
		return true;
	}

}
