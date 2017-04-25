package com.dumu.housego.presenter;

import com.dumu.housego.entity.User;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IUserModel;
import com.dumu.housego.model.UserModel;
import com.dumu.housego.view.IRegistView;

public class RegistPresenter implements IRegistPresenter {

	private IRegistView registView;
	private IUserModel userModel;

	public RegistPresenter(IRegistView registView) {
		super();
		userModel = new UserModel();
		this.registView = registView;
	}

	@Override
	public void Regist(User user) {
		userModel.regist(user, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				registView.registSuccess(info);
			}

			@Override
			public void onError(Object error) {
				registView.registFail(error.toString());

			}
		});

	}

}
