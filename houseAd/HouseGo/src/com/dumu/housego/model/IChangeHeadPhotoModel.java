package com.dumu.housego.model;

import android.graphics.Bitmap;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface IChangeHeadPhotoModel extends IModel {
	void changeHead(String userid, String imagePath, AsycnCallBack back);
}
