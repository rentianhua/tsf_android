package com.dumu.housego.presenter;

import com.dumu.housego.model.AddAgentCommentModel;
import com.dumu.housego.model.IAddAgentCommentModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAddAgentCommentView;

public class AddAgentCommentPresenter implements IAddAgentCommentPresenter{
	private IAddAgentCommentView view;
	private IAddAgentCommentModel model;
	
	
	public AddAgentCommentPresenter(IAddAgentCommentView view) {
		super();
		this.view = view;
		model=new AddAgentCommentModel();
	}


	@Override
	public void AgentComment(String id, String user_id, String author, String agent, String content) {
		model.AgentComment(id, user_id, author, agent, content, new AsycnCallBack() {
			
			@Override
			public void onSuccess(Object success) {
				String info=(String) success;
				view.AddAgentComment(info);
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
			}
		});
		
	}

}
