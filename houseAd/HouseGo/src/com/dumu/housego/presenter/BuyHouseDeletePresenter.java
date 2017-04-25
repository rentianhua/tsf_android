package com.dumu.housego.presenter;

import com.dumu.housego.model.BuyHouseModel;
import com.dumu.housego.model.IBuyHouseModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IBuyHouseDeleteView;

public class BuyHouseDeletePresenter implements IBuyHouseDeletePresenter {
	private IBuyHouseModel model;
	private IBuyHouseDeleteView view;

	public BuyHouseDeletePresenter(IBuyHouseDeleteView view) {
		super();
		this.view = view;
		model = new BuyHouseModel();
	}

	@Override
	public void DeletebuyHouse(String id, String userid, String username) {
		model.buyhousedelete(id, userid, username, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.deleteBuyHouse(info);

			}

			@Override
			public void onError(Object error) {
				view.deleteBuyHouse(error.toString());
			}
		});

	}

}
