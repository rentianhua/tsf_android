package com.dumu.housego.presenter;

import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.model.ErShouEditModel;
import com.dumu.housego.model.IErShouEditModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.view.IErShouFangEditView;

public class ErShouEditPresenter implements IErShouFangEditPresenter {

    private IErShouFangEditView view;
    private IErShouEditModel model;

    public ErShouEditPresenter(IErShouFangEditView view) {
        super();
        this.view = view;
        model = new ErShouEditModel();
    }

    public void ershouedit(ATerShouSubmit ershou) {
        model.ershouedit(ershou, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String info = (String) success;
                view.ershouedit(info);
            }

            public void onError(Object error) {
                String info = (String) error;
                view.ershouedit(info);
            }
        });
    }

    @Override
    public void Rentingedit(RentingDetail rent) {
        model.Rentingedit(rent, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String info = (String) success;
                view.ershouedit(info);

            }

            public void onError(Object error) {
                String info = (String) error;
                view.ershouedit(info);
            }
        });
    }

    public void ptershouedit(String username, String userid, String modelid, ErShouFangDetails e) {
        model.ptershouedit(username, userid, modelid, e, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String info = (String) success;
                view.ershouedit(info);
            }

            public void onError(Object error) {
                String info = (String) error;
                view.ershouedit(info);
            }
        });
    }

    public void ptrentingedit(RentingDetail r) {
        model.ptrentingedit(r, new AsycnCallBack() {
            public void onSuccess(Object success) {
                String info = (String) success;
                view.ershouedit(info);
            }

            public void onError(Object error) {
                String info = (String) error;
                view.ershouedit(info);
            }
        });

    }
}
