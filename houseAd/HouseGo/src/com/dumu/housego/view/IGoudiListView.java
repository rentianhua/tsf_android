package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.GouDiInfo;

public interface IGoudiListView {
	
	void GouDilistSuccess(List<GouDiInfo>goudis);
	void GouDiFail(String info);
	void GouDiDelete(String info);
	
}
