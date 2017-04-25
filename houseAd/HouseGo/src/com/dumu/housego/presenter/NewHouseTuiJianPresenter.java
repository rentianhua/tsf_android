package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.INewTuiJianModel;
import com.dumu.housego.model.NewTuiJianModel;
import com.dumu.housego.view.INewTuiJianView;

public class NewHouseTuiJianPresenter implements INewTuiJianPresenter{

	private INewTuiJianView view;
	private INewTuiJianModel model;
	
	public NewHouseTuiJianPresenter(INewTuiJianView view) {
		super();
		this.view = view;
		model=new NewTuiJianModel();
	}

	@Override
	public void Tuijian(String posid) {
		model.Tuijian(posid,new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
			List<NewHouseHotRecommend>tuijians=(List<NewHouseHotRecommend>) success;
			view.Tuijian(tuijians);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
