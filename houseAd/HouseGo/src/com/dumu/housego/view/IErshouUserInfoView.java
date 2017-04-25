package com.dumu.housego.view;

import com.dumu.housego.entity.UserInfo;

/**
 * Created by yanglijun 2016-6-28����9:22:47
 */
public interface IErshouUserInfoView {
	void loginUserInfoFail(String errorMessage);

	void loginUserInfoSuccess(UserInfo u);
}
