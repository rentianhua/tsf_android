package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IATershouListDeleteModel {
	 void DeleteershouList(String username,String userid,String table,String id,AsycnCallBack back);
}
