package com.dumu.housego.presenter;

import com.dumu.housego.model.ILoginModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.UserModel;
import com.dumu.housego.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
	private ILoginModel model;
	private ILoginView view;

	public LoginPresenter(ILoginView view) {
		super();
		this.view = view;
		model = new UserModel();
	}

	@Override
	public void login(String phonenum, String password) {
		model.login(phonenum, password, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				view.loginSuccess();

			}

			@Override
			public void onError(Object error) {
				view.loginFail(error.toString());

			}
		});

	}

}
