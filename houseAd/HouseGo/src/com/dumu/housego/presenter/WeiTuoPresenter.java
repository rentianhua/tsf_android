package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.IWeiTuoModel;
import com.dumu.housego.model.WeiTuoModel;
import com.dumu.housego.view.IWeiTuoErShouView;

public class WeiTuoPresenter implements IWeiTuoPresenter{
	private IWeiTuoModel model;
	private IWeiTuoErShouView view;
	
	public WeiTuoPresenter(IWeiTuoErShouView view) {
		super();
		this.view = view;
		model=new WeiTuoModel();
	}

	@Override
	public void WeiTuo(String userid, String table) {
		model.WeiTuo(userid, table, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<ErShouFangDetails> submitershous = (List<ErShouFangDetails>) success;
				view.weituoSuccess(submitershous);
			}
			
			@Override
			public void onError(Object error) {
			view.weituofail(error.toString());
			}
		});
		
	}

}
