package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYuYueHouseModel;
import com.dumu.housego.model.YuYueHouseModel;
import com.dumu.housego.view.IYuYueHouseView;

public class YuYueHousePresenter implements IYuYueHousePresenter {
	private IYuYueHouseModel model;
	private IYuYueHouseView view;

	public YuYueHousePresenter(IYuYueHouseView view) {
		super();
		this.view = view;
		model = new YuYueHouseModel();
	}

	@Override
	public void loadyuyue(String formid, String fromtable, String username, String fromuserid, String type,
			String yuyuedate, String yuyuetime, String t) {
		model.Loadyuyue(formid, fromtable, username, fromuserid, type, yuyuedate, yuyuetime, t, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.yuYueSuccess(info);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.yuYueFail(errorinfo);

			}
		});

	}

}
