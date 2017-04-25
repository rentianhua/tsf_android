package com.dumu.housego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class WebHousePriceDetailActivity extends BaseActivity {
    private WebView webView;
    private LinearLayout ll_house_price_detail_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_house_price_detail);
        FontHelper.injectFont(findViewById(android.R.id.content));
        webView = (WebView) findViewById(R.id.web_house_price);
        ll_house_price_detail_back = (LinearLayout) findViewById(R.id.ll_house_price_detail_back);
        setListener();
        setwebView();

    }

    private void setListener() {
        ll_house_price_detail_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    private void setwebView() {
        webView.clearCache(true);

        webView.getSettings().setJavaScriptEnabled(true);
        String url = "http://www.taoshenfang.com/index.php?a=lists&catid=57";

        Log.i("yanglijun", "---------------------------------------" + url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            // ÿ�ε��������ӻ�ִ��
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String tag = "tarena:tel/";
                if (url.contains(tag)) {
                    int taglength = tag.length();
                    String mobile = url.substring(taglength);
                    Uri uri = Uri.parse("tel:" + mobile);
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(intent);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
