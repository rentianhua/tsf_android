package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.model.IMyYuYueHouseModel;
import com.dumu.housego.model.MyYuYueHouseModel;
import com.dumu.housego.view.IMyYuYueHouseView;

public class MyYuYueHousePresenter implements IMyYuYueHousePresenter {

	private IMyYuYueHouseModel model;
	private IMyYuYueHouseView view;

	public MyYuYueHousePresenter(IMyYuYueHouseView view) {
		super();
		this.view = view;
		model = new MyYuYueHouseModel();
	}

	@Override
	public void LoadMyYuYueHosue(String username) {
		model.LoadYuYueData(username, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<YuYueData> yuyuedatas = (List<YuYueData>) success;
				view.LoadYuYueDatasuccess(yuyuedatas);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.LoadYuYueDataFaid(errorinfo);

			}
		});

	}

}
