package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.model.IMyYuYueHouseModel;
import com.dumu.housego.model.MyYuYueHouseModel;
import com.dumu.housego.view.IMyYuYueDeleteView;
import com.dumu.housego.view.IMyYuYueHouseView;

public class MyYuYueDeletePresenter implements IMyYuYueDeletePresenter {

	private IMyYuYueHouseModel model;
	private IMyYuYueDeleteView view;

	public MyYuYueDeletePresenter(IMyYuYueDeleteView view) {
		super();
		this.view = view;
		model = new MyYuYueHouseModel();
	}

	@Override
	public void DeleteMyYuYueHosue(String id, String userid, String username) {
		model.DeleteYuYue(id, userid, username, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.deleteYuYue(info);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.deleteYuYue(errorinfo);

			}
		});

	}

}
