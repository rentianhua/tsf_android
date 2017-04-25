package com.dumu.housego.presenter;

import com.dumu.housego.entity.AgentDetail;
import com.dumu.housego.model.AgentDetailModel;
import com.dumu.housego.model.IAgentDetailModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAgentDetailView;

public class AgentDetailPresenter implements IAgentDetailPresenter {
	private IAgentDetailModel model;
	private IAgentDetailView view;
	
	public AgentDetailPresenter(IAgentDetailView view) {
		super();
		this.view = view;
		model=new AgentDetailModel();
	}

	@Override
	public void AgentDetail(String userid) {
		model.AgentDetail(userid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				AgentDetail agentdetail= (com.dumu.housego.entity.AgentDetail) success;
				view.AgentDetail(agentdetail);
				
			}
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}


}
