package com.dumu.housego.presenter;

import com.dumu.housego.model.HasGouDiModel;
import com.dumu.housego.model.IHasGouDiModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IHasGouDiView;

public class HasGouDiPresenter implements IHasGouDiPresenter{

	private IHasGouDiModel model;
	private IHasGouDiView view;
	
	public HasGouDiPresenter(IHasGouDiView view) {
		super();
		this.view = view;
		model=new HasGouDiModel();
	}

	@Override
	public void hasgoudi(String house_id, String userid) {
		model.Hasgoudi(house_id, userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.hasgoudi(info);
				
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
