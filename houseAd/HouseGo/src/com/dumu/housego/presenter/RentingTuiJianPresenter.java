package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.RentingTuijian;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IRentingTuiJianPresenter;
import com.dumu.housego.model.IRentingTuijianModel;
import com.dumu.housego.model.RentingTuijianModel;
import com.dumu.housego.view.IRentingTuijianView;

public class RentingTuiJianPresenter implements IRentingTuiJianPresenter {
    private IRentingTuijianModel model;
    private IRentingTuijianView view;

    public RentingTuiJianPresenter(IRentingTuijianView view) {
        super();
        this.view = view;
        model = new RentingTuijianModel();
    }

    public void tuijian() {
        model.tuijian(new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<RentingTuijian> tuijians = (List<RentingTuijian>) success;
                view.tuijian(tuijians);
            }

            public void onError(Object error) {
            }
        });
    }
}
