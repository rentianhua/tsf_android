package com.dumu.housego.activity;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityManager.addActivity(this);
	}

	protected void onDestroy() {
		super.onDestroy();
		ActivityManager.removeActivity(this);
	}
}
