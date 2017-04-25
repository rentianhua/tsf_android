package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.model.AgentDetailModel;
import com.dumu.housego.model.IAgentDetailModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAgentCommentView;

public class AgentCommentPresenter implements IAgentCommentPresenter{
	private IAgentCommentView view;
	private IAgentDetailModel model;
	
	public AgentCommentPresenter(IAgentCommentView view) {
		super();
		this.view = view;
		model=new AgentDetailModel();
	}

	@Override
	public void AgentComment(String userid) {
		model.AgentComment(userid, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				List<AgentCommentList>comments=(List<AgentCommentList>) success;
				view.AgentCommentSuccess(comments);;
				
			}
			
			@Override
			public void onError(Object error) {
				view.AgentCommentFail(error.toString());
				
			}
		});
		
	}

}
