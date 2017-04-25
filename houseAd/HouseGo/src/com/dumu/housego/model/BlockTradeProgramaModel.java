package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseRecommendData;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.BlockTradeListJSONParse;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.ErShouFangReconmendJSONParse;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.NewHouseReconmendJSONParse;
import com.dumu.housego.util.UrlFactory;

public class BlockTradeProgramaModel implements IFourDataProgramaModel {

    public BlockTradeProgramaModel() {
        super();
    }

    public void GetRecommedHouse(final FourDataPrograma fourdata, final AsycnCallBack back) {
        String url = UrlFactory.PostFourDataProgramaUrl();
        CommonRequest request = new CommonRequest(Request.Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        List<BlockTradeList> blocktrades;
                        blocktrades = BlockTradeListJSONParse.parseSearch(response);
                        back.onSuccess(blocktrades);
                    } else {
                        if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                            back.onError("无对应房源数据！");
                        else
                            back.onError("已无更多房源数据");
                    }
                } catch (JSONException e) {
                    if (fourdata.getPage() != null && fourdata.getPage().equals("1"))
                        back.onError("无对应房源数据！");
                    else
                        back.onError("已无更多房源数据");
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("catid", fourdata.getCatid());
                params.put("page", fourdata.getPage());
                params.put("ct", fourdata.getCt());
                params.put("ar", fourdata.getAr());

                params.put("zj", fourdata.getZj());
                params.put("mj", fourdata.getMj());
                params.put("sx", fourdata.getSx());

                params.put("lx", fourdata.getLx());
                params.put("fs", fourdata.getFs());
                params.put("kwds", fourdata.getKwds());
                return params;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}
