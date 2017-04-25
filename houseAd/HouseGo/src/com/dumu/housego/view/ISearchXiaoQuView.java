package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.SearchXiaoQu;

public interface ISearchXiaoQuView {
	void SearchxiaoquSuccess(List<SearchXiaoQu> xiaoqus);
	void SearchxiaoquFaid(String info);
}
