package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.util.FontHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class JiSuanQiFragment extends Fragment{
	private LinearLayout ll_jisuanqi_back;
	private WebView wb_jisuanqi;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragment_jisuanqi, null);
	initViews(view);
	setListener();
	setwebView();
	FontHelper.injectFont(view);
		return view;
	}

	private void setListener() {
		ll_jisuanqi_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
	}

	private void initViews(View view) {
		ll_jisuanqi_back=(LinearLayout) view.findViewById(R.id.ll_jisuanqi_back);
		wb_jisuanqi=(WebView) view.findViewById(R.id.wb_jisuanqi);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setwebView() {
		wb_jisuanqi.clearCache(true);

		wb_jisuanqi.getSettings().setJavaScriptEnabled(true);
				String url = "http://www.taoshenfang.com/index.php?g=Wap&m=calculate&a=fangdai";

				Log.i("yanglijun", "---------------------------------------" + url);
				wb_jisuanqi.loadUrl(url);
				wb_jisuanqi.setWebViewClient(new WebViewClient() {
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