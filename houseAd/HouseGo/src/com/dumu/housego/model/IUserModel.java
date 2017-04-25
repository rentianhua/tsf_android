package com.dumu.housego.model;

import com.dumu.housego.entity.User;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IUserModel {

	void regist(User user, AsycnCallBack back);
}
