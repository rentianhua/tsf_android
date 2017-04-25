package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface IFindPasswordCodeModel extends IModel {
	void Findcode(String phonenum, AsycnCallBack back);
}
