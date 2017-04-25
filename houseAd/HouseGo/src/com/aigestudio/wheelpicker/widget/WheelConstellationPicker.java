package com.aigestudio.wheelpicker.widget;

import java.util.Arrays;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.dumu.housego.R;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 基于WheelPicker的星座选择控件 ConstellationPicker base on WheelPicker
 *
 * @author AigeStudio 2015-12-03
 * @author AigeStudio 2015-12-08 Init初始化
 * @version 1.0.0 beta
 */
public class WheelConstellationPicker extends WheelPicker {
	public WheelConstellationPicker(Context context) {
		this(context, null);
	}

	public WheelConstellationPicker(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public WheelConstellationPicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		super.setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayConstellation)));
	}

	@Override
	public void setData(List<String> data) {
		throw new RuntimeException("Set data will not allow here!");
	}
}