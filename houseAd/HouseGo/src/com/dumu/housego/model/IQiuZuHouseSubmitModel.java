package com.dumu.housego.model;

public interface IQiuZuHouseSubmitModel extends IModel {
	void QiuZuSubmit(String username, String province, String city, String area, String zulin, String shi,
			String zujinrange, String chenghu, String title, AsycnCallBack back);
}
