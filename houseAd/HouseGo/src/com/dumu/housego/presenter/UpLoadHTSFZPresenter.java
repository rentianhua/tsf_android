package com.dumu.housego.presenter;

import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IUpLoadHTSFZModel;
import com.dumu.housego.model.UpLoadHTSFZModel;
import com.dumu.housego.view.IUpLoadHTSFZView;

public class UpLoadHTSFZPresenter implements IUpLoadHTSFZPresenter {
	private IUpLoadHTSFZView view;
	private IUpLoadHTSFZModel model;
	
	
	
	public UpLoadHTSFZPresenter(IUpLoadHTSFZView view) {
		super();
		this.view = view;
		model=new UpLoadHTSFZModel();
	}



	@Override
	public void HTSFZ(UpLoadHtsfz htsfz) {
		model.HTSFZ(htsfz,new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.HTSFZ(info);
				
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
