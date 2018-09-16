package com.engineerskasa.eknews;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dmax.dialog.SpotsDialog;


public class DetailNews extends AppCompatActivity {

     WebView mWebView;
     SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        mWebView = (WebView)findViewById(R.id.webview);
        dialog = new SpotsDialog(this);
        dialog.show();


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
            }
        });

        if(getIntent()!=null)
        {
            if(!getIntent().getStringExtra("webURL").isEmpty())
                mWebView.loadUrl(getIntent().getStringExtra("webURL"));
        }
    }
}
