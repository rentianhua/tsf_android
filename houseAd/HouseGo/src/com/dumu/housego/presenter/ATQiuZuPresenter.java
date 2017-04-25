package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.ATQiuZuModel;
import com.dumu.housego.model.IATQiuZuModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IATQiuZuView;

public class ATQiuZuPresenter implements IATQiuZuPresenter{

	private IATQiuZuModel model;
	private IATQiuZuView view;
	
	public ATQiuZuPresenter(IATQiuZuView view) {
		super();
		this.view = view;
		model=new ATQiuZuModel();
	}

	
	@Override
	public void qiuzu(String jjrid, String table) {
		model.qiuzu(jjrid, table, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<QiuzuANDQiuGou	>qiuzus=(List<QiuzuANDQiuGou>) success;
				view.qiuzuSuccess(qiuzus);
				
			}
			
			@Override
			public void onError(Object error) {
			view.qiuzuFail(error.toString());
				
			}
		});
		
	}

}
