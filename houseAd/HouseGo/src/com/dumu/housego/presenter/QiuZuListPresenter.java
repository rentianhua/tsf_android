package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.QiuZuBuyHouseList;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.IQiuZuListModel;
import com.dumu.housego.model.QiuZuListModel;
import com.dumu.housego.view.IQiuZuListView;

public class QiuZuListPresenter implements IQiuZuListPresenter {

    private IQiuZuListModel model;
    private IQiuZuListView view;

    public QiuZuListPresenter(IQiuZuListView view) {
        super();
        this.view = view;
        model = new QiuZuListModel();
    }

    @Override
    public void qiuzulist(String username, String table) {
        model.QiuZuList(username, table, new AsycnCallBack() {
            @Override
            public void onSuccess(Object success) {
                List<QiuzuANDQiuGou> qiuzulists = (List<QiuzuANDQiuGou>) success;
                view.QiuZuListSuccess(qiuzulists);
            }

            @Override
            public void onError(Object error) {
                view.QiuZuListFail(error.toString());
            }
        });

    }

}
