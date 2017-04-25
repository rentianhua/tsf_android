package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface IFindPasswordModel extends IModel {
	void FindPassword(String phonenum, String smscode, String password, String password2, AsycnCallBack back);
}
