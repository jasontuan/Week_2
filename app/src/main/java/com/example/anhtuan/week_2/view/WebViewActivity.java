package com.example.anhtuan.week_2.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.anhtuan.week_2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends Activity {
    String strURL;

    @BindView(R.id.webView)
    WebView myWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        strURL = bundle.getString("URL");

        myWebView.setWebViewClient(new MyBrowser());
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.loadUrl(strURL);
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, WebResourceRequest request) {
            view.loadUrl(strURL);
            return true;
        }
    }
}
