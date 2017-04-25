package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.ISubmitErShouListModel;
import com.dumu.housego.model.SubmitErShouListModel;
import com.dumu.housego.view.ISubmitErShouListView;

public class SubmitErShoulistpresenter implements ISubmitErShouListpresenter {

	private ISubmitErShouListModel model;;
	private ISubmitErShouListView view;

	public SubmitErShoulistpresenter(ISubmitErShouListView view) {
		super();
		this.view = view;
		model = new SubmitErShouListModel();
	}

	@Override
	public void SubmitErShouList(String username, String userid, String table) {
		model.submitershoulist(username, userid, table, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<ErShouFangDetails> submitershous = (List<ErShouFangDetails>) success;
				view.SubmitListSuccess(submitershous);

			}

			@Override
			public void onError(Object error) {
				view.SubmitListError(error.toString());

			}
		});

	}

}
