package com.dumu.housego.presenter;

import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.ErShouUserInfoModel;
import com.dumu.housego.model.IErShouUserInfoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IErshouUserInfoView;

public class ErShouUserInfoPresenter implements IErShouuserInfoPresenter {
	private IErShouUserInfoModel model;
	private IErshouUserInfoView view;

	public ErShouUserInfoPresenter(IErshouUserInfoView view) {
		super();
		this.view = view;
		model = new ErShouUserInfoModel();
	}

	@Override
	public void login(String userid) {
		model.login(userid, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				UserInfo userinfo = (UserInfo) success;
				view.loginUserInfoSuccess(userinfo);

			}

			@Override
			public void onError(Object error) {
				view.loginUserInfoFail(error.toString());
			}
		});

	}
}
