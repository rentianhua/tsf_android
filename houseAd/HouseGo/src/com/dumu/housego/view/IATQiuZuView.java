package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuzuANDQiuGou;

public interface IATQiuZuView {
	void qiuzuSuccess(List<QiuzuANDQiuGou>qiuzus);
	void qiuzuFail(String info);
}
