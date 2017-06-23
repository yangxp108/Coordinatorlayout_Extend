package com.example.administrator.qzsdevelopapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kennyc.view.MultiStateView;

/**
 * Created by qzs on 2017/5/11.
 */

public class SportDetailActivity extends AppCompatActivity {
   private MultiStateView multiStateView;
    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sportdetial);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        webView= (WebView) findViewById(R.id.webView);
        multiStateView= (MultiStateView) findViewById(R.id.multiStateView);
        Intent intent=getIntent();
        String url=intent.getStringExtra("weburl");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
             webView.loadUrl(url);
         webView.setWebViewClient(new WebViewClient(){
             @Override
             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                 super.onPageStarted(view, url, favicon);
                 multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
             }

             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
             }

             @Override
             public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                 super.onReceivedError(view, errorCode, description, failingUrl);
                 multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
             }
         });

    }
}
