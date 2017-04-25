package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.model.GuanZhuErShouModel;
import com.dumu.housego.model.GuanZhuNewModel;
import com.dumu.housego.model.IGuanZhuErShouModel;
import com.dumu.housego.model.IGuanZhuNewModel;
import com.dumu.housego.model.IModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGuanZhuErShouView;
import com.dumu.housego.view.IGuanZhuNewView;

public class GuanZhuNewPresenter implements IGuanZhuNewPresenter {
	private IGuanZhuNewView view;
	private IGuanZhuNewModel model;

	public GuanZhuNewPresenter(IGuanZhuNewView view) {
		super();
		this.view = view;
		model = new GuanZhuNewModel();
	}

	@Override
	public void LoadGuanZhuNew(String username, String table) {
		model.loadGuanZhuNew(username, table, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseDetail> newhousedetails = (List<NewHouseDetail>) success;
				view.showGuanZhuSuccess(newhousedetails);

			}

			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.showGuanZhuFail(errorinfo);

			}
		});

	}

}
