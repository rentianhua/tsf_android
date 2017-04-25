package com.dumu.housego.model;

import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IErShouEditModel {
	void ershouedit(ATerShouSubmit ershou,AsycnCallBack back);
	void Rentingedit(RentingDetail rent,AsycnCallBack back);
	void ptershouedit( String username,  String userid,  String modelid,  ErShouFangDetails e,AsycnCallBack back);
	void ptrentingedit( RentingDetail r,AsycnCallBack back);
}
