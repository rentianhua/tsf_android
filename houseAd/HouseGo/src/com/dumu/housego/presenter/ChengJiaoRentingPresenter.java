package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.model.ChengJiaoESModel;
import com.dumu.housego.model.ChengJiaoRtModel;
import com.dumu.housego.model.IChengJiaoESModel;
import com.dumu.housego.model.IChengJiaoRTModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IChengJiaoErShouView;
import com.dumu.housego.view.IChengJiaoRentingView;

public class ChengJiaoRentingPresenter implements IChengjiaoRTPresenter {

	private IChengJiaoRTModel model;
	private IChengJiaoRentingView view;

	public ChengJiaoRentingPresenter(IChengJiaoRentingView view) {
		super();
		this.view = view;
		model = new ChengJiaoRtModel();
	}


	@Override
	public void ChengJiaoRT(String username,String userid, String table) {
		model.ChengJiaoRT(username,userid, table, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				// TODO Auto-generated method stub
				List<RentingRecommendData> ershoudetails = (List<RentingRecommendData>) success;
				view.ChengjiaorentingSuccess(ershoudetails);;
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				view.chengjiaorentingFail(error.toString());
			}
		});
		
	}



}
