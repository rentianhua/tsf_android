package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.NoScrollViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class AGChengJiaohouseFragment extends Fragment {
	private LinearLayout llBackAgentchengjiao;
	private NoScrollViewPager chengjiaoViewpage;
	private List<Fragment> fragments;
	private RadioGroup rg_chengjiao_control;
	private RadioButton rbChengJiaoErShou;
	private RadioButton rbChengJiaoZuFang;
	private PagerAdapter pagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chengjiaohouse, null);
		initView(view);
		rbChengJiaoErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
		FontHelper.injectFont(view);
		setViewPagerAdapter();
		setListener();
		return view;
	}

	private void setViewPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new ATChengJiaoESFragment());
		fragments.add(new ATChengJiaoRTFragment());

		pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
		chengjiaoViewpage.setAdapter(pagerAdapter);

	}

	private void initView(View view) {
		llBackAgentchengjiao = (LinearLayout) view.findViewById(R.id.ll_back_agentchengjiao);
		chengjiaoViewpage = (NoScrollViewPager) view.findViewById(R.id.chengjiao_viewpage);
		rg_chengjiao_control = (RadioGroup) view.findViewById(R.id.rg_chengjiao_control);
		rbChengJiaoErShou = (RadioButton) view.findViewById(R.id.chengjiao_ershou);
		rbChengJiaoZuFang = (RadioButton) view.findViewById(R.id.chengjiao_zufang);
	}

	private void setListener() {
		llBackAgentchengjiao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});

		rg_chengjiao_control.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.chengjiao_ershou:
					chengjiaoViewpage.setCurrentItem(0);
					rbChengJiaoErShou.setTextColor(getResources().getColor(R.color.button_ckeck));
					rbChengJiaoZuFang.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;
				case R.id.chengjiao_zufang:
					chengjiaoViewpage.setCurrentItem(1);
					rbChengJiaoZuFang.setTextColor(getResources().getColor(R.color.button_ckeck));
					rbChengJiaoErShou.setTextColor(getResources().getColor(R.color.button_unckeck));
					break;

				}

			}
		});

	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments==null?0:fragments.size();
		}

	}

}
