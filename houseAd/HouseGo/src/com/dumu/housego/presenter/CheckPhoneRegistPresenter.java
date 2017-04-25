package com.dumu.housego.presenter;

import com.dumu.housego.model.CheckPhoneRegistModel;
import com.dumu.housego.model.ICheckPhoneRegistModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.ICheckPhoneRegistView;

public class CheckPhoneRegistPresenter implements ICheckPhoneRegistPresenter {
	private ICheckPhoneRegistModel model;
	private ICheckPhoneRegistView view;

	public CheckPhoneRegistPresenter(ICheckPhoneRegistView view) {
		super();
		this.view = view;
		model = new CheckPhoneRegistModel();

	}

	@Override
	public void checkPhone(String phonenum) {
		model.checkPhone(phonenum, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.CheckSuccess(info);

			}

			@Override
			public void onError(Object error) {
				view.CheckFail(error.toString());

			}
		});

	}

}
