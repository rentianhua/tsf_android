package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.adapter.ChengJiaoErShouAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ChengJiaoESPresenter;
import com.dumu.housego.presenter.IChengjiaoESPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IChengJiaoErShouView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ATChengJiaoESFragment extends Fragment implements IChengJiaoErShouView {
	private ListView lvChengjiaoErshou;
	private ChengJiaoErShouAdapter adapter;
	private List<ErShouFangDetails> ershoudetails;
	private IChengjiaoESPresenter cjespresenter;
	private UserInfo userinfo;
	private String userid;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chengjiao_ershou, null);
		cjespresenter = new ChengJiaoESPresenter(this);
		initView(view);
		FontHelper.injectFont(view);
		setListener();
		return view;
	}

	@Override
	public void onResume() {
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String username = userinfo.getUsername();
		String table = "ershou";
		userid=userinfo.getUserid();
		
		cjespresenter.ChengJiaoES(username, userid,table);
		super.onResume();
	}

	private void initView(View view) {
		lvChengjiaoErshou = (ListView) view.findViewById(R.id.lv_chengjiao_ershou);

	}

	private void setListener() {
//		lvChengjiaoErshou.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
//				String catid = ershoudetails.get(position).getCatid();
//				String ID = ershoudetails.get(position).getId();
//				i.putExtra("catid", catid);
//				i.putExtra("id", ID);
//				startActivity(i);
//
//			}
//		});

	}

	@Override
	public void ChengjiaoErShouSuccess(List<ErShouFangDetails> ershoudetails) {
		this.ershoudetails = ershoudetails;
		Log.e("FRAGMENT", "FRAGMENT="+ershoudetails);
		adapter = new ChengJiaoErShouAdapter(ershoudetails, getActivity());

		lvChengjiaoErshou.setAdapter(adapter);

	}

	@Override
	public void chengjiaoErShouFail(String info) {
		MyToastShowCenter.CenterToast(getActivity(), info);

	}
}
