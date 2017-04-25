package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRecommendHouseModel;
import com.dumu.housego.model.NewhouseRecommendModel;
import com.dumu.housego.model.RecommendHouseModel;
import com.dumu.housego.view.INewHouseRecommendView;
import com.dumu.housego.view.IShopGuideView;

public class NewHouseRecommendPresenter implements IRecommendHousePresenter {
	private IRecommendHouseModel model;
	private INewHouseRecommendView view;

	public NewHouseRecommendPresenter(INewHouseRecommendView view) {
		super();
		this.view = view;
		model = new NewhouseRecommendModel();
	}

	@Override
	public void LoadRecommend() {
		model.GetRecommedHouse(new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseRecommendData> newrecommends = (List<NewHouseRecommendData>) success;
				view.showData(newrecommends);
			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
