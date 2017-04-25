package com.dumu.housego.presenter;

import com.dumu.housego.model.ILiuYanDeleteModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LiuYanDeleteModel;
import com.dumu.housego.model.LiuYanDetailModel;
import com.dumu.housego.view.ILiuYanDeleteView;

public class LiuYanDeletePresenter implements ILiuYanDeletePresenter {
	private ILiuYanDeleteModel model;
	private ILiuYanDeleteView view;
	
	public LiuYanDeletePresenter(ILiuYanDeleteView view) {
		super();
		this.view = view;
		model=new LiuYanDeleteModel();
	}

	@Override
	public void Deleteliuyan(String id, String userid) {
		model.Deleteliuyan(id, userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.deleteliuyan(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
