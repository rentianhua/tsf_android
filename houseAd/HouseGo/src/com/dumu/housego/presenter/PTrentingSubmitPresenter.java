package com.dumu.housego.presenter;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IPTrentingSubmitModel;
import com.dumu.housego.model.PTrentingSubmitModel;
import com.dumu.housego.view.IPTrentingSubmitView;

public class PTrentingSubmitPresenter implements IPTrentingSubmitPresenter {

    private IPTrentingSubmitModel model;
    private IPTrentingSubmitView view;

    public PTrentingSubmitPresenter(IPTrentingSubmitView view) {
        super();
        this.view = view;
        model = new PTrentingSubmitModel();
    }

    @Override
    public void PTrentingSubmit(RentingDetail r) {
        model.PTrentingSubmit(r, new AsycnCallBack() {

            @Override
            public void onSuccess(Object success) {
                String info = (String) success;
                view.PTrentingSubmit(info);
            }

            @Override
            public void onError(Object error) {
                String info = (String) error;
                view.PTrentingSubmit(info);
            }
        });

    }

}
