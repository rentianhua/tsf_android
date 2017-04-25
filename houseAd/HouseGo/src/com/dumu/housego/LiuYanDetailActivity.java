package com.dumu.housego;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.LiuYanDetailAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.AddLiuyanPresenter;
import com.dumu.housego.presenter.IAddLiuYanPresenter;
import com.dumu.housego.presenter.ILiuYanDetailPresenter;
import com.dumu.housego.presenter.IchangeLiuyanstatusPresenter;
import com.dumu.housego.presenter.LiuYanDetailPresenter;
import com.dumu.housego.presenter.changLiuyansatusPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IAddLiuYanView;
import com.dumu.housego.view.ILiuYanDetailsView;
import com.dumu.housego.view.IchangeLiuyanstatusView;

public class LiuYanDetailActivity extends BaseActivity  implements  IchangeLiuyanstatusView, ILiuYanDetailsView ,IAddLiuYanView{
	
	private ImageView liuyandetail_back;
	private TextView tvcontact;
	private ListView lv_liuyandetail;
	private LinearLayout ll_edit;
	private EditText et_edit_liuyan;
	private TextView tv_send_liuyan;
	private String towho;
	private List<LiuYanList> liuyans;
	private  LiuYanDetailAdapter adapter;
	private String userid;
	private ILiuYanDetailPresenter presenter;
	private UserInfo userinfo;
	private IAddLiuYanPresenter addPresenter;
	private IchangeLiuyanstatusPresenter changestatusPresenter;
	private String realname;
	private String id;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liu_yan_detail);
		initView();
		setListener();
	}
	
	@Override
	protected void onResume() {
		changestatusPresenter=new changLiuyansatusPresenter(this);
		presenter=new LiuYanDetailPresenter(this);
		addPresenter=new AddLiuyanPresenter(this);
		userinfo=HouseGoApp.getLoginInfo(getApplicationContext());
		
		userid=userinfo.getUserid();
		
	
		if(getIntent().getStringExtra("towhos")!=null){
			towho=getIntent().getStringExtra("towhos");
			realname=getIntent().getStringExtra("realnames");
			presenter.liuyandetail(userid, towho);
		}
		
		
		if(getIntent().getStringExtra("towho")!=null){
			towho=getIntent().getStringExtra("towho");
			realname=getIntent().getStringExtra("realname");
			id=getIntent().getStringExtra("id");
			presenter.liuyandetail(userid, towho);
		}
		
		if(towho.equals("0")){
			tvcontact.setText("系统消息");
		}else{
			if(realname!=null){
				tvcontact.setText(realname);
			}else{
				tvcontact.setText("淘深房用户");
			}
		}
		
		changestatusPresenter.changeStatus(id, userid);
		
		super.onResume();
	}
	
	private void initView() {
		
		tvcontact=(TextView) findViewById(R.id.contact);
		liuyandetail_back=(ImageView) findViewById(R.id.liuyandetail_back);
		lv_liuyandetail=(ListView) findViewById(R.id.lv_liuyandetail);
		ll_edit=(LinearLayout) findViewById(R.id.ll_edit);
		et_edit_liuyan=(EditText) findViewById(R.id.et_edit_liuyan);
		tv_send_liuyan=(TextView) findViewById(R.id.tv_send_liuyan); 
		
		et_edit_liuyan.setFocusable(false);
	}
	
	private void setListener() {
		tv_send_liuyan.setOnClickListener(new OnClickListener() {
			
			private String content;

			@Override
			public void onClick(View v) {
				
				content=et_edit_liuyan.getText().toString();
				addPresenter.addLiuyan(userid, towho, content);
				
			}
		});
		liuyandetail_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		lv_liuyandetail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
//				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘 
			}
		});
		
		et_edit_liuyan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et_edit_liuyan.setFocusable(true);
				et_edit_liuyan.setFocusableInTouchMode(true);
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);  
//				imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘 
				
			}
		});
		
		
		ll_edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et_edit_liuyan.setFocusable(true);
				et_edit_liuyan.setFocusableInTouchMode(true);
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
				imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);  
//				imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘 
				
			}
		});
		
	}

	@Override
	public void liuyanDetailSuccess(List<LiuYanList> liuyans) {
		this.liuyans=liuyans;
		adapter=new LiuYanDetailAdapter(liuyans, LiuYanDetailActivity.this, userid);
		lv_liuyandetail.setAdapter(adapter);
		lv_liuyandetail.setSelection(adapter.getCount()-1);  
		
	}

	@Override
	public void liuyanDetailFail(String info) {
	MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
	}

	@Override
	public void addliuyan(String info) {
//		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		et_edit_liuyan.setText("");
		presenter.liuyandetail(userid, towho);
		
//		lv_liuyandetail.setSelection(adapter.getCount()-1);  
	}
	
	@Override
	public void changestatus(String info) {
	}

	
}
