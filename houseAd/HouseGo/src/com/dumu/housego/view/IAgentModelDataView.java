package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.BlockTradeList;

public interface IAgentModelDataView {
	void showAgentModelData(List<AgentData> agentdatas);
	void showAgentFail(String info);
}
