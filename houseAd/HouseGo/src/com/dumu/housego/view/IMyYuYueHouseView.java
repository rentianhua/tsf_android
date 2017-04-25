package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.YuYueData;

public interface IMyYuYueHouseView {
	void LoadYuYueDatasuccess(List<YuYueData> yuyuedatas);

	void LoadYuYueDataFaid(String info);
}
