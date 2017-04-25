package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYzmLoginModel;
import com.dumu.housego.model.UserModel;
import com.dumu.housego.view.IYZMLoginView;

public class YzmLoginPresenter implements IYzmLoginPresenter {

	private IYzmLoginModel model;
	private IYZMLoginView view;

	public YzmLoginPresenter(IYZMLoginView view) {
		super();
		this.view = view;
		model = new UserModel();
	}

	@Override
	public void login(String shortnumber, String shortYZM) {
		model.Yzmlogin(shortnumber, shortYZM, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				view.YzmloginSuccess();

			}

			@Override
			public void onError(Object error) {
				view.YzmloginFail(error.toString());

			}
		});

	}

}
