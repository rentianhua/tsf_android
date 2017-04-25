package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.model.ErShouFangProgramaModel;
import com.dumu.housego.model.IFourDataProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.RentingProgramaModel;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.dumu.housego.view.IRentingProgramaView;

public class RentingProgramaPresenter implements IFourDataProgramePresenter {
	private IFourDataProgramaModel model;
	private IRentingProgramaView view;

	public RentingProgramaPresenter(IRentingProgramaView view) {
		super();
		this.view = view;
		model = new RentingProgramaModel();
	}

	@Override
	public void LoadProgrameData(FourDataPrograma fourdata) {
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<RentingRecommendData> ershoufangrecommends = (List<RentingRecommendData>) success;

				view.showData(ershoufangrecommends);

			}

			@Override
			public void onError(Object error) {
				view.showlistFail(error.toString());
			}
		});

	}

}
