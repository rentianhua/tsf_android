package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.QiuzuANDQiuGou;

public interface IBuyHouseListView {
	void buyhouseSuccess(List<QiuzuANDQiuGou> lists);

	void buyhouseFail(String info);
}
