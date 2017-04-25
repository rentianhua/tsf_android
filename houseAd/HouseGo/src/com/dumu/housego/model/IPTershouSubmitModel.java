package com.dumu.housego.model;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IPTershouSubmitModel {
	void PTershouSubmit(String username,String userid,String modelid,ErShouFangDetails e,AsycnCallBack back);
}
