package com.dumu.housego.presenter;

import com.dumu.housego.model.FindPasswordModel;
import com.dumu.housego.model.IFindPasswordModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IFindPasswordView;

public class FindPasswordPresenter implements IFindPasswordPresenter {

	private IFindPasswordModel model;
	private IFindPasswordView view;

	public FindPasswordPresenter(IFindPasswordView view) {
		super();
		this.view = view;
		model = new FindPasswordModel();
	}

	@Override
	public void FindPassword(String phonenum, String smscode, String password, String password2) {
		model.FindPassword(phonenum, smscode, password, password2, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				view.FindPasswordSuccess();

			}

			@Override
			public void onError(Object error) {
				view.FindPasswordFail(error.toString());
			}
		});

	}

}
