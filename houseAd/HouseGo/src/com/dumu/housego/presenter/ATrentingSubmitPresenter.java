package com.dumu.housego.presenter;

import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.ATrentingSubmitModel;
import com.dumu.housego.model.IATrentingSubmitModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IATrentingSubmitView;

public class ATrentingSubmitPresenter implements IATrentingSubmitPresenter {
    private IATrentingSubmitView view;
    private IATrentingSubmitModel model;

    public ATrentingSubmitPresenter(IATrentingSubmitView view) {
        super();
        this.view = view;
        model = new ATrentingSubmitModel();
    }

    @Override
    public void ATrentingSubmit(RentingDetail rentings) {
        model.ATrentingSubmit(rentings, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String info = (String) success;
                view.ATrentingSubmit(info);
            }

            public void onError(Object error) {
                String info = (String) error;
                view.ATrentingSubmit(info);
            }
        });
    }
}
