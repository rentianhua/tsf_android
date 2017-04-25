package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.ISubmitErShouListModel;
import com.dumu.housego.model.SubmitErShouListModel;
import com.dumu.housego.view.ISubmitRentingListView;

public class SubmitRentinglistpresenter implements ISubmitRentingListpresenter {

	private ISubmitErShouListModel model;;
	private ISubmitRentingListView view;

	public SubmitRentinglistpresenter(ISubmitRentingListView view) {
		super();
		this.view = view;
		model = new SubmitErShouListModel();
	}

	@Override
	public void SubmitRentingList(String username, String userid, String table) {
		model.submitrentinglist(username, userid, table, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<RentingDetail> rentingdetails = (List<RentingDetail>) success;
				view.SubmitListSuccess(rentingdetails);

			}

			@Override
			public void onError(Object error) {
				view.SubmitListError(error.toString());

			}
		});
	}

}
