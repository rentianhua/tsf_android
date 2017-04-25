package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RentingRecommendData;

public interface IRentingProgramaView {
	void showData(List<RentingRecommendData> ershoufangrecommends);
	void showlistFail(String info);
}
