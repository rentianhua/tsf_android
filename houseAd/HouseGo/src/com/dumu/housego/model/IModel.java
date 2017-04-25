package com.dumu.housego.model;

import java.util.List;

import com.dumu.housego.entity.RecommendNews;

public interface IModel {
	public interface AsycnCallBack {
		void onSuccess(Object success);

		void onError(Object error);
	}
}
