package com.dumu.housego.presenter;

import java.io.File;
import java.io.FileOutputStream;

public interface IHouseUpLoadPicPresenter {
//	void uploadpic(String userid,String catid,String imagepath,String module);
//	void uploadpic(String userid,String catid,File imagepath,String module);

	void uploadpic(String userid, String catid, File out, String module);
}
