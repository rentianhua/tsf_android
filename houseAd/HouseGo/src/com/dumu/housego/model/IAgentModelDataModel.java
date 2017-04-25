package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface IAgentModelDataModel extends IModel {
	void FindAgentModelData( String ct, String bq, String kw, AsycnCallBack back);
}
