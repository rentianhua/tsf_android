package com.dumu.housego.presenter;

import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;

public interface IErShouFangEditPresenter {
	void ershouedit(ATerShouSubmit ershou);
	void Rentingedit(RentingDetail rent);
	void ptershouedit( String username,  String userid,  String modelid,  ErShouFangDetails e);
	void ptrentingedit( RentingDetail r);
}
