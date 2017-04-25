package com.dumu.housego.presenter;

import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IPhoneCodeModel;
import com.dumu.housego.model.PhoneCodeModel;
import com.dumu.housego.view.IPhoneCodeView;

public class PhoneCodePresenter implements IPhoneCodePresenter {
    private IPhoneCodeModel model;
    private IPhoneCodeView view;

    public PhoneCodePresenter(IPhoneCodeView view) {
        super();
        this.view = view;
        model = new PhoneCodeModel();
    }

    public void LoadMob(String mob, String verify) {
        model.GetPhoneCode(mob, verify, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String infomation = success.toString();
                view.setData(true, infomation);
            }

            public void onError(Object error) {
                String infomation = error.toString();
                view.setData(false, infomation);
            }
        });

    }

}
