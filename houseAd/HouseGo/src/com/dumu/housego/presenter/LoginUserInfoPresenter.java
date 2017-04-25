package com.dumu.housego.presenter;

import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.ILoginUserInfoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.view.ILoginUserInfoView;

public class LoginUserInfoPresenter implements ILoginUserInfoPresenter {
	private ILoginUserInfoModel model;
	private ILoginUserInfoView view;

	public LoginUserInfoPresenter(ILoginUserInfoView view) {
		super();
		this.view = view;
		model = new LoginUserInfoModel();
	}

	@Override
	public void login(String userid) {
		model.login(userid, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				UserInfo userinfo = (UserInfo) success;
				view.loginUserInfoSuccess();

			}

			@Override
			public void onError(Object error) {
				view.loginUserInfoFail(error.toString());

			}
		});

	}
}
