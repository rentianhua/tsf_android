package com.dumu.housego.model;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IPTrentingSubmitModel {
	void PTrentingSubmit(RentingDetail r,AsycnCallBack back);
}
