package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.LiuYanList;

public interface ILiuYanDetailsView {
	void liuyanDetailSuccess(List<LiuYanList>liuyans);
	void liuyanDetailFail(String info);
}
