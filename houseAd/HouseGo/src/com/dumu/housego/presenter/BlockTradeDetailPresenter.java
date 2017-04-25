package com.dumu.housego.presenter;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.model.BlockTradeDetailModel;
import com.dumu.housego.model.IBlockTradeDetailaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IBlockTradeDetailView;

public class BlockTradeDetailPresenter implements IBlockTradeDetailPresenter {
	private IBlockTradeDetailaModel model;
	private IBlockTradeDetailView view;

	public BlockTradeDetailPresenter(IBlockTradeDetailView view) {
		super();
		this.view = view;
		model = new BlockTradeDetailModel();
	}

	@Override
	public void FindBlockTradedetail(String catid, String id) {
		model.FindBlockTradeDetail(catid, id, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				BlockTradeDetail blocktradedetail = (BlockTradeDetail) success;
				view.showData(blocktradedetail);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

}
