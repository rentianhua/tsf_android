package com.dumu.housego;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.util.FontHelper;

public class SearchMainActivity extends BaseActivity {
	
	private EditText et_search,et_detail_search;
	private Spinner sp_search;
	private TextView tv_cacle,tv_detail_cacle;
	private LinearLayout rl_detail_search,rl_all_search;
	private String tag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		initView();
		setListener();
	}
	
	@Override 
	protected void onResume() {
		tag=getIntent().getStringExtra("TAG");
		if(tag.equals("all")){
			rl_detail_search.setVisibility(View.GONE);
			rl_all_search.setVisibility(View.VISIBLE);
		}else{
			rl_detail_search.setVisibility(View.VISIBLE);
			rl_all_search.setVisibility(View.GONE);
		}
		super.onResume();
	}
	
	private void initView() {
		et_search=(EditText) findViewById(R.id.et_search);
		sp_search=(Spinner) findViewById(R.id.sp_search);
		tv_cacle=(TextView) findViewById(R.id.tv_cacle);
		tv_detail_cacle=(TextView) findViewById(R.id.tv_detail_cacle);
		et_detail_search=(EditText) findViewById(R.id.et_detail_search);
		rl_detail_search=(LinearLayout) findViewById(R.id.rl_detail_search);
		rl_all_search=(LinearLayout) findViewById(R.id.rl_all_search);
		
	}
	
	private void setListener() {
		tv_cacle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				   et_search.setText("success");
                   String search=et_search.getText().toString().trim();
                   String spinner = sp_search.getSelectedItem().toString();
                   Intent i;
                  if(spinner.equals("二手房")){
                  	i=new Intent(getApplicationContext(), ErShouFangMainActivity.class);
                  }else if(spinner.equals("新房")){
                  	i=new Intent(getApplicationContext(), NewHouseListActivity.class);
                  }else{
                  	i=new Intent(getApplicationContext(), RentingMainActivity.class);
                  }
                  i.putExtra("search", search);
              	startActivity(i);
              	finish();
			}
		});
		tv_detail_cacle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 String search=et_detail_search.getText().toString().trim();
                 Intent i;
                if(tag.equals("ershou")){
                	i=new Intent(getApplicationContext(), ErShouFangMainActivity.class);
                }else if(tag.equals("new")){
                	i=new Intent(getApplicationContext(), NewHouseListActivity.class);
                }else if(tag.equals("block")){
                  	i=new Intent(getApplicationContext(), BlockTradeMainActivity.class);
                }else if(tag.equals("agent")){
                	i=new Intent(getApplicationContext(), AgentMainActivity.class);
                }
               else{
                	i=new Intent(getApplicationContext(), RentingMainActivity.class);
                }
                
                i.putExtra("search", search);
            	startActivity(i);
            	finish();
			}
		});
		
		
		et_detail_search.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    /*判断是否是“GO”键*/
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                     
//                    et_search.setText("success");
                     String search=et_detail_search.getText().toString().trim();
                     Intent i;
                    if(tag.equals("ershou")){
                    	i=new Intent(getApplicationContext(), ErShouFangMainActivity.class);
                    }else if(tag.equals("new")){
                    	i=new Intent(getApplicationContext(), NewHouseListActivity.class);
                    }else if(tag.equals("block")){
                      	i=new Intent(getApplicationContext(), BlockTradeMainActivity.class);
                    }else if(tag.equals("agent")){
                    	i=new Intent(getApplicationContext(), AgentMainActivity.class);
                    }
                   else{
                    	i=new Intent(getApplicationContext(), RentingMainActivity.class);
                    }
                    
                    i.putExtra("search", search);
                	startActivity(i);
                    finish();
                    return true;
                }
                return false;
			}
		});
		
		
		et_search.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    /*判断是否是“GO”键*/
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                     
//                    et_search.setText("success");
                     String search=et_search.getText().toString().trim();
                     String spinner = sp_search.getSelectedItem().toString();
                     Intent i;
                    if(spinner.equals("二手房")){
                    	i=new Intent(getApplicationContext(), ErShouFangMainActivity.class);
                    }else if(spinner.equals("新房")){
                    	i=new Intent(getApplicationContext(), NewHouseListActivity.class);
                    }else{
                    	i=new Intent(getApplicationContext(), RentingMainActivity.class);
                    }
                    i.putExtra("search", search);
                	startActivity(i);
                	finish();
                    return true;
                }
                return false;
			}
		});
  
		
	}


}
