package com.dumu.housego.presenter;

import java.io.File;

import com.dumu.housego.model.HouseUpLoadPicModel;
import com.dumu.housego.model.IHouseUpLoadPicModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IHouseUploadPicView;

public class HouseUpLoadPicPresenter implements IHouseUpLoadPicPresenter{

	private IHouseUpLoadPicModel model;
	private IHouseUploadPicView view;
	
	
	public HouseUpLoadPicPresenter(IHouseUploadPicView view) {
		super();
		this.view = view;
		model=new HouseUpLoadPicModel();
	}


	@Override
	public void uploadpic(String userid, String catid, File imagepath, String module) {
		model.uploadpic(userid, catid, imagepath, module, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
