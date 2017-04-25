package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.XiaoquMapHouse;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IxiaoquMapHouseModel;
import com.dumu.housego.model.XiaoquMapHouseModel;
import com.dumu.housego.view.IXiaoquMapHouseView;

public class XiaoquMapHousePresenter implements IXiaoquMapHousePresenter {
	private IXiaoquMapHouseView view;
	private IxiaoquMapHouseModel model;
	
	public XiaoquMapHousePresenter(IXiaoquMapHouseView view) {
		super();
		this.view = view;
		model=new XiaoquMapHouseModel();
	}

	@Override
	public void Xiaoqu(String area, String fromtable) {
		
		model.xiaoqu(area, fromtable, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<XiaoquMapHouse>xiaoqus=(List<XiaoquMapHouse>) success;
				view.xiaoquSuccess(xiaoqus);
				
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void Allxiaoqu(String xiaoqu, String fromtable) {
		if(fromtable.equals("ershou")){
			model.Allxiaoqu(xiaoqu, fromtable, new AsycnCallBack() {
				
				@Override
				public void onSuccess(Object success) {
					List<ErShouFangRecommendData>ershous=(List<ErShouFangRecommendData>) success;
					view.AllxiaoquhouseErshou(ershous);;
				}
				
				@Override
				public void onError(Object error) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			model.Allxiaoqu(xiaoqu, fromtable, new AsycnCallBack() {
				
				@Override
				public void onSuccess(Object success) {
					List<RentingRecommendData>rentings=(List<RentingRecommendData>) success;
					view.AllxiaoquhouseRenting(rentings);;
				}
				
				@Override
				public void onError(Object error) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	
		
	}

}
