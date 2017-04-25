package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;

public interface IGuanZhuNewView {
	void showGuanZhuSuccess(List<NewHouseDetail> newhousedetails);

	void showGuanZhuFail(String errorinfo);
}
