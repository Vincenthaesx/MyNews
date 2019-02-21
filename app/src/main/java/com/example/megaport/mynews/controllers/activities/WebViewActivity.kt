package com.example.megaport.mynews.controllers.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.megaport.mynews.R

class WebViewActivity : Activity() {

    @SuppressLint("SetJavaScriptEnabled")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val intent = intent
        // Retrieves the URL of the article
        val url = intent.getStringExtra("Url")
        // Finds the WebView object
        val webView = findViewById<WebView>(R.id.webView1)
        // Assigns a client to the WebView
        webView.webViewClient = AuthClient()
        webView.settings.javaScriptEnabled = true
        // Load the URL of the article
        webView.loadUrl(url)
    }

    private inner class AuthClient : WebViewClient() {
        override fun onPageFinished(v: WebView, url: String) {
            v.clearHistory()
        }
    }
}

