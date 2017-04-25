package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.ErShouFangRecommendModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRecommendHouseModel;
import com.dumu.housego.model.NewhouseRecommendModel;
import com.dumu.housego.model.RecommendHouseModel;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.dumu.housego.view.INewHouseRecommendView;
import com.dumu.housego.view.IShopGuideView;

public class ErShouFangRecommendPresenter implements IRecommendHousePresenter {
	private IRecommendHouseModel model;
	private IErShouFangRecommendView view;

	public ErShouFangRecommendPresenter(IErShouFangRecommendView view) {
		super();
		this.view = view;
		model = new ErShouFangRecommendModel();
	}

	@Override
	public void LoadRecommend() {
		model.GetRecommedHouse(new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershoufangrecommends = (List<ErShouFangRecommendData>) success;
				view.showData(ershoufangrecommends);
			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
