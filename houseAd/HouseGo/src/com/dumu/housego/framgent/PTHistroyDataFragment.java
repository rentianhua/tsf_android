package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.adapter.HistroyDataAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.HistroyData;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.HistoryDataPresenter;
import com.dumu.housego.presenter.IHistroyDataPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.HistoryDataJSONParse;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IHistroyDateView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PTHistroyDataFragment extends Fragment implements IHistroyDateView{
	private LinearLayout llHistroyBack;
	private List<HistroyData> histroys;
	private IHistroyDataPresenter presenter;
	private HistroyDataAdapter adapter;
	private String username;
	private UserInfo userinfo;
	private ListView lvhistroy;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_pt_histroy, null);
		initView(view);
		setListener();
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		username=userinfo.getUsername();
		presenter=new HistoryDataPresenter(this);
		presenter.histroydata(username);
		FontHelper.injectFont(view);
		return view;
	}
	private void initView(View view) {
		llHistroyBack=(LinearLayout) view.findViewById(R.id.ll_histroy_back);
		lvhistroy=(ListView) view.findViewById(R.id.lv_pt_histroy);
	}
	private void setListener() {
		llHistroyBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
	}
	@Override
	public void histroydateSuccess(List<HistroyData> histroys) {
		this.histroys=histroys;
		adapter=new HistroyDataAdapter(histroys, getContext());
		lvhistroy.setAdapter(adapter);
		
	}
	@Override
	public void histroydateFail(String info) {
		MyToastShowCenter.CenterToast(getContext(), info);
		
	}
}
