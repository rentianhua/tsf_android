package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.YHQ;

public interface IYhqListView {
	void yhqListsuccess(List<YHQ>yhqs);
	void yhqfail(String info);
	void yhqDelete(String info);
}
