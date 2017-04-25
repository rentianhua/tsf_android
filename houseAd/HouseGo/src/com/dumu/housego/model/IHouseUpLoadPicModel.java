package com.dumu.housego.model;

import java.io.File;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IHouseUpLoadPicModel {
	void uploadpic(String userid,String catid,File imagepath,String module,AsycnCallBack back);
}
