package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.LiuYanList;

public interface ILiuYanView {
	void liuyanlist(List<LiuYanList> liuyans);
	void liuyanerror(String info);
}
