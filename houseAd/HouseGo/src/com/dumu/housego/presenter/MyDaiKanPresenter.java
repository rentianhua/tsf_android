package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;

import java.util.List;

import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.model.IMyDaiKanModel;
import com.dumu.housego.model.MyDaiKanHouseModel;
import com.dumu.housego.view.IMyYuYueHouseView;

public class MyDaiKanPresenter implements IMyDaiKanHousePresenter{
	private IMyDaiKanModel model;
	private IMyYuYueHouseView view;
	
	
	public MyDaiKanPresenter(IMyYuYueHouseView view) {
		super();
		this.view = view;
		model=new MyDaiKanHouseModel();
	}


	@Override
	public void LoadMyYuYueHosue(String username, String t) {
		model.LoadDaiKanData(username, t, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				List<YuYueData> yuyuedatas = (List<YuYueData>) success;
				view.LoadYuYueDatasuccess(yuyuedatas);
				
			}
			
			@Override
			public void onError(Object error) {
				String errorinfo = (String) error;
				view.LoadYuYueDataFaid(errorinfo);
				
			}
		});
		
	}

}
