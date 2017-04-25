package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ShouYeBiaoTi;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IShouYeBiaotiModel;
import com.dumu.housego.model.ShouYeBiaoTiModel;
import com.dumu.housego.view.IShouYeBiaoTiView;

public class ShouYeBiaoTiPresenter implements IShouYeBiaoTiPresenter {
private IShouYeBiaotiModel model;
private IShouYeBiaoTiView view;


	public ShouYeBiaoTiPresenter(IShouYeBiaoTiView view) {
	super();
	this.view = view;
	model=new ShouYeBiaoTiModel();
}
	@Override
	public void shouye() {
		model.shouye(new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<ShouYeBiaoTi>shouyes=(List<ShouYeBiaoTi>) success;
				view.shouye(shouyes);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
