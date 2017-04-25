package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.QiuzuANDQiuGou;

public interface IATQiuGouView {
	void qiugouSuccess(List<QiuzuANDQiuGou>qiugous);
	void qiugouFail(String info);
}
