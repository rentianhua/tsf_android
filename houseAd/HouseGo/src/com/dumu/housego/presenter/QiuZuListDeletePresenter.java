package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel;
import com.dumu.housego.model.IQiuZuListModel;
import com.dumu.housego.model.QiuZuListModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IQiuZuListDeleteView;

public class QiuZuListDeletePresenter implements IQiuZuListDeletePresenter {

	private IQiuZuListModel model;
	private IQiuZuListDeleteView view;

	public QiuZuListDeletePresenter(IQiuZuListDeleteView view) {
		super();
		this.view = view;
		model = new QiuZuListModel();
	}

	@Override
	public void DeleteQiuZuList(String id, String userid, String username) {
		model.DeleteQiuZu(id, userid, username, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.qiuzuDelete(info);

			}

			@Override
			public void onError(Object error) {

			}
		});

	}

}
