package com.dumu.housego.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.util.MultiPartStack;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class HouseUpLoadPicModel implements IHouseUpLoadPicModel {
    private UserInfo userinfo;
    private static RequestQueue mSingleQueue;
    private static String TAG = "test";

    public HouseUpLoadPicModel() {
        super();
        mSingleQueue = Volley.newRequestQueue(HouseGoApp.getContext(), new MultiPartStack());
    }

    public void uploadpic(String userid, String catid, File imagepath, String module, AsycnCallBack back) {
        String url = UrlFactory.PostupLoadPic();
        Map<String, File> files = new HashMap<String, File>();
        files.put("pic", imagepath);

        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userid);
        params.put("catid", catid);
        params.put("module", module);

        addPutUploadFileRequest(
                url,
                files, params, mResonseListenerString, mErrorListener, null);
    }

    Listener<JSONObject> mResonseListener = new Listener<JSONObject>() {
        public void onResponse(JSONObject response) {
        }
    };

    Listener<String> mResonseListenerString = new Listener<String>() {
        public void onResponse(String response) {
        }
    };

    ErrorListener mErrorListener = new ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
//            if (error != null) {
//                if (error.networkResponse != null)
//                    Log.e(TAG, " error " + new String(error.networkResponse.data));
//            }
        }
    };

    public static void addPutUploadFileRequest(final String url,
                                               final Map<String, File> files, final Map<String, String> params,
                                               final Listener<String> responseListener, final ErrorListener errorListener,
                                               final Object tag) {
        if (null == url || null == responseListener) {
            return;
        }

        MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
                Request.Method.PUT, url, responseListener, errorListener) {

            public Map<String, File> getFileUploads() {
                return files;
            }

            public Map<String, String> getStringUploads() {
                return params;
            }
        };

        mSingleQueue.add(multiPartRequest);
    }
}
