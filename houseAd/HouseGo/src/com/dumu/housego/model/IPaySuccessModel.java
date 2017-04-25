package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IPaySuccessModel {
	void PayInfo(String resultStatus,String jine,String order_no,String trade_no,AsycnCallBack back);
}
