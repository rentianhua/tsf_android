package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.RentingDetailActivity;
import com.dumu.housego.adapter.RentingChengjiaodAdapter;
import com.dumu.housego.adapter.RentingRecommendAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ChengJiaoRentingPresenter;
import com.dumu.housego.presenter.IChengjiaoRTPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IChengJiaoRentingView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ATChengJiaoRTFragment extends Fragment implements IChengJiaoRentingView {
	private ListView lvChengjiaorenting;
	private RentingChengjiaodAdapter adapter;
	private List<RentingRecommendData> rentings;
	private IChengjiaoRTPresenter cjeRTpresenter;
	private UserInfo userinfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chengjiao_zufang, null);
		cjeRTpresenter = new ChengJiaoRentingPresenter(this);
		FontHelper.injectFont(view);
		initView(view);

		setListener();
		return view;
	}
	@Override
	public void onResume() {
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		cjeRTpresenter.ChengJiaoRT(userinfo.getUsername(), userinfo.getUserid(),"chuzu");
		super.onResume();
	}
	private void initView(View view) {
		lvChengjiaorenting=(ListView) view.findViewById(R.id.lv_chengjiao_zufang);
		
	}
	private void setListener() {
		lvChengjiaorenting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getActivity(), RentingDetailActivity.class);
				String catid = rentings.get(position).getCatid();
				String ID = rentings.get(position).getId();
				i.putExtra("catid", catid);
				i.putExtra("id", ID);
				startActivity(i);
				
			}
		});
		
	}
	@Override
	public void ChengjiaorentingSuccess(List<RentingRecommendData> ershoudetails) {
		this.rentings=ershoudetails;
		adapter=new RentingChengjiaodAdapter(rentings, getActivity());
		lvChengjiaorenting.setAdapter(adapter);
		
	}
	@Override
	public void chengjiaorentingFail(String info) {
//		MyToastShowCenter.CenterToast(getContext(), info);
		
	}
}