package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;

public interface IWeiTuoErShouView {
	void weituoSuccess(List<ErShouFangDetails>ershous);
	void weituofail(String info);
}
