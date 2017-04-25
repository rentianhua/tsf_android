package com.dumu.housego;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.RecommendHouseAdapter;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.presenter.GouHouseXuZhiPresenter;
import com.dumu.housego.presenter.IGouHouseXuzhiPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IGouHouseXuzhiView;

public class HousePuchaseMainActivity extends BaseActivity implements IGouHouseXuzhiView{
	private LinearLayout llHousePuchaseback;
	private ListView lvGoufangxuzhi;
	private RecommendHouseAdapter adapter;
	private List<RecommendNews> recommends;
	private IGouHouseXuzhiPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_puchase_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
	
		setViews();
		
		setListener();
		presenter=new GouHouseXuZhiPresenter(this);
		presenter.GouHouseXuzhi();
	}

	private void setListener() {
		llHousePuchaseback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		lvGoufangxuzhi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent i = new Intent(HousePuchaseMainActivity.this, WapRecommedMainActivity.class);
				String url = recommends.get(position).getUrl();//"http://www.taoshenfang.com" + recommends.get(position).getThumb();
				//String title = recommends.get(position).getTitle();
				//String content = recommends.get(position).getDescription();
				//i.putExtra("content", content);
				i.putExtra("url", url);
				//i.putExtra("title", title);
				startActivity(i);
			}
		});

	}

	private void setViews() {
		llHousePuchaseback = (LinearLayout) findViewById(R.id.ll_house_puchase_back);
		lvGoufangxuzhi=(ListView) findViewById(R.id.lv_goufangxuzhi);
	}

	@Override
	public void GouHouseXuzhi(List<RecommendNews> recommends) {
		this.recommends=recommends;
		Log.e("recommends", "recommends="+recommends);
		adapter=new RecommendHouseAdapter(recommends, getApplicationContext());
		lvGoufangxuzhi.setAdapter(adapter);
		
	}

}
