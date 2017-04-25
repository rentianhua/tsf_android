package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.Address;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IAddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IAddressView;

public class AddressPresenter implements IAddressPresenter {
	private IAddressModel model;
	private IAddressView view;

	public AddressPresenter(IAddressView view) {
		super();
		this.view = view;
		model = new AddressModel();
	}

	@Override
	public void address(String pid) {
		model.address(pid, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				List<Address> addresses = (List<Address>) success;
				view.address(addresses);
			}

			@Override
			public void onError(Object error) {

			}
		});

	}

}
