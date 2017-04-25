package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.YHQ;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IYhqListModel;
import com.dumu.housego.model.YHQListModel;
import com.dumu.housego.view.IYhqListView;

public class YHQListPresenter implements IYhqListPresenter{
	private IYhqListModel model;
	private IYhqListView view;
	
	
	public YHQListPresenter(IYhqListView view) {
		super();
		this.view = view;
		model=new YHQListModel();
	}

	@Override
	public void yhqList(String userid) {
		model.yhqList(userid,new  AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<YHQ>yhqs=(List<YHQ>) success;
				view.yhqListsuccess(yhqs);
				
			}
			
			@Override
			public void onError(Object error) {
				view.yhqfail(error.toString());
				
			}
		});
		
	}

	@Override
	public void yhqDelete(String userid, String id, String username, String yzm) {
		model.yhqDelete(userid, id, username, yzm, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.yhqDelete(info);
				
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
