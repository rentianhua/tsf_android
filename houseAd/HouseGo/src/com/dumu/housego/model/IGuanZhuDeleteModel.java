package com.dumu.housego.model;

public interface IGuanZhuDeleteModel extends IModel {
	void deleteGuanZhu(String id, String userid, String username, AsycnCallBack back);
}
