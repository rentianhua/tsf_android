package com.dumu.housego.presenter;

import com.dumu.housego.model.ApplyShouChuModel;
import com.dumu.housego.model.IApplyShouChuModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IApplyShouChuView;

public class ApplyShouChuPresenter implements IApplyShouChuPresenter{
	private IApplyShouChuModel model;
	private IApplyShouChuView view;
	
	public ApplyShouChuPresenter(IApplyShouChuView view) {
		super();
		this.view = view;
		model=new ApplyShouChuModel();
	}

	@Override
	public void ApplyShouchu(String id, String table, String username) {
		model.ApplyShouchu(id, table, username, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
			String info=(String) success;
				view.applyShouchu(info);
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
	}

}
