package com.dumu.housego.presenter;

import com.dumu.housego.entity.YHQ;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYHQGetYzmModel;
import com.dumu.housego.model.YHQGetYzmModel;
import com.dumu.housego.view.IYHQGetYzmView;

public class YHQGetYzmPresenter implements IYHQGetyzmPresenter{
	private IYHQGetYzmModel model;
	private IYHQGetYzmView view;
	
	 
	public YHQGetYzmPresenter(IYHQGetYzmView view) {
		super();
		this.view = view;
		model=new YHQGetYzmModel();
	}

	@Override
	public void YzmInfo(String mob) {
		model.YzmInfo(mob, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.YzmInfo(info);
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
	}

	@Override
	public void AddYHQ(String house_id, String coupon_id, String userid, String buyname, String buytel, String username,
			String yzm) {
		model.AddYHQ(house_id, coupon_id, userid, buyname, buytel, username, yzm, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				YHQ y=(YHQ) success;
				view.AddYhqsuccess(y);
				
			}
			
			@Override
			public void onError(Object error) {
			view.AddYhqfail(error.toString());
				
			}
		});
		
	}

}
