package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WeiTuoGuanLiFragment extends Fragment{
	private LinearLayout ll_weituo_back;
	private RadioGroup rg_weituo;
	private RadioButton btn_ershoufang,btn_renting;
	private ViewPager viewpage;
	private List<Fragment> fragments;
	private PagerAdapter pagerAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_weituo, null);
		initView(view);
		setViewPagerAdapter();
		setListener();
		return view;
	}

	private void setViewPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new ErShouWeiTuoFragment());
		fragments.add(new RentingWeiTuoFragment());

		pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
		viewpage.setAdapter(pagerAdapter);

	}

	private void setListener() {
		ll_weituo_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		viewpage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {

				case 0:
					btn_ershoufang.setChecked(true);
					break;
				case 1:
					btn_renting.setChecked(true);
					break;
			}
			}
		});
		
		rg_weituo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.btn_ershoufang:
					viewpage.setCurrentItem(0);
					break;
				case R.id.btn_renting:
					viewpage.setCurrentItem(1);
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

	
	private void initView(View view) {
		ll_weituo_back=(LinearLayout) view.findViewById(R.id.ll_weituo_back);
		rg_weituo=(RadioGroup) view.findViewById(R.id.rg_weituo);
		btn_renting=(RadioButton) view.findViewById(R.id.btn_renting);
		btn_ershoufang=(RadioButton) view.findViewById(R.id.btn_ershoufang);
		viewpage=(ViewPager) view.findViewById(R.id.viewpage);
	}
}
