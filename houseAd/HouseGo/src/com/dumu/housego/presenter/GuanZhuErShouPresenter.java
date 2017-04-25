package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.GuanZhuErShouModel;
import com.dumu.housego.model.IGuanZhuErShouModel;
import com.dumu.housego.model.IModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGuanZhuErShouView;

public class GuanZhuErShouPresenter implements IGuanZhuErShouPresenter {
	private IGuanZhuErShouView view;
	private IGuanZhuErShouModel model;

	public GuanZhuErShouPresenter(IGuanZhuErShouView view) {
		super();
		this.view = view;
		model = new GuanZhuErShouModel();
	}

	@Override
	public void LoadGuanZhuErShou(String username, String table) {
		model.loadGuanZhuErShou(username, table, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangDetails> ershoufangdetails = (List<ErShouFangDetails>) success;
				view.showGuanZhuSuccess(ershoufangdetails);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.showGuanZhuFail(errorinfo);

			}
		});

	}

}
