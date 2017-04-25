package com.dumu.housego;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.util.FontHelper;

public class WelcomeMainActivity extends BaseActivity {
	private AlphaAnimation startAnima;
	private TextView tvVersion;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_welcome_main, null);
		setContentView(view);
		FontHelper.injectFont(findViewById(android.R.id.content));
		initData();
	}

	private void initData() {
		tvVersion = (TextView) findViewById(R.id.tv_version);

		PackageManager pm = getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo("com.dumu.housego", 0);
			tvVersion.setText("Version " + pi.versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		startAnima = new AlphaAnimation(0.3f, 1.0f);
		startAnima.setDuration(2000);
		view.startAnimation(startAnima);

		startAnima.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				returnToMain();

			}
		});

	}

	protected void returnToMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();

	}

}
