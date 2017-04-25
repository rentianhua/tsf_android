package com.dumu.housego.presenter;

import com.dumu.housego.model.ATershouListDeleteModel;
import com.dumu.housego.model.IATershouListDeleteModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IATershouListDeleteView;

public class ATershouListDeletePresenter implements IATershouListDeletePresenter{
	private IATershouListDeleteModel model;
	private IATershouListDeleteView view;
	
	public ATershouListDeletePresenter(IATershouListDeleteView view) {
		super();
		this.view = view;
		model=new ATershouListDeleteModel();
	}

	@Override
	public void DeleteershouList(String username, String userid, String table, String id) {
		model.DeleteershouList(username, userid, table, id, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.ATershouListDelete(info);
			}
			
			@Override
			public void onError(Object error) {
				
			}
		});
		
	}

}
