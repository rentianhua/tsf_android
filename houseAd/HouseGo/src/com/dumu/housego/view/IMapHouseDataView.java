package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.AreaHouse;
import com.dumu.housego.entity.XiaoquMapHouse;

public interface IMapHouseDataView {
	void ShowMapHouse(List<AreaHouse> areahouses);
	void ShowAreaMapHouse(List<AreaHouse> streetes);
}
