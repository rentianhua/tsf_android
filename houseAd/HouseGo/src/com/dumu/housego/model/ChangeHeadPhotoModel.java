package com.dumu.housego.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.MultiPartStack;
import com.dumu.housego.util.UrlFactory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class ChangeHeadPhotoModel implements IChangeHeadPhotoModel {
    private UserInfo userinfo;
    private static RequestQueue mSingleQueue;
    private static String TAG = "test";

    public ChangeHeadPhotoModel() {
        super();

        mSingleQueue = Volley.newRequestQueue(HouseGoApp.getContext(), new MultiPartStack());
    }

    @Override
    public void changeHead(final String userid, final String imagePath, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeHeadPhotoUrl();

        Log.e("imagePath", "imagePath=imagePath=" + imagePath);
        Map<String, File> files = new HashMap<String, File>();
        files.put("__avatar1", new File(
                imagePath));

        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userid);

//		String uri = "your_url";
        addPutUploadFileRequest(
                url,
                files, params, mResonseListenerString, mErrorListener, null);
    }


    Listener<JSONObject> mResonseListener = new Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            Log.i(TAG, " on response json" + response.toString());
        }
    };

    Listener<String> mResonseListenerString = new Listener<String>() {

        @Override
        public void onResponse(String response) {
            Log.i(TAG, " on response String" + response.toString());
        }
    };

    ErrorListener mErrorListener = new ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            if (error != null) {
                if (error.networkResponse != null)
                    Log.i(TAG, " error " + new String(error.networkResponse.data));
            }
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

            @Override
            public Map<String, File> getFileUploads() {
                return files;
            }

            @Override
            public Map<String, String> getStringUploads() {
                return params;
            }

        };

        Log.i(TAG, " volley put : uploadFile " + url);

        mSingleQueue.add(multiPartRequest);
    }
}
