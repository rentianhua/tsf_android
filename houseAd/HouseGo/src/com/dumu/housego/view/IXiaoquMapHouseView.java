package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.XiaoquMapHouse;

public interface IXiaoquMapHouseView {
	void xiaoquSuccess(List<XiaoquMapHouse>xiaoqus);
	void AllxiaoquhouseErshou(List<ErShouFangRecommendData>ershous);
	void AllxiaoquhouseRenting(List<RentingRecommendData>rentings);
}
