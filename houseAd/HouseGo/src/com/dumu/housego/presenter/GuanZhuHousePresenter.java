package com.dumu.housego.presenter;

import com.dumu.housego.model.GuanZhuHouseModel;
import com.dumu.housego.model.IGuanZhuHouseModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGuanZhuHouseView;

public class GuanZhuHousePresenter implements IGuanZhuHousePresenter {
	private IGuanZhuHouseModel model;
	private IGuanZhuHouseView view;

	public GuanZhuHousePresenter(IGuanZhuHouseView view) {
		super();
		this.view = view;
		model = new GuanZhuHouseModel();
	}

	@Override
	public void LoadGuanZhuHouse(String fromid, String fromtable, String userid, String username, String type,
			String t) {
		model.LoadGuanZhuHouse(fromid, fromtable, userid, username, type, t, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.GuanZhuSuccess(info);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.GuanZhuFail(errorinfo);

			}
		});

	}

}
