package com.dumu.housego.presenter;

import com.dumu.housego.model.GuanZhuDeleteModel;
import com.dumu.housego.model.IGuanZhuDeleteModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGuanZhuDeleteView;

public class GuanZhuDeletePresenter implements IGuanZhuDeletePresenter {
	private IGuanZhuDeleteModel model;
	private IGuanZhuDeleteView view;

	public GuanZhuDeletePresenter(IGuanZhuDeleteView view) {
		super();
		this.view = view;
		model = new GuanZhuDeleteModel();
	}

	@Override
	public void deleteGuanZhu(String id, String userid, String username) {
		model.deleteGuanZhu(id, userid, username, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.deleteGuanZhuSuccess(info);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.deleteGuanZhuFail(errorinfo);

			}
		});

	}

}
