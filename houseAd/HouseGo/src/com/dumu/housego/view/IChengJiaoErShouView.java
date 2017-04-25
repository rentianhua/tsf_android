package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;

public interface IChengJiaoErShouView {
	void ChengjiaoErShouSuccess(List<ErShouFangDetails> ershoudetails);

	void chengjiaoErShouFail(String info);
}
