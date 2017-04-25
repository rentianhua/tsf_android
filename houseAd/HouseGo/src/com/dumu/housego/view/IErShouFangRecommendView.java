package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.NewHouseRecommendData;

public interface IErShouFangRecommendView {
	void showData(List<ErShouFangRecommendData> ershoufangrecommends);
	void showDatafail(String info);
}
