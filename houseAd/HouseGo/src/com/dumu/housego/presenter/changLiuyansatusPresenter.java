package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IchangeLiuyanstatusModel;
import com.dumu.housego.model.changeLiuyanstatusModel;
import com.dumu.housego.view.IchangeLiuyanstatusView;

public class changLiuyansatusPresenter implements IchangeLiuyanstatusPresenter{
private IchangeLiuyanstatusModel model;
private IchangeLiuyanstatusView view;


	public changLiuyansatusPresenter(IchangeLiuyanstatusView view) {
	super();
	this.view = view;
	model=new changeLiuyanstatusModel();
}


	@Override
	public void changeStatus(String id, String userid) {
		model.changeStatus(id, userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.changestatus(info);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
