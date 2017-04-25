package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.SubmitErshouList;

public interface ISubmitRentingListView {
	void SubmitListSuccess(List<RentingDetail> rentingdetails);

	void SubmitListError(String info);
}
