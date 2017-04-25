package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IAgentDetailModel {
	void AgentDetail(String userid,AsycnCallBack back);
	void AgentComment(String userid,AsycnCallBack back);
}
