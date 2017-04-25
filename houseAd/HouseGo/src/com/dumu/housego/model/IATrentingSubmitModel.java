package com.dumu.housego.model;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IATrentingSubmitModel {
	void ATrentingSubmit(RentingDetail rentings,AsycnCallBack back);
}
