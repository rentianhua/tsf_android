package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.UpLoadPic;

public interface IHouseUploadPicView {
	void uploadpicsuccess(List<UpLoadPic>pics);
	void uploadfail(String info);
	
	
}
