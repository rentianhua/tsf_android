package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.BuyHouseModel;
import com.dumu.housego.model.IBuyHouseModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IBuyHouseListView;

public class BuyHouseListPresenter implements IBuyHouseListPresenter {
	private IBuyHouseListView view;
	private IBuyHouseModel model;

	public BuyHouseListPresenter(IBuyHouseListView view) {
		super();
		this.view = view;
		model = new BuyHouseModel();
	}

	@Override
	public void buyHouse(String username, String table) {
		model.buyhouselist(username, table, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				List<QiuzuANDQiuGou> lists = (List<QiuzuANDQiuGou>) success;
				view.buyhouseSuccess(lists);
			}

			@Override
			public void onError(Object error) {
				view.buyhouseFail(error.toString());
			}
		});

	}

}
