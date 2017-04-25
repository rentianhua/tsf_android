package com.dumu.housego.presenter;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.ErShouFangDetailModel;
import com.dumu.housego.model.IErShouFangDetailaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IErShouFangDetailView;

public class ErShouFangDetailPresenter implements IErShouFangDetailPresenter {

	private IErShouFangDetailaModel model;
	private IErShouFangDetailView view;
	private ErShouFangDetails ershoufangdetail;

	public ErShouFangDetailPresenter(IErShouFangDetailView view) {
		super();
		this.view = view;
		model = new ErShouFangDetailModel();
	}

	@Override
	public void FindErShouFangdetail(String catid, String id) {
		model.FindErShouFangDetail(catid, id, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				ershoufangdetail = (ErShouFangDetails) success;
				view.showErshoufangData(ershoufangdetail);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

}
