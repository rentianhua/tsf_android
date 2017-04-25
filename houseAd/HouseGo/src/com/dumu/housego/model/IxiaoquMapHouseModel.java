package com.dumu.housego.model;

public interface IxiaoquMapHouseModel extends IModel{
	void xiaoqu(String area,String fromtable,AsycnCallBack back);
	void Allxiaoqu(String xiaoqu,String fromtable,AsycnCallBack back);
}
