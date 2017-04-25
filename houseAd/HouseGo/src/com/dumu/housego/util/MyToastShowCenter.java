package com.dumu.housego.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class MyToastShowCenter {

	public static void CenterToast(Context context, String info) {
		Toast toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);

		toast.setGravity(Gravity.CENTER, 0, 0);

		toast.show();
	}
}
