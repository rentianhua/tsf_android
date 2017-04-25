package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.QiuzuANDQiuGou;

public interface IQiuZuListView {
	void QiuZuListSuccess(List<QiuzuANDQiuGou> qiuzulists);

	void QiuZuListFail(String info);
}
