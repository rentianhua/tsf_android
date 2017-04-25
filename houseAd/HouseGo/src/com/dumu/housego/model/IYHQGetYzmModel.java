package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IYHQGetYzmModel {
	void YzmInfo(String mob,AsycnCallBack back);
	void AddYHQ(String house_id,String coupon_id,String userid,String buyname,String buytel,String username,String yzm,AsycnCallBack back);
}
