package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.AgentChengJiaoErShouAdapter;
import com.dumu.housego.adapter.AgentCommentAdapter;
import com.dumu.housego.adapter.AgentSubmitErShouAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.AddAgentCommentPresenter;
import com.dumu.housego.presenter.IAddAgentCommentPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IAddAgentCommentView;

public class ShowAgentDataActivity extends BaseActivity implements IAddAgentCommentView {
	private Handler handler=new Handler();
	private RelativeLayout rl_agent_comment,rl_agent_list;
	private LinearLayout ll_back_agent,ll_back_agent2;
	private TextView tv_submit_comment,tv_agent_list_title;
	private EditText et_comment_agent;
	private ListViewForScrollView  agent_listview;
	private List<ErShouFangDetails> ESers=new ArrayList<ErShouFangDetails>();
	private List<ErShouFangDetails> ershoudes=new ArrayList<ErShouFangDetails>();
	private List<AgentCommentList> comments=new ArrayList<AgentCommentList>();
	private String TAG;
	
	private AgentCommentAdapter adapter;
	private AgentChengJiaoErShouAdapter chengjiaoadapter;
	private AgentSubmitErShouAdapter ershouadapter;
	
	private IAddAgentCommentPresenter presenter;
	private String id,user_id,author,agent,content;
	private UserInfo userinfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_agent_data);
		presenter=new AddAgentCommentPresenter(this);
		FontHelper.injectFont(findViewById(android.R.id.content));
		initViews();
		setListener();
	}
	@Override
	protected void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
	
//		
//		user_id=userinfo.getUserid();
//		author=userinfo.getUsername();
		agent="android";
		id=getIntent().getStringExtra("userid");
		
		ershoudes.clear();
		ESers.clear();
		comments.clear();
		
		String type=getIntent().getStringExtra("TYPE");
		if(type.equals("COMMENT")){
			rl_agent_comment.setVisibility(View.VISIBLE);
			rl_agent_list.setVisibility(View.GONE);
		}else{
			rl_agent_comment.setVisibility(View.GONE);
			rl_agent_list.setVisibility(View.VISIBLE);
			
			this.TAG=getIntent().getStringExtra("TAG");
			if(TAG.equals("chengjiao")){
				tv_agent_list_title.setText("经纪人成交房源");
				this.ershoudes=(List<ErShouFangDetails>) getIntent().getSerializableExtra("chengjiao");
				chengjiaoadapter=new AgentChengJiaoErShouAdapter(ershoudes, getApplicationContext());
				agent_listview.setAdapter(chengjiaoadapter);
			}else if(TAG.equals("ershou")){
				tv_agent_list_title.setText("经纪人二手房");
				this.ESers=(List<ErShouFangDetails>) getIntent().getSerializableExtra("ershou");
				ershouadapter=new AgentSubmitErShouAdapter(ESers, getApplicationContext());
				agent_listview.setAdapter(ershouadapter);
			}else{
				tv_agent_list_title.setText("评论");
				this.comments=(List<AgentCommentList>) getIntent().getSerializableExtra("comment");
				adapter=new AgentCommentAdapter(comments, getApplicationContext());
				agent_listview.setAdapter(adapter);
			}
			
		}
		
		super.onResume();
	}
	
	
	private void initViews() {
		rl_agent_comment=(RelativeLayout) findViewById(R.id.rl_agent_comment);
		rl_agent_list=(RelativeLayout) findViewById(R.id.rl_agent_list);
		ll_back_agent=(LinearLayout) findViewById(R.id.ll_back_agent);
		ll_back_agent2=(LinearLayout) findViewById(R.id.ll_back_agent2);
		
		tv_submit_comment=(TextView) findViewById(R.id.tv_submit_comment);
		tv_agent_list_title=(TextView) findViewById(R.id.tv_agent_list_title);
		et_comment_agent=(EditText) findViewById(R.id.et_comment_agent);
		agent_listview=(ListViewForScrollView) findViewById(R.id.agent_listview);
	}
	
	private void setListener() {
		et_comment_agent.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count==50){
					MyToastShowCenter.CenterToast(getApplicationContext(), "字数已达到上限！");
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
		ll_back_agent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		ll_back_agent2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		tv_submit_comment.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				content=et_comment_agent.getText().toString();
				
				user_id=userinfo.getUserid();
				author=userinfo.getUsername();
				presenter.AgentComment(id, user_id, author, agent, content);
			}
		});
		
		agent_listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(TAG.equals("chengjiao")){
					ErShouFangDetails e=	ershoudes.get(position);
					Intent i = new Intent(ShowAgentDataActivity.this, ErShouFangDetailsActivity.class);
					String catid=e.getCatid();
					String ID=e.getId();
					i.putExtra("catid", catid);
					i.putExtra("id", ID);
					startActivity(i);
				}else if(TAG.equals("ershou")){
					ErShouFangDetails e=ESers.get(position);
					Intent i = new Intent(ShowAgentDataActivity.this, ErShouFangDetailsActivity.class);
					String catid=e.getCatid();
					String ID=e.getId();
					i.putExtra("catid", catid);
					i.putExtra("id", ID);
					startActivity(i);
				}else{
					
				}
			}
		});
		
		
		
		
	}
	@Override
	public void AddAgentComment(String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		handler.postDelayed(new Runnable() {
			public void run() {
				finish();
			}
		}, 1000);
	}
	

}
