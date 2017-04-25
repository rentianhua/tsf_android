package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface ILoginUserInfoModel extends IModel {
	void login(String userid, AsycnCallBack back);
}
