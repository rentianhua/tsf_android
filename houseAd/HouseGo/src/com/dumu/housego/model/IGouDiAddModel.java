package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IGouDiAddModel {
	void AddGoudi(String house_id,String userid,String title,String jine,AsycnCallBack back);
}
