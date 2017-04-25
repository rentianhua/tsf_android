package com.dumu.housego.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class FontHelper {
	public static final String FONTS_DIR = "fonts/";
	public static final String DEF_FONT = FONTS_DIR + "PingFang_Medium.ttf";

	public static final void injectFont(View rootView) {
		injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(), DEF_FONT));

	}

	private static void injectFont(View rootView, Typeface tf) {
		if (rootView instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) rootView;
			int count = group.getChildCount();
			for (int i = 0; i < count; i++) {
				injectFont(group.getChildAt(i), tf);
			}
		} else if (rootView instanceof TextView) {
			((TextView) rootView).setTypeface(tf);
		} else if (rootView instanceof Button) {
			((Button) rootView).setTypeface(tf);
		} else if (rootView instanceof RadioButton) {
			((RadioButton) rootView).setTypeface(tf);
		} else if (rootView instanceof EditText) {
			((EditText) rootView).setTypeface(tf);
		}

	}
}
