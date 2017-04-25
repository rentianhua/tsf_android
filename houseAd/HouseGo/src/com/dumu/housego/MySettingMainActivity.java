package com.dumu.housego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.FontHelper;

public class MySettingMainActivity extends BaseActivity {
	private LinearLayout llSettingBack;
	private TextView tvLoginOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_setting_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
	}

	private void setListener() {
		llSettingBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		tvLoginOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//User user=null;
				UserInfo userinfo=null;
				
				HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
				HouseGoApp.getContext().SaveCurrentUserInfo(userinfo);
				//HouseGoApp.getContext().SaveCurrentUser(user);
				
				SharedPreferences sharedPre = MySettingMainActivity.this.getSharedPreferences("config", MySettingMainActivity.this.MODE_PRIVATE);
				// ��ȡEditor����
				Editor editor = sharedPre.edit();
				editor.putString("token", "").commit();
				
				
				
				Intent logoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(logoutIntent);
				
//				Intent intent = new Intent();
//				intent.setClass(MySettingMainActivity.this, LoginActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG设置
//				startActivity(intent);
			}
		});

	}

	private void setViews() {
		llSettingBack = (LinearLayout) findViewById(R.id.ll_setting_back);
		tvLoginOut = (TextView) findViewById(R.id.tv_setting_LoginOut);
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

	@Override
	protected void onDestroy() {

		finish();

		super.onDestroy();
	}

}
