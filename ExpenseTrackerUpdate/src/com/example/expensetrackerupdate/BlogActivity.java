package com.example.expensetrackerupdate;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class BlogActivity extends Activity{
	WebView web;

	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.blog);
		web = (WebView)findViewById(R.id.webView1);
		web.loadUrl("http://192.168.2.4:8090/wordpress/?page_id=696");
	}
}
