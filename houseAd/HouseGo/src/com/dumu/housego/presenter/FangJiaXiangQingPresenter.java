package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.FangJiaXiangQing;
import com.dumu.housego.model.FangJiaXiangqingModel;
import com.dumu.housego.model.IFangJiaXiangQingPresenter;
import com.dumu.housego.model.IFangJiaXiangqingModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IFangjiaXiangQingView;

public class FangJiaXiangQingPresenter implements IFangJiaXiangQingPresenter{
	private IFangJiaXiangqingModel model;
	private IFangjiaXiangQingView view;
	
	public FangJiaXiangQingPresenter(IFangjiaXiangQingView view) {
		super();
		this.view = view;
		model=new FangJiaXiangqingModel();
	}

	@Override
	public void fangjia() {
		model.fangjia(new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<FangJiaXiangQing>fangs=(List<FangJiaXiangQing>) success;
				view.fangjia(fangs);
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
