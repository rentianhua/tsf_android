package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYHQYZMModel;
import com.dumu.housego.model.YHQYZMModel;
import com.dumu.housego.view.IYHQYZMView;

public class YHQYZMPresenter implements IYHQYZMPresenter{
private IYHQYZMView view;
private IYHQYZMModel model;


	public YHQYZMPresenter(IYHQYZMView view) {
	super();
	this.view = view;
	model=new YHQYZMModel();
}


	@Override
	public void yhqyzm(String mob) {
		model.yhqyzm(mob,new  AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				 String info=(String) success;
			view.yhqyzm(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
