package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.ATQiuZuLsitAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ATQiuGouPresenter;
import com.dumu.housego.presenter.ATQiuZuPresenter;
import com.dumu.housego.presenter.IATQiuZuPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IATQiuZuView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ATQiuZuFragment extends Fragment implements IATQiuZuView{
	private LinearLayout ll_qiuzu_back;
	private ListView lvQiuzu;
	private ATQiuZuLsitAdapter adapter;
	private List<QiuzuANDQiuGou>qiuzus;
	private IATQiuZuPresenter presenter;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_at_qiuzu, null);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		presenter=new ATQiuZuPresenter(this);
		presenter.qiuzu(userinfo.getUserid(), "userqiuzu");
		FontHelper.injectFont(view);
		initview(view);
		setlisener();
		return view;
	}
	private void setlisener() {
		ll_qiuzu_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
	}
	private void initview(View view) {
		ll_qiuzu_back=(LinearLayout) view.findViewById(R.id.ll_qiuzu_back);
		lvQiuzu=(ListView) view.findViewById(R.id.lv_qiuzu_at);
		
	}
	@Override
	public void qiuzuSuccess(List<QiuzuANDQiuGou> qiuzus) {
		this.qiuzus=qiuzus;
		adapter=new ATQiuZuLsitAdapter(qiuzus, getActivity());
		lvQiuzu.setAdapter(adapter);
		
	}
	@Override
	public void qiuzuFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}
}
