package com.dumu.housego.model;

/**
 * Created by yanglijun 2016-6-28����9:27:29
 */
public interface IBlockTradeDetailaModel extends IModel {
	void FindBlockTradeDetail(String catid, String id, AsycnCallBack back);
}
