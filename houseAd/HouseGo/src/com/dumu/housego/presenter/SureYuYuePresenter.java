package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.ISureYuYueModel;
import com.dumu.housego.model.SureYuYueModel;
import com.dumu.housego.view.ISureYuYueView;

public class SureYuYuePresenter implements ISureYuYuePresenter{
	private ISureYuYueModel model;
	private ISureYuYueView view;
	
	
	public SureYuYuePresenter(ISureYuYueView view) {
		super();
		this.view = view;
		model=new SureYuYueModel();
	}


	@Override
	public void sureyuyue(String username, String id) {
		model.sureyuyue(username, id,new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.sureyuyue(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
