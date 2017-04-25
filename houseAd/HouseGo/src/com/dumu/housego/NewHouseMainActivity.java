package com.dumu.housego;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.NewHouseHotAdapter;
import com.dumu.housego.adapter.NewHouseRecommendAdapter;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.presenter.INewHouseHotPresenter;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.NewHouseHotPresenter;
import com.dumu.housego.presenter.NewHouseRecommendPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.INewHouseHotView;
import com.dumu.housego.view.INewHouseRecommendView;

public class NewHouseMainActivity extends BaseActivity implements INewHouseRecommendView, INewHouseHotView {
	private ImageView ivNewHouseBack,iv_location;
	private List<NewHouseRecommendData> newRecommends;
	private ListView lvLoushizixun, lvRexiaofangyuan;
	private IRecommendHousePresenter presenter;
	private NewHouseRecommendAdapter adapter;
	private ScrollView scrollview;
	private TextView tv_newhouse_morenews_rexiao,tv_newhouse_morenews;
	private INewHouseHotPresenter hotPresenter;
	private List<NewHouseHotRecommend> newhousehots;
	private NewHouseHotAdapter hotadapter;
	private LinearLayout ll_newmain_search;
	private TextView tv_newmain_search;
	
	private Button btnfutian,btnliangju,btnjingzhuangxiu,btnlonggang,btnsanju,btnshangpinfang,btnluohu,btnsiju,btnall; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_house_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		presenter = new NewHouseRecommendPresenter(this);
		hotPresenter = new NewHouseHotPresenter(this);
		presenter.LoadRecommend();
		hotPresenter.LoadNewHouseHot();
	}

	private void setListener() {
		
		tv_newhouse_morenews.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(NewHouseMainActivity.this, NewMoreActivity.class));
				
			}
		});
		
		ll_newmain_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), SearchMainActivity.class);
				i.putExtra("TAG", "new");
				startActivity(i);
			}
		});
		
		
		iv_location.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), MapHouseMainActivity.class));
				
			}
		});
		
		ivNewHouseBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NewHouseMainActivity.this.finish();

			}
		});

		lvLoushizixun.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent i = new Intent(NewHouseMainActivity.this, WapRecommedMainActivity.class);
				String url = newRecommends.get(position).getUrl();//"http://www.taoshenfang.com" + newRecommends.get(position).getThumb();
				//String title = newRecommends.get(position).getTitle();
				//String content = newRecommends.get(position).getDescription();
				//i.putExtra("content", content);
				i.putExtra("url", url);
				//i.putExtra("title", title);
				startActivity(i);
			}
		});

		tv_newhouse_morenews_rexiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), NewHouseListActivity.class));

			}
		});

		lvRexiaofangyuan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i1 = new Intent(getApplicationContext(), NewHouseDetailActivity.class);
				NewHouseHotRecommend hot = newhousehots.get(position);
				i1.putExtra("Id", hot.getId());
				i1.putExtra("catid", hot.getCatid());
				startActivity(i1);

			}
		});
		
	
		btnall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "all");
				startActivity(i);
			}
		});
		btnfutian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "futian");
				startActivity(i);
			}
		});
		btnjingzhuangxiu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "jingzhuangxiu");
				startActivity(i);
			}
		});
		btnliangju.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "liangju");
				startActivity(i);
			}
		});
		btnlonggang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "longgang");
				startActivity(i);
			}
		});
		btnluohu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "luohu");
				startActivity(i);
			}
		});
		btnsanju.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "sanju");
				startActivity(i);
			}
		});
		btnshangpinfang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "shangpinfang");
				startActivity(i);
			}
		});
		btnsiju.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), NewHouseListActivity.class);
				i.putExtra("newTag", "siju");
				startActivity(i);
			}
		});

	}

	private void setViews() {
		btnfutian=(Button) findViewById(R.id.futian);
		btnall=(Button) findViewById(R.id.all);
		btnjingzhuangxiu=(Button) findViewById(R.id.refined_decoration);
		btnliangju=(Button) findViewById(R.id.two);
		btnlonggang=(Button) findViewById(R.id.longgang);
		btnluohu=(Button) findViewById(R.id.luohu);
		btnsanju=(Button) findViewById(R.id.tree);
		btnshangpinfang=(Button) findViewById(R.id.building);
		btnsiju=(Button) findViewById(R.id.four);
		
		
		iv_location=(ImageView) findViewById(R.id.iv_location);
		ll_newmain_search=(LinearLayout) findViewById(R.id.ll_newmain_search);
		tv_newmain_search=(TextView) findViewById(R.id.tv_newmain_search);
		ivNewHouseBack = (ImageView) findViewById(R.id.iv_newhouse_back);
		lvLoushizixun = (ListView) findViewById(R.id.lv_loushizixun);
		lvRexiaofangyuan = (ListView) findViewById(R.id.lv_rexiaofangyuan);
		tv_newhouse_morenews_rexiao = (TextView) findViewById(R.id.tv_newhouse_morenews_rexiao);
		tv_newhouse_morenews = (TextView) findViewById(R.id.tv_newhouse_morenews);
		
		scrollview = (ScrollView) findViewById(R.id.newHouse_scrollview);
		scrollview.smoothScrollTo(0, 0);

	}

	@Override
	public void showData(List<NewHouseRecommendData> newrecommends) {
		this.newRecommends = newrecommends;
		adapter = new NewHouseRecommendAdapter(newrecommends, getApplicationContext());
		lvLoushizixun.setAdapter(adapter);
	}

	@Override
	public void showNewHouseHot(List<NewHouseHotRecommend> newhousehots) {
		this.newhousehots = newhousehots;
		hotadapter = new NewHouseHotAdapter(newhousehots, getApplicationContext());
		lvRexiaofangyuan.setAdapter(hotadapter);
	}
}
