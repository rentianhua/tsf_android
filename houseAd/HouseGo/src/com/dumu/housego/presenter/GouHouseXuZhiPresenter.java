package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.GouHouseXuzhiModel;
import com.dumu.housego.model.IGouHouseXuzhiModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGouHouseXuzhiView;

public class GouHouseXuZhiPresenter implements IGouHouseXuzhiPresenter{
	private IGouHouseXuzhiModel model;
	private IGouHouseXuzhiView view;
	
	public GouHouseXuZhiPresenter(IGouHouseXuzhiView view) {
		super();
		this.view = view;
		model=new GouHouseXuzhiModel();
	}

	@Override
	public void GouHouseXuzhi() {
		model.GouHouseXuzhi(new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<RecommendNews> recommends=(List<RecommendNews>) success;
			view.GouHouseXuzhi(recommends);
				
			}
			
			@Override
			public void onError(Object error) {
				
			}
		});
		
	}

}
