package com.dumu.housego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;

public class ProprietorMainActivity extends BaseActivity {

	private LinearLayout llProprietorBack;
	private RelativeLayout rlRentingProprietor;
	private RelativeLayout rlErshoufangProprietor;
	private UserInfo userinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proprietor_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		setViews();
		setListener();

	}

	private void setListener() {
		llProprietorBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		rlErshoufangProprietor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(userinfo!=null){
					if(userinfo.getModelid().equals("35")){
					Intent i=new Intent(getApplicationContext(), PuTongMyGuanZhuActivity.class);
					i.putExtra("v", "yezhuershou");
//					Bundle b=new Bundle();
//					b.putString("v", "yezhuershou");
//					i.putExtras(b);
					startActivity(i);
					}else{
						MyToastShowCenter.CenterToast(getApplicationContext(), "经纪人没有委托权限");
					}
				}else{
					MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
					startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				}
				
			
			}
		});

		rlRentingProprietor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(userinfo!=null){
					if(userinfo.getModelid().equals("35")){
						Intent i=new Intent(getApplicationContext(), PuTongMyGuanZhuActivity.class);
						i.putExtra("v", "yezhurenting");
//						Bundle b=new Bundle();
//						b.putString("v", "yezhurenting");
//						i.putExtras(b);
						startActivity(i);
					}else{
						MyToastShowCenter.CenterToast(getApplicationContext(), "经纪人没有委托权限");
					}
				}else{
					MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
					startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				}

			}
		});

	}

	private void setViews() {
		llProprietorBack = (LinearLayout) findViewById(R.id.ll_proprietor_back);
		rlRentingProprietor = (RelativeLayout) findViewById(R.id.rl_renting_proprietor);
		rlErshoufangProprietor = (RelativeLayout) findViewById(R.id.rl_ershoufang_proprietor);
	}

}
