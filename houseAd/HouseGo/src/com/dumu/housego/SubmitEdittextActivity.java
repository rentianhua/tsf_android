package com.dumu.housego;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class SubmitEdittextActivity extends BaseActivity {
	private static final int BIAOTI = 1;
	private static final int FANGYUANMIAOSHU = 2;
	private static final int TOUZIFENXI = 3;
	private static final int HUXINGJIESHAO = 4;
	private static final int XIAOQUJIESHAO = 5;
	private static final int SHUIFEIJIEXI = 6;
	private static final int ZHUANGXIUMIAOSHU = 7;
	private static final int ZHOUBIANPEITAO = 8;
	private static final int JIAOYUPEITAO = 9;
	private static final int JIAOTONGCHUXING = 10;
	private static final int HEXINMAIDIAN = 11;
	private static final int XIAOQUYOUSHI = 12;
	private static final int QUANSHUDIYA = 13;
	private static final int TUIJIANLIYOU = 14;
	
	private static final int HOUSEADDRESS = 16;
	private static final int HOUSEBEST = 17;
	private static final int CHUZUCAUSE = 18;
	private static final int YEZHUSHUO = 19;
	

	private static final int BACK = 404;

	private LinearLayout llBackSubmitedittext;
	private EditText submitEdittext;
	private TextView tvSubmiteditsave;
	private String string;
	private String string1;
	int X;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_edittext);
		FontHelper.injectFont(findViewById(android.R.id.content));
		initView();

		setListener();

	}

	@Override
	protected void onResume() {
		X = getIntent().getIntExtra("X", 0);
		switch (X) {
		case BIAOTI:
			string = getIntent().getStringExtra("S1");
			break;
		case FANGYUANMIAOSHU:
			string = getIntent().getStringExtra("S2");
			break;
		case TOUZIFENXI:
			string = getIntent().getStringExtra("S3");
			break;
		case HUXINGJIESHAO:
			string = getIntent().getStringExtra("S4");
			break;
		case XIAOQUJIESHAO:
			string = getIntent().getStringExtra("S5");
			break;

		case SHUIFEIJIEXI:
			string = getIntent().getStringExtra("S6");
			break;
		case ZHUANGXIUMIAOSHU:
			string = getIntent().getStringExtra("S7");
			break;
		case ZHOUBIANPEITAO:
			string = getIntent().getStringExtra("S8");
			break;
		case JIAOYUPEITAO:
			string = getIntent().getStringExtra("S9");
			break;
		case JIAOTONGCHUXING:
			string = getIntent().getStringExtra("S10");
			break;
		case HEXINMAIDIAN:
			string = getIntent().getStringExtra("S11");
			break;
		case XIAOQUYOUSHI:
			string = getIntent().getStringExtra("S12");
			break;
		case QUANSHUDIYA:
			string = getIntent().getStringExtra("S13");
			break;
		case TUIJIANLIYOU:
			string = getIntent().getStringExtra("S14");
			break;
			
		case HOUSEADDRESS:
			string = getIntent().getStringExtra("S16");
			break;
		case HOUSEBEST:
			string = getIntent().getStringExtra("S17");
			break;
		case CHUZUCAUSE:
			string = getIntent().getStringExtra("S18");
			break;
		case YEZHUSHUO:
			string = getIntent().getStringExtra("S19");
			break;
			
		}
		
		Log.e("xxxxxxxxxxxxxxxxx", "string+x="+string+X);
		submitEdittext.setText(string);
		super.onResume();
	}

	private void setListener() {
		llBackSubmitedittext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent i = new Intent(SubmitEdittextActivity.this, AgentPersonalActivity.class);
//				i.putExtra(name, value);
//				setResult(BACK, i);
				finish();
				
			}
		});

		tvSubmiteditsave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				switch (X) {
				case BIAOTI:
					String str = submitEdittext.getText().toString();
					Intent i = new Intent(SubmitEdittextActivity.this, AgentPersonalActivity.class);
					i.putExtra("M1", str);
					setResult(BIAOTI, i);
					break;
				case FANGYUANMIAOSHU:
					String str2 = submitEdittext.getText().toString();
					Intent i2 = new Intent(SubmitEdittextActivity.this, AgentPersonalActivity.class);
					i2.putExtra("M2", str2);
					setResult(FANGYUANMIAOSHU, i2);
					break;
				case TOUZIFENXI:

					IntentData(TOUZIFENXI, "M3");
					break;
				case HUXINGJIESHAO:

					IntentData(HUXINGJIESHAO, "M4");
					break;
				case XIAOQUJIESHAO:

					IntentData(XIAOQUJIESHAO, "M5");
					break;

				case SHUIFEIJIEXI:

					IntentData(SHUIFEIJIEXI, "M6");
					break;
				case ZHUANGXIUMIAOSHU:

					IntentData(ZHUANGXIUMIAOSHU, "M7");
					break;
				case ZHOUBIANPEITAO:

					IntentData(ZHOUBIANPEITAO, "M8");
					break;
				case JIAOYUPEITAO:

					IntentData(JIAOYUPEITAO, "M9");
					break;
				case JIAOTONGCHUXING:

					IntentData(JIAOTONGCHUXING, "M10");
					break;
				case HEXINMAIDIAN:

					IntentData(HEXINMAIDIAN, "M11");
					break;
				case XIAOQUYOUSHI:

					IntentData(XIAOQUYOUSHI, "M12");
					break;
				case QUANSHUDIYA:

					IntentData(QUANSHUDIYA, "M13");
					break;
				case TUIJIANLIYOU:

					IntentData(TUIJIANLIYOU, "M14");
					break;
					
				case HOUSEADDRESS:

					IntentData(HOUSEADDRESS, "M16");
					break;
				case HOUSEBEST:

					IntentData(HOUSEBEST, "M17");
					break;
				case CHUZUCAUSE:

					IntentData(CHUZUCAUSE, "M18");
					break;
				case YEZHUSHUO:
					String str19 = submitEdittext.getText().toString();
					Intent i19 = new Intent(SubmitEdittextActivity.this, AgentPersonalActivity.class);
					i19.putExtra("M19", str19);
					setResult(YEZHUSHUO, i19);
					break;

				}

				finish();

			}
		});

	}

	private void initView() {
		llBackSubmitedittext = (LinearLayout) findViewById(R.id.ll_back_submitedittext);
		submitEdittext = (EditText) findViewById(R.id.submit_edittext);
		tvSubmiteditsave = (TextView) findViewById(R.id.tv_submiteditsave);
	}

	private void IntentData(int date, String model) {
		
		String str = submitEdittext.getText().toString();
		Intent i = new Intent(SubmitEdittextActivity.this, AgentPersonalActivity.class);
		i.putExtra(model, str);
		setResult(date, i);
	}

}
