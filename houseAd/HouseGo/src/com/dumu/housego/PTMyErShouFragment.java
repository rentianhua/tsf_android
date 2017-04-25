package com.dumu.housego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PTMyErShouFragment extends Fragment {
	private TextView tvShow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ershou_pt, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		tvShow = (TextView) view.findViewById(R.id.tv_show);
		tvShow.setText("你个大猪头");
	}
}
