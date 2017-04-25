package com.dumu.housego.view;

import java.util.List;

import com.dumu.housego.entity.AgentCommentList;

public interface IAgentCommentView {
	
	void AgentCommentSuccess(List<AgentCommentList>comments);
	void AgentCommentFail(String info);
	
}
