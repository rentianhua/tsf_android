package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IYhqListModel {
	void yhqList(String userid,AsycnCallBack back); 
	void yhqDelete(String userid,String id,String username,String yzm,AsycnCallBack back); 
}
