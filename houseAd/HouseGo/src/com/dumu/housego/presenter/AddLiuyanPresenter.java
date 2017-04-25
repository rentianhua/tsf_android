package com.dumu.housego.presenter;

import com.dumu.housego.model.AddLiuYanModel;
import com.dumu.housego.model.IAddLiuYanModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAddLiuYanView;

public class AddLiuyanPresenter implements IAddLiuYanPresenter {
	private IAddLiuYanModel model;
	private IAddLiuYanView view;
	
	
	
	
	public AddLiuyanPresenter(IAddLiuYanView view) {
		super();
		this.view = view;
		model=new AddLiuYanModel();
	}


	@Override
	public void addLiuyan(String from_uid, String to_uid, String content) {
		model.addLiuyan(from_uid, to_uid, content, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.addliuyan(info);
				
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
	}

}
