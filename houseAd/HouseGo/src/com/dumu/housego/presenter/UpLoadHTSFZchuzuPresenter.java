package com.dumu.housego.presenter;

import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IUpLoadHTSFZModel;
import com.dumu.housego.model.IUpLoadHTSFZchuzuModel;
import com.dumu.housego.model.UpLoadHTSFZModel;
import com.dumu.housego.model.UpLoadHTSFZchuzuModel;
import com.dumu.housego.view.IUpLoadHTSFZView;
import com.dumu.housego.view.IUpLoadHTSFZchuzuView;

public class UpLoadHTSFZchuzuPresenter implements IUpLoadHTSFZchuzuPresenter {
	private IUpLoadHTSFZchuzuView view;
	private IUpLoadHTSFZchuzuModel model;
	
	
	
	public UpLoadHTSFZchuzuPresenter(IUpLoadHTSFZchuzuView view) {
		super();
		this.view = view;
		model=new UpLoadHTSFZchuzuModel();
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
