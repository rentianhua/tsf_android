package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.HistroyData;

public interface IHistroyDateView {
	void histroydateSuccess(List<HistroyData>histroys);
	void histroydateFail(String info);
}
