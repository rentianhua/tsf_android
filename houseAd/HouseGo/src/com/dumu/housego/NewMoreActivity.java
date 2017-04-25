package com.dumu.housego;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.NewHouseRecommendAdapter;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.NewHouseRecommendPresenter;
import com.dumu.housego.view.INewHouseRecommendView;

public class NewMoreActivity extends BaseActivity implements INewHouseRecommendView {
    private LinearLayout ll_newhouse_back;
    private ListView lv_new_more;
    private List<NewHouseRecommendData> newRecommends;
    private IRecommendHousePresenter presenter;
    private NewHouseRecommendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_more);
        InItView();
        setListener();
        presenter = new NewHouseRecommendPresenter(this);
        presenter.LoadRecommend();


    }

    private void InItView() {
        ll_newhouse_back = (LinearLayout) findViewById(R.id.ll_newhouse_back);
        lv_new_more = (ListView) findViewById(R.id.lv_new_more);
    }

    private void setListener() {
        ll_newhouse_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_new_more.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NewMoreActivity.this, WapRecommedMainActivity.class);
                String url = newRecommends.get(position).getUrl();//"http://www.taoshenfang.com" + newRecommends.get(position).getThumb();
                //String title = newRecommends.get(position).getTitle();
                //String content = newRecommends.get(position).getDescription();
                //i.putExtra("content", content);
                i.putExtra("url", url);
                //i.putExtra("title", title);
                startActivity(i);

            }
        });

    }

    @Override
    public void showData(List<NewHouseRecommendData> newrecommends) {

        this.newRecommends = newrecommends;
        adapter = new NewHouseRecommendAdapter(newrecommends, getApplicationContext());
        lv_new_more.setAdapter(adapter);


    }


}
