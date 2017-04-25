package com.dumu.housego.model;

import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IFourDataProgramaModel {
	void GetRecommedHouse(FourDataPrograma fourdata, AsycnCallBack back);
}
