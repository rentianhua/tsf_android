package com.dumu.housego.presenter;

import java.util.List;

import com.dumu.housego.entity.SearchXiaoQu;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.ISearchXiaoQuModel;
import com.dumu.housego.model.SearchXiaoQuModel;
import com.dumu.housego.view.ISearchXiaoQuView;

public class SearchXiaoQuPresenter implements ISearchXiaoQuPresenter {
    private ISearchXiaoQuModel model;
    private ISearchXiaoQuView view;

    public SearchXiaoQuPresenter(ISearchXiaoQuView view) {
        super();
        model = new SearchXiaoQuModel();
        this.view = view;
    }

    public void SearchXiaoQu(String area, String key) {
        model.SearchXiaoqu(area, key, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<SearchXiaoQu> xiaoqus = (List<com.dumu.housego.entity.SearchXiaoQu>) success;
                view.SearchxiaoquSuccess(xiaoqus);
            }

            public void onError(Object error) {
                view.SearchxiaoquFaid(error.toString());
            }
        });
    }
}
