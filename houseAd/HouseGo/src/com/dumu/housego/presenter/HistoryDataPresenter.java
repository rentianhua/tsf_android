package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.HistroyData;
import com.dumu.housego.model.HistroyDataModel;
import com.dumu.housego.model.IHistroyDataModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IHistroyDateView;

public class HistoryDataPresenter implements IHistroyDataPresenter{
	private IHistroyDataModel model;
	private IHistroyDateView view;
	
	
	public HistoryDataPresenter(IHistroyDateView view) {
		super();
		this.view = view;
		model=new HistroyDataModel();
	}


	@Override
	public void histroydata(String username) {
		model.histroydata(username, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<HistroyData> histroys=(List<HistroyData>) success;
				view.histroydateSuccess(histroys);
				
			}
			
			@Override
			public void onError(Object error) {
				view.histroydateFail(error.toString());
				
			}
		});
		
	}

}
