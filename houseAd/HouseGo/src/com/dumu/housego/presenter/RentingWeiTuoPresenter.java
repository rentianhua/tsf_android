package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRentingWeiTuoModel;
import com.dumu.housego.model.RentingWeiTuoModel;
import com.dumu.housego.view.IRentingWeiTuoView;

public class RentingWeiTuoPresenter implements IRentingWeiTuoPresenter{
	private IRentingWeiTuoModel model;
	private IRentingWeiTuoView view;
	
	public RentingWeiTuoPresenter(IRentingWeiTuoView view) {
		super();
		this.view = view;
		model=new RentingWeiTuoModel();
	}

	@Override
	public void weituo(String userid, String table) {
		model.weituo(userid, table, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<RentingDetail> submitershous = (List<RentingDetail>) success;
				view.weituoSuccess(submitershous);
			}
			
			@Override
			public void onError(Object error) {
			view.weituofail(error.toString());
			}
		});
		
	}

}
