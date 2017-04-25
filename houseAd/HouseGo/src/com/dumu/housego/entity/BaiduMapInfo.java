package com.dumu.housego.entity;

import java.io.Serializable;

import android.widget.AbsListView.SelectionBoundsAdjuster;

public class BaiduMapInfo implements Serializable {
	private double latitude;// 纬度
	private double longitude;// 经度
	private String name;// 名字
	private int imgId;// 图片
	private String description;// 描述

	public BaiduMapInfo(double latitude, double longitude, String name, int imgId, String description) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.imgId = imgId;
		this.description = description;
	}

	public BaiduMapInfo() {
		super();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BaiduMapInfo [latitude=" + latitude + ", longitude=" + longitude + ", name=" + name + ", imgId=" + imgId
				+ ", description=" + description + "]";
	}

}
