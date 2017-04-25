package com.dumu.housego.presenter;

import com.dumu.housego.model.ChangeHeadPhotoModel;
import com.dumu.housego.model.IChangeHeadPhotoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IChangeHeadPhotoView;

public class ChangeHeadPhotoPresenter implements IChangeHeadPhotoPresenter {
	private IChangeHeadPhotoModel model;
	private IChangeHeadPhotoView view;

	public ChangeHeadPhotoPresenter(IChangeHeadPhotoView view) {
		super();
		this.view = view;
		model = new ChangeHeadPhotoModel();
	}

	@Override
	public void ChangeHead(String userid, String imagePath) {
		model.changeHead(userid, imagePath, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String picurl = (String) success;
				view.changeHeadSuccess(picurl);

			}

			@Override
			public void onError(Object error) {
				view.changeHeadFail(error.toString());

			}
		});

	}

}
