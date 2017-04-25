package com.dumu.housego;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class RentingProprietorActivity extends BaseActivity {
	private LinearLayout llRentingProprietorBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renting_proprietor);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
	}

	private void setListeners() {
		llRentingProprietorBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	private void setViews() {
		llRentingProprietorBack = (LinearLayout) findViewById(R.id.ll_renting_proprietor_back);

	}

}
