package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.NewHouseList;

public interface INewHouseListView {
	void showNewHouseList(List<NewHouseList> newhouselists);
	void showlistFail(String info);
}
