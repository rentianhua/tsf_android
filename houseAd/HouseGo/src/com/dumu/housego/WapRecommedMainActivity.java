package com.dumu.housego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class WapRecommedMainActivity extends BaseActivity {
    private LinearLayout llRecommendBack;
    private WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_newsl);
        FontHelper.injectFont(findViewById(android.R.id.content));
        webView = (WebView) findViewById(R.id.web_house_price);
        llRecommendBack = (LinearLayout) findViewById(R.id.ll_recommend_back_web);
        llRecommendBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // 开启 localStorage
        webView.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        webView.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        String url = getIntent().getStringExtra("url");
        if (url != null && url.length() > 0) {
            webView.loadUrl(url);
        }
    }
}
