package com.dumu.housego.entity;

public class FangJiaXiangQing {
    private String  id;
    private String  month;
    private String  avg_price;
    private String  avg_price_o;
    private String  avg_percent;
    private String  avg_percent_o;
    private String  house_percent;
    private String  house_percent_o;
    private String  comp_count;
    private String  comp_count_o;
    private String  view_count;
    private String  view_count_o;
	public FangJiaXiangQing(String id, String month, String avg_price, String avg_price_o, String avg_percent,
			String avg_percent_o, String house_percent, String house_percent_o, String comp_count, String comp_count_o,
			String view_count, String view_count_o) {
		super();
		this.id = id;
		this.month = month;
		this.avg_price = avg_price;
		this.avg_price_o = avg_price_o;
		this.avg_percent = avg_percent;
		this.avg_percent_o = avg_percent_o;
		this.house_percent = house_percent;
		this.house_percent_o = house_percent_o;
		this.comp_count = comp_count;
		this.comp_count_o = comp_count_o;
		this.view_count = view_count;
		this.view_count_o = view_count_o;
	}
	public FangJiaXiangQing() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(String avg_price) {
		this.avg_price = avg_price;
	}
	public String getAvg_price_o() {
		return avg_price_o;
	}
	public void setAvg_price_o(String avg_price_o) {
		this.avg_price_o = avg_price_o;
	}
	public String getAvg_percent() {
		return avg_percent;
	}
	public void setAvg_percent(String avg_percent) {
		this.avg_percent = avg_percent;
	}
	public String getAvg_percent_o() {
		return avg_percent_o;
	}
	public void setAvg_percent_o(String avg_percent_o) {
		this.avg_percent_o = avg_percent_o;
	}
	public String getHouse_percent() {
		return house_percent;
	}
	public void setHouse_percent(String house_percent) {
		this.house_percent = house_percent;
	}
	public String getHouse_percent_o() {
		return house_percent_o;
	}
	public void setHouse_percent_o(String house_percent_o) {
		this.house_percent_o = house_percent_o;
	}
	public String getComp_count() {
		return comp_count;
	}
	public void setComp_count(String comp_count) {
		this.comp_count = comp_count;
	}
	public String getComp_count_o() {
		return comp_count_o;
	}
	public void setComp_count_o(String comp_count_o) {
		this.comp_count_o = comp_count_o;
	}
	public String getView_count() {
		return view_count;
	}
	public void setView_count(String view_count) {
		this.view_count = view_count;
	}
	public String getView_count_o() {
		return view_count_o;
	}
	public void setView_count_o(String view_count_o) {
		this.view_count_o = view_count_o;
	}
	@Override
	public String toString() {
		return "IFangJiaXiangQingView [id=" + id + ", month=" + month + ", avg_price=" + avg_price + ", avg_price_o="
				+ avg_price_o + ", avg_percent=" + avg_percent + ", avg_percent_o=" + avg_percent_o + ", house_percent="
				+ house_percent + ", house_percent_o=" + house_percent_o + ", comp_count=" + comp_count
				+ ", comp_count_o=" + comp_count_o + ", view_count=" + view_count + ", view_count_o=" + view_count_o
				+ "]";
	}
    
    
    
    
}
