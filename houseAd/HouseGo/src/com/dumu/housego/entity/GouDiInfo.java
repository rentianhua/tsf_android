package com.dumu.housego.entity;

public class GouDiInfo {
	
	private String id;
    private String order_no;
    private String userid;
    private String house_id;
    private String title;
    private String jine;
    private long addtime;
    private String pay_status;
    private String trade_no;
    private String paytime;
    private String buyer_email;
	public GouDiInfo(String id, String order_no, String userid, String house_id, String title, String jine,
			long addtime, String pay_status, String trade_no, String paytime, String buyer_email) {
		super();
		this.id = id;
		this.order_no = order_no;
		this.userid = userid;
		this.house_id = house_id;
		this.title = title;
		this.jine = jine;
		this.addtime = addtime;
		this.pay_status = pay_status;
		this.trade_no = trade_no;
		this.paytime = paytime;
		this.buyer_email = buyer_email;
	}
	public GouDiInfo() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHouse_id() {
		return house_id;
	}
	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJine() {
		return jine;
	}
	public void setJine(String jine) {
		this.jine = jine;
	}
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	@Override
	public String toString() {
		return "GouDiInfo [id=" + id + ", order_no=" + order_no + ", userid=" + userid + ", house_id=" + house_id
				+ ", title=" + title + ", jine=" + jine + ", addtime=" + addtime + ", pay_status=" + pay_status
				+ ", trade_no=" + trade_no + ", paytime=" + paytime + ", buyer_email=" + buyer_email + "]";
	}
  
    
    
}
