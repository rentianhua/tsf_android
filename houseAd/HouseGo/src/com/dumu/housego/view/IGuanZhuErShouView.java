package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;

public interface IGuanZhuErShouView {
	void showGuanZhuSuccess(List<ErShouFangDetails> ershoufangdetails);

	void showGuanZhuFail(String errorinfo);
}
