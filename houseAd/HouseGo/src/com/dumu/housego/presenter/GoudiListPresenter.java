package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.GouDiInfo;
import com.dumu.housego.model.GouDiListModel;
import com.dumu.housego.model.IGouDiListModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IGoudiListView;

public class GoudiListPresenter implements IGoudiListPresenter {
	private IGouDiListModel model;
	private IGoudiListView view;
	
	
	
	public GoudiListPresenter(IGoudiListView view) {
		super();
		this.view = view;
		model=new GouDiListModel();
	}

	@Override
	public void GoudiList(String userid) {
		model.GoudiList(userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<GouDiInfo>goudis=(List<GouDiInfo>) success;
				view.GouDilistSuccess(goudis);
				
			}
			
			@Override
			public void onError(Object error) {
				view.GouDiFail(error.toString());
				
			}
		});
		
		
	}

	@Override
	public void GouDiDelete(String id, String userid) {
		model.GouDiDelete(id, userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.GouDiDelete(info);
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
	}

}
