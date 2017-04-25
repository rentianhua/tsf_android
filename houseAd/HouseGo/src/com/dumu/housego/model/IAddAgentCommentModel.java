package com.dumu.housego.model;

import com.dumu.housego.model.IModel.AsycnCallBack;

public interface IAddAgentCommentModel {
	void AgentComment(String id,String user_id,String author,String agent,String content,AsycnCallBack back);
}
