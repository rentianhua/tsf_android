package com.dumu.housego.app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.MyToastShowCenter;
//import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
//import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import util.ImageLoadProxy;

public class HouseGoApp extends Application implements Thread.UncaughtExceptionHandler {
    private static HouseGoApp context;
    private static RequestQueue Queue;
    private static HouseGoApp housegoapp;
//	private   String tag;

    //private User user;
    private UserInfo userinfo;

    public static HouseGoApp getContext() {
        return context;
    }

    public static RequestQueue getQueue() {
        return Queue;
    }

    public HouseGoApp() {
        super();
    }

    public static HouseGoApp getHousegoapp() {
        return housegoapp;
    }

    public void onCreate() {
        super.onCreate();
        housegoapp = this;
        context = this;
        Queue = Volley.newRequestQueue(context);
        x.Ext.init(getHousegoapp());
        x.Ext.setDebug(true);

        ImageLoadProxy.initImageLoader(this);
        SDKInitializer.initialize(getApplicationContext());

        // 创建默认的ImagerLoader配置参数
//        File cacheDir = StorageUtils.getCacheDirectory(context);
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                // .memoryCacheExtraOptions(480, 800)
//                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
//                .discCache(new UnlimitedDiscCache(cacheDir)).discCacheSize(50 * 1024 * 1024)
//                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
//                .discCacheFileCount(100)
//                // 缓存的文件数量
//                // 自定义缓存路径
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)).build();
//        ImageLoader.getInstance().init(config);

        //
        this.userinfo = HouseGoApp.getLoginInfo(this.getApplicationContext());

        //
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread arg0, final Throwable arg1) {
        //在此处理异常， arg1即为捕获到的异常
        Log.i("HouseException", "uncaughtException:" + arg1);
        new Thread() {
            public void run() {
                Looper.prepare();
                MyToastShowCenter.CenterToast(getContext(), "很抱歉，app出现了异常，如有新版本，请尝试新版本");
                MyToastShowCenter.CenterToast(getContext(), "uncaughtException:" + arg1);
                Looper.loop();
            }
        }.start();
    }

//	public void SaveCurrentUser(User user) {
//		this.user = user;
//	}
//
//	public User getCurrentUser() {
//		return this.user;
//	}

    public void SaveCurrentUserInfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public UserInfo getCurrentUserInfo() {
        return this.userinfo;
    }

    public static void saveLoginInfo(Context context, UserInfo userinfo) {
        if (userinfo == null)
            return;

        SharedPreferences sharedPre = context.getSharedPreferences("config", context.MODE_PRIVATE);
        Editor editor = sharedPre.edit();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(userinfo);
            String string64 = new String(Base64.encode(baos.toByteArray(), 0));
            editor.putString("token", string64).commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserInfo getLoginInfo(Context context) {
        UserInfo userinfo = null;
        try {
            String base64 = context.getSharedPreferences("config", context.MODE_PRIVATE).getString("token", "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            userinfo = (UserInfo) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    public static UserInfo clearData(UserInfo userinfo) {
        userinfo = HouseGoApp.getLoginInfo(getContext());
        SharedPreferences sharedPre = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sharedPre.edit().clear().commit();
        return userinfo;
    }

    public static void clearData() {
        SharedPreferences sharedPre = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sharedPre.edit().clear().commit();
    }
}
