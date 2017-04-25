package com.dumu.housego.presenter;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRentingDetailModel;
import com.dumu.housego.model.RentingDetailModel;
import com.dumu.housego.view.IRentingDetailView;

public class RentingDetailPresenter implements IRentingDetailPresenter {
	private IRentingDetailView view;
	private IRentingDetailModel model;

	public RentingDetailPresenter(IRentingDetailView view) {
		super();
		this.view = view;
		model = new RentingDetailModel();
	}

	@Override
	public void LoadRenting(String catid, String id) {
		model.LoadRentingData(catid, id, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				RentingDetail r = (RentingDetail) success;
				view.GetRenting(r);

			}

			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub

			}
		});

	}

}
