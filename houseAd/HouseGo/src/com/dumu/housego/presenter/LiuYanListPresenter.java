package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.model.ILiuYanModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LiuYanModel;
import com.dumu.housego.view.ILiuYanView;

public class LiuYanListPresenter implements ILiuYanPresenter{
	private ILiuYanModel model;
	private ILiuYanView view;
	
	public LiuYanListPresenter(ILiuYanView view) {
		super();
		this.view = view;
		model=new LiuYanModel();
	}

	@Override
	public void liuyan(String userid) {
		model.liuyan(userid,new  AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<LiuYanList>liuyans=(List<LiuYanList>) success;
				view.liuyanlist(liuyans);
			}
			@Override
			public void onError(Object error) {
				view.liuyanerror(error.toString());
			}
		});
		
	}

}
