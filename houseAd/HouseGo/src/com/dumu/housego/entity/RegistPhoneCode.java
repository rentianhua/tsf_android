package com.dumu.housego.entity;

public class RegistPhoneCode {
	private int success;
	private String info;

	public RegistPhoneCode() {
		super();
	}

	public RegistPhoneCode(int success, String info) {
		super();
		this.success = success;
		this.info = info;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "RigestPhoneCode [success=" + success + ", info=" + info + "]";
	}

}
