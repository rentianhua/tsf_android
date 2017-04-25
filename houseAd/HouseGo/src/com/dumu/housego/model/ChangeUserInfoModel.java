package com.dumu.housego.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.UrlFactory;

import android.util.Log;

public class ChangeUserInfoModel implements IChangeUserInfoModel {
    private UserInfo userinfo;

    public ChangeUserInfoModel() {
        super();
    }

    public void ChangeRealName(final String userid, final String realname, final String modelid,
                               final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setRealname(realname);

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("realname", realname);
                map.put("modelid", modelid);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeSex(final String userid, final String sex, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setSex(sex);
                            ;

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);
                            HouseGoApp.saveLoginInfo(app.getContext(), userinfo);

                            back.onSuccess(info);
                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("sex", sex);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeGeRenJieshao(final String userid, final String about, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setAbout(about);

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);
                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("about", about);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeSQfenjihao(final String tel, final String userid, final AsycnCallBack back) {
        String url = UrlFactory.GetShenQing400Url(tel, userid);
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        String info = obj.getString("info");

                        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                        userinfo.setZhuanjie("1");
                        ;

                        HouseGoApp app = HouseGoApp.getContext();
                        app.SaveCurrentUserInfo(userinfo);

                        back.onSuccess(info);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        });
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeJBfenjihao(final String tel, final String userid, final AsycnCallBack back) {
        String url = UrlFactory.GetJieBang400Url(tel, userid);
        CommonRequest request = new CommonRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        String info = obj.getString("info");

                        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                        userinfo.setZhuanjie("0");
                        ;

                        HouseGoApp app = HouseGoApp.getContext();
                        app.SaveCurrentUserInfo(userinfo);

                        back.onSuccess(info);
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        });
        HouseGoApp.getQueue().add(request);

    }

    public void ChangePassword(final String username, final String userid, final String oldpwd, final String pwd1,
                               final String pwd2, final AsycnCallBack back) {
        String url = UrlFactory.PostChangePassword();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "156") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setPassword(pwd1);

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("username", username);
                map.put("oldpwd", oldpwd);
                map.put("pwd1", pwd1);
                map.put("pwd2", pwd2);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    @Override
    public void ChangeShenFenZheng(final String userid, final String cardnumber, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setCardnumber(cardnumber);

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("cardnumber", cardnumber);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeShenFenZhengPic(final String userid, final String sfzpic, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setSfzpic(sfzpic);

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("sfzpic", sfzpic);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeWorkTime(final String userid, final String worktime, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setWorktime(worktime);
                            ;

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("worktime", worktime);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeMainArea(final String userid, final String mainarea, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setMainarea(mainarea);
                            ;

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("mainarea", mainarea);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeLeixing(final String userid, final String leixing, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setLeixing(leixing);
                            ;

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("leixing", leixing);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }

    public void ChangeConame(final String userid, final String coname, final AsycnCallBack back) {
        String url = UrlFactory.PostChangeUserInfoUrl();
        CommonRequest request = new CommonRequest(Method.POST, url, new Listener<String>() {
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getString("success") == "54") {
                            String info = obj.getString("info");

                            userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                            userinfo.setConame(coname);
                            ;

                            HouseGoApp app = HouseGoApp.getContext();
                            app.SaveCurrentUserInfo(userinfo);

                            back.onSuccess(info);

                        } else {
                            String error = obj.getString("info");
                            back.onSuccess(error);
                        }
                    } else {
                        back.onError("请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                back.onError(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("coname", coname);
                return map;
            }
        };
        HouseGoApp.getQueue().add(request);
    }
}
