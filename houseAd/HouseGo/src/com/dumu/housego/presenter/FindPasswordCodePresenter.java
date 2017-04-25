package com.dumu.housego.presenter;

import com.dumu.housego.model.FindPasswordCodeModel;
import com.dumu.housego.model.IFindPasswordCodeModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IFindPasswordCodeView;

public class FindPasswordCodePresenter implements IFindPasswordCodePresenter {
	private IFindPasswordCodeView view;
	private IFindPasswordCodeModel model;

	public FindPasswordCodePresenter(IFindPasswordCodeView view) {
		super();
		this.view = view;
		model = new FindPasswordCodeModel();
	}

	@Override
	public void FindCode(String phonenum) {
		model.Findcode(phonenum, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				view.FindCodeSuccess();

			}

			@Override
			public void onError(Object error) {
				view.FindCodeFail(error.toString());

			}
		});

	}

}
