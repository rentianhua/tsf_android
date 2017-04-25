package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.BlockTradeProgramaModel;
import com.dumu.housego.model.IFourDataProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRecommendHouseModel;
import com.dumu.housego.view.IBlockTradeProgramaView;
import com.dumu.housego.view.IErShouFangRecommendView;

public class BlockTradeProgramaPresenter implements IFourDataProgramePresenter {
	private IFourDataProgramaModel model;
	private IBlockTradeProgramaView view;

	public BlockTradeProgramaPresenter(IBlockTradeProgramaView view) {
		super();
		this.view = view;
		model = new BlockTradeProgramaModel();
	}

	@Override
	public void LoadProgrameData(FourDataPrograma fourdata) {
		model.GetRecommedHouse(fourdata, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<BlockTradeList> blocktrades = (List<BlockTradeList>) success;
				view.showData(blocktrades);

			}

			@Override
			public void onError(Object error) {
				view.showDataFail(error.toString());

			}
		});

	}

}
