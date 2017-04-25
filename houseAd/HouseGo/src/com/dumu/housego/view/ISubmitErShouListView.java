package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;

public interface ISubmitErShouListView {
	void SubmitListSuccess(List<ErShouFangDetails> ershoudetails);

	void SubmitListError(String info);
}
