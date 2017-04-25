package com.dumu.housego.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityManager.addActivity(this);
	}

	protected void onDestroy() {
		super.onDestroy();
		ActivityManager.removeActivity(this);
	}
}
