package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.RentingRecommendData;

public interface IChengJiaoRentingView {
	void ChengjiaorentingSuccess(List<RentingRecommendData> ershoudetails);

	void chengjiaorentingFail(String info);
}
