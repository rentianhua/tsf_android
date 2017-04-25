package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class shenZhenLocation implements Serializable {
	private String id;
	private double latitude;
	private double longitude;
	private String name;

	public static List<shenZhenLocation> locations = new ArrayList<shenZhenLocation>();
	static {
		locations.add(new shenZhenLocation("2",114.137543, 22.555234, "罗湖区"));
		locations.add(new shenZhenLocation("4",114.061087, 22.528578, "福田区"));
		locations.add(new shenZhenLocation("5",113.93695, 22.539017, "南山区"));
		locations.add(new shenZhenLocation("6",114.243911, 22.564199, "盐田区"));
		locations.add(new shenZhenLocation("7",113.889506, 22.559626, "宝安区"));
		locations.add(new shenZhenLocation("8",114.21332952038, 22.663330039461, "龙岗新区"));
		locations.add(new shenZhenLocation("9",114.039945, 22.661746, "龙华新区"));
		locations.add(new shenZhenLocation("10",113.923662, 22.779082, "光明新区"));
		locations.add(new shenZhenLocation("11",114.349555, 22.687449, "坪山新区"));
		locations.add(new shenZhenLocation("12",114.493599, 22.578712, "大鹏新区"));
		locations.add(new shenZhenLocation("13",113.758231, 23.026997, "东莞"));
		locations.add(new shenZhenLocation("14",114.423348, 23.116409, "惠州"));
	}
	public shenZhenLocation(String id, double latitude, double longitude, String name) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
	public shenZhenLocation() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public static List<shenZhenLocation> getLocations() {
		return locations;
	}
	public static void setLocations(List<shenZhenLocation> locations) {
		shenZhenLocation.locations = locations;
	}
	@Override
	public String toString() {
		return "shenZhenLocation [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ "]";
	}
	
	


}
