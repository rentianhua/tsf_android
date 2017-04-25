package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.ATQiuGouModel;
import com.dumu.housego.model.ATQiuZuModel;
import com.dumu.housego.model.IATQiuGouModel;
import com.dumu.housego.model.IATQiuZuModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IATQiuGouView;
import com.dumu.housego.view.IATQiuZuView;

public class ATQiuGouPresenter implements IATQiuGouPresenter{

	private IATQiuGouModel model;
	private IATQiuGouView view;
	
	public ATQiuGouPresenter(IATQiuGouView view) {
		super();
		this.view = view;
		model=new ATQiuGouModel();
	}

	@Override
	public void qiugou(String jjrid, String table) {
		model.qiugou(jjrid, table, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<QiuzuANDQiuGou	>qiugous=(List<QiuzuANDQiuGou>) success;
				view.qiugouSuccess(qiugous);
				
			}
			
			@Override
			public void onError(Object error) {
			view.qiugouFail(error.toString());
				
			}
		});
		
	}

	

}
