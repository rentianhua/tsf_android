package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.ErShouFangProgramaModel;
import com.dumu.housego.model.IFourDataProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IErShouFangRecommendView;

public class ErShouFangProgramaPresenter implements IFourDataProgramePresenter {
	private IFourDataProgramaModel model;
	private IErShouFangRecommendView view;

	public ErShouFangProgramaPresenter(IErShouFangRecommendView view) {
		super();
		this.view = view;
		model = new ErShouFangProgramaModel();
	}

	@Override
	public void LoadProgrameData(FourDataPrograma fourdata) {
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangRecommendData> ershoufangrecommends = (List<ErShouFangRecommendData>) success;

				view.showData(ershoufangrecommends);

			}

			@Override
			public void onError(Object error) {
				view.showDatafail(error.toString());
			}
		});

	}

}
