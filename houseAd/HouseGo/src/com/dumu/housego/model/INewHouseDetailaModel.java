package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface INewHouseDetailaModel extends IModel {
	void FindNewHouseDetail(String catid, String id, AsycnCallBack back);
	void GetYHQinfo(String new_id,String new_catid,AsycnCallBack back);
}
