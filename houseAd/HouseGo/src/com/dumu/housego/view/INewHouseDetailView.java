package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.YHQinfo;

public interface INewHouseDetailView {
	void showNewHouseDetailData(NewHouseDetail newhousedetail);
	void YHQgetInfoSuccess(List<YHQinfo>yhqs);
	void YHQgetInfoFail(String info);
}
