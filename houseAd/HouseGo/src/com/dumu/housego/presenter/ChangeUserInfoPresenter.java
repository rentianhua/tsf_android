package com.dumu.housego.presenter;

import com.dumu.housego.model.ChangeUserInfoModel;
import com.dumu.housego.model.IChangeUserInfoModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IChangeUserInfoView;

public class ChangeUserInfoPresenter implements IChangeUserInfoPresenter {
	private IChangeUserInfoModel model;
	private IChangeUserInfoView view;

	public ChangeUserInfoPresenter(IChangeUserInfoView view) {
		super();
		this.view = view;
		model = new ChangeUserInfoModel();
	}

	@Override
	public void ChangeRealName(String userid, String realname, String modelid) {
		model.ChangeRealName(userid, realname, modelid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);
			}
		});

	}

	@Override
	public void ChangeSex(String userid, String sex) {
		model.ChangeSex(userid, sex, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);
			}
		});

	}

	@Override
	public void ChangeGeRenJieshao(String userid, String about) {
		model.ChangeGeRenJieshao(userid, about, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);
			}
		});

	}

	@Override
	public void ChangeSQfenjihao(String tel, String userid) {
		model.ChangeSQfenjihao(tel, userid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);
			}
		});

	}

	@Override
	public void ChangeJBfenjihao(String tel, String userid) {
		model.ChangeJBfenjihao(tel, userid, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);
			}
		});

	}

	@Override
	public void ChangePassword(String username, String userid, String oldpwd, String pwd1, String pwd2) {
		model.ChangePassword(username, userid, oldpwd, pwd1, pwd2, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeShenFenZheng(String userid, String cardnumber) {
		model.ChangeShenFenZheng(userid, cardnumber, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeShenFenZhengPic(String userid, String sfzpic) {
		model.ChangeShenFenZhengPic(userid, sfzpic, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);
			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeWorkTime(String userid, String worktime) {
		model.ChangeWorkTime(userid, worktime, new AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeMainArea(String userid, String mainarea) {
		model.ChangeMainArea(userid, mainarea, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeLeixing(String userid, String leixing) {
		model.ChangeLeixing(userid, leixing, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

	@Override
	public void ChangeConame(String userid, String coname) {
		model.ChangeConame(userid, coname, new AsycnCallBack() {

			@Override
			public void onSuccess(Object success) {
				String info = (String) success;
				view.changeInfo(info);

			}

			@Override
			public void onError(Object error) {
				String info = (String) error;
				view.changeInfo(info);

			}
		});

	}

}
