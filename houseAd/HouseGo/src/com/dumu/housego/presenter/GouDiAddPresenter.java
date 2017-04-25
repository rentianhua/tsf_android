package com.dumu.housego.presenter;

import com.dumu.housego.model.GouDiAddModel;
import com.dumu.housego.model.IGouDiAddModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGouDiAddView;

public class GouDiAddPresenter implements IGouDiAddPresenter{
	private IGouDiAddModel model;
	private IGouDiAddView view;
	
	public GouDiAddPresenter(IGouDiAddView view) {
		super();
		this.view = view;
		model=new GouDiAddModel();
	}

	@Override
	public void AddGoudi(String house_id, String userid, String title, String jine) {
		model.AddGoudi(house_id, userid, title, jine, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.addGoudiSuccess(info);
				
			}
			
			@Override
			public void onError(Object error) {
				view.addGoudiFail(error.toString());
				
			}
		});
		
	}

}
