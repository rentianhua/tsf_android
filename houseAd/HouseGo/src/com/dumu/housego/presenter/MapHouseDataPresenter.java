package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.XiaoquMapHouse;
import com.dumu.housego.model.IMapHouseDataModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.MapHouseDataModel;
import com.dumu.housego.view.IMapHouseDataView;

public class MapHouseDataPresenter implements IMapHouseDataPresenter {
	private IMapHouseDataView view;
	private IMapHouseDataModel model;

	public MapHouseDataPresenter(IMapHouseDataView view) {
		super();
		this.view = view;
		model = new MapHouseDataModel();
	}

	@Override
	public void LoadMapHouse(String fromtable) {
		model.LoadMapHouseData(fromtable, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<AreaHouse> areahouses = (List<AreaHouse>) success;
				view.ShowMapHouse(areahouses);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

	@Override
	public void LoadAreaMapHouse(String city, String fromtable) {
		model.LoadAreaMapHouseData(city, fromtable, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<AreaHouse> streetes=(List<AreaHouse>) success;
				view.ShowAreaMapHouse(streetes);
			}
			
			@Override
			public void onError(Object error) {
				
				
			}
		});
		
	}



}
