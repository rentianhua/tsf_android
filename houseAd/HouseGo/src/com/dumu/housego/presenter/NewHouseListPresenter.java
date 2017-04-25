package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.model.IFourDataProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.NewHouseListModel;
import com.dumu.housego.view.INewHouseListView;

public class NewHouseListPresenter implements IFourDataProgramePresenter {
	private INewHouseListView view;
	private IFourDataProgramaModel model;

	public NewHouseListPresenter(INewHouseListView view) {
		super();
		this.view = view;
		model = new NewHouseListModel();
	}

	@Override
	public void LoadProgrameData(FourDataPrograma fourdata) {
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<NewHouseList> newhouselists = (List<NewHouseList>) success;
				view.showNewHouseList(newhouselists);

			}

			@Override
			public void onError(Object error) {
				view.showlistFail(error.toString());

			}
		});

	}

}
