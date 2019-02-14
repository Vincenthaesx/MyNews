package com.example.megaport.mynews.Controllers.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.megaport.mynews.R;

public class WebViewActivity extends Activity {

    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        // Retrieves the URL of the article
        String url = intent.getStringExtra("Url");
        // Finds the WebView object
        WebView webView = findViewById(R.id.webView1);
        // Assigns a client to the WebView
        webView.setWebViewClient(new AuthClient());
        webView.getSettings().setJavaScriptEnabled(true);
        // Load the URL of the article
        webView.loadUrl(url);
    }

    private class AuthClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView v, String url) {
            v.clearHistory();
        }
    }
}

