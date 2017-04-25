package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.ATQiuGouLsitAdapter;
import com.dumu.housego.adapter.ATQiuZuLsitAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ATQiuGouPresenter;
import com.dumu.housego.presenter.IATQiuGouPresenter;
import com.dumu.housego.presenter.IATQiuZuPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IATQiuGouView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ATQiuGouFragment extends Fragment implements IATQiuGouView{
	private LinearLayout ll_qiugou_back;
	private ListView lvQiuGou;
	private ATQiuGouLsitAdapter adapter;
	private List<QiuzuANDQiuGou>qiugous;
	private IATQiuGouPresenter presenter;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_at_qiugou, null);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		presenter=new ATQiuGouPresenter(this);
		presenter.qiugou(userinfo.getUserid(), "userqiugou");
		FontHelper.injectFont(view);
		initview(view);
		setlisener();
		return view;
	}
	
	
	private void setlisener() {
		ll_qiugou_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
	}
	private void initview(View view) {
		ll_qiugou_back=(LinearLayout) view.findViewById(R.id.ll_qiugou_back);
		lvQiuGou=(ListView) view.findViewById(R.id.lv_qiugou_at);
		
	}
	
	
	@Override
	public void qiugouSuccess(List<QiuzuANDQiuGou> qiugous) {
		this.qiugous=qiugous;
		adapter=new ATQiuGouLsitAdapter(qiugous, getActivity());
		lvQiuGou.setAdapter(adapter);
		
	}
	@Override
	public void qiugouFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);
		
	}

}
