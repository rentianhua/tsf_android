package com.dumu.housego;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.SearchXiaoQuAdapter;
import com.dumu.housego.entity.SearchXiaoQu;
import com.dumu.housego.presenter.ISearchXiaoQuPresenter;
import com.dumu.housego.presenter.SearchXiaoQuPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ISearchXiaoQuView;

public class SearchActivity extends BaseActivity implements ISearchXiaoQuView {
    private static final int XIAOQUNAME = 15;
    private ListViewForScrollView listview_search;
    private TextView cancleSearch;
    private EditText et_search;
    private List<SearchXiaoQu> xiaoqus;
    private ISearchXiaoQuPresenter presenter;
    private SearchXiaoQuAdapter adapter;
    String area = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        FontHelper.injectFont(findViewById(android.R.id.content));
        presenter = new SearchXiaoQuPresenter(this);
        area = getIntent().getStringExtra("Area");
        initView();
        setListener();
    }

    private void initView() {
        listview_search = (ListViewForScrollView) findViewById(R.id.listview_search);
        cancleSearch = (TextView) findViewById(R.id.btn_sure_search);
        et_search = (EditText) findViewById(R.id.et_search);
    }

    private void setListener() {
        listview_search.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchXiaoQu x = xiaoqus.get(position);
                String xiaoquname = x.getTitle();
                Intent i = new Intent(SearchActivity.this, AgentPersonalActivity.class);
                i.putExtra("XIAOQUNAME", xiaoquname);
                setResult(XIAOQUNAME, i);
                finish();
            }
        });

        cancleSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Intent i=new Intent(getBaseContext(), AgentPersonalActivity.class);
//				startActivity(i);
                String search = et_search.getText().toString();
                if (search != null || search != "") {
                    Intent i = new Intent(SearchActivity.this, AgentPersonalActivity.class);
                    i.putExtra("XIAOQUNAME", search);
                    setResult(XIAOQUNAME, i);
                } else {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "小区不能为空");
                }

                finish();
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 0) {
                    String key = et_search.getText().toString();

                    presenter.SearchXiaoQu(area, key);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void SearchxiaoquSuccess(List<SearchXiaoQu> xiaoqus) {
        this.xiaoqus = xiaoqus;
        adapter = new SearchXiaoQuAdapter(xiaoqus, getApplicationContext());
        listview_search.setAdapter(adapter);
    }

    public void SearchxiaoquFaid(String info) {
        MyToastShowCenter.CenterToast(getBaseContext(), info);
    }
}
