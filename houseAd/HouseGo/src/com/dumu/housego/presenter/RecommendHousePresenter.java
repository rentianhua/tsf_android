package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRecommendHouseModel;
import com.dumu.housego.model.RecommendHouseModel;
import com.dumu.housego.view.IShopGuideView;

public class RecommendHousePresenter implements IRecommendHousePresenter {
	private IRecommendHouseModel model;
	private IShopGuideView view;

	public RecommendHousePresenter(IShopGuideView view) {
		super();
		this.view = view;
		model = new RecommendHouseModel();
	}

	@Override
	public void LoadRecommend() {
		model.GetRecommedHouse(new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<RecommendNews> recommends = (List<RecommendNews>) success;
				view.showData(recommends);
			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
