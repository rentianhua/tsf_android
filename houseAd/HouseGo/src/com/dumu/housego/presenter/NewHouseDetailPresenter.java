package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.YHQinfo;
import com.dumu.housego.model.BlockTradeDetailModel;
import com.dumu.housego.model.IBlockTradeDetailaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.INewHouseDetailaModel;
import com.dumu.housego.model.NewHouseDetailModel;
import com.dumu.housego.view.IBlockTradeDetailView;
import com.dumu.housego.view.INewHouseDetailView;

public class NewHouseDetailPresenter implements INewHouseDetailPresenter {
	private INewHouseDetailaModel model;
	private INewHouseDetailView view;

	public NewHouseDetailPresenter(INewHouseDetailView view) {
		super();
		this.view = view;
		model = new NewHouseDetailModel();
	}

	@Override
	public void FindNewHousedetail(String catid, String id) {
		model.FindNewHouseDetail(catid, id, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				NewHouseDetail e = (NewHouseDetail) success;
				view.showNewHouseDetailData(e);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

	@Override
	public void GetYHQinfo(String new_id, String new_catid) {
		model.GetYHQinfo(new_id, new_catid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<YHQinfo>yhqs=(List<YHQinfo>) success;
				view.YHQgetInfoSuccess(yhqs);
				
			}
			
			@Override
			public void onError(Object error) {
				view.YHQgetInfoFail(error.toString());
				
			}
		});
		
	}
}
