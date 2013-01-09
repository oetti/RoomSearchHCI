package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebSicht extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
	        setContentView(R.layout.web_sicht);
	        ActivityRegistry.register(this);
	        WebView view = (WebView) findViewById(R.id.webView1);
	        view.loadUrl("http://www.studentenwerk-berlin.de/mensen/speiseplan/beuth/index.html");
	        //System.out.println(view.getUrl());
	        
	}
}
