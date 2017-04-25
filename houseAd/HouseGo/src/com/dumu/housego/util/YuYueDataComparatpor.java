package com.dumu.housego.util;

import java.util.Comparator;

import com.dumu.housego.entity.YuYueData;

public class YuYueDataComparatpor implements Comparator<YuYueData> {
	private String type;

	/**
	 * 1降序 其余升序
	 */
	public YuYueDataComparatpor(String type) {
		super();
		this.type = type;
	}

	@Override
	public int compare(YuYueData lhs, YuYueData rhs) {
		// TODO 自动生成的方法存根
		if (type.equals("1")) {
			return Integer.parseInt(rhs.getInputtime()) - Integer.parseInt(lhs.getInputtime());
		} else {
			return Integer.parseInt(lhs.getInputtime()) - Integer.parseInt(rhs.getInputtime());
		}
	}

}
