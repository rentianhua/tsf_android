package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.model.ILiuYanDetailModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LiuYanDetailModel;
import com.dumu.housego.view.ILiuYanDetailsView;

public class LiuYanDetailPresenter implements ILiuYanDetailPresenter {
	private ILiuYanDetailModel model;
	private ILiuYanDetailsView view;
	
	public LiuYanDetailPresenter(ILiuYanDetailsView view) {
		super();
		this.view = view;
		model=new LiuYanDetailModel();
	}

	@Override
	public void liuyandetail(String userid, String towho) {
		model.liuyandetail(userid, towho, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<LiuYanList>liuyans=(List<LiuYanList>) success;
				view.liuyanDetailSuccess(liuyans);
				
			}
			
			@Override
			public void onError(Object error) {
				view.liuyanDetailFail(error.toString());
			}
		});
		
	}

}	
