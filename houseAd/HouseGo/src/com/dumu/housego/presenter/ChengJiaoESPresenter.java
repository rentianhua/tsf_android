package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.ChengJiaoESModel;
import com.dumu.housego.model.IChengJiaoESModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IChengJiaoErShouView;

public class ChengJiaoESPresenter implements IChengjiaoESPresenter {

	private IChengJiaoESModel model;
	private IChengJiaoErShouView view;

	public ChengJiaoESPresenter(IChengJiaoErShouView view) {
		super();
		this.view = view;
		model = new ChengJiaoESModel();
	}

	@Override
	public void ChengJiaoES(String username,String userid, String table) {
		model.ChengJiaoES(username,userid, table, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangDetails> ershoudetails = (List<ErShouFangDetails>) success;
				view.ChengjiaoErShouSuccess(ershoudetails);

			}

			@Override
			public void onError(Object error) {
				view.chengjiaoErShouFail(error.toString());

			}
		});

	}

}
