package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.BlockTradeList;

public interface IBlockTradeProgramaView {
	void showData(List<BlockTradeList> blocktrades);
	void showDataFail(String info);
}
