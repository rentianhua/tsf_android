package com.dumu.housego;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class MyRentingHouseAgentActivity extends BaseActivity {
	private LinearLayout llBackRentingHouse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_renting_house);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();

	}

	private void setListeners() {
		llBackRentingHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	private void setViews() {
		llBackRentingHouse = (LinearLayout) findViewById(R.id.ll_back_renting_house_agent);

	}

}
