package com.dumu.housego.presenter;

import com.dumu.housego.model.BuyHouseSubmitModel;
import com.dumu.housego.model.IBuyHouseSubmitModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IBuyHouseSubmitView;

public class BuyHouseSubmitPresenter implements IBuyHouseSubmitPresenter {

	private IBuyHouseSubmitModel model;
	private IBuyHouseSubmitView view;

	public BuyHouseSubmitPresenter(IBuyHouseSubmitView view) {
		super();
		this.view = view;
		model = new BuyHouseSubmitModel();
	}

	@Override
	public void buyhousesubmit(String username, String province, String city, String area, String shi,
			String zongjiarange, String chenghu, String title) {
		model.buyhousesubmit(username, province, city, area, shi, zongjiarange, chenghu, title, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.buyHouse(info);

			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
