package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.RentingDetail;

public interface IRentingWeiTuoView {
	void weituoSuccess(List<RentingDetail>rentings);
	void weituofail(String info);
}
