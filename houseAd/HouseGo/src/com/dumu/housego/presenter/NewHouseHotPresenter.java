package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.INewHouseHotModel;
import com.dumu.housego.model.NewHouseHotModel;
import com.dumu.housego.view.INewHouseHotView;

public class NewHouseHotPresenter implements INewHouseHotPresenter {
	private INewHouseHotModel model;
	private INewHouseHotView view;

	public NewHouseHotPresenter(INewHouseHotView view) {
		super();
		this.view = view;
		model = new NewHouseHotModel();
	}

	@Override
	public void LoadNewHouseHot() {
		model.GetNewHouseHot(new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseHotRecommend> newhousehots = (List<NewHouseHotRecommend>) success;
				view.showNewHouseHot(newhousehots);

			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
