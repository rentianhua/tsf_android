package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IQiuZuHouseSubmitModel;
import com.dumu.housego.model.QiuZuHouseSubmitModel;
import com.dumu.housego.view.IQiuZuHouseSubmitView;

public class QiuZuHouseSubmitPresenter implements IQiuZuHouseSubmitPresenter {

	private IQiuZuHouseSubmitModel model;
	private IQiuZuHouseSubmitView view;

	public QiuZuHouseSubmitPresenter(IQiuZuHouseSubmitView view) {
		super();
		this.view = view;
		model = new QiuZuHouseSubmitModel();
	}

	@Override
	public void QiuZuSubmit(String username, String province, String city, String area, String zulin, String shi,
			String zujinrange, String chenghu, String title) {
		model.QiuZuSubmit(username, province, city, area, zulin, shi, zujinrange, chenghu, title, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.QiuZuSubmit(info);

			}

			@Override
			public void onError(Object error) {
				view.QiuZuSubmit(error.toString());

			}
		});

	}

}
