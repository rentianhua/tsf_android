package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.AgentData;
import com.dumu.housego.model.AgentModelDataModel;
import com.dumu.housego.model.IAgentModelDataModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAgentModelDataView;

public class AgentModelDataPresenter implements IAgentModelDataPresenter {

	private IAgentModelDataModel model;
	private IAgentModelDataView view;

	public AgentModelDataPresenter(IAgentModelDataView view) {
		super();
		this.view = view;
		model = new AgentModelDataModel();
	}

	@Override
	public void FindAgentModelData(String ct, String bq, String kw) {
		model.FindAgentModelData( ct,bq,kw,new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<AgentData> agentdatas = (List<AgentData>) success;
				view.showAgentModelData(agentdatas);
			}
			@Override
			public void onError(Object error) {
				view.showAgentFail(error.toString());
			}
		});

	}


}
