package com.dumu.housego.entity;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxArea {
	private String city;
	private int ck;
	
	
	public static List<CheckBoxArea>checks=new ArrayList<CheckBoxArea>();
	
	static{
		checks.add(new CheckBoxArea("罗湖区", 1));
		checks.add(new CheckBoxArea("福田区", 2));
		checks.add(new CheckBoxArea("南山区", 3));
		checks.add(new CheckBoxArea("盐田区", 4));
		checks.add(new CheckBoxArea("宝安区", 5));
		checks.add(new CheckBoxArea("龙岗新区", 6));
		checks.add(new CheckBoxArea("龙华新区", 7));
		checks.add(new CheckBoxArea("光明新区", 8));
		checks.add(new CheckBoxArea("坪山新区", 9));
		checks.add(new CheckBoxArea("大鹏新区", 10));
		checks.add(new CheckBoxArea("东莞", 11));
		checks.add(new CheckBoxArea("惠州", 12));
		
	}
	
	
	public CheckBoxArea(String city, int ck) {
		super();
		this.city = city;
		this.ck = ck;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCk() {
		return ck;
	}
	public void setCk(int ck) {
		this.ck = ck;
	}
	public CheckBoxArea() {
		super();
	}
	@Override
	public String toString() {
		return "CheckBoxArea [city=" + city + ", ck=" + ck + "]";
	}
	
	
}
